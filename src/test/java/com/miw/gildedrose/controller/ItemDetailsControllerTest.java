package com.miw.gildedrose.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miw.gildedrose.enums.StatusCode;
import com.miw.gildedrose.request.BuyItemRequest;
import com.miw.gildedrose.response.BaseResponse;
import com.miw.gildedrose.response.ItemResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemDetailsController.class)
public class ItemDetailsControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ItemDetailsController itemDetailsController;

	@Test
	public void getItemsTest() throws Exception {
		List<ItemResponse> itemList = new ArrayList<>();
		ItemResponse i1 = ItemResponse.builder().description("item1").name("Reebok").price(233.00).build();
		ItemResponse i2 = ItemResponse.builder().description("item2").name("Adidas").price(199.00).build();
		itemList.add(i1);
		itemList.add(i2);

		given(itemDetailsController.getItems())
				.willReturn(new BaseResponse<>(StatusCode.SC_OK.getCode(), StatusCode.SC_OK.getMessage(), itemList));
		mockMvc.perform(get("/api/gildedrose/getitems").with(user("admin").password("password")))
				.andExpect(status().isOk()).andExpect(jsonPath("$.items", Matchers.hasSize(2)))
				.andExpect(jsonPath("$.items[0].name", Matchers.is("Reebok")));
	}

	@Test
	public void getItemsTestWithEmptyData() throws Exception {
		List<ItemResponse> itemList = new ArrayList<>();
		given(itemDetailsController.getItems())
				.willReturn(new BaseResponse<>(StatusCode.SC_OK.getCode(), StatusCode.SC_OK.getMessage(), itemList));
		mockMvc.perform(get("/api/gildedrose/getitems").with(user("admin").password("password")))
				.andExpect(status().isOk()).andExpect(jsonPath("$.items", Matchers.hasSize(0)));
	}

	@Test
	public void getItemsTestWithNullData() throws Exception {
		given(itemDetailsController.getItems())
				.willReturn(new BaseResponse<>(StatusCode.SC_OK.getCode(), StatusCode.SC_OK.getMessage(), null));
		mockMvc.perform(get("/api/gildedrose/getitems").with(user("admin").password("password")))
				.andExpect(status().isOk()).andExpect(jsonPath("$.count", Matchers.is(0)))
				.andExpect(jsonPath("$.items").doesNotExist());
	}

	@Test
	public void viewItemTest() throws Exception {
		given(itemDetailsController.viewItem(Mockito.anyString())).willReturn(new BaseResponse<>(
				StatusCode.SC_OK.getCode(), StatusCode.SC_OK.getMessage(),
				ItemResponse.builder().itemNo("#123").description("item1").name("Reebok").price(233.00).build()));
		mockMvc.perform(
				get("/api/gildedrose/viewitem").param("itemNo", "#123").with(user("admin").password("password")))
				.andExpect(status().isOk()).andExpect(jsonPath("$.item.name", Matchers.is("Reebok")));

	}

	@Test
	public void viewItemTestWithEmptyData() throws Exception {
		given(itemDetailsController.viewItem(Mockito.anyString())).willReturn(new BaseResponse<>(
				StatusCode.SC_OK.getCode(), StatusCode.SC_OK.getMessage(), ItemResponse.builder().build()));
		mockMvc.perform(
				get("/api/gildedrose/viewitem").param("itemNo", "#123").with(user("admin").password("password")))
				.andExpect(status().isOk()).andExpect(jsonPath("$.item").isEmpty());

	}

	@Test
	public void viewItemTestWithNullData() throws Exception {
		given(itemDetailsController.viewItem(Mockito.anyString()))
				.willReturn(new BaseResponse<>(StatusCode.SC_OK.getCode(), StatusCode.SC_OK.getMessage()));
		mockMvc.perform(
				get("/api/gildedrose/viewitem").param("itemNo", "#123").with(user("admin").password("password")))
				.andExpect(status().isOk()).andExpect(jsonPath("$.item").doesNotExist());

	}

	@Test
	public void buyItemTest() throws Exception {
		BuyItemRequest buyItemRequest = BuyItemRequest.builder().itemNo("ADME80034").build();
		given(itemDetailsController.buyItem(Mockito.any(BuyItemRequest.class))).willReturn(new BaseResponse<>(
				StatusCode.SC_OK.getCode(), StatusCode.SC_OK.getMessage(),
				ItemResponse.builder().itemNo("ADME80034").description("item1").name("Reebok").price(233.00).build()));
		mockMvc.perform(post("/api/gildedrose/buyitem").content(mapToJson(buyItemRequest))
				 .contentType(MediaType.APPLICATION_JSON_VALUE).with(user("admin").password("password"))).andExpect(status().isOk())
				.andExpect(jsonPath("$.item.name", Matchers.is("Reebok")));

	}
	
	@Test
	public void buyItemTestWithEmptyData() throws Exception {
		BuyItemRequest buyItemRequest = BuyItemRequest.builder().itemNo("ADME80034").build();
		given(itemDetailsController.buyItem(Mockito.any(BuyItemRequest.class))).willReturn(new BaseResponse<>(
				StatusCode.SC_OK.getCode(), StatusCode.SC_OK.getMessage(),
				ItemResponse.builder().build()));
		mockMvc.perform(post("/api/gildedrose/buyitem").content(mapToJson(buyItemRequest))
				 .contentType(MediaType.APPLICATION_JSON_VALUE).with(user("admin").password("password"))).andExpect(status().isOk())
				.andExpect(jsonPath("$.item").isEmpty());

	}
	
	@Test
	public void buyItemTestWithNullData() throws Exception {
		BuyItemRequest buyItemRequest = BuyItemRequest.builder().itemNo("ADME80034").build();
		given(itemDetailsController.buyItem(Mockito.any(BuyItemRequest.class))).willReturn(new BaseResponse<>(
				StatusCode.SC_OK.getCode(), StatusCode.SC_OK.getMessage()));
		mockMvc.perform(post("/api/gildedrose/buyitem").content(mapToJson(buyItemRequest))
				 .contentType(MediaType.APPLICATION_JSON_VALUE).with(user("admin").password("password"))).andExpect(status().isOk())
				.andExpect(jsonPath("$.item").doesNotExist());

	}

	public String mapToJson(Object object) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
