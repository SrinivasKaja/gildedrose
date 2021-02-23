package com.miw.gildedrose.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public final class BaseResponse<T> {
	private Integer statusCode;
	private String statusDescription;
	private final Integer count;
	private final List<T> items;
	private final T item;

	public BaseResponse(final Integer statusCode, final String statusDescription, final List<T> items) {
		this.statusCode = statusCode;
		this.statusDescription = statusDescription;
		this.count = items == null ? 0 : items.size();
		this.item = null;
		this.items = items;
	}


	public BaseResponse(final Integer statusCode, final String statusDescription, final T item) {
		this.statusCode = statusCode;
		this.statusDescription = statusDescription;
		this.count = null;
		this.item = item;
		this.items = null;
	}

	public BaseResponse(final Integer statusCode, final String statusDescription) {
		this.statusCode = statusCode;
		this.statusDescription = statusDescription;
		this.count = null;
		this.item = null;
		this.items = null;
	}

}
