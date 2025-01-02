package com.lms.controller;

import com.lms.dto.MemberFormDto;
import com.lms.dto.StudentCourseHisDto;
import com.lms.entity.Member;
import com.lms.repository.MemberRepository;
import com.lms.service.MemberService;
import com.lms.service.StudentCourseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/members/")
public class MemberController {


    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final StudentCourseService studentCourseService;

    public MemberController(MemberService memberService, MemberRepository memberRepository, StudentCourseService studentCourseService) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.studentCourseService = studentCourseService;
    }

    @GetMapping(value = "/login")
    public String memberLogin(Model model) {
        log.info("login");
        model.addAttribute("pageTitle", "로그인");
        return "member/memberLoginForm";
    }

    //로그인실패시 Get요청으로 실패메시지 전달
    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해 주십시오.");
        log.info("로그인 실패");
        return "member/memberLoginForm";
    }


    @GetMapping(value = "/register")
    public String memberRegister(Model model) {
        log.info("register");
        model.addAttribute("pageTitle", "회원가입");
        model.addAttribute("member", new MemberFormDto());
        return "member/register";
    }

    @PostMapping(value = "/new")
    public String memberRegister(MemberFormDto memberFormDto, Model model) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Member member = Member.createMember(memberFormDto,passwordEncoder);
        memberService.saveMember(member);
        model.addAttribute("success", "회원가입이 완료되었습니다.");
        return "redirect:/";
    }

    @GetMapping(value = "/modify")
    public String memberModify(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MemberFormDto memberFormDto =  memberService.modifyMember(username);
        model.addAttribute("member",memberFormDto);
        model.addAttribute("pageTitle", "회원정보 수정");

        return "member/memberFormModify";
    }

    /* 마이페이지 수강현황 대시보드 */
    @GetMapping(value = "/dashBoard")
    public String dashBoard(Model model) {

        log.info("==================dashBoard==================");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Member member = memberRepository.findByLoginId(username);
        Long memberId = member.getId();

        List<StudentCourseHisDto> hisDtos =
                studentCourseService.findStudentCourseHis(memberId);

        //TEST완료 : 정상반환함
        hisDtos.forEach((dto -> log.info(dto.getCourseTitle())));

        model.addAttribute("pageTitle", "나의 수강현황");
        model.addAttribute("hisDtos", hisDtos);
        return "member/dashBoard";
    }


}

