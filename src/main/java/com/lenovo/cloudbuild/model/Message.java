package com.lenovo.cloudbuild.model;

import java.util.Calendar;

import javax.validation.constraints.NotEmpty;

public class Message {

	private Long id;
	@NotEmpty(message = "Text is required.")
	private String text;
	@NotEmpty(message = "Summary is required.")
	private String summary;
	private Calendar createTime = Calendar.getInstance();

	public Message(String text, String summary, Calendar createTime) {
		this.text = text;
		this.summary = summary;
		this.createTime = createTime;
	}

	public Message() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", text=" + text + ", summary=" + summary + ", createTime=" + createTime + "]";
	}

}
