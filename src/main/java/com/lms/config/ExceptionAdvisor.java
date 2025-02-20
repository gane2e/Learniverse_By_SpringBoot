package com.lms.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(TemplateInputException.class)
    public String TemplateInputException(Exception e, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        System.out.println("exception message : " + e.getMessage() + ", httpservletRequest : " + request.toString());
        redirectAttributes.addFlashAttribute("exceptionMessage", "템플릿 오류가 발생하였습니다.");
        System.out.println("템플릿 예외가 발생하였을때 동작");
        return "redirect:/";
    }

    @ExceptionHandler(NullPointerException.class)
    public String NullPointerException(NullPointerException e, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        System.out.println("exception message : " + e.getMessage() + ", httpservletRequest : " + request.toString());
        redirectAttributes.addFlashAttribute("exceptionMessage", "NULL 오류가 발생하였습니다.");
        System.out.println("Null값 예외가 발생하였을때 동작");
        return "redirect:/";
    }

    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(AuthenticationException e, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        System.out.println("exception message : " + e.getMessage() + ", httpservletRequest : " + request.toString());
        redirectAttributes.addFlashAttribute("exceptionMessage", "접근 권한이 없습니다.");
        System.out.println("권한 예외가 발생하였을때 동작");
        return "redirect:/";
    }


    @ExceptionHandler(Exception.class)
    public String myExceptionHandler(Exception e, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        System.out.println("exception message : " + e.getMessage() + ", httpservletRequest : " + request.toString());
        redirectAttributes.addFlashAttribute("exceptionMessage", "오류가 발생하였습니다.");
        return "redirect:/";
    }




}
