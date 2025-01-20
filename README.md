<img src="https://github.com/user-attachments/assets/01ee4a1b-577b-4454-8a58-46778779b66a" width="200px"> <br/>

## 📋 목차
1. [🔮 프로젝트 소개](#프로젝트-소개)
2. [🙋‍♀️ 프로젝트 참여자](#프로젝트-참여자)
3. [🛠 개발환경](#개발환경)
4. [✒️ 기술소개](#기술소개)
5. [📐 UML](#uml)
6. [개발일정 및 작업관리](#개발일정-및-작업-관리)
7. [개발후_기능시연](#개발후_기능시연)
8. [개발후_화면캡쳐](#개발후_화면캡쳐)

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

## 🙋‍♀프로젝트 참여자
- 참여자 : 김가은(프론트엔드&백엔드 개발) 
- 이메일 : b1a409896@naver.com
- 휴대폰 : 010-7933-6261
- 기  간 : 2024-12-26 ~ 2025-01-15 (약 3주일)


## 🛠개발환경
<img src="https://github.com/user-attachments/assets/38f6b2e6-d2c4-4e96-b3fe-4a84eabcd163" width="800px">

## 기술소개
### Spring Security
- Spring Security를 사용해 웹페이지의 보안을 강화하고, "비회원", "회원", "관리자" 경로별 접근 권한을 SecurityConfig 클래스로 관리하였습니다.
- OAuth2 기반 소셜 로그인과의 통합을 위해 UserDetails, OAuth2User를 커스텀하여 소셜로그인과 일반로그인 동작을 동일하게 할 수 있도록 하였습니다.
  
### Spring Boot JPA 
- JPA Repository에서 제공하는 CRUD 메서드를 활용하여 SQL 작업을 간편하게 처리하였습니다.
- JPQL을 이용하여 동적 쿼리 작성 및 재사용성과 유지보수성을 향상시켰습니다.

### API
- **[VideoJs]** VideoJS API를 활용하여 사용자의 마지막 시청초를 추적하고, 영상의 길이를 계산하여 총 진도율을 계산하는 기능을 구현하였습니다.
- **[VideoJs]** API에서 제공하는 메서드를 활용해 영상별 총길이를 구하여 해당 교육과정의 영상길이 합산 초 및 사용자가 총 시청한 시점을 추적하는 기능을 구현하였습니다.
- **[Fetch]** 영상 종료 시점이나 영상 일시정지 시, 마지막 시청 초를 FETCH API를 통해 서버로 전송하여 비동기 통신을 구현하고 진도율을 실시간으로 저장하도록 하며, 성공/실패에 따른 예외처리를 다양하게 할수있도록 하였습니다.
- **[Slick]** Slick 슬라이드 API를 사용하여 자동 슬라이드 기능과 화면에 표시되는 슬라이드 수를 직접 조정할 수 있어, 개발 시간을 단축하고 효율성을 높였습니다.
- **[Kakao, Google, Naver]** 카카오, 네이버, 구글 소셜로그인을 통해 사용자가 손쉽게 로그인할 수 있도록 하며,  OAuth2를 기반으로 한 보안 로그인을 적용하여 사용자 인증을 처리했습니다.


## UML
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
- [섹션1] 메인비주얼 : slick 슬라이드 API를 사용하여 최근 등록된 교육과정 5개를 AUTO슬라이드가 가능하도록 하였습니다.
- [섹션2] 교육과정 검색기 : 교육과정명을 입력하여 검색된 페이지로 이동할 수 있도록 하였습니다.<br />해시태그는 관리자가 교육 등록 시 입력한 해시태그를 DB에 저장하여 가장 많이 등록된(중복수가 가장 높은)순으로 5개가 노출됩니다.
- [섹션3] 가장 많이 신청한 교육 리스트 : 사용자가 교육과정 신청 시 해당 교육과정 테이블의 "신청자 수" 를 1씩 증가시켜 <br /> 신청자가 많은 순으로, 신청자 수가 같을 시 최근 등록된 순으로 1차>2차정렬을 하였습니다.
<img src="https://github.com/user-attachments/assets/d3aa760c-48f6-4945-a9d6-e3beb7d11d10" width="800px"></br>

### 회원가입
- [아이디 중복체크] 아이디 입력 후 "중복확인" 클릭 시 사용자가 입력 한 ID를 JSON형태(key,Value)로 FetchAPI 통신을 POST 방식으로 요청합니다. <br />
서버에서 아이디 중복체크 후 반환된 "true", "false" 값으로 중복 여부에따른 success 로직을 실행합니다.
- [유효성검사] 회원가입 버튼 클릭 시 자바스크립트의 유효성검사 함수를 실행하여, 각 입력폼이 빈 문자열인지 또는 형식에 맞지않는지 if문으로 검사하여 통과하지 못할시 return으로 함수를 종료시켜 폼 제출을 방지하였습니다. <br />
유효성검사를 모두 통과시 폼 id값으로 DOM요소를 선택하여 Form submit을 실행시킵니다.
<img src="https://github.com/user-attachments/assets/9625332e-4a5d-49c5-9ed0-10d0f6ff3d8d" width="800px"></br>
### 로그인
- [입력필드 필수값] 아이디 및 비밀번호는 html태그 속성 중 required를 사용하여 "폼 제출 전 반드시 채워져있어야 하는 필드"임을 명시하였습니다.
- [아이디/비밀번호 체킹] 스프링 시큐리티를 통해 로그인처리를 하였습니다. 
<img src="https://github.com/user-attachments/assets/003fcb9f-c72e-4bb8-8fdb-03c8bd63924e" width="800px"></br>
### 수강신청
- [카테고리] 1차 카테고리는 서버에서 전송한 1차 카테고리 데이터를 셀렉트할 수 있도록 하였고, <br />
1차 값 선택 시 자바스크립트의 AJAX통신 GET 요청을 통해 쿼리파라미터로 1차 카테고리 값 전달, 응답으로 json형태인 2차카테고리 값을 반환받았습니다.
- [검색기] 검색기를 GET방식의 Form태그로 감싸고, "검색" 버튼은 submit요청을 하도록 하여 쿼리파라미터로 {키워드}/{서브카테고리Id}/{1차카테고리Id} 값이 하나라도 있을 경우 해당 조건을 포함하여 JPQL 쿼리를 통해 조회하도록 했습니다.
- [수강신청] 비회원은 수강신청이 불가하며, 수강신청 버튼 클릭 시 FetchAPI GET 요청을 통해 서버에서 로그인 체크 함수를 실행하도록 했고, 시큐리티 Authentication 인터페이스를 통해 인증된 사용자 정보를 조회하여 회원여부를 체크하였습니다.
<img src="https://github.com/user-attachments/assets/579a5b9a-0d7e-4724-b6ef-999fde0a30c5" width="800px"></br>
### 학습하기 -1
<img src="https://github.com/user-attachments/assets/15573d41-c22b-4855-87ac-63b384138b9f" width="800px"></br>
### 학습하기 -2, 시험보기-불합격
<img src="https://github.com/user-attachments/assets/04105d26-db0d-4e75-a7d6-c10f983ecf52" width="800px"></br>
### 시험보기-합격
<img src="https://github.com/user-attachments/assets/f554d635-b684-4793-90b5-c306b242ef54" width="800px"></br>
### 수료증 출력
<img src="https://github.com/user-attachments/assets/2a7047ec-fcdc-4f41-9c4d-78d1c1ab8af7" width="800px"></br>


<details><summary>📸개발후_화면소개
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
> 이번 프로젝트를 진행하면서 혼자 개발하는 과정에서 부족한 점도 많았고, 많은 것을 배울 수 있었습니다. <br />

> 특히 기간이 부족하여 충분한 예외 처리를 하지 못한 점이 아쉬움으로 남습니다. 이 부분은 향후 더 신경 써야 할 부분이라고 느꼈고, 팀원과의 협업을 통해 해결할 수 있는 문제들이 많다는 것도 실감했습니다. <br /><br />

> 카테고리 선택과 실시간 진도율 저장을 구현하기 위해 Ajax 비동기 처리 방식을 사용했는데, 이 과정에서 Fetch API에 대해 알게 되었습니다. Fetch 방식은 스트림을 사용하여 응답 본문을 처리할 수 있어 편리하게 느껴졌습니다. <br />
이를 통해 비동기 통신 방식에 대해 더 깊이 이해할 수 있었습니다.<br /><br />

> 백엔드 로직을 개발하는 과정에서 큰 재미를 느꼈습니다. 그러나 컨트롤러에서 로직을 지나치게 길게 작성하거나 중복된 코드를 반복하게 되는 순간들이 있었습니다. <br />
다행히도 인텔리제이에서 중복된 코드를 메서드로 분리하거나, 서비스에서 처리하라는 알림을 띄워주어 불필요한 코드작성을 피할 수 있게 되었습니다.<br /><br />

> 이번 프로젝트는 저에게 큰 경험이 되었으며, 앞으로도 더 많은 공부가 필요할것 같다고 느꼈습니다.  
