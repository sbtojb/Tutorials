package com.bs.vo;

public class SelectedItem {

	private int itemId;
	
	private int quantity;

	public SelectedItem(){
		
	}
	
	public SelectedItem(int itemId, int quantity) {
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
