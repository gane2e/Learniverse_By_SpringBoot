package com.lms.service;

import com.lms.constant.Completion_status;
import com.lms.constant.Enrollment_status;
import com.lms.constant.Test_status;
import com.lms.dto.StudentCourseDto;
import com.lms.dto.StudentCourseHisDto;
import com.lms.entity.StudentCourse;
import com.lms.repository.StudentCourseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;

    // 영상학습페이지 - 수강정보 반환
    public StudentCourseDto findByApplicationId(Long applicationId) {
        StudentCourse student = studentCourseRepository.findByCourseApplication_ApplicationId(applicationId);
        StudentCourseDto studentCourseDto = StudentCourseDto.of(student);
        return studentCourseDto;
    }

    // 수강생 마지막 시청시간(초) 실시간저장
    public void saveLastWatched(Long studentCourseId, Long last_watched) {
        StudentCourse studentCourse = studentCourseRepository.findById(studentCourseId).orElseThrow(EntityNotFoundException::new);
        studentCourse.updateLastWatched(last_watched);
        studentCourseRepository.save(studentCourse);
    }

    // 수강생 대시보드 요청시 회원명, 교육정보, 수강정보 반환
    public List<StudentCourseHisDto> findStudentCourseHis(Long studentCourseId) {
        List<StudentCourseHisDto> hisDtos
                = studentCourseRepository.getDashBoard(studentCourseId);
        return hisDtos;
    }
}
