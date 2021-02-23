package com.miw.gildedrose.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyItemRequest implements Serializable {
	private String itemNo;
}
