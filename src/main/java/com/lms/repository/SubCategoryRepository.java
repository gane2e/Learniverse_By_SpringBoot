package com.lms.repository;

import com.lms.entity.Category;
import com.lms.entity.SubCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    @Query("SELECT s " +
            "FROM SubCategory s " +
            "WHERE s.categories.categoryId = :categoryId")
    List<SubCategory> findSubCategoriesByCategoryId(@Param("categoryId") Long categoryId);
}
