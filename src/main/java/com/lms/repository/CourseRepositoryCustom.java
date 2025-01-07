package com.lms.repository;

import com.lms.dto.CourseListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseRepositoryCustom {

    Page<CourseListDto> getCourses(Pageable pageable);
}
