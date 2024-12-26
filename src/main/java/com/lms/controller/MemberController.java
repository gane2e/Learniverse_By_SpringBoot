package com.lms.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/members/")
public class MemberController {


    @GetMapping(value = "/register")
    public String memberRegister(Model model) {
        log.info("register");
        model.addAttribute("pageTitle", "회원가입");
        return "member/register";
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

    /* 마이페이지 수강현황 대시보드 */
    @GetMapping(value = "/dashBoard")
    public String dashBoard(Model model) {
        log.info("dashBoard");
        model.addAttribute("pageTitle", "나의 강의실");
        return "member/dashBoard";
    }


}
