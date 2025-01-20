<img src="https://github.com/user-attachments/assets/01ee4a1b-577b-4454-8a58-46778779b66a" width="200px"> <br/>
"학습과 우주적 탐구가 만나는 무한한 가능성의 공간"

## :clipboard: 목차
1. [🔮 프로젝트 소개](#프로젝트-소개)
2. [🙋‍♀️ 프로젝트 참여자](#-좀-더-구체적으로-가르쳐주세요)
3. [🛠 개발환경](#개발환경)
4. [🛠 기술소개](#기술소개)




## 🙋‍♀️ 프로젝트 참여자
- 참여자 : 김가은(프론트엔드&백엔드 개발)
- 이메일 : b1a409896@naver.com
- 휴대폰 : 010-7933-6261
- 기  간 : 2024-12-26 ~ 2025-01-15 (약 3주일)

## 🔮프로젝트 소개
- ### 온라인 학습관리 시스템이란?
   관공서, 기업에서 필요로하는 온라인 영상학습 시스템을 구축하여,<br/>
  사용자의 교육영상 학습 진도율 추적, 문제은행식 온라인 시험 응시, 수료증 발급이 가능한 홈페이지입니다.
- ### 주제 선정 배경
     최근 몇 년 간, **온라인 교육과 디지털 학습 환경이 급격히 확산**되었습니다. <br/>
      특히 코로나19 팬데믹 이후, 
    많은 교육 기관들이 오프라인 수업에서 온라인 수업으로 빠르게 전환하였고, <br/>
    이는 학습 관리 시스템(LMS)의 중요성을 더욱 부각시켰습니다.
- ### 기획 의도
  LMS는 학습자의 진도를 추적하며, 수업 자료를 관리할 수 있는 중요한 도구로 자리 잡았습니다. <br/>
    **다양한 온라인 학습이 필요한 기업과 관공서가 간편하게 활용**할 수 있는 
    LMS 템플릿을 개발하였습니다.


## 개발환경
<img src="https://github.com/user-attachments/assets/38f6b2e6-d2c4-4e96-b3fe-4a84eabcd163" width="800px">

## 기술소개
### Spring Security
- Spring Security를 사용해 웹 애플리케이션의 보안을 강화하고, 세부적인 경로별 접근 권한을 관리.
- OAuth2 기반 소셜 로그인과의 통합을 통해 보안 강화를 동시에 구현.
  
### Spring Boot JPA 
- JPA Repository에서 제공하는 CRUD 메서드를 활용하여 SQL 작업을 간편하게 처리.
- JPQL을 이용하여 동적 쿼리를 작성, 재사용성과 유지보수성을 향상시켰습니다.

### API
- **[VideoJs]** VideoJS API를 활용하여 사용자의 마지막 시청초를 추적하고, 영상의 길이를 계산하여 총 진도율을 계산하는 기능을 구현하였습니다.
- **[VideoJs]** API에서 제공하는 메서드를 활용해 영상별 총길이를 구하여 해당 교육과정의 영상길이 합산 초 및 사용자가 총 시청한 시점을 추적하는 기능을 구현하였습니다.
- **[Fetch]** 영상 종료 시점이나 영상 일시정지 시, 마지막 시청 초를 FETCH API를 통해 서버로 전송하여 비동기 통신을 구현하고 진도율을 실시간으로 저장하도록 하며, 성공/실패에 따른 예외처리를 다양하게 할수있도록 하였습니다.
- **[Slick]** Slick 슬라이드 API를 사용하여 자동 슬라이드 기능과 화면에 표시되는 슬라이드 수를 직접 조정할 수 있어, 개발 시간을 단축하고 효율성을 높였습니다.
- **[Kakao, Google, Naver]** 카카오, 네이버, 구글 소셜로그인을 통해 사용자가 손쉽게 로그인할 수 있도록 하며,  OAuth2를 기반으로 한 보안 로그인을 적용하여 사용자 인증을 처리했습니다.


## :triangular_ruler: UML(다이어그램)
<details>
   <summary>ER다이어그램</summary>
   <img src="https://github.com/user-attachments/assets/71389521-253a-49c6-ade3-9f3b050b626e" width="800px"></br>
</details>
<details>
   <summary>액티비티 다이어그램</summary>
   <img src="https://github.com/user-attachments/assets/34801402-bae3-48a0-bb1e-781a642cd6c2" width="800px"></br>
</details>

## 개발일정 및 작업 관리
- 전체 개발 기간 : 2024-12-24 ~ 2025-01-15
- UI 구현 : 2024-12-24 ~ 2025-01-10
- 기능 구현 : 2024-12-24 ~ 2022-01-14
- 작업 관리 : 노션(https://www.notion.so/13ec99b9b63e80c6bf3fdf2fee349c64?v=15ec99b9b63e80a6a421000c70dbd718)

## 개발후_기능시연
### 메인 페이지
<img src="https://github.com/user-attachments/assets/d3aa760c-48f6-4945-a9d6-e3beb7d11d10" width="800px"></br>
### 회원가입
<img src="https://github.com/user-attachments/assets/9625332e-4a5d-49c5-9ed0-10d0f6ff3d8d" width="800px"></br>
### 로그인
<img src="https://github.com/user-attachments/assets/003fcb9f-c72e-4bb8-8fdb-03c8bd63924e" width="800px"></br>





<details><summary>## 개발후_화면소개
</summary>

### (사용자) 메인 페이지
![image](https://github.com/user-attachments/assets/6e2d2990-bc1a-409a-bea1-26de17a20a63)
### (사용자) 강의 리스트
![강의 리스트](https://github.com/user-attachments/assets/ade63de0-b051-4b89-a5e2-27cd667d49f7)
### (사용자) 강의 상세페이지
![강의 상세페이지](https://github.com/user-attachments/assets/07139eff-b4b2-4894-a7b6-cc3ce1ef1737)
### (사용자) 수강신청 완료 페이지
![강의 신청완료화면](https://github.com/user-attachments/assets/8f605a16-b767-43b6-a6cb-783a928665f9)
### (사용자) 교육 대시보드
![대시보드](https://github.com/user-attachments/assets/cbd016ff-268b-48e5-9153-92ac1dbeaaea)
### (사용자) 학습하기 페이지
![학습하기](https://github.com/user-attachments/assets/3b5a8376-8753-4b73-8c63-85661e318efb)
### (사용자) 시험보기 팝업
![시험보기 팝업](https://github.com/user-attachments/assets/428ef033-1ad9-4165-8163-8abe8cb8baa4)
### (사용자) 시험보기 페이지
![시험보기 페이지](https://github.com/user-attachments/assets/a45b535e-6e0f-4bd1-bcfb-9b18c248cf18)
### (사용자) 시험 결과화면
- 합격 결과화면
![시험 합격 화면](https://github.com/user-attachments/assets/4403193c-0822-45a4-8327-57ae9246e829)
- 불합격 결과화면
- ![시험 불합격 화면](https://github.com/user-attachments/assets/9eeafff9-7ffd-453d-8ed7-65d3e3be1b42)
- 3회 불합격 결과화면
![시험 3회차 불합격 화면](https://github.com/user-attachments/assets/2e5ae5f0-335d-42eb-b8b5-1af9fd213dc2)
### (사용자) 수료증 인쇄화면
![수료증 인쇄화면](https://github.com/user-attachments/assets/aed3cd9d-5a6a-4ea3-b3ab-eaecebd09067)

### 회원가입
![회원가입 페이지](https://github.com/user-attachments/assets/93e3126d-bc10-4bbd-9625-a4622c0d9286)

### 로그인
![로그인](https://github.com/user-attachments/assets/9acceb51-5d3e-43f2-a7a8-b6c64eb50160)

### OAuth2 소셜로그인 (Google/Naver/Kakao)
<img src="https://github.com/user-attachments/assets/76f71055-0fc0-4d94-a9a7-f6b68b97dbef" width="500px"> <br />
<img src="https://github.com/user-attachments/assets/840fdb20-f432-4ecd-8eda-555e8bbb201f" width="500px"> <br />
<img src="https://github.com/user-attachments/assets/562223b3-c619-459b-bf2a-76163e649b4c" width="500px"> <br />

### 아이디/비밀번호 찾기

#### 아이디 찾기
![아이디찾기-1](https://github.com/user-attachments/assets/183a1d18-1878-4c07-86b4-204674ac58d0)
<div align="center">
   <img src="https://github.com/user-attachments/assets/f61dc165-afb4-427d-b082-b19164c8767b" width="790px"  align="center">
</div>

![아이디찾기 - 2](https://github.com/user-attachments/assets/58894654-2b0e-43f9-8cd1-fea1208c1f1f)
![아이디찾기-3](https://github.com/user-attachments/assets/d6c23e39-a410-4dfd-8d4d-b4e6b8485473)

#### 비밀번호 찾기
![비밀번호 찾기 -1](https://github.com/user-attachments/assets/94f32470-4c69-4357-b4e6-837503690f8a)
![비밀번호 찾기 -2](https://github.com/user-attachments/assets/1a63f50f-9c62-4995-8689-0b465d410583)
![비밀번호 찾기 -3](https://github.com/user-attachments/assets/c139d051-e293-4280-a038-6724297dde7e)
![비밀번호 찾기 -4](https://github.com/user-attachments/assets/9b212abc-88ed-4733-b1ac-3caa685be780)
</details>

## :memo: 프로젝트 후 느낀점
이번 프로젝트를 진행하면서 혼자 개발하는 과정에서 부족한 점도 많았고, 많은 것을 배울 수 있었습니다. <br />
특히 기간이 부족하여 충분한 예외 처리를 하지 못한 점이 아쉬움으로 남습니다. 이 부분은 향후 더 신경 써야 할 부분이라고 느꼈고, 팀원과의 협업을 통해 해결할 수 있는 문제들이 많다는 것도 실감했습니다. <br /><br />

카테고리 선택과 실시간 진도율 저장을 구현하기 위해 Ajax 비동기 처리 방식을 사용했는데, 이 과정에서 Fetch API에 대해 알게 되었습니다. Fetch 방식은 스트림을 사용하여 응답 본문을 처리할 수 있어 편리하게 느껴졌습니다. <br />
이를 통해 비동기 통신 방식에 대해 더 깊이 이해할 수 있었습니다.<br /><br />

백엔드 로직을 개발하는 과정에서 큰 재미를 느꼈습니다. 그러나 컨트롤러에서 로직을 지나치게 길게 작성하거나 중복된 코드를 반복하게 되는 순간들이 있었습니다. <br />
다행히도 인텔리제이에서 중복된 코드를 메서드로 분리하거나, 서비스에서 처리하라는 알림을 띄워주어 불필요한 코드작성을 피할 수 있게 되었습니다.<br /><br />

이번 프로젝트는 저에게 큰 경험이 되었으며, 앞으로도 더 많은 공부가 필요할것 같다고 느꼈습니다.







1. [❓ EASYME.md가 뭐예요?  ](#-easymemd가-뭐예요)
2. [🙋‍♀️ 좀 더 구체적으로 가르쳐주세요!](#-좀-더-구체적으로-가르쳐주세요)
3. [🛠 기능 엿보기](#-기능-엿보기)
    - [Header](#header)   
    - [Text Style1](#text-style1)   
    - [Text Stlye2](#text-style2)   
    - [List](#list)      
    - [Link](#link)   
    - [Code Block](#code-block)   
    - [Table](#table)   
   
## Header
- # H1 Header   
- ## H2 Header   
- ### H3 Header   
- #### H4 Header   
- ##### H5 Header   
- ###### H6 Header   

<br>   

## Text Style1
- **진하게** (`Ctrl(Command) + B`)   
- *기울이기* (`Ctrl(Command) + I`)   
- <s>취소선</s> (`Ctrl(Command) + D`)   
- <u>밑줄</u> (`Ctrl(Command) + U`)   

<br>   
   
## Text Style2

>인용문   
   


- EASYME.md를 드래그하고 상단에 `Aa` 아이콘을 누르면? 👉 Easyme.md   
- EASYME.md를 드래그하고 상단에 `A` 아이콘을 누르면? 👉 EASYME.MD   
- EASYME.md를 드래그하고 상단에 `a` 아이콘을 누르면? 👉 easyme.md   
   
<br>   
   
## List   
### Table of contents
1. [title1](#write-title-here!)   
2. [title2](#only-lowercase)   
3. [title3](#use"-"instead-of-spacing-words)   
4. [title4](#example)   
    - [❓ EASYME.md가 뭐예요?](#-easymemd가-뭐예요)   
    - [🛠 기능 엿보기](#-기능-엿보기)
   
### Unordered list   
- unordered list1   
- unordered list2   
- unordered list3   
- unordered list4   
   
### Ordered list   
1. ordered list1   
2. ordered list2   
3. ordered list3   
4. ordered list4   
   
<br>   
   
## Link   
### General link
- [🚗 Visit EASYME.md's Repo](https://github.com/EASYME-md/client)   
- [🙋‍♂️ Visit ONE:A's Github](https://github.com/onealog)

### Image link
![onealog](/assets/readme/easyme.png)   
   
<br>   
   
## Code Block   
### Code inline
- `console.log('Hello EASYME.md!');`   
   
### Code block
```js
function makeDeveloper(name, language) {
  if (name === 'ONE:A' && language === 'JavaScript') {
    return 'perfect!';
  }

  return false;
}

makeDeveloper('ONE:A', 'JavaScript');
```

<br>   
   
## Table   


| title1 | title2 | title3 |
| --- | --- | --- |
| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |


<br>   

