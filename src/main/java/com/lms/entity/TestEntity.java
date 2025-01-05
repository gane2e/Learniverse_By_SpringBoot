package com.lms.entity;

import com.lms.constant.Completion_status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "student_test")
@Getter
@Setter
public class TestEntity extends BaseTimeEntity{

    @Id
    @Column(name = "student_test_id")
    @GeneratedValue(strategy = GenerationType.AUTO) /*자동증가*/
    private Long studentTestId;

    @Column(name = "first_score")
    private int firstScore; //1차점수
    private Completion_status firstAttemptStatus; //1차 응시 상태

    @Column(name = "second_score")
    private int secondScore; //2차점수
    private Completion_status secondAttemptStatus; //2차 응시 상태

    @Column(name = "third_score")
    private int thirdScore; //3차점수
    private Completion_status thirdAttemptStatus; //3차 응시 상태

    private boolean isReset; // 리셋여부

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_course_id")
    private StudentCourse studentCourse;
}
