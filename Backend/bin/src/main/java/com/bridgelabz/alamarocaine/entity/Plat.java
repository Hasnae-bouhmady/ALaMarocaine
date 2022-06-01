package com.bridgelabz.alamarocaine.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "plat")
public class Plat implements Serializable {
	// 11
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long platId;///

	private Long userId; /// // id de l'admin(==seller) sera toujours le même
	private String platName;///
	private Long noOfPlats;
	private Double price;///
	private String chefName;///
	@Column(columnDefinition = "TEXT")
	private String platDescription;///
	private LocalDateTime createdDateAndTime;///
	private LocalDateTime updatedDateAndTime;///
	private String status = "Approved"; // h à supprimer le plat n'a pas besoin d'être approuvé,il est ajouté par
										// l'admin
	private String image;///

	public Long getPlatId() {
		return platId;
	}

	public void setPlatId(Long platId) {
		this.platId = platId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public String getPlatDescription() {
		return platDescription;
	}

	public void setPlatDescription(String platDescription) {
		this.platDescription = platDescription;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}