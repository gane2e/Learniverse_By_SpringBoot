package com.lms.controller;

import com.lms.dto.StudentCourseDto;
import com.lms.repository.StudentCourseRepository;
import com.lms.service.StudentCourseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/student/")
public class StudentCourseController {

    @Autowired
    StudentCourseService studentCourseService;
    @Autowired
    StudentCourseRepository studentCourseRepository;

    // 사용자 마지막 시청시간 실시간저장
    @PostMapping(value = "/lastWatchedSave")
    public ResponseEntity<String> lastWatchedSave(@RequestBody StudentCourseDto studentCourseDto){

        Long studentCourseId = studentCourseDto.getStudentCourseId();
        Long last_watched = studentCourseDto.getLast_watched();

        studentCourseService.saveLastWatched(studentCourseId, last_watched);

        return ResponseEntity.ok("마지막 시청시간 저장완료");
    }
}
