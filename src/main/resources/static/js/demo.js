let player = null
document.addEventListener('DOMContentLoaded', () => {
    player = new ncplayer('player',{
        playlist:[
            {
                file:'https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8',
                poster:'https://blog.kakaocdn.net/dn/buBbXt/btspOAijvT5/kK8QRvBlK8LyxId3G69ZA1/img.jpg',
                description: {
                    title: "네이버클라우드 소개 영상",
                    created_at: "2023.07.13",
                    profile_name: "네이버클라우드",
                    profile_image: "https://nnbkegvqsbcu5297614.cdn.ntruss.com/profile/202208/d127c8db642716d84b3201f1d152e52a.png"
                },
            }
        ],
        autostart:true,
        muted:true,
        keyboardShortcut:true,
        controls:true,
        lang:'ko',
        ui:"all",
        controlBtn:{
            play:true,
            fullscreen:true,
            volume:true,
            times:true,
            pictureInPicture:true,
            setting:true,
            subtitle:false,
        },
        progressBarColor:"#ff0000",
        controlActiveTime:3000,
        startMutedInfoNotVisible:false,
        aspectRatio:"16/9",
        objectFit:"contain",
        playRateSetting:[0.5,0.75,1,1.5,2],
        autoPause:false,
        repeat:false,
        lowLatencyMode:true
    })

    console.log(player);

    player.on()

    player.on('play',(e)=>{
        console.log('Play 되었습니다.',e);
    })

    player.on('timeupdate', (data) => {
        console.log('Received data:', data); // 데이터 객체 구조 확인
    });

    player.on('timeupdate',(data)=>{
        console.log('영상 전체 길이 (duration) : ',data.duration);
        console.log('현재 재생 위치 (currentTime) : ',data.currentTime);
        console.log('현재 재생 퍼센트 (percent) : ',data.percent);
        console.log('누적 재생 시간 (viewingTime) : ',data.viewingTime);
        console.log('재생소스 타입 (sourceType) : ',data.sourceType); // 재생소스 타입(vod, live)
    })

    player.on('timeupdate', function(data) {
        const currentTime = data.currentTime; // 현재 시청 시간
        const duration = data.duration;       // 전체 비디오 길이
        const progress = (currentTime / duration) * 100; // 진도율 계산

        console.log(`진도율: ${progress.toFixed(2)}%`);
    });





});