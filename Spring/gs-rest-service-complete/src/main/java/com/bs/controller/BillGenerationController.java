package com.bs.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.service.BillGenerationService;
import com.bs.vo.Bill;
import com.bs.vo.BillEntry;
import com.bs.vo.SelectedItem;

@RestController
public class BillGenerationController {

    @Autowired  private BillGenerationService billGenerationService;

    
    @RequestMapping("/bill")
    public Bill getBill(@RequestBody ArrayList<SelectedItem> selection) {
    	Bill bill = billGenerationService.generateBill(selection);
    	printBill(bill);
        return bill;
    }

	private void printBill(Bill bill) {
		if(bill != null && bill.getBillEntries() != null){
//			System.out.println("No\t Item Name\t Item Price\t Quantity\t Total Item Price\t Item Sales Tax");
			System.out.println("*******************************************************Bill******************************************************");
			int i=1;
			for (BillEntry entry : bill.getBillEntries()) {
				System.out.println("No:"+(i++)+"\t Item Name:"+entry.getItemName()+"\t Item Price:"+entry.getItemPrice()+"\t Quantity:"+entry.getQuantity()+"\t Total Item Price:"+entry.getItemPriceTotal()+"\t Item Sales Tax:"+entry.getSalesTaxTotal());
//				System.out.println(i+"\t "+entry.getItemName()+"\t "+entry.getItemPrice()+"\t "+entry.getQuantity()+"\t "+entry.getItemPriceTotal()+"\t "+entry.getSalesTaxTotal());
			}
			System.out.println("All items price: "+bill.getAllItemPrice());
			System.out.println("All items sales tax: "+bill.getAllItemsSalesTax());
			System.out.println("Final Total: "+bill.getTotal());
			System.out.println("******************************************************Thank You***************************************************");
		}
	}
}
