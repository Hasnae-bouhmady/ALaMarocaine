package com.bridgelabz.alamarocaine.response;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bridgelabz.alamarocaine.entity.Chef;

@Component
public class ChefResponse {

	Chef chef;

	private Object obj;
	double rate;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	int statusCode;
	String response;
	List<Chef> chefList;

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public List<Chef> getChefList() {
		return chefList;
	}

	public void setChefList(List<Chef> chefList) {
		this.chefList = chefList;
	}

	public ChefResponse() {

	}

//	public BookResponse(String response, List<Book> bookList) {
//		super();
//
//		this.response = response;
//		this.bookList = bookList;
//	}

	public ChefResponse(String response, Object obj) {
		super();
		this.obj = obj;

		this.response = response;
	}

	public ChefResponse(int statusCode, String response) {
		super();
		this.statusCode = statusCode;
		this.response = response;
	}

}