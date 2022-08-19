package com.venkat.questionservice.bean;

import java.util.List;
import java.util.Objects;

public class QuestionBean {
	private String qid;
	private String operation;
	private List<Integer> numbers;
	private Integer answer;

	public QuestionBean() {
	}

	public QuestionBean(String qid, String operation, List<Integer> numbers, Integer answer) {
		super();
		this.qid = qid;
		this.operation = operation;
		this.numbers = numbers;
		this.answer = answer;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}

	public Integer getAnswer() {
		return answer;
	}

	public void setAnswer(Integer answer) {
		this.answer = answer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(answer, numbers, operation, qid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionBean other = (QuestionBean) obj;
		return Objects.equals(answer, other.answer) && Objects.equals(numbers, other.numbers)
				&& Objects.equals(operation, other.operation) && Objects.equals(qid, other.qid);
	}

	@Override
	public String toString() {
		return "QuestionBean [qid=" + qid + ", operation=" + operation + ", numbers=" + numbers + ", answer=" + answer
				+ "]";
	}

}
