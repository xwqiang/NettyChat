package io.netty.example.util;

import java.io.Serializable;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int sn;
	private String name;
	public UserInfo(){
	}
	public UserInfo(String name){
		this.name = name;
	}
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString(){
		return "user: "+name;
	}
	
}
