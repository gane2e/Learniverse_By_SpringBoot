package com.lms.repository;


import com.lms.dto.CourseListDto;
import com.lms.dto.VideoListDto;
import com.lms.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Courses, Long> {


    // 사용자 교육리스트 표출용
    @Query("SELECT new com.lms.dto.CourseListDto(co.courseId, co.title, co.description, co.recruitment_status, " +
            "co.recruitment_start_date, co.recruitment_end_date, co.course_status, co.course_start_date, co.course_end_date, " +
            "co.completionCriteria, co.regTime, co.updateTime, co.createdBy, co.modifiedBy, " +
            "co.imgUrl, co.oriImgName, ct.categoryId , ct.categoryName , sct.subCategoryId, sct.subCategoryName) " +
            "FROM Courses co " +
            "JOIN co.subCategory sct " +
            "JOIN sct.categories ct")
    public List<CourseListDto> findAllCourseWithCategoryInfo();

    // 사용자 페이지 교육 상세정보 표출용
    @Query("SELECT new com.lms.dto.CourseListDto(co.courseId, co.title, co.description, co.recruitment_status, " +
            "co.recruitment_start_date, co.recruitment_end_date, co.course_status, co.course_start_date, co.course_end_date, " +
            "co.completionCriteria, co.regTime, co.updateTime, co.createdBy, co.modifiedBy, " +
            "co.imgUrl, co.oriImgName, ct.categoryId , ct.categoryName , sct.subCategoryId, sct.subCategoryName) " +
            "FROM Courses co " +
            "JOIN co.subCategory sct " +
            "JOIN sct.categories ct " +
            "WHERE co.courseId = :courseId")
    public CourseListDto findCourseById(@Param("courseId") Long courseId);


}
