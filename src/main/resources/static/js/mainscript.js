// script.js

// 각 섹션에 대한 reference
const sections = document.querySelectorAll('.section');

// 현재 보고 있는 섹션의 인덱스
let currentSection = 0;

// 스크롤 방지 및 제어를 위한 플래그
let isScrolling = false;

// 섹션을 스크롤하는 함수
function scrollToSection(index) {
    // 인덱스가 섹션 범위를 벗어나지 않도록 처리
    if (index < 0 || index >= sections.length) return;

    // 각 섹션으로 스크롤 이동
    sections[index].scrollIntoView({
        behavior: 'smooth',
        block: 'start'
    });

    // 현재 섹션을 업데이트
    currentSection = index;
}

// 스크롤 이벤트 리스너
window.addEventListener('wheel', (e) => {
    // 이미 스크롤 중이라면 이벤트를 무시
    if (isScrolling) return;

    // 스크롤 방지 플래그 설정
    isScrolling = true;

    if (e.deltaY > 0) {
        // 아래로 스크롤
        if (currentSection < sections.length - 1) {
            scrollToSection(currentSection + 1);
        }
    } else {
        // 위로 스크롤
        if (currentSection > 0) {
            scrollToSection(currentSection - 1);
        }
    }

    // 스크롤이 완료된 후 일정 시간 뒤에 플래그 해제 (디바운싱)
    setTimeout(() => {
        isScrolling = false;
    }, 800); // 800ms 후에 다시 스크롤 가능
});

// 키보드 이벤트 리스너 (위/아래 화살표 키로도 스크롤 가능)
window.addEventListener('keydown', (e) => {
    if (e.key === 'ArrowDown') {
        // 아래 화살표 키 눌렀을 때
        if (currentSection < sections.length - 1) {
            scrollToSection(currentSection + 1);
        }
    } else if (e.key === 'ArrowUp') {
        // 위 화살표 키 눌렀을 때
        if (currentSection > 0) {
            scrollToSection(currentSection - 1);
        }
    }
});
