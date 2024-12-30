package com.lms.controller;

import com.lms.dto.CourseFormDto;
import com.lms.dto.CourseListDto;
import com.lms.service.CourseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        log.info("courseDtl 요청");

        CourseListDto courseDto = courseService.CourseByCourseId(courseId);
        model.addAttribute("course", courseDto);
        model.addAttribute("pageTitle", "온라인 교육");
        return "course/courseDtl";
    }

    @GetMapping(value = "/video")
    public String videoLearning() {
        log.info("video 요청");
//        return "member/videoLearning";
        return "member/testVideo";
    }

}
