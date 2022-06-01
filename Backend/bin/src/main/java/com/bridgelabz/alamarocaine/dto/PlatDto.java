package com.bridgelabz.alamarocaine.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class PlatDto {
	// 7
	private String platName;
	private Long noOfPlats;
	private Double price;
	private String chefName;
	private String image;
	private String platDescription;
	private String userId;

	public String getPlatName() {
		return platName;
	}

	public void setPlatName(String platName) {
		this.platName = platName;
	}

	public Long getNoOfPlats() {
		return noOfPlats;
	}

	public void setNoOfPlats(Long noOfPlats) {
		this.noOfPlats = noOfPlats;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getChefName() {
		return chefName;
	}

	public void setChefName(String chefName) {
		this.chefName = chefName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPlatDescription() {
		return platDescription;
	}

	public void setPlatDescription(String platDetails) {
		this.platDescription = platDetails;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}