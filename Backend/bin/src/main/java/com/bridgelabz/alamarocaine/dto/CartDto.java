package com.bridgelabz.alamarocaine.dto;

import lombok.Data;

@Data
public class CartDto {

	private Long quantityId;
	private Long quantityOfPlat;
	private Double eachPrice;

	public Long getQuantityId() {
		return quantityId;
	}

	public void setQuantityId(Long quantityId) {
		this.quantityId = quantityId;
	}

	public Long getQuantityOfPlat() {
		return quantityOfPlat;
	}

	public void setQuantityOfPlat(Long quantityOfPlat) {
		this.quantityOfPlat = quantityOfPlat;
	}

	public Double getEachPrice() {
		return eachPrice;
	}

	public void setEachPrice(Double eachPrice) {
		this.eachPrice = eachPrice;
	}

	// @ApiModelProperty(notes = "The TotalPrice of the Book", required = true)//
	// for swagger property mention...
}
