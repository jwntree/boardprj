package com.co.spring02.vo;

//ResponseEntity가 있는데 이걸 사용할 이유가 있나?
public class ResponseDto<T> {
	private int status;
	private T data;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
    
}