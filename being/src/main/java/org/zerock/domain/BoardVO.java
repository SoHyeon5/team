package org.zerock.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

public class BoardVO {
	private Integer num;
	private String name;
	private String title;
	private String contentof;
	private Date registDay;
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentof() {
		return contentof;
	}
	public void setContentof(String contentof) {
		this.contentof = contentof;
	}
	public Date getRegistDay() {
		return registDay;
	}
	public void setRegistDay(Date registDay) {
		this.registDay = registDay;
	}
	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", name=" + name + ", title=" + title + ", contentof=" + contentof + ", readCount="
				 + ", writeDate=" + registDay + "]";
	}

}
