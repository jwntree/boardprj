package com.co.spring02.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReplyVO {
	private int rno;
	private int bno;
	private String content;
	private String writer;
	private String writerId;
	private boolean loginUser;
	@JsonIgnore
	private String password;
	private Date regDate;
	private Date updateDate;
	private boolean mine;
	private String deleted;
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
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
	public boolean isLoginUser() {
		return loginUser;
	}
	public void setLoginUser(boolean loginUser) {
		this.loginUser = loginUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public boolean isMine() {
		return mine;
	}
	public void setMine(boolean mine) {
		this.mine = mine;
	}
	
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	@Override
	public String toString() {
		return "ReplyVO [rno=" + rno + ", bno=" + bno + ", content=" + content + ", writer=" + writer + ", writerId="
				+ writerId + ", loginUser=" + loginUser + ", password=" + password + ", regDate=" + regDate
				+ ", updateDate=" + updateDate + ", mine=" + mine + ", deleted=" + deleted + "]";
	}

	
	
	
}
