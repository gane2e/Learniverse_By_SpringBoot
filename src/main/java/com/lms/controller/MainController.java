package com.lms.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/")
public class MainController {

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("message", "로그인에 성공하였습니다.");
        return "index";
    }



    public void gitTest()
    {
        System.out.println("commit");
    }


}
