package com.lms.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/")
public class MainController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    //로그인 여부 체크하는 공통 ajax 처리
    @GetMapping(value = "/loginCheck")
    public ResponseEntity<String> loginCheck() {

        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok("로그인 상태입니다."); // 로그인 상태
        } else {
            return ResponseEntity.status(401).body("로그인되지 않았습니다."); // 비로그인 상태
        }
    }


}
