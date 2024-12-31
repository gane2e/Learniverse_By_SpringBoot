package com.lms.dto;

import com.lms.constant.Completion_status;
import com.lms.constant.Enrollment_status;
import com.lms.constant.Test_status;
import com.lms.entity.StudentCourse;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentCourseDto {

    private Long studentCourseId;

    private LocalDateTime courseStarDateTime; //수강 시작일

    private LocalDateTime completionDateTime; //학습 수료일

    private double ProgressRate; // 진도율

    //수강상태(수강신청, 학습중, 학습완료)
    private Enrollment_status enrollmentStatus;

    //시험응시상태(미응시, 합격, 불합격)
    private Test_status testStatus;

    //수료상태(수료, 미수료)
    private Completion_status completionStatus;

    //신청내역 외래키
    private Long applicationId;

    private static ModelMapper modelMapper = new ModelMapper();

    public StudentCourse createStudentCourse() {
        return modelMapper.map(this, StudentCourse.class);
    }

}
