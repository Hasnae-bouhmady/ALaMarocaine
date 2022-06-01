package com.bridgelabz.alamarocaine.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EditChefDto {

	/*
	 * private Long platId; private Long userId; private LocalDateTimen
	 * createdDateAndTime; private String status = "Approved";
	 * 
	 * ces attributs de plat.java ne sonnt pas utilisés ici
	 * 
	 * 
	 * 
	 */

	private String chefName;

	private String origine;
	private String chefPrenom;
	private String image;
	private String specialite;
	private boolean chefDeSemaine;
	private LocalDateTime updatedAt;

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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isChefDeSemaine() {
		return chefDeSemaine;
	}

	public void setChefDeSemaine(boolean chefDeSemaine) {
		this.chefDeSemaine = chefDeSemaine;
	}

}
