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
    var player = videojs('videoPlayer', {
        autoplay: 'muted', // 자동재생 여부
        controls: true,
        poster: 'https://blog.kakaocdn.net/dn/buBbXt/btspOAijvT5/kK8QRvBlK8LyxId3G69ZA1/img.jpg',
        loop: true,
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

    // 비디오가 재생되는 동안 현재 시간과 총 길이를 실시간으로 확인하는 방법
    player.on('timeupdate', function () {
        var currentTime = player.currentTime();  // 현재 시간 (초)
        var duration = player.duration();  // 총 비디오 길이 (초)
        console.log('Current time: ' + currentTime + ' / Total duration: ' + duration);
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

