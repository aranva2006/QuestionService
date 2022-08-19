package com.venkat.questionservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.venkat.questionservice.bean.QuestionBean;
import com.venkat.questionservice.model.QuestionDTO;
import com.venkat.questionservice.utility.ConstantsUtil;
import com.venkat.questionservice.utility.ApplicationUtility;

@Service("questionService")
public class AdditionQuestionServiceImpl implements QuestionService {
	private List<QuestionBean> questionsList = new ArrayList<QuestionBean>();

	public QuestionDTO getQuestion() {
		List<Integer> numbers = ApplicationUtility
				.getRandomNumbers(ApplicationUtility.getRandomNumberInRange(new Random(), 2, 3));
		String strNumbers = numbers.stream().map(Object::toString).collect(Collectors.joining(","));

		StringBuffer sb = new StringBuffer();
		QuestionDTO question = new QuestionDTO();
		question.setQid(UUID.randomUUID().toString());
		question.setQuestionStr(sb.append(ConstantsUtil.QUOTE_STRING).append(ConstantsUtil.QUESTION_ADD_STRING)
				.append(strNumbers).append(ConstantsUtil.QUOTE_STRING).toString());

		QuestionBean questionBean = new QuestionBean();
		questionBean.setQid(question.getQid());
		questionBean.setOperation("Addition");
		questionBean.setNumbers(numbers);
		questionBean.setAnswer(numbers.stream().collect(Collectors.summingInt(Integer::intValue)));
		questionsList.add(questionBean);

		return question;
	}

	@Override
	public boolean validateAnswer(String qid, String answerStr) {
		int userInputSum = ApplicationUtility.getSumFromAnswerStr(answerStr);
		QuestionBean questionBean = questionsList.stream().filter(q -> q.getQid().equals(qid)).findFirst().get();
		if (userInputSum == questionBean.getAnswer())
			return true;
		else
			return false;
	}

}
