package com.lms.controller;

import com.lms.dto.*;
import com.lms.entity.Category;
import com.lms.entity.Member;
import com.lms.repository.MemberRepository;
import com.lms.service.*;
import com.lms.util.UserCheck;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@Log4j2
@RequestMapping("/course/")
@Transactional
public class CourseController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private StudentCourseService studentCourseService;
    @Autowired
    private QuestionService questionService; //시험문제관리
    @Autowired
    private StudentTestService studentTestService; //사용자 시험응시내역
    @Autowired
    private CourseHashTagService courseHashTagService; //교육 해시태그
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping({"/courses", "/courses/search", "/courses/{page}"})
    public String courses(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "categoryId", required = false) Long categoryId,
                          @RequestParam(value = "subCategoryId", required = false) Long subCategoryId,
                          Model model,
                          @PathVariable("page") Optional<Integer> page) {

        Pageable pageable =
                PageRequest.of(page.isPresent() ? page.get() : 0, 15);

        // 1차카테고리 얻어서 모델로 보내기
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        Page<List<CourseListDto>> courseList = courseService.getCourseList(keyword, categoryId, subCategoryId, pageable);
        model.addAttribute("pageTitle", "온라인 교육");
        model.addAttribute("courseList", courseList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("subCategoryId", subCategoryId); 
        model.addAttribute("maxPage", 5);
        model.addAttribute("total", courseList.getTotalElements());
        return "course/courseList";
    }

    // 교육 상세페이지
    @GetMapping(value = "/courseDtl/{courseId}")
    public String courseDtl(@PathVariable("courseId") Long courseId, Model model) {

        CourseListDto courseDto = courseService.CourseByCourseId(courseId);
        List<CourseVideoDto> video = courseService.findVideoById(courseId);
        List<HashTagFormDto> hashTagFormDtos = courseHashTagService.getHashTagFormDto(courseId);
        model.addAttribute("course", courseDto);
        model.addAttribute("video", video);
        model.addAttribute("hashTags", hashTagFormDtos);
        model.addAttribute("pageTitle", "온라인 교육");
        return "course/courseDtl";
    }

    // CourseRequest => 신청내역 받을 DTO 생성후 변환해서 사용 (CourseDtl에서 ajax요청)
    // 교육 신청내역 저장(신청내역+수강내역+시험내역)
    @PostMapping(value = "/courseApplication")
    public ResponseEntity<Map<String, Long>> handlePostRequest(@RequestBody CourseApplicationDto courseApplicationDto) {

        Long courseId = courseApplicationDto.getCourseId();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Long applicationId = courseService.saveApplication(courseId, username);

        Member member = memberRepository.findByLoginId(username);

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
                                Model model, RedirectAttributes redirectAttributes) {

        if(!UserCheck.isAuthenticated()) {
            redirectAttributes.addFlashAttribute("exceptionMessage", "수강 권한이 없습니다.");
            return "redirect:/";
        }

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

        //수강정보 반환
        StudentCourseDto student = studentCourseService.findByApplicationId(applicationId);
        model.addAttribute("student", student);

        //시험정보 반환
        Long studentCourseId = student.getStudentCourseId();
        StudentTestDto studentTestDto = studentTestService.findByStudentCourseId(studentCourseId);
        model.addAttribute("studentTest", studentTestDto);

        return "member/videoLearning";
    }


    //시험보기 페이지로 이동
    @GetMapping(value = "/question")
    public String courseQuestion(Model model,
                                 @RequestParam("subCategoryId") Long subCategoryId,
                                 @RequestParam("studentCourseId") Long studentCourseId,
                                 @RequestParam("courseTitle") String courseTitle) {

        //문제목록
        List<QuestionDto> questionDtos =
                questionService.getQuestionDtoList(subCategoryId);

        //시험응시ID찾아 보내기
        StudentTestDto studentTestDto =
                studentTestService.findByStudentCourseId(studentCourseId);
        Long studentTestId = studentTestDto.getStudentTestId();

        model.addAttribute("questions", questionDtos);
        model.addAttribute("studentTestId", studentTestId);
        model.addAttribute("courseTitle", courseTitle);
        model.addAttribute("studentCourseId", studentCourseId);
        model.addAttribute("subCategoryId", subCategoryId);
        model.addAttribute("pageTitle", "온라인 교육");
        return "course/question";
    }


    //별점등록 수행POST메서드
    @PostMapping(value = "/saveRating")
    public ResponseEntity<String>  saveRating(@RequestBody CourseListDto courseListDto) {
        courseService.saveRating(courseListDto.getRating(),
                courseListDto.getCourseId());
        System.out.println("courseListDto.getRating() : " + courseListDto.getRating());
        System.out.println("courseListDto.getCourseId() : " + courseListDto.getCourseId());
        return ResponseEntity.ok("Rating saved successfully");
    }
    

}