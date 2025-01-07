package com.lms.repository;

import com.lms.dto.CourseListDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CourseRepositoryCustomImpl implements CourseRepositoryCustom {

    /* QueryDSL을 사용하여 JPA 쿼리를 생성하고 실행하는 데 필요한 클래스 */
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<CourseListDto> getCourses(Pageable pageable) {
        return null;
    }
}
