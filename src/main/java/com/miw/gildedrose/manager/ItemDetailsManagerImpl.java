package com.miw.gildedrose.manager;

import java.text.DecimalFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.miw.gildedrose.entities.Item;
import com.miw.gildedrose.repository.ItemDetailsRepository;

@Component
public class ItemDetailsManagerImpl implements ItemDetailsManager {

	@Autowired
	private ItemDetailsRepository itemDetailsRepository;

	@Override
	@Transactional
	public List<Item> getItems() {
		return itemDetailsRepository.findAll();
	}

	@Override
	@Transactional
	public Item viewItem(String itemNo) {
		Item item = itemDetailsRepository.queryByItemNo(itemNo);
		if (null != item) {
			Integer count = item.getNoOfViews();
			if (null != count) {
				item.setNoOfViews(count + 1);
				itemDetailsRepository.saveAndFlush(item);
			}
		}
		return item;
	}

	@Override
	@Transactional
	public void updatePriceEvryHour() {
		List<Item> itemList = itemDetailsRepository.findAll();
		if (itemList != null && itemList.size() > 0) {
			itemList.stream().filter(i -> (i.getNoOfViews() > 10 && i.getStatus().equalsIgnoreCase("Available")))
					.forEach(i -> {
						String newPrice = new DecimalFormat("##.##").format(i.getPrice() / 10);
						i.setPrice(i.getPrice() + Double.parseDouble(newPrice));
						i.setNoOfViews(0);
						itemDetailsRepository.saveAndFlush(i);
					});
		}
	}

	@Override
	@Transactional
	public Item buyItem(String itemNo) {
		Item item = itemDetailsRepository.queryByItemNo(itemNo);
		if (null != item) {
			Integer count = item.getNoOfitemsInStock();
			if (null != count) {
				if (count == 1) {
					item.setNoOfitemsInStock(0);
					item.setStatus("Sold Out");
				} else {
					item.setNoOfitemsInStock(count - 1);
				}
				itemDetailsRepository.saveAndFlush(item);
			}
		}
		return item;
	}

}
