package com.dhcircle.dhCircleWebApp.response;

import java.util.List;

import com.dhcircle.dhCircleWebApp.model.user.User;

public class AjaxResponseBody {
	private String message;
	private String status;
    private List<User> data;
    private User user;
    
    public AjaxResponseBody() {}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<User> getResult() {
		return data;
	}
	public void setResult(List<User> result) {
		this.data = result;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "AjaxResponseBody [message=" + message + ", status=" + status + ", data=" + data + ", user=" + user
				+ "]";
	}
	
    
}
