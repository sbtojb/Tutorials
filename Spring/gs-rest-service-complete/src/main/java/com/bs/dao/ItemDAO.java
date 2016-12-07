package com.bs.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bs.entity.Item;
import com.bs.vo.SelectedItem;

@Repository
@SuppressWarnings("unchecked")
@Transactional
public class ItemDAO {

    @PersistenceContext
    private EntityManager em;



    @Transactional(readOnly = true)
    public List<Item> getAllItems() {
        return em.createQuery("FROM " + Item.class.getName()).getResultList();
    }

	public List<Item> getSelectedItems(List<SelectedItem> selectedItems) {
		return em.createQuery("FROM " + Item.class.getName() + " item where item.id IN :selectedList")
				.setParameter("selectedList", selectedItems.stream().map(item->item.getItemId()).collect(Collectors.toList()))
				.getResultList();
		
	}

}
