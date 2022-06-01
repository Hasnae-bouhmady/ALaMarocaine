/** Chef data model class corresponds to entity and table chefs. **/

package com.bridgelabz.alamarocaine.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // it indicates that the class is a persistent Java class
@Table(name = "chef") // @Table annotation provides the table that maps this entity
public class Chef {
	// 10
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue annotation is used to define generation
	private long chefId; /// platId // strategy

	private Long userId; /// userId
	private String chefName;/// platName
	private String chefPrenom;/// chefName
	private String origine;/// price (long)
	private String specialite;/// platDescription
	private String image;/// image
	private boolean chefDeSemaine;
	private LocalDateTime createdDateAndTime;/// createdDateAndTime
	private LocalDateTime updatedDateAndTime;/// updatedDateAndTime

	public long getChefId() {
		return chefId;
	}

	public void setChefId(long chefId) {
		this.chefId = chefId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getChefName() {
		return chefName;
	}

	public void setChefName(String chefName) {
		this.chefName = chefName;
	}

	public String getChefPrenom() {
		return chefPrenom;
	}

	public void setChefPrenom(String chefPrenom) {
		this.chefPrenom = chefPrenom;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isChefDeSemaine() {
		return chefDeSemaine;
	}

	public void setChefDeSemaine(boolean chefDeSemaine) {
		this.chefDeSemaine = chefDeSemaine;
	}

	public LocalDateTime getCreatedDateAndTime() {
		return createdDateAndTime;
	}

	public void setCreatedDateAndTime(LocalDateTime createdDateAndTime) {
		this.createdDateAndTime = createdDateAndTime;
	}

	public LocalDateTime getUpdatedDateAndTime() {
		return updatedDateAndTime;
	}

	public void setUpdatedDateAndTime(LocalDateTime updatedDateAndTime) {
		this.updatedDateAndTime = updatedDateAndTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
