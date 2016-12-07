package com.bs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.dao.ItemDAO;
import com.bs.entity.Item;
import com.bs.vo.Bill;
import com.bs.vo.BillEntry;
import com.bs.vo.SelectedItem;

@Service
@SuppressWarnings("unchecked")
@Transactional
public class BillGenerationService {

	@Autowired
	private ItemDAO itemDAO;

	@Transactional(readOnly = true)
	public Bill generateBill(List<SelectedItem> selectedItems) {
		Map<Integer, Integer> itemQuantityMap = getItemQuantityMap(selectedItems);
		List<Item> items = itemDAO.getSelectedItems(selectedItems);
		return calculateAndGenerateBill(items, itemQuantityMap);
	}

	private Bill calculateAndGenerateBill(List<Item> items, Map<Integer, Integer> itemQuantityMap) {
		final Bill bill = new Bill();
		List<BillEntry> billEntries = new ArrayList<>();
		bill.setBillEntries(billEntries);
		double allItemsPrice = 0;
		double allItemsSalesTax = 0;
		for (Item item : items) {
			BillEntry entry = new BillEntry();
			entry.setItemName(item.getItemName());
			entry.setItemPrice(item.getPrice());
			entry.setQuantity(itemQuantityMap.get(item.getId()));
			entry.setSalesTax(item.getPrice() * item.getSalesTax().getPercentageTax() / 100);
			entry.setItemPriceTotal(item.getPrice() * entry.getQuantity());
			entry.setSalesTaxTotal(entry.getSalesTax() * entry.getQuantity());
			allItemsPrice += entry.getItemPriceTotal();
			allItemsSalesTax += entry.getSalesTaxTotal();
			billEntries.add(entry);
		}
		bill.setAllItemPrice(allItemsPrice);
		bill.setAllItemsSalesTax(allItemsSalesTax);
		bill.setTotal(bill.getAllItemPrice() + bill.getAllItemsSalesTax());
		return bill;
	}

	private Map<Integer, Integer> getItemQuantityMap(List<SelectedItem> items) {
		Map<Integer, Integer> quantityMap = new HashMap<>();
		for (SelectedItem selectedItem : items) {
			quantityMap.put(selectedItem.getItemId(), selectedItem.getQuantity());
		}
		return quantityMap;
	}
}
