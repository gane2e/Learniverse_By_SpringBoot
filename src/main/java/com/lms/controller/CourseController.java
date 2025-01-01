package com.lms.controller;

import com.lms.dto.CourseApplicationDto;
import com.lms.dto.CourseFormDto;
import com.lms.dto.CourseListDto;
import com.lms.dto.CourseVideoDto;
import com.lms.entity.Member;
import com.lms.repository.MemberRepository;
import com.lms.service.ApplicationService;
import com.lms.service.CourseService;
import com.lms.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/course/")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private MemberRepository memberRepository;

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
    public ResponseEntity<Map<String, Long>> handlePostRequest(@RequestBody CourseApplicationDto courseApplicationDto) {

        Long courseId = courseApplicationDto.getCourseId();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Long applicationId = courseService.saveApplication(courseId, username);

        // 응답으로 applicationId 반환
        Map<String, Long> response = new HashMap<>();
        response.put("applicationId", applicationId);

        return ResponseEntity.ok(response);
    }

    // 수강신청 완료페이지
    @GetMapping(value = "/applicationSuccess")
    public String applicationSuccess(
            @RequestParam("courseId") Long courseId,
            @RequestParam("applicationId") Long applicationId,
            Model model) {
        CourseListDto courseDto = courseService.CourseByCourseId(courseId);
        model.addAttribute("course", courseDto);
        model.addAttribute("pageTitle", "온라인 교육");
        model.addAttribute("applicationId", applicationId);
        return "course/courseReg-completion";
    }



    // 영상학습 페이지
    @GetMapping(value = "/video/{applicationId}")
    public String videoLearning(@PathVariable("applicationId") Long applicationId,
                                Model model) {

        // 로그인중인 사용자 고유키 찾기
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        Member member = memberRepository.findByLoginId(username);
//        Long memberId = member.getId();


        // 신청내역 고유키 => 교육 신청내역 반환
        CourseApplicationDto application = applicationService.findByApplicationId(applicationId);
        model.addAttribute("application", application);

        Long courseId = application.getCourseId();
        
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