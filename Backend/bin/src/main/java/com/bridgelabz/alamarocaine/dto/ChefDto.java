package com.bridgelabz.alamarocaine.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ChefDto {
	// 7
	private String chefName;

	private String origine;
	private String chefPrenom;
	private String image;
	private String specialite;
	private boolean chefDeSemaine;
	private String userId;

	public String getChefName() {
		return chefName;
	}

	public void setChefName(String chefName) {
		this.chefName = chefName;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getChefPrenom() {
		return chefPrenom;
	}

	public void setChefPrenom(String chefPrenom) {
		this.chefPrenom = chefPrenom;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isChefDeSemaine() {
		return chefDeSemaine;
	}

	public void setChefDeSemaine(boolean chefDeSemaine) {
		this.chefDeSemaine = chefDeSemaine;
	}

}