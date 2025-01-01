// your-custom-plugin.js

// var video = videojs('#videoPlayer');
//
// console.log(video)
//
// video.play(); //재생
//
// video.pause(); //정지
//
// video.playbackRate = 5.0; //재생속도
//
// video.currentTime(2); //재생초(2초로이동)

/* videoJS 플러그인 https://videojs.com/plugins/ */

document.addEventListener('DOMContentLoaded', function () {

    let totalDuration = 0; // 모든 영상의 총합길이
    let watchedDuration = 0;  // 사용자가 시청한 총 길이 (초)
    let videoCount = 0; // 비디오의 개수를 셀 변수

    document.querySelectorAll('.lecture_btn').forEach(function(button) {


        // 각 버튼에서 비디오 URL 가져오기
        const videoUrl = button.getAttribute('data-video-url');

        // 비디오 요소 생성
        let videoElement = document.createElement('video');
        videoElement.src = videoUrl; // 비디오 URL을 설정

        // 비디오가 로드되었을 때 duration 값을 얻기 위해 이벤트 리스너 추가
        videoElement.onloadedmetadata = function() {
            
            //각 영상의 영상길이
            const videoDuration = videoElement.duration;

            // 교육과정의 모든 영상길이 총합
            totalDuration += videoDuration;
            videoCount++;

            // 모든 비디오 길이의 총합 계산이 끝난 후 콘솔에 출력
            if (videoCount === document.querySelectorAll('.lecture_btn').length) {
                console.log("모든 비디오의 총 길이 (초):", totalDuration);
                console.log("모든 비디오의 총 길이 (분):", (totalDuration / 60).toFixed(2));
            }
        };

        // 영상 목차 클릭 시 영상 변경 이벤트
        button.addEventListener('click', function() {

            var videoUrl = this.getAttribute('data-video-url');
            var localUrl = 'http://localhost:8080';
            var playUrl = localUrl + videoUrl
            const index = button.getAttribute('data-index');  // 각 비디오의 고유 인덱스
            console.log("index => " + index)

            player.src({ src: playUrl, type: "video/mp4" });

            // 비디오가 재생되는 동안 현재 시간과 총 길이를 실시간으로 확인하는 방법
            player.on('timeupdate', function () {
                var currentTime = player.currentTime();  // 현재 시간 (초)
                var duration = player.duration();  // 총 비디오 길이 (초)
                watchedDuration = currentTime;  // 사용자가 시청한 시간 (초)
                console.log("watchedDuration => " + watchedDuration.toFixed(0))
                // console.log('Current time: ' + currentTime + ' / Total duration: ' + duration);

                let progress = (currentTime / duration) * 100;
                // console.log('전체 비디오 진도율: ' + progress.toFixed(2) + '%');

                document.querySelectorAll('.line2').forEach(function(bar) {
                    bar.style.width = '0%';  // 모든 progress bar 초기화
                });

                const progressBar = document.getElementById('progress-' + index);
                if (progressBar) {
                    progressBar.style.width = progress + '%';
                } else
                    console.log("progressBar가 없습니다.")
            });
        });
    });

    var player = videojs('videoPlayer', {
        autoplay: 'muted', // 자동재생 여부
        controls: true,
        poster: 'https://blog.kakaocdn.net/dn/buBbXt/btspOAijvT5/kK8QRvBlK8LyxId3G69ZA1/img.jpg',
        loop: false, //반복재생
        /* fluid: true, //크기제어?
         aspectRatio: '4:3', //비율*/
        playbackRates: [0.25, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4], //재생속도
        /* userActions: { //사용자 작업 및 단축키
             hotkeys: true //space바로 재생컨트롤, M클릭시 음소거 컨트롤, F클릭시 전체화면컨트롤
         }*/
        plugins: {
            hotkeys: {
                enableModifiersForNumber: false, /* 특정행위시 초이동하는것 방지 */
                seekStep: 1 /* 방향키로 1초씩 증가/감소 */
            }
        }
    });

    // 비디오가 로드된 후 비디오의 총 길이 구하기
    player.on('loadeddata', function() {
        console.log('Total video duration:', player.duration(), 'seconds');
        console.log('Total video currentTime:', player.currentTime(), 'seconds');
        let total =  player.duration();
    });


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

