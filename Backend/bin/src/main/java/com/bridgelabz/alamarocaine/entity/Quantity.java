package com.bridgelabz.alamarocaine.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Quantity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long quantity_id;

	@Column
	private Long quantityOfPlat;

	@Column
	private Double totalprice;

	public long getQuantity_id() {
		return quantity_id;
	}

	public void setQuantity_id(long quantity_id) {
		this.quantity_id = quantity_id;
	}

	public Long getQuantityOfPlat() {
		return quantityOfPlat;
	}

	public void setQuantityOfPlat(Long quantityOfPlat) {
		this.quantityOfPlat = quantityOfPlat;
	}

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
