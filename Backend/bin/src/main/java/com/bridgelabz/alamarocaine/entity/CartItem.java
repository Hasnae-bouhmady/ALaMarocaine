package com.bridgelabz.alamarocaine.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class CartItem implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Plat> platsList;

	/**
	 * one-to-many mapping means that one row in a table is mapped to multiple rows
	 * in another table.
	 */

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Quantity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "cartId")
	private List<Quantity> quantityOfPlat;
	/**
	 * 
	 * CartItem (the owner entity) has a join column cartId that stores the id value
	 * and has a foreign key to the Quantity entity.
	 * 
	 */

	private LocalDateTime createdTime;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public List<Plat> getPlatsList() {
		return platsList;
	}

	public void setPlatsList(List<Plat> platsList) {
		this.platsList = platsList;
	}

	public List<Quantity> getQuantityOfPlat() {
		return quantityOfPlat;
	}

	public void setQuantityOfPlat(List<Quantity> quantityOfPlat) {
		this.quantityOfPlat = quantityOfPlat;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}