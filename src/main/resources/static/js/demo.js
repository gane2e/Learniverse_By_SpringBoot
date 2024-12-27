let player = null;
document.addEventListener('DOMContentLoaded', () => {
  player = new ncplayer('player', {
    playlist: [
      {
        file: 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/av1/360/Big_Buck_Bunny_360_10s_1MB.mp4',
        poster:
          'https://blog.kakaocdn.net/dn/buBbXt/btspOAijvT5/kK8QRvBlK8LyxId3G69ZA1/img.jpg',
        description: {
          title: '네이버클라우드 소개 영상',
          created_at: '2023.07.13',
          profile_name: '네이버클라우드',
          profile_image:
            'https://nnbkegvqsbcu5297614.cdn.ntruss.com/profile/202208/d127c8db642716d84b3201f1d152e52a.png',
        },
      },
    ],
    autostart: true,
    muted: true,
    keyboardShortcut: true,
    controls: true,
    lang: 'ko',
    ui: 'all',
    controlBtn: {
      play: true,
      fullscreen: true,
      volume: true,
      times: true,
      pictureInPicture: true,
      setting: true,
      subtitle: false,
    },
    progressBarColor: '#ff0000',
    controlActiveTime: 3000,
    startMutedInfoNotVisible: false,
    aspectRatio: '16/9',
    objectFit: 'contain',
    playRateSetting: [0.5, 0.75, 1, 1.5, 2],
    autoPause: false,
    repeat: false,
    lowLatencyMode: true,
  });

  // player는 받아지는데
  console.log(player);

  player.play();
  player.currentTime(8)

  player.on('play',(e)=>{
    console.log('Play 되었습니다.',e);
  });
});

// document.addEventListener('DOMContentLoaded', () => {
// let player = new ncplayer('myElement', {
//       playlist: [
//       {
//         file: 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/av1/360/Big_Buck_Bunny_360_10s_1MB.mp4',
//         poster:
//           'https://blog.kakaocdn.net/dn/buBbXt/btspOAijvT5/kK8QRvBlK8LyxId3G69ZA1/img.jpg',
//         description: {
//           title: '네이버클라우드 소개 영상',
//           created_at: '2023.07.13',
//           profile_name: '네이버클라우드',
//           profile_image:
//             'https://nnbkegvqsbcu5297614.cdn.ntruss.com/profile/202208/d127c8db642716d84b3201f1d152e52a.png',
//         },
//       },
//     ],
//     autostart: true,
//     muted: true,
//     keyboardShortcut: true,
//     controls: true,
//     lang: 'ko',
//     ui: 'all',
//     controlBtn: {
//       play: true,
//       fullscreen: true,
//       volume: true,
//       times: true,
//       pictureInPicture: true,
//       setting: true,
//       subtitle: false,
//     },
//     progressBarColor: '#ff0000',
//     controlActiveTime: 3000,
//     startMutedInfoNotVisible: false,
//     aspectRatio: '16/9',
//     objectFit: 'contain',
//     playRateSetting: [0.5, 0.75, 1, 1.5, 2],
//     autoPause: false,
//     repeat: false,
//     lowLatencyMode: true,
//   });
//
// player.on('play',(e)=>{
//   console.log('Play 되었습니다.',e);
//   })
// })
