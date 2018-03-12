package com.cainew.vo;

import java.io.Serializable;
/**
 * 后台登录封装的POJO
 * @author Administrator
 * @version 2018-1-22 16:00:07
 */
public class SystemLogin implements Serializable {
	private String username;
	private String password;
	private String imgCode;

	@Override
	public String toString() {
		return "SystemLogin [username=" + username + ", password=" + password + ", imgCode=" + imgCode + "]";
	}

	public String getImgCode() {
		return imgCode;
	}

	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
