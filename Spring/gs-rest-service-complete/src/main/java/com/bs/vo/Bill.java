package com.bs.vo;

import java.util.List;

public class Bill {

	private List<BillEntry> billEntries;
	
	private double allItemPrice;
	
	private double allItemsSalesTax;
	
	private double total;

	public List<BillEntry> getBillEntries() {
		return billEntries;
	}

	public void setBillEntries(List<BillEntry> billEntries) {
		this.billEntries = billEntries;
	}

	public double getAllItemPrice() {
		return allItemPrice;
	}

	public void setAllItemPrice(double allItemPrice) {
		this.allItemPrice = allItemPrice;
	}

	public double getAllItemsSalesTax() {
		return allItemsSalesTax;
	}

	public void setAllItemsSalesTax(double allItemsSalesTax) {
		this.allItemsSalesTax = allItemsSalesTax;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
