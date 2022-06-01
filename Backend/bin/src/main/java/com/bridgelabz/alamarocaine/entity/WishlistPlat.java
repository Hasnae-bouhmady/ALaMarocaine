package com.bridgelabz.alamarocaine.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "wishplat")
public class WishlistPlat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long wishlistId;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Plat> platsList;

	private LocalDateTime wishlistTime;

	public long getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(long wishlistId) {
		this.wishlistId = wishlistId;
	}

	public List<Plat> getPlatsList() {
		return platsList;
	}

	public void setPlatsList(List<Plat> platsList) {
		this.platsList = platsList;
	}

	public LocalDateTime getWishlistTime() {
		return wishlistTime;
	}

	public void setWishlistTime(LocalDateTime wishlistTime) {
		this.wishlistTime = wishlistTime;
	}
}
