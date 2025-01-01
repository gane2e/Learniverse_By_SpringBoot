package com.lms.repository;

import com.lms.entity.CourseApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseApplicationRepository extends JpaRepository<CourseApplication, Long> {

    CourseApplication findByApplicationId(Long applicationId);

}
