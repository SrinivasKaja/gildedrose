package com.miw.gildedrose.mappers;

public interface Mapper<S, T> {
	T map(S entity);
}
