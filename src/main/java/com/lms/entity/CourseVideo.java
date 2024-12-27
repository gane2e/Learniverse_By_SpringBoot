package com.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "course_video")
@Getter
@Setter
public class CourseVideo {

    @Id
    @Column(name = "course_video_id")
    @GeneratedValue(strategy = GenerationType.AUTO) /*자동증가*/
    private Long courseVideoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Courses courses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Videos videos;

    private Long courseVideoIndex; /* 교육 내 영상 목차 순서 */
}
