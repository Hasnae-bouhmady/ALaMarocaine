package com.bridgelabz.alamarocaine.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EditPlatDto {

	/*
	 * private Long platId; private Long userId; private LocalDateTimen
	 * createdDateAndTime; private String status = "Approved";
	 * 
	 * ces attributs de plat.java ne sonnt pas utilisés ici
	 * 
	 * 
	 * 
	 */

	private String platName;
	private Long noOfPlats;
	private Double price;
	private String chefName;
	private String image;
	private String platDescription;
	private LocalDateTime updatedAt;

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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
