package com.lms.dto;

import com.lms.constant.Application_status;
import com.lms.constant.Completion_status;
import com.lms.constant.Enrollment_status;
import com.lms.constant.Test_status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentCourseHisDto {

    //신청내역
    private Long applicationId;
    private Application_status application_status;

    //수강내역
    private Long studentCourseId;
    private double ProgressRate; //진도율
    private LocalDateTime completionDateTime; //학습 수료일
    private LocalDateTime courseStarDateTime; //수강 시작일
    private Completion_status completionStatus; //수료상태(수료, 미수료)
    private Enrollment_status enrollmentStatus; //수강상태(수강신청, 학습중, 학습완료)
    private Test_status testStatus; //시험응시상태(미응시, 합격, 불합격)
    private LocalDateTime regTime;
    
    //회원정보
    private Long memberId;
    private String memberName;
    
    //교육정보
    private Long courseId;
    private String courseTitle;

    //카테고리 정보
    private String subCategoryName;
    private String categoryName;

    public StudentCourseHisDto(Long applicationId, Application_status application_status,
                               Long studentCourseId, double ProgressRate, LocalDateTime completionDateTime,  Completion_status completionStatus,  LocalDateTime courseStarDateTime,
                               Enrollment_status enrollmentStatus, Test_status testStatus, LocalDateTime regTime,
                               Long memberId, String memberName, Long courseId, String courseTitle, String subCategoryName,
                               String categoryName) {

        // 신청내역
        this.applicationId = applicationId;
        this.application_status = application_status;

        // 수강내역
        this.studentCourseId = studentCourseId;
        this.ProgressRate = ProgressRate;
        this.completionDateTime = completionDateTime;
        this.completionStatus = completionStatus;
        this.courseStarDateTime = courseStarDateTime;
        this.enrollmentStatus = enrollmentStatus;
        this.testStatus = testStatus;
        this.regTime = regTime;

        // 회원정보
        this.memberId = memberId;
        this.memberName = memberName;

        // 교육정보
        this.courseId = courseId;
        this.courseTitle = courseTitle;

        // 카테고리 정보
        this.subCategoryName = subCategoryName;
        this.categoryName = categoryName;
    }


}
