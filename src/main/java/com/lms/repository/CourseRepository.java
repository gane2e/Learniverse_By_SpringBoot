package com.lms.repository;


import com.lms.dto.CourseListDto;
import com.lms.dto.CourseVideoDto;
import com.lms.dto.VideoListDto;
import com.lms.entity.Courses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Courses, Long> {


    @Query("SELECT new com.lms.dto.CourseListDto(co.courseId, co.title, co.description, co.recruitment_status, " +
            "co.recruitment_start_date, co.recruitment_end_date, co.course_status, co.course_start_date, co.course_end_date, " +
            "co.completionCriteria, co.regTime, co.updateTime, co.createdBy, co.modifiedBy, " +
            "co.imgUrl, co.oriImgName, ct.categoryId, ct.categoryName, sct.subCategoryId, sct.subCategoryName) " +
            "FROM Courses co " +
            "JOIN co.subCategory sct " +
            "JOIN sct.categories ct " +
            "WHERE (:keyword IS NULL OR LOWER(co.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(co.description) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:categoryId IS NULL OR ct.categoryId = :categoryId) " +
            "AND (:subCategoryId IS NULL OR sct.subCategoryId = :subCategoryId)")
    Page<List<CourseListDto>> findAllCourseWithCategoryInfo(@Param("keyword") String keyword,
                                                      @Param("categoryId") Long categoryId,
                                                      @Param("subCategoryId") Long subCategoryId,
                                                            Pageable pageable);



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


    @Query("SELECT new com.lms.dto.CourseVideoDto" +
            "(c.courseId , v.videoId , cv.courseVideoIndex, v.title, " +
            "v.videoName, v.oriVideoName, v.videoUrl, c.title) " +
            "FROM CourseVideo cv " +
            "JOIN cv.videos v " +
            "JOIN cv.courses c " +
            "WHERE cv.courses.courseId = :courseId")
    List<CourseVideoDto> findVideoById(@Param("courseId") Long courseId);


}
