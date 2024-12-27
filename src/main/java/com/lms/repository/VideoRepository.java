package com.lms.repository;

import com.lms.entity.Category;
import com.lms.entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Videos, Long> {

    // 관리자 > 교육등록 > 카테고리선택 > 선택한 카테고리의 교육영상 등록일기준 내림차순 찾기
    List<Videos> findBySubCategory_SubCategoryId(Long subCategoryId);
}
