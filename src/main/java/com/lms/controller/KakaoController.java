package com.lms.controller;

import java.util.Map;


import com.lms.dto.KakaoDto;
import com.lms.dto.MemberFormDto;
import com.lms.entity.Member;
import com.lms.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@Log4j2
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoApi kakaoApi;
    private final MemberService memberService;

    @RequestMapping("/kakao-login")
    public String kakaoLogin(@RequestParam String code, Model model, HttpSession session) {

        log.info("---kakao-login 진입---");

        // 1. 인가 코드 받기 (@RequestParam String code)

        System.out.println("code => " + code);

        // 2. 토큰 받기
        String accessToken = kakaoApi.getAccessToken(code);
        System.out.println("accessToken => " + accessToken);

        // 3. 사용자 정보 받기
        Map<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
        System.out.println("userInfo ==> " + userInfo);

        String id = (String) userInfo.get("id");
        String email = (String) userInfo.get("email");
        String nickname = (String) userInfo.get("nickname");

        System.out.println("id = " + id);
        System.out.println("email = " + email);
        System.out.println("nickname = " + nickname);
        System.out.println("accessToken = " + accessToken);

        Member member = memberService.kakaoUserCheck(id);

        //카카오연동이력이 없으면 회원가입 페이지로 이동
        if (member == null) {

            KakaoDto kakaoDto = new KakaoDto();
            kakaoDto.setId(id);
            kakaoDto.setEmail(email);
            kakaoDto.setNickname(nickname);

            model.addAttribute("member", new MemberFormDto());
            model.addAttribute("kakao", kakaoDto);
            return "member/register";
        } else {
            System.out.println("카카오연동 로그인 구현중입니다.");
            member.setAccessToken(accessToken);
            session.setAttribute("member", member);
            return "redirect:/";
        }
    }

    @PostMapping("doJoin/kakao")
    public String kakaoJoin(@RequestParam("kakaoKey") String kakaoKey,
                            MemberFormDto memberFormDto) {

        log.info("kakaoId ==> " + kakaoKey);
//        userVO.setKakaoId(kakaoKey);
//        userService.kakaoJoin(kakaoKey);

        return "redirect:/";
    }

    @PostMapping("user/kakao/delete")
    public String kakaoLogout(@RequestParam("kakaoId") String kakaoId,
                              @RequestParam("accessToken") String accessToken) {

        // 카카오 연동해지 처리
        kakaoApi.kakaoLogout(accessToken);
        // 테이블에서 카카오키 삭제

        // userService.deleteKakao(kakaoId);

        return "redirect:/";
    }

}
