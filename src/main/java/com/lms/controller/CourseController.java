package com.lms.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/course/")
public class CourseController {

    @GetMapping(value = "/courses")
    public String courses(Model model) {
        log.info("courses 요청");
        model.addAttribute("pageTitle", "온라인 교육");
        return "course/courseList";
    }

    @GetMapping(value = "/courseDtl")
    public String courseDtl(Model model) {
        log.info("courseDtl 요청");
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
