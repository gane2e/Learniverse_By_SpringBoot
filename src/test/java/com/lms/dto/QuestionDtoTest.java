package com.lms.dto;

import com.lms.entity.Questions;
import com.lms.repository.QuestionsRepository;
import com.lms.service.QuestionService;
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
class QuestionDtoTest {

    @Autowired
    QuestionsRepository questionsRepository;

    @Test
    @DisplayName("문제 등록 테스트")
    @Commit
    public void saveQuestion() {
        try {
            Questions questions = new Questions();
            questions.setQuestionId(1L);
            questions.setQuestionTitle("1번째 문제 제목입니다.");
            questions.setOption1("이게 정답입니다.");
            questions.setOption2("이건 정답이 아닙니다.");
            questions.setOption3("이건 정답이 아닙니다.");
            questions.setOption4("이건 정답이 아닙니다.");
            questions.setCorrectAnswer(1);
            questionsRepository.save(questions);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}