package com.miw.gildedrose.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miw.gildedrose.entities.Item;
import com.miw.gildedrose.enums.StatusCode;
import com.miw.gildedrose.manager.ItemDetailsManager;
import com.miw.gildedrose.mappers.ItemDetailsResponseMapper;
import com.miw.gilderose.response.BaseResponse;
import com.miw.gilderose.response.ItemResponse;

@Service
public class ItemDetailsServiceImpl implements ItemDetailsService {

	@Autowired
	private ItemDetailsManager itemDetailsManager;

	@Autowired
	private ItemDetailsResponseMapper itemDetailsResponseMapper;

	@Override
	public BaseResponse<ItemResponse> getItems() {
		List<Item> itemList = itemDetailsManager.getItems();
		if (itemList != null && itemList.size() >0) {
			List<ItemResponse> itemResponseList = itemList.stream().map(itemDetailsResponseMapper::map)
					.collect(Collectors.toList());
			return new BaseResponse<>(StatusCode.SC_OK.getCode(), StatusCode.SC_OK.getMessage(), itemResponseList);
		} else {
			return new BaseResponse<>(StatusCode.NOT_FOUND.getCode(), StatusCode.NOT_FOUND.getMessage(), new ArrayList<>());
		}
	}

	@Override
	public BaseResponse<ItemResponse> viewItem(String itemNo) {
		Item item = itemDetailsManager.viewItem(itemNo);
		if (item != null && item.getItemNo() !=null) {
			return new BaseResponse<>(StatusCode.SC_OK.getCode(), StatusCode.SC_OK.getMessage(),
					ItemResponse.builder().description(item.getDescription()).name(item.getName())
							.itemNo(item.getItemNo()).noOfitemsInStock(item.getNoOfitemsInStock())
							.price(item.getPrice()).build());
		} else {
			return new BaseResponse<>(StatusCode.NOT_FOUND.getCode(), StatusCode.NOT_FOUND.getMessage());
		}
	}

	@Override
	public BaseResponse<ItemResponse> buyItem(String itemNo) {
		Item item = itemDetailsManager.buyItem(itemNo);
		if(item != null && item.getItemNo() !=null) {
			return new BaseResponse<>(StatusCode.SC_OK.getCode(), StatusCode.SC_OK.getMessage(), ItemResponse.builder().description(item.getDescription()).name(item.getName())
					.itemNo(item.getItemNo()).noOfitemsInStock(item.getNoOfitemsInStock())
					.price(item.getPrice()).build());
		}
		else {
			return new BaseResponse<>(StatusCode.NOT_FOUND.getCode(), StatusCode.NOT_FOUND.getMessage());
		}
	}
}
