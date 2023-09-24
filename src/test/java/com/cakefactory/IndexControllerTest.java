package com.cakefactory;

import com.cakefactory.domain.Item;
import com.cakefactory.services.Catalog;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IndexController.class)
class IndexControllerTest {

	private WebClient webClient;

	@MockBean
	Catalog catalog;

	@Autowired
	MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		this.webClient = MockMvcWebClientBuilder.mockMvcSetup(mockMvc).build();
	}

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

		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attribute("items", items));

		verify(catalog).findAllItems();
	}

	@Test
	@DisplayName("index page return a list of items from the database")
	void returnsListOfItemsFromDb() throws Exception {
		final String expectedTitle = "Red Velvet";
		final double value = 3;
		when(catalog.findAllItems())
				.thenReturn(Collections.singletonList(new Item(expectedTitle, value)));
		HtmlPage page = webClient.getPage("http://localhost/");

		assertThat(page.querySelectorAll(".item-title"))
				.anyMatch(domElement -> expectedTitle.equals(domElement.asNormalizedText()));
	}
}
