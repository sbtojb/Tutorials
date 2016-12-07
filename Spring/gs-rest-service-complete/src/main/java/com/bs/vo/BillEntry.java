package com.bs.vo;

public class BillEntry {

	private String itemName;
	
	private double itemPrice;
	
	private int quantity;
	
	private double salesTax;
	
	private double itemPriceTotal;
	
	private double salesTaxTotal;

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}

	public double getItemPriceTotal() {
		return itemPriceTotal;
	}

	public void setItemPriceTotal(double itemPriceTotal) {
		this.itemPriceTotal = itemPriceTotal;
	}

	public double getSalesTaxTotal() {
		return salesTaxTotal;
	}

	public void setSalesTaxTotal(double salesTaxTotal) {
		this.salesTaxTotal = salesTaxTotal;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
}
