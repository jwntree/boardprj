package com.co.spring02.vo;

import java.util.Date;

public class BoardVO {
	int bno;
	String title;
	String content;
	String writer;
	String writerId;
	String password;
	Date regdate;
	int viewcnt;
	boolean loginUser;
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public boolean isLoginUser() {
		return loginUser;
	}
	public void setLoginUser(boolean loginUser) {
		this.loginUser = loginUser;
	}
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", writerId=" + writerId + ", password=" + password + ", regdate=" + regdate + ", viewcnt=" + viewcnt
				+ ", loginUser=" + loginUser + "]";
	}

}
