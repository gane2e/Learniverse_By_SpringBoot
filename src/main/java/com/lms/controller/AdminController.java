package com.lms.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/admin/")
public class AdminController {

    @GetMapping(value = "/login")
    public String adminLogin() {
        log.info("admin login");
        return "admin/adminLoginForm";
    }
}
