package com.miw.gildedrose.service;

import com.miw.gildedrose.response.BaseResponse;
import com.miw.gildedrose.response.ItemResponse;

public interface ItemDetailsService {
	
public BaseResponse<ItemResponse> getItems();

public BaseResponse<ItemResponse> viewItem(String itemNo);

public BaseResponse<ItemResponse> buyItem(String itemNo);
}
