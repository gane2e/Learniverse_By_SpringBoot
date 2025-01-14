package com.lms.controller;

import com.lms.dto.CourseListDto;
import com.lms.dto.HashTagCountDto;
import com.lms.dto.HashTagFormDto;
import com.lms.entity.Courses;
import com.lms.service.CourseHashTagService;
import com.lms.service.CourseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/")
public class MainController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseHashTagService courseHashTagService;

    @GetMapping(value = "/")
    public String index(Model model) {

        //메인비주얼 교육목록 (최근등록순 5개)
        List<CourseListDto> getMainVisualList = courseService.getMainVisualList();
        model.addAttribute("mainVisualList", getMainVisualList);
        
        //메인검색기 해시태그 표출(가장 많이등록된순 5개)
        List<HashTagCountDto> top5Hashtags = courseHashTagService.findByTop5Hashtags();
        model.addAttribute("top5Hashtags", top5Hashtags);

        //가장 많이 신청한 교육과정 8개
        List<CourseListDto> top8CourseList = courseService.top8CourseList();
        model.addAttribute("top8CourseList", top8CourseList);
        return "index";
    }

    @GetMapping(value = "/success")
    public String loginSuccess(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "로그인 되었습니다.");
        return "redirect:/";
    }

    //로그인 여부 체크하는 공통 ajax 처리
    @GetMapping(value = "/loginCheck")
    public ResponseEntity<String> loginCheck() {

        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            System.out.println("authentication : " + authentication.getName());
            return ResponseEntity.ok("로그인 상태입니다."); // 로그인 상태
        } else {
            return ResponseEntity.status(401).body("로그인되지 않았습니다."); // 비로그인 상태
        }
    }

    @GetMapping(value = "/id-find")
    public String idFind(Model model) {
        model.addAttribute("pageTitle", "아이디/비밀번호 찾기");
        return "member/id-find";
    }

    @GetMapping(value = "/pw-find")
    public String pwFind(Model model) {
        model.addAttribute("pageTitle", "아이디/비밀번호 찾기");
        return "member/pw-find";
    }


}
