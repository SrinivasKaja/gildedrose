package com.miw.gildedrose.request;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonDeserialize(builder = BuyItemRequest.BuyItemRequestBuilder.class)
public class BuyItemRequest implements Serializable {
	private String itemNo;
}
