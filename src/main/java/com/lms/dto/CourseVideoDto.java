package com.lms.dto;

import com.lms.entity.CourseVideo;
import com.lms.entity.Courses;
import com.lms.entity.Videos;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class CourseVideoDto {

    private Long courseVideoId; //교육에 등록된 영상목록 기본키

    private Long courseId; //교육과정 외래키
    
    private Long videoId; //영상 외래키

    private Long courseVideoIndex; /* 교육 내 영상 목차 순서 */


    private static ModelMapper modelMapper = new ModelMapper();

    /* CourseVideoDto -> CourseVideo 변환 */
    public CourseVideo createCourseVideo() {
        return modelMapper.map(this, CourseVideo.class);
    }

    /* CourseVideo -> CourseVideoDto 변환 */
    public static CourseVideoDto of(CourseVideo courseVideo) {
        return modelMapper.map(courseVideo, CourseVideoDto.class);
    }
}
