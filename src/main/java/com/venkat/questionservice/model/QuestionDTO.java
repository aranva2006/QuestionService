package com.venkat.questionservice.model;

import java.util.Objects;

public class QuestionDTO {
	private String qid;
	private String questionStr;

	public QuestionDTO() {

	}

	public QuestionDTO(String string, String string2) {
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getQuestionStr() {
		return questionStr;
	}

	public void setQuestionStr(String questionStr) {
		this.questionStr = questionStr;
	}

	@Override
	public int hashCode() {
		return Objects.hash(qid, questionStr);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionDTO other = (QuestionDTO) obj;
		return Objects.equals(qid, other.qid) && Objects.equals(questionStr, other.questionStr);
	}

	@Override
	public String toString() {
		return "QuestionDTO [qid=" + qid + ", questionStr=" + questionStr + "]";
	}

}
