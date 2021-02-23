package com.miw.gildedrose.manager;

import java.util.List;

import com.miw.gildedrose.entities.Item;

public interface ItemDetailsManager {
	
	List<Item> getItems();

	void updatePriceEvryHour();

	Item viewItem(String itemNo);

	Item buyItem(String itemNo);
}
