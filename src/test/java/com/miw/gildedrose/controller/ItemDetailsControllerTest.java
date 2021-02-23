package com.miw.gildedrose.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.miw.gildedrose.enums.StatusCode;
import com.miw.gildedrose.response.BaseResponse;
import com.miw.gildedrose.response.ItemResponse;

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
		mockMvc.perform(get("/api/gildedrose/getitems").with(user("user").password("password")))
				.andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[1].name", Matchers.equalTo("Reebok")));

	}
}
