package com.lms.entity;

import com.lms.constant.Application_status;
import com.lms.constant.Course_status;
import com.lms.constant.Recruitment_status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "course_application")
@Getter
@Setter
public class CourseApplication {

    @Id
    @Column(name = "application_id")
    @GeneratedValue(strategy = GenerationType.AUTO) /*자동증가*/
    private Long applicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Courses course;

    //application이 삭제되면 stuentCourse(수강내역)도 삭제되도록 양방향관계 설정
    @OneToOne(mappedBy = "courseApplication", cascade = CascadeType.REMOVE)
    private StudentCourse studentCourse;

    /* 신청상태 */
    @Enumerated(EnumType.STRING) //
    private Application_status application_status;
}