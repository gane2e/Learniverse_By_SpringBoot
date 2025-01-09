package com.lms.repository;

import com.lms.entity.CourseHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseHashTagRepository extends JpaRepository<CourseHashTag, Long> {

    List<CourseHashTag> findByCourseIsNull();

    List<CourseHashTag> findByCourse_CourseId(Long courseId);
}
