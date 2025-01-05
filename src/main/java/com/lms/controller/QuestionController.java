package com.lms.controller;

import com.lms.service.QuestionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@Log4j2
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping(value = "/question/grading")
    public String gradingQuestion(@RequestParam Map<String, String> answers, Model model) {

        int totalScore = 0;
        int passingScore = 60;
        int totalQuestions = answers.size();

        // 문제의 배점 계산 (100점 / 문제 개수)
        int pointsPerQuestion = 100 / totalQuestions;

        for (Map.Entry<String, String> entry : answers.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            Long questionId = Long.parseLong(entry.getKey()); //응시한 문제
            int userAnswer = Integer.parseInt(entry.getValue()); //선택한 답
            boolean result =  questionService.checkAnswer(questionId, userAnswer);
            if (result) {
                totalScore += pointsPerQuestion;
            }
        }
        if(totalScore <= passingScore) {
            log.info("점수 : " + totalScore + " / " + "결과 : 불합격");
        } else 
            log.info("점수 : " + totalScore + " / " + "결과 : 합격");

        return "course/question";
    }

}
