package com.bs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	public Item(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@Column(name="ITEM_NAME")
	private String itemName;

	@ManyToOne(optional=false)
    @JoinColumn(name="categary", nullable=false, updatable=false)
	private SalesTax salesTax;
	
	@NotNull
	private double price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public SalesTax getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(SalesTax salesTax) {
		this.salesTax = salesTax;
	}
}