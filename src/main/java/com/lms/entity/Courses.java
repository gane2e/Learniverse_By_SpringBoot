package com.lms.entity;

import com.lms.constant.Course_status;
import com.lms.constant.Recruitment_status;
import com.lms.constant.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Courses {

    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.AUTO) /*자동증가*/
    private Long courseId;

    @Column(nullable = false, length = 100)
    private String title; /* 교육과정 명 */

    @Column(nullable = false, length = 500)
    private String description; /* 교육소개 */


    @Enumerated(EnumType.STRING)
    private Recruitment_status recruitment_status; //모집상태
    
    private Date recruitment_start_date; //모집시작일(선택)
    private Date recruitment_end_date; //모집종료일(선택)


    @Enumerated(EnumType.STRING) //
    private Course_status course_status; //교육상태

    private Date course_start_date; //교육시작일(선택)
    private Date course_end_date; //교육종료일(선택)
    
    /* 해시태그 보류 */

    @Column(nullable = false, length = 500)
    private String completionCriteria; /* 수료기준 */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;
}
