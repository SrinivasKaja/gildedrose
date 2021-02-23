package com.miw.gildedrose.manager;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.miw.gildedrose.entities.Item;
import com.miw.gildedrose.repository.ItemDetailsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemDetailsManagerImplTest {
	@Autowired
	private ItemDetailsManager itemDetailsManager ;
	
	@MockBean
	private ItemDetailsRepository itemDetailsRepository;
	
	private List<Item> itemList;
	private Item item;
	
	@Before
	public void init()
	{
		itemList = new ArrayList<>();
		item = new Item();
		item.setName("Reebok");
		item.setDescription("Reebok shoes");
		item.setItemNo("#56RE789");
		item.setNoOfitemsInStock(20);
		item.setNoOfViews(0);
		item.setStatus("Available");
		item.setPrice(199.00);
		Item i2 = new Item();
		i2.setName("Adidas");
		i2.setDescription("Adidas shoes");
		i2.setItemNo("#5ADS789");
		i2.setNoOfitemsInStock(90);
		i2.setNoOfViews(11);
		i2.setPrice(399.00);
		i2.setStatus("Available");
		itemList.add(item);
		itemList.add(i2);
	}	

	@Test
	public void getItemsWithData() {
		when(itemDetailsRepository.findAll()).thenReturn(itemList);
		List<Item> itemList = itemDetailsManager.getItems();
		assertEquals(2, itemList.size());
		assertEquals(399.00, itemList.get(1).getPrice());
		assertEquals("Adidas", itemList.get(1).getName());
	}
	
	@Test
	public void getItemsWithEmptyData() {
		when(itemDetailsRepository.findAll()).thenReturn(new ArrayList<>());
		List<Item> itemList = itemDetailsManager.getItems();
		assertNotNull(itemList);
		assertEquals(0, itemList.size());
	}
	
	@Test
	public void getItemsWithNullData() {
		when(itemDetailsRepository.findAll()).thenReturn(null);
		List<Item> itemList = itemDetailsManager.getItems();
		assertNull(itemList);
	}
	
	@Test
	public void viewItemsWithEmptyData() {
		when(itemDetailsRepository.queryByItemNo("#5ADS790")).thenReturn(new Item());
		Item item = itemDetailsManager.viewItem("#5ADS790");
		assertNotNull(item);
		assertEquals(null, item.getNoOfViews());
	}
	
	@Test
	public void viewItemsWithData() {
		when(itemDetailsRepository.queryByItemNo("#56RE789")).thenReturn(item);
		Item result = itemDetailsManager.viewItem("#56RE789");
		assertNotNull(result);
		assertEquals(this.item.getNoOfViews(), result.getNoOfViews());
	}
	
	@Test
	public void buyItemWithItemNo() {
		when(itemDetailsRepository.queryByItemNo("#56RE789")).thenReturn(item);
		Item item = itemDetailsManager.buyItem("#56RE789");
		assertNotNull(item);
		assertEquals("Reebok", item.getName());
		assertEquals("Available", item.getStatus());
		assertEquals(this.item.getNoOfitemsInStock(), item.getNoOfitemsInStock());
	}
	
	@Test
	public void buyItemWithEmptyItemData() {
		when(itemDetailsRepository.queryByItemNo("#56RE789")).thenReturn(new Item());
		Item item = itemDetailsManager.buyItem("#56RE789");
		assertNotNull(item);
		assertEquals(null, item.getStatus());
		assertEquals(null, item.getNoOfitemsInStock());
	}
	
	@Test
	public void buyItemWithOneItemInStock() {
		item.setNoOfitemsInStock(1);
		when(itemDetailsRepository.queryByItemNo("#56RE789")).thenReturn(item);
		Item item = itemDetailsManager.buyItem("#56RE789");
		assertNotNull(item);
		assertEquals("Reebok", item.getName());
		assertEquals("Sold Out", item.getStatus());
		assertEquals(this.item.getNoOfitemsInStock(), item.getNoOfitemsInStock());
	}
	
	@Test
	public void updatePriceEveryHourTest() {
		when(itemDetailsRepository.findAll()).thenReturn(itemList);
		itemDetailsManager.updatePriceEvryHour();
	}
	
	@Test
	public void updatePriceEveryHourTestWithEmptyData() {
		when(itemDetailsRepository.findAll()).thenReturn(new ArrayList<>());
		itemDetailsManager.updatePriceEvryHour();
	}
	
	@Test
	public void updatePriceEveryHourTestWithNullData() {
		when(itemDetailsRepository.findAll()).thenReturn(null);
		itemDetailsManager.updatePriceEvryHour();
	}
	
	
}
