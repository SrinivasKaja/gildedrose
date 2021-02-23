package com.miw.gildedrose.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = ItemResponse.ItemResponseBuilder.class)
@JsonInclude(Include.NON_NULL)
public class ItemResponse implements Serializable {
private String itemNo;
private String name;
private String description;
private Double price;
private Integer noOfitemsInStock;
}
