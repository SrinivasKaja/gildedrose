package com.miw.gildedrose.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miw.gildedrose.request.BuyItemRequest;
import com.miw.gildedrose.service.ItemDetailsService;
import com.miw.gilderose.response.BaseResponse;
import com.miw.gilderose.response.ItemResponse;

@RestController
@RequestMapping("/api/gildedrose")
public class ItemDetailsController {
	
	@Autowired
	private ItemDetailsService itemDetailsService;
	
	@GetMapping("/getitems")
	public BaseResponse<ItemResponse> getItems() {
		return itemDetailsService.getItems();
	}
	
	@GetMapping("/viewitem")
	public BaseResponse<ItemResponse> viewItem(@RequestParam(name = "itemNo") final String itemNo) {
		return itemDetailsService.viewItem(itemNo);
	}
	
	@PostMapping(value = "/buyitem", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public BaseResponse<ItemResponse> buyItem(@RequestBody BuyItemRequest itemNo) {
		return itemDetailsService.buyItem(itemNo.getItemNo());
	}

}
