function generateCertificate() {
    // 서버에서 HTML 템플릿을 동적으로 가져옵니다.
    fetch('/student/certificate')
        .then(response => response.text())  // HTML을 텍스트로 받음
        .then(htmlContent => {
            // 새 창 열기
            let printWindow = window.open("", "", "width=1050,height=800");

            // HTML 콘텐츠를 새 창에 삽입
            printWindow.document.write(htmlContent);  // 서버에서 받은 HTML 삽입
            printWindow.document.close();  // 문서 닫기

            // 인쇄 대화상자 띄우기
            printWindow.print();
        })
        .catch(error => console.error("수료증 로드 실패:", error));
}

function printCertificate() {
    var printWindow = window.open('', '_blank', 'width=800,height=600');
    printWindow.document.write(`
               <!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
<!--    <link th:href="@{/css/course/certificate.css}" rel="stylesheet">-->
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@200..900&display=swap');
    @page {
      size: A4;
      margin: 0;
    }
    @media print {
      img {
        display: inline !important;
        visibility: visible !important;
        max-width: 100% !important; /* 이미지 크기를 조정 */
      }
      body {
        background-image: url("https://apms.epis.or.kr/images/home/kor/user/sub/certificate.png"); -webkit-background-image: ;
        background-size: cover;
        background-repeat: no-repeat;
        background-position: center;
        -webkit-print-color-adjust:exact;
      }
      .page {
        position: relative;
        margin: 0;
        border: initial;
        border-radius: initial;
        box-shadow: initial;
        page-break-after: always;
      }
    }
    body {
      font-family: "Noto Serif KR", serif;
      font-optical-sizing: auto;
    }
    * {
      box-sizing: border-box;
      -moz-box-sizing: border-box;
      -webkit-print-color-adjust: exact !important;   /* Chrome, Safari, Edge */
      color-adjust: exact !important;                 /*Firefox*/
    }
    body{
      margin: 0;
      padding: 0;
    }

    .page {
      width: 635px;
      height: 900px;
      position:relative;
      margin: 0 auto;
      padding: 1.5cm 1.5cm 2cm 1.5cm;
      background-image: url("https://apms.epis.or.kr/images/home/kor/user/sub/certificate.png");
      background-size: cover; /* 배경 이미지를 화면 크기에 맞게 확장 */
      background-position: center; /* 이미지가 화면 중앙에 오도록 설정 */
      background-repeat: no-repeat; /* 이미지가 반복되지 않도록 설정 */
    }
    .completionNum {
      position: absolute;
      top: 85px;
      right: 80px;
      text-align: center;
      line-height: 35px;
      font-weight: 800;
      font-size: 18px;
    }
    .title {
      position: absolute;
      top: 167px;
      left: 225px;
      font-weight: 800;
      font-size: 38px;
      line-height: 63px;
      color: #000;
    }
    .content {
      position: relative;
    }
    table {
      position: absolute;
      top: 230px;
      left: 50px;
    }
    th, td {
      text-align: left;
      font-weight: 700;
      font-size: 18px;
      height: 30px;
    }

    td{
      padding-left: 15px;
    }

    .txt {
      position: absolute;
      top: 510px;
      left: 50px;
      text-align: center;
      line-height: 35px;
      font-weight: 900;
      font-size: 18px;
    }
    .director {
      position: absolute;
      top: 650px;
      left: 175px;
      text-align: center;
      line-height: 35px;
      font-weight: 900;
      font-size: 22px;
      z-index: 1;
    }
    .sample {
      position: absolute;
      width: 80px;
      top: 625px;
      left: 330px;
    }
  </style>
</head>
<body>


<img src="/img/certificate_image.png" 
    class="page">
  <div class="completionNum">제 2025-0001호</div>
  <div class="title">교육 수료증</div>
  <div class="content">
    <table>
      <tr>
        <th>성&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;명&nbsp;&nbsp;:
         </th>
        <td>김가은</td>
      </tr>
      <tr>
        <th>생&nbsp;&nbsp;&nbsp;&nbsp;년&nbsp;&nbsp;&nbsp;&nbsp;월&nbsp;&nbsp;&nbsp;일&nbsp;&nbsp;:</th>
        <td>2003-02-06</td>
      </tr>
      <tr>
        <th>교&nbsp;&nbsp;육&nbsp;&nbsp;과&nbsp;&nbsp;정&nbsp;&nbsp;명&nbsp;&nbsp;:</th>
        <td>교육과정명 입니다.</td>
      </tr>
      <tr>
        <th>수&nbsp;&nbsp;&nbsp;&nbsp;료&nbsp;&nbsp;&nbsp;&nbsp;일&nbsp;&nbsp;&nbsp;자&nbsp;&nbsp;:</th>
        <td>2024-01-07</td>
      </tr>
    </table>
    <div class="txt">
      귀하는 위의 과정에 참여하여 교육과정을 수료하였음을 <br>확인합니다.
    </div>
    <div class="director">온라인학습 센터장</div>
    <img src="/img/샘플직인.png" class="sample" alt="직인">
  </div>
</img>
</body>
</html>
    `);
    // 새 창에서 이미지를 로드한 후 인쇄
    var img = printWindow.document.querySelector('.sample');
    img.onload = function() {
        // 이미지가 로드된 후 인쇄
        printWindow.document.close();
        printWindow.print();
    };
    // 이미지가 이미 로드된 경우 즉시 인쇄
    if (img.complete) {
        printWindow.document.close();
        printWindow.print();
    }

}