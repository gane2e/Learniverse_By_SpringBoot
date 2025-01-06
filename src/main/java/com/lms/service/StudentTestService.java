package com.lms.service;

import com.lms.constant.Test_status;
import com.lms.dto.StudentTestDto;
import com.lms.entity.StudentTest;
import com.lms.repository.StudentTestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class StudentTestService {

    private final StudentTestRepository studentTestRepository;

    // 수강내역ID 해당하는 시험응시내역 찾기
    public StudentTestDto findByStudentCourseId(Long studentCourseId) {
        StudentTest studentTest = studentTestRepository.findByStudentCourse_StudentCourseId(studentCourseId);
        StudentTestDto studentTestDto = StudentTestDto.of(studentTest);
        return studentTestDto;
    }


    // 시험 응시결과 저장하기
    public int saveTest(Long studentTestId,
           Test_status testStatus, int totalScore) {
       
        StudentTest studentTest = studentTestRepository.findById(studentTestId)
                .orElseThrow(() -> new EntityNotFoundException("시험내역 조회중 에러발생"));

        //회원의 차수별 시험결과 조회
        Test_status firstTestStatus = studentTest.getFirstAttemptStatus();
        Test_status secondTestStatus = studentTest.getSecondAttemptStatus();
        Test_status thirdTestStatus = studentTest.getThirdAttemptStatus();

        int testCount = 0;

        //1차시험 미응시면 첫번째 시험결과 저장
        if(firstTestStatus == Test_status.미응시) {
            studentTest.setFirstAttemptStatus(testStatus);
            studentTest.setFirstScore(totalScore);
            testCount = 1;
        }
        // 1차시험 불합격&2차시험 미응시면 시험결과 저장
        else if ( (firstTestStatus == Test_status.불합격)
                && secondTestStatus == Test_status.미응시) {
            studentTest.setSecondAttemptStatus(testStatus);
            studentTest.setSecondScore(totalScore);
            testCount = 2;
        } 
        // 2차시험 불합격&3차시험 미응시면 시험결과 저장
        else if ( (secondTestStatus == Test_status.불합격)
                && (thirdTestStatus == Test_status.미응시) ) {
            studentTest.setThirdAttemptStatus(testStatus);
            studentTest.setThirdScore(totalScore);
            testCount = 3;
        }
        // 조건에 따른 결과 및 점수 저장후 DB에저장
        studentTestRepository.save(studentTest);
        return testCount;
    }

}
