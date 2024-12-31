package com.lms.controller;

import com.lms.dto.CourseApplicationDto;
import com.lms.dto.CourseFormDto;
import com.lms.dto.CourseListDto;
import com.lms.dto.CourseVideoDto;
import com.lms.service.CourseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/course/")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/courses")
    public String courses(Model model) {
        log.info("courses 요청");
        List<CourseListDto> courseList = courseService.getCourseList();
        model.addAttribute("pageTitle", "온라인 교육");
        model.addAttribute("courseList", courseList);
        return "course/courseList";
    }


    // 교육 상세페이지
    @GetMapping(value = "/courseDtl/{courseId}")
    public String courseDtl(@PathVariable("courseId") Long courseId, Model model) {

        CourseListDto courseDto = courseService.CourseByCourseId(courseId);
        model.addAttribute("course", courseDto);
        model.addAttribute("pageTitle", "온라인 교육");
        return "course/courseDtl";
    }

    // CourseRequest => 신청내역 받을 DTO 생성후 변환해서 사용 (CourseDtl에서 ajax요청)
    @PostMapping(value = "/courseApplication")
    public ResponseEntity<String> handlePostRequest(@RequestBody CourseApplicationDto courseApplicationDto) {

        Long courseId = courseApplicationDto.getCourseId();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        courseService.saveApplication(courseId, username);

        // 필요한 처리 후 응답 반환
        return ResponseEntity.ok("Request successfully processed");
    }

    // 수강신청 완료페이지
    @GetMapping(value = "/applicationSuccess")
    public String applicationSuccess() {
        return "course/courseReg-completion";
    }



    // 영상학습 페이지
    @GetMapping(value = "/video/{courseId}")
    public String videoLearning(@PathVariable("courseId") Long courseId, Model model) {

        //교육 상세정보 반환
        CourseListDto courseDto = courseService.CourseByCourseId(courseId);
        model.addAttribute("course", courseDto);

        // 교육영상 정보 반환
        List<CourseVideoDto> courseVideo = courseService.findVideoById(courseId);
        model.addAttribute("courseVideo", courseVideo);

        if(courseVideo.isEmpty()){
            System.out.println("비디오 목록이 없습니다.");
        }
        for (CourseVideoDto courseVideoDto : courseVideo) {
            System.out.println("-----------------------------------");
            System.out.println(courseVideoDto.getCourseVideoIndex());
            System.out.println(courseVideoDto.getVideoTitle());
            System.out.println(courseVideoDto.getVideoUrl());
        }

        return "member/videoLearning";
    }

}