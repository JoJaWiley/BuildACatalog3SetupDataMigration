package com.cakefactory;

import com.cakefactory.model.Item;
import com.cakefactory.services.Catalog;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IndexController.class)
class IndexControllerTest {

	@MockBean
	Catalog catalog;

	@Autowired
	MockMvc mockMvc;


	@Test
	@DisplayName("index page returns the landing page")
	void returnsLandingPage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(containsString("Cake Factory")));
	}

	@Test
	@DisplayName("findAllItems adds a list of all 6 items as model attributes")
	void returnsAListOfAllTheItems() throws Exception {
		Item item1 = new Item("abcr", "All Butter Croissant", 0.75);
		Item item2 = new Item("ccr", "Chocolate Croissant", 0.95);
		Item item3 = new Item("b", "Fresh Baguette", 1.60);
		Item item4 = new Item("rv", "Red Velvet", 3.95);
		Item item5 = new Item("vs", "Victoria Sponge", 5.45);
		Item item6 = new Item("cc", "Carrot Cake", 3.45);

		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		items.add(item6);

		given(catalog.findAllItems()).willReturn(items);

		mockMvc.perform(MockMvcRequestBuilders.get("/index"))
				.andExpect(status().isOk())
				//.andExpect(model().size(1))
				.andExpect(view().name("index"))
				.andExpect(model().attribute("items", items));

		verify(catalog).findAllItems();
	}

	//@BeforeTestMethod
	//public void init(WebApplicationContext context) throws Exception {
	//	webClient = MockMvcWebClientBuilder
	//			.webAppContextSetup(context)
	//			.build();
	//	HtmlPage page = webClient.getPage("http://localhost/");
	//}


}
