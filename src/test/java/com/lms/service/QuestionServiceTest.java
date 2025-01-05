package com.lms.service;

import com.lms.dto.QuestionDto;
import com.lms.entity.Questions;
import groovy.util.logging.Log4j2;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Log4j2
class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    public Questions createQuestions() {
        QuestionDto dto = new QuestionDto();
        dto.setQuestionId(1L);
        dto.setQuestionTitle("1번문제입니다~");
        dto.setOption1("이건 답이아닙니다.");
        dto.setOption2("이게 답입니다.");
        dto.setOption3("이건 답이아닙니다.");
        dto.setOption4("이건 답이아닙니다.");
        dto.setCorrectAnswer(2);
        return Questions.createQuestions(dto);
    }

    @Test
    @DisplayName("시험문제등록 테스트")
    @Commit
    public void saveQuestion() {
            Questions questions = createQuestions();
            questionService.saveQusetions(questions);
    }


}