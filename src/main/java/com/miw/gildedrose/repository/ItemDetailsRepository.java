package com.miw.gildedrose.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.miw.gildedrose.entities.Item;

@Repository
public interface ItemDetailsRepository extends JpaRepository<Item, Long> {
	@Query(nativeQuery = true, value = "select it.* from item_details it where it.item_no=:item_no")
	Item queryByItemNo(@Param("item_no") String itemNo);
}
