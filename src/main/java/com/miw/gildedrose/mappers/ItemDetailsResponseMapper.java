package com.miw.gildedrose.mappers;

import org.springframework.stereotype.Component;

import com.miw.gildedrose.entities.Item;
import com.miw.gilderose.response.ItemResponse;

@Component
public class ItemDetailsResponseMapper implements Mapper<Item, ItemResponse> {

	@Override
	public ItemResponse map(Item entity) {
		return ItemResponse.builder().description(entity.getDescription()).name(entity.getName())
				.itemNo(entity.getItemNo()).noOfitemsInStock(entity.getNoOfitemsInStock()).price(entity.getPrice())
				.build();
	}

}
