package com.venkat.questionservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.venkat.questionservice.model.QuestionDTO;
import com.venkat.questionservice.service.QuestionService;
import com.venkat.questionservice.utility.ConstantsUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(ConstantsUtil.PATH_QUESTION)
public class QuestionController {
	public static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);

	@Autowired
	QuestionService questionService;

	@GetMapping(value = ConstantsUtil.PATH_STRING, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "API Operation to Request a question ", response = String.class, code = 200, notes = " Example : Hey Service, can you provide me a question with numbers to add?")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Response : Here you go, solve the question: \"Please sum the numbers 9,5,3\"."),
			@ApiResponse(code = 400, message = "Not Valid Request"),
			@ApiResponse(code = 401, message = "You are not authorized to view "),
			@ApiResponse(code = 403, message = "Accessing the Question Controller, you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found") })
	public ResponseEntity<QuestionDTO> getQuestion() {
		try {
			return new ResponseEntity<QuestionDTO>(questionService.getQuestion(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<QuestionDTO>(new QuestionDTO(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = ConstantsUtil.PATH_CLIENTRESPONSE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "API Operation to Send response to the question ", response = String.class, code = 200, notes = " Example : Great. The original question was “Please sum the numbers 9,5,3” and the answer is 15.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "That’s great"),
			@ApiResponse(code = 400, message = "Not Valid Request"),
			@ApiResponse(code = 401, message = "You are not authorized to view "),
			@ApiResponse(code = 403, message = "Accessing the Question Controller, you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found") })
	public ResponseEntity<String> verifyAnswer(@RequestParam(value = "qid") String qid,
			@RequestParam(value = "answer") String answer, HttpServletRequest request) {
		if (!qid.isEmpty() && !answer.isEmpty()) {
			String response = "";
			if (questionService.validateAnswer(qid, answer)) {
				response = "That’s great";
				return new ResponseEntity<String>(response, HttpStatus.OK);
			} else {
				response = "That’s wrong. Please try again";
				return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<String>("Not Valid  Request", HttpStatus.BAD_REQUEST);
	}
}
