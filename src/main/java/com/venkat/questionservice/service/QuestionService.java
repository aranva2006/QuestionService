package com.venkat.questionservice.service;

import com.venkat.questionservice.model.QuestionDTO;

public interface QuestionService {

	QuestionDTO getQuestion();

	boolean validateAnswer(String qid, String answerStr);

}
