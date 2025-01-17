## :mortar_board: 온라인 학습관리 시스템
<img src="https://github.com/user-attachments/assets/01ee4a1b-577b-4454-8a58-46778779b66a" width="200px"> <br/>
"학습과 우주적 탐구가 만나는 무한한 가능성의 공간"



## 🙋‍♀️ 프로젝트 참여자
- 참여자 : 김가은(프론트엔드&백엔드 개발)
- 이메일 : b1a409896@naver.com
- 휴대폰 : 010-7933-6261

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


## 🛠 개발환경
<img src="https://github.com/user-attachments/assets/38f6b2e6-d2c4-4e96-b3fe-4a84eabcd163" width="800px">

## 🛠 기술소개
### Spring Security를 통한 보안 관리 및 권한 제어
- Spring Security를 사용해 웹 애플리케이션의 보안을 강화하고, 세부적인 경로별 접근 권한을 관리.
- OAuth2 기반 소셜 로그인과의 통합을 통해 보안 강화를 동시에 구현.
### Spring Boot JPA 활용한 데이터 처리
- JPA Repository에서 제공하는 CRUD 메서드를 활용하여 SQL 작업을 간편하게 처리.
- JPQL을 이용하여 동적 쿼리를 작성, 재사용성과 유지보수성을 향상시켰습니다.

### VideoJS / FETCH API
- VideoJS API를 활용하여 사용자의 마지막 시청초를 추적하고, 영상의 길이를 계산하여 총 진도율을 계산하는 기능을 구현하였습니다.
- API에서 제공하는 메서드를 활용해 영상별 총길이를 구하여 해당 교육과정의 영상길이 합산 초 및 사용자가 총 시청한 시점을 추적하는 기능을 구현하였습니다.
- 영상 종료 시점이나 일시정지 시, 마지막 시청 초를 FETCH API를 통해 서버로 전송하여 진도율을 저장하도록 하였습니다.


## 🛠 개발환경 소개

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
   
<details><summary>접고 펴는 기능
</summary>

*Write here!*
</details>

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

