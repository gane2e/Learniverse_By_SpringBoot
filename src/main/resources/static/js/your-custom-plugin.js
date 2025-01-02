// var video = videojs('#videoPlayer');
// console.log(video)
// video.play(); //재생
// video.pause(); //정지
// video.playbackRate = 5.0; //재생속도
// video.currentTime(2); //재생초(2초로이동)
// player.duration(); == 비디오의 길이
// player.currentTime() == 비디오 재생시점
/* videoJS 플러그인 https://videojs.com/plugins/ */

document.addEventListener('DOMContentLoaded', function () {

    let totalDuration = 0; // 모든 영상의 총합길이
    let videoCount = 0; // 비디오의 개수를 셀 변수
    const studentCourseId = document.getElementById('studentCourseId').value;
    let last_watched = document.getElementById('last_watched').value;


    document.querySelectorAll('.lecture_btn').forEach(function(button) {

        const videoUrl = button.getAttribute('data-video-url'); // 각 버튼에서 비디오 URL 가져오기
        const videoIndex = button.getAttribute('data-index');
        let videoElement = document.createElement('video');   // 비디오 요소 생성
        videoElement.src = videoUrl; // 비디오 URL을 설정

        // 비디오가 로드되었을 때 duration 값을 얻기 위해 이벤트 리스너 추가
        videoElement.onloadedmetadata = function() {

            const videoDuration = videoElement.duration;  //각 영상의 영상길이
            console.log("각 영상의 비디오 길이 : " + videoDuration);
            totalDuration += videoDuration; // 교육과정의 모든 영상길이 총합
            videoCount++;

            //각 버튼에 영상길이값 할당
            const button = document.querySelector(`[data-index="${videoIndex}"]`);
            if (button) {
                button.setAttribute('data-duration', videoDuration);
            }
            // 모든 비디오 길이의 총합 계산이 끝난 후 콘솔에 출력(테스트완료)
            if (videoCount === document.querySelectorAll('.lecture_btn').length) {
                console.log("모든 비디오의 총 길이 (초):", totalDuration);
            }
            // 페이지 로드시 첫번째 영상을 클릭처리(테스트완료)
            if (videoIndex == 1) {
                const targetButton = document.querySelector(`button[data-index="1"]`);
                if (targetButton) {
                    targetButton.click();
                }
            }
        };

        // 영상 목차 클릭 시 영상 변경 이벤트
        button.addEventListener('click', function() {

            var videoUrl = this.getAttribute('data-video-url');
            var localUrl = 'http://localhost:8080';
            var playUrl = localUrl + videoUrl
            const index = button.getAttribute('data-index');  // 각 비디오의 고유 인덱스
            checkVideoIndex(index); //이전영상 수강여부체크
            let lastWatchedTime = 0;  //  서버에서 해당 비디오의 시청한 시간을 가져옵니다.

            player.src({ src: playUrl, type: "video/mp4" });

            player.on('timeupdate', function () {
                var currentTime = player.currentTime();  // 플레이중인 시간 (초)
                var duration = player.duration();  // 플레이중인 영상 길이 (초)

                let progress = (currentTime / duration) * 100;

                // 총 시청 시간 업데이트 (현재 재생 시간 + 이전 시청 시간)
                last_watched = lastWatchedTime + currentTime;

                //재생중인 영상의 프로그레스바 width값, 영상 진도율 % 업데이트하기
                const percent = document.getElementById('percent-' + index);
                percent.innerText = progress.toFixed(0) + '%';

                document.querySelectorAll('.line2').forEach(function(bar) {
                    bar.style.width = '0%';  // 모든 progress bar 초기화
                });
                const progressBar = document.getElementById('progress-' + index);
                progressBar.style.width = progress.toFixed(1) + '%';
                if(progress === 100) {
                    const status = document.getElementById('status-' + index);
                    status.innerText = '학습완료';
                    status.style.color = '#0076c0';
                    let prevVideo = Number(index) + 1;
                    const targetButton = document.querySelector(`button[data-index="${prevVideo}"]`);
                    console.log("prevVideo ==> " + prevVideo)
                    if (targetButton) {
                        targetButton.click();
                    } else
                        alert("모든 학습이 완료되었습니다.")

                }
            });

            // 비디오가 정지되었을 때 서버로 마지막 시청시간 전송
            player.on('pause', function() {
                console.log("현재까지 총 시청한 시간 (초): " + last_watched.toFixed(0));
                console.log("Video.js 플레이어가 정지되었습니다.");
                fetch('/student/lastWatchedSave', {
                    method: 'POST',  // POST 요청
                    headers: {
                        'Content-Type': 'application/json',  // JSON 형식으로 데이터 전송
                    },
                    body: JSON.stringify({
                        last_watched: last_watched, //Long
                        studentCourseId: studentCourseId //Long
                    })
                })
                    .then(response => response.text())
                    .then(data => {
                        console.log('Server data : ' + data);
                    })
                    .catch(error => {
                        console.error("AJAX 오류", error)
                    })
            });
        });
    });

    //플레이어 객체
    var player = videojs('videoPlayer', {
        autoplay: 'muted', // 자동재생 여부
        controls: true,
        poster: 'https://blog.kakaocdn.net/dn/buBbXt/btspOAijvT5/kK8QRvBlK8LyxId3G69ZA1/img.jpg',
        loop: false, //반복재생
        /* fluid: true, //크기제어?
         aspectRatio: '4:3', //비율*/
        playbackRates: [0.25, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4], //재생속도
        plugins: {
            hotkeys: {
                enableModifiersForNumber: false, /* 특정행위시 초이동하는것 방지 */
                seekStep: 1 /* 방향키로 1초씩 증가/감소 */
            }
        }
    });
    
    
    // 비디오가 로드된 후 비디오의 총 길이 구하기
    player.on('loadeddata', function() {
        player.currentTime(last_watched);
        console.log('Total video duration:', player.duration(), 'seconds');
        console.log('Total video currentTime:', player.currentTime(), 'seconds');
    });



    function checkVideoIndex(index){
        console.log("클릭한 영상 index ===> " + index);

        // 클릭한 영상의 이전영상 index
        let indexCheck = index - 1

        const button = document.querySelector(`[data-index="${indexCheck}"]`);
        // button.get
    }
})



/*  VideoJS에서 플레이 시간까지만 이동 되도록 */
/*   출처 : https://gogorchg.tistory.com/entry/JavaScript-VideoJS%EC%97%90%EC%84%9C-%ED%94%8C%EB%A0%88%EC%9D%B4-%EC%8B%9C%EA%B0%84%EA%B9%8C%EC%A7%80%EB%A7%8C-%EC%9D%B4%EB%8F%99-%EB%90%98%EB%8F%84%EB%A1%9D */
/*
var percentAllowForward=0;
var videoPlayer;
var disableForwardScrubbing = function(player){
    player.on("timeupdate",function(){
        var percentPlayed= player.currentTime()/player.duration()*100;
        if (percentPlayed>percentAllowForward){
            percentAllowForward=percentPlayed;
        }
    });
    return{
        setSource: function setSource(srcObj, next){
            next(null, srcObj);
        },
        setCurrentTime: function setCurrentTime(ct) {
            var percentPlayed= ct/player.duration()*100;
            if(ct<player.currentTime()||percentPlayed<=percentAllowForward){
                return ct;
            }else if(percentPlayed>percentAllowForward){
                return player.duration()*percentAllowForward/100;
            }
            return player.currentTime();
        }
    }
};
*/

