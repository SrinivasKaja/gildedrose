package com.miw.gildedrose.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.miw.gildedrose.entities.Item;
import com.miw.gildedrose.enums.StatusCode;
import com.miw.gildedrose.manager.ItemDetailsManager;
import com.miw.gildedrose.mappers.ItemDetailsResponseMapper;
import com.miw.gildedrose.response.BaseResponse;
import com.miw.gildedrose.response.ItemResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemDetailsServiceTest {

	@Autowired
	private ItemDetailsService itemDetailsService;

	@MockBean
	private ItemDetailsManager itemDetailsManager;
	@MockBean
	private ItemDetailsResponseMapper itemDetailsResponseMapper;
	private List<Item> itemList;
	private Item item;

	@Before
	public void init() {
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
		itemList.add(item);
		itemList.add(i2);

	}

	@Test
	public void getItemsTest() {
		when(itemDetailsManager.getItems()).thenReturn(itemList);
		when(itemDetailsResponseMapper.map(Mockito.any(Item.class))).thenReturn(ItemResponse.builder()
				.description("Reebok Shoes").name("Reebok").itemNo("#34234").price(199.00).build());
		BaseResponse<ItemResponse> baseResponse = itemDetailsService.getItems();
		assertNotNull(baseResponse);
		assertEquals(StatusCode.SC_OK.getCode(), baseResponse.getStatusCode());
		assertEquals(StatusCode.SC_OK.getMessage(), baseResponse.getStatusDescription());
		List<ItemResponse> itemResponseList = baseResponse.getItems();
		assertNotNull(itemResponseList);
		assertEquals(2, itemResponseList.size());
		assertEquals(199.00, itemResponseList.get(0).getPrice());

	}

	@Test
	public void getItemsTestWithEmptyData() {
		when(itemDetailsManager.getItems()).thenReturn(new ArrayList<>());
		BaseResponse<ItemResponse> baseResponse = itemDetailsService.getItems();
		assertNotNull(baseResponse);
		assertEquals(StatusCode.NOT_FOUND.getCode(), baseResponse.getStatusCode());
		assertEquals(StatusCode.NOT_FOUND.getMessage(), baseResponse.getStatusDescription());
		assertEquals(0, baseResponse.getItems().size());
	}

	@Test
	public void getItemsTestWithNullData() {
		when(itemDetailsManager.getItems()).thenReturn(null);
		BaseResponse<ItemResponse> baseResponse = itemDetailsService.getItems();
		assertNotNull(baseResponse);
		assertEquals(StatusCode.NOT_FOUND.getCode(), baseResponse.getStatusCode());
		assertEquals(StatusCode.NOT_FOUND.getMessage(), baseResponse.getStatusDescription());
		assertEquals(0, baseResponse.getItems().size());
	}

	@Test
	public void viewItemTest() {
		when(itemDetailsManager.viewItem("#12121")).thenReturn(item);
		BaseResponse<ItemResponse> baseResponse = itemDetailsService.viewItem("#12121");
		assertNotNull(baseResponse);
		assertEquals(StatusCode.SC_OK.getCode(), baseResponse.getStatusCode());
		assertEquals(StatusCode.SC_OK.getMessage(), baseResponse.getStatusDescription());
		assertNotNull(baseResponse.getItem());

	}

	@Test
	public void viewItemTestWithEmptyData() {
		when(itemDetailsManager.viewItem("#12121")).thenReturn(new Item());
		BaseResponse<ItemResponse> baseResponse = itemDetailsService.viewItem("#12121");
		assertNotNull(baseResponse);
		assertEquals(StatusCode.NOT_FOUND.getCode(), baseResponse.getStatusCode());
		assertEquals(StatusCode.NOT_FOUND.getMessage(), baseResponse.getStatusDescription());
		assertNull(baseResponse.getItem());

	}

	@Test
	public void viewItemTestWithNullData() {
		when(itemDetailsManager.viewItem("#12121")).thenReturn(null);
		BaseResponse<ItemResponse> baseResponse = itemDetailsService.viewItem("#12121");
		assertNotNull(baseResponse);
		assertEquals(StatusCode.NOT_FOUND.getCode(), baseResponse.getStatusCode());
		assertEquals(StatusCode.NOT_FOUND.getMessage(), baseResponse.getStatusDescription());
		assertNull(baseResponse.getItem());
	}

	@Test
	public void buyItemTest() {
		when(itemDetailsManager.buyItem("#12121")).thenReturn(item);
		BaseResponse<ItemResponse> baseResponse = itemDetailsService.buyItem("#12121");
		assertNotNull(baseResponse);
		assertEquals(StatusCode.SC_OK.getCode(), baseResponse.getStatusCode());
		assertEquals(StatusCode.SC_OK.getMessage(), baseResponse.getStatusDescription());
		assertNotNull(baseResponse.getItem());
	}
	
	@Test
	public void buyItemTestWithEmptyData() {
		when(itemDetailsManager.buyItem("#12121")).thenReturn(new Item());
		BaseResponse<ItemResponse> baseResponse = itemDetailsService.buyItem("#12121");
		assertNotNull(baseResponse);
		assertEquals(StatusCode.NOT_FOUND.getCode(), baseResponse.getStatusCode());
		assertEquals(StatusCode.NOT_FOUND.getMessage(), baseResponse.getStatusDescription());
		assertNull(baseResponse.getItem());
	}
	
	@Test
	public void buyItemTestWithNullData() {
		when(itemDetailsManager.buyItem("#12121")).thenReturn(null);
		BaseResponse<ItemResponse> baseResponse = itemDetailsService.buyItem("#12121");
		assertNotNull(baseResponse);
		assertEquals(StatusCode.NOT_FOUND.getCode(), baseResponse.getStatusCode());
		assertEquals(StatusCode.NOT_FOUND.getMessage(), baseResponse.getStatusDescription());
		assertNull(baseResponse.getItem());
	}
}
