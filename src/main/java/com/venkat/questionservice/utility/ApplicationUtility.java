package com.venkat.questionservice.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationUtility {

	public static final Logger LOGGER = LoggerFactory.getLogger(ApplicationUtility.class);

	public static Integer getNumberTotal(String userInput) {
		Pattern pattern = Pattern.compile(ConstantsUtil.NUMBER_PATTERN);
		Matcher matcher = pattern.matcher(userInput);
		int sum = 0;
		while (matcher.find()) {
			sum += Integer.valueOf(matcher.group());
		}
		LOGGER.info("total Sum : " + sum);
		return sum;
	}

	public static int getRandomNumberInRange(Random random, int min, int max) {
		return min + random.nextInt(max - min + 1);
	}

	public static List<Integer> getRandomNumbers(int noOfRandomNumbers) {
		List<Integer> listOfIntergers = new ArrayList<Integer>();
		for (int i = 1; i <= noOfRandomNumbers; i++) {
			listOfIntergers.add(generateRandomNumber(1, 9));
		}
		return listOfIntergers;
	}

	public static int generateRandomNumber(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}

	public static int getSumFromAnswerStr(String answerStr) {
		String answerSubStr = answerStr.substring(answerStr.indexOf("\"") + 1, answerStr.length())
				.replace(ConstantsUtil.QUOTE_STRING, "");
		Pattern pattern = Pattern.compile(ConstantsUtil.INT_MATCH_PATTERN);
		Matcher matcher = pattern.matcher(answerSubStr);
		int inputSum = 0;
		if (matcher.find()) {
			inputSum = Integer.valueOf(matcher.group().toString());
		}
		return inputSum;
	}
}
