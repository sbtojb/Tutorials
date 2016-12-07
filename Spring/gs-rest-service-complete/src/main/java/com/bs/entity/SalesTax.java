package com.bs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="sales_tax")
public class SalesTax {

	public SalesTax(){}
	
	@Id
	private String categary;
	
	@Column(name="PERCENTAGE_TAX")
	private int percentageTax;

	public String getCategary() {
		return categary;
	}

	public void setCategary(String categary) {
		this.categary = categary;
	}

	public int getPercentageTax() {
		return percentageTax;
	}

	public void setPercentageTax(int percentageTax) {
		this.percentageTax = percentageTax;
	}
}
