package com.venkat.questionservice.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.venkat.questionservice.model.QuestionDTO;

public class QuestionControllerTest extends QuestionAbstractTest {
	QuestionDTO questionDTO;

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getQuestion() throws Exception {
		String uri = "/question/";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		questionDTO = super.mapFromJson(content, QuestionDTO.class);
		assertTrue(questionDTO.getQuestionStr().startsWith("\"Please sum the numbers "));
		assertTrue(questionDTO.getQid().length() == 36);
	}

	@Test
	public void validateAnswer() throws Exception {
		String uri = "/question/";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		questionDTO = super.mapFromJson(content, QuestionDTO.class);

		String uri1 = "/question/clientResponse?" + "qid=" + questionDTO.getQid()
				+ "&answer=Great. The original question was " + questionDTO.getQuestionStr() + " and the answer is "
				+ addNumberList(questionDTO);
		MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.get(uri1).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status1 = mvcResult1.getResponse().getStatus();
		assertEquals(200, status1);

		String content1 = mvcResult1.getResponse().getContentAsString();
		assertEquals(content1, "That’s great");
	}

	@Test
	public void getQuestion2() throws Exception {
		String uri = "/question/";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		questionDTO = super.mapFromJson(content, QuestionDTO.class);
		assertTrue(questionDTO.getQuestionStr().startsWith("\"Please sum the numbers "));
		assertTrue(questionDTO.getQid().length() == 36);
	}

	@Test
	public void validateAnswer2() throws Exception {
		String uri = "/question/";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		questionDTO = super.mapFromJson(content, QuestionDTO.class);

		String uri1 = "/question/clientResponse?" + "qid=" + questionDTO.getQid()
				+ "&answer=Great. The original question was " + questionDTO.getQuestionStr() + " and the answer is "
				+ addNumberList(questionDTO);
		MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.get(uri1).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status1 = mvcResult1.getResponse().getStatus();
		assertEquals(200, status1);

		String content1 = mvcResult1.getResponse().getContentAsString();
		assertEquals(content1, "That’s great");
	}
	
	private Integer addNumberList(QuestionDTO questionDTO2) {
		String numbersStr = questionDTO2.getQuestionStr().substring(" \"Please sum the numbers".length());
		int[] intArr = Stream.of(numbersStr.replace("\"", "").split(",")).mapToInt(Integer::parseInt).toArray();
		List<Integer> listOfInts = Arrays.stream(intArr).boxed().collect(Collectors.toList());
		Integer sum = listOfInts.stream().reduce(0, (a, b) -> a + b);
		return sum;
	}
}
