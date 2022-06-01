package com.bridgelabz.alamarocaine.response;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bridgelabz.alamarocaine.entity.Plat;

@Component
public class PlatResponse {
	Plat plat;

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
	List<Plat> platList;

	public Plat getPlat() {
		return plat;
	}

	public void setPlat(Plat plat) {
		this.plat = plat;
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

	public List<Plat> getPlatList() {
		return platList;
	}

	public void setPlatList(List<Plat> platList) {
		this.platList = platList;
	}

	public PlatResponse() {

	}

//	public BookResponse(String response, List<Book> bookList) {
//		super();
//
//		this.response = response;
//		this.bookList = bookList;
//	}

	public PlatResponse(String response, Object obj) {
		super();
		this.obj = obj;

		this.response = response;
	}

	public PlatResponse(int statusCode, String response) {
		super();
		this.statusCode = statusCode;
		this.response = response;
	}

}