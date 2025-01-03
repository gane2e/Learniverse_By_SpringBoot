package com.lms.service;

import com.lms.entity.Member;
import com.lms.entity.Questions;
import com.lms.repository.QuestionsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class QuestionService {

    private final QuestionsRepository questionsRepository;

    public void saveQusetions(Questions questions) {
       questionsRepository.save(questions);
    }
}
