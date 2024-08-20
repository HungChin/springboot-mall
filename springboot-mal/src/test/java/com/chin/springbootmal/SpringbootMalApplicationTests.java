package com.chin.springbootmal;

import com.chin.springbootmal.constant.ProductCategory;
import com.chin.springbootmal.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringbootMalApplicationTests {


	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	MockMvc mockMvc;

	@AfterAll
	public static void init(){
		System.out.println("開始執行product測試");
	}

	@Test
	public void getProductSuccess() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/{productId}",1);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productName",equalTo("蘋果（富士）")))
				.andExpect(jsonPath("$.category",equalTo("FOOD")))
				.andExpect(jsonPath("$.price",notNullValue()))
				.andExpect(jsonPath("$.imageUrl",notNullValue()))
				.andExpect(jsonPath("$.description",notNullValue()))
				.andExpect(jsonPath("$.createdDate",notNullValue()))
				.andExpect(jsonPath("$.lastModifiedDate",notNullValue()));
	}

	@Test
	@DisplayName("測試getProduct找不到資料異常")
	public void getProductNotFound() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/{productId}",100);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	public void createProductSuccess() throws Exception {
		ProductRequest productRequest = new ProductRequest();
		productRequest.setProductName("釋迦");
		productRequest.setCategory(ProductCategory.FOOD);
		productRequest.setPrice(1300);
		productRequest.setImageUrl("http://www.baidu.com");
		productRequest.setStock(10);
		productRequest.setDescription("釋迦牟尼");

		String json = objectMapper.writeValueAsString(productRequest);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products")
				.contentType(MediaType.APPLICATION_JSON).content(json);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isCreated());
	}

	@Test
	@Transactional
	public void createProductBadRequest() throws Exception {
		ProductRequest productRequest = new ProductRequest();
		productRequest.setProductName("火龍果");

		String json =  objectMapper.writeValueAsString(productRequest);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products")
				.contentType(MediaType.APPLICATION_JSON).content(json);
		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
	}

	@Test
	public void getAllProductSuccess() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products")
				.param("category","FOOD")
				.param("search","蘋果")
				.param("orderByColumn","created_date")
				.param("sortMethod","desc")
				.param("limit","10")
				.param("offset","0");
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.limit", equalTo(10)))
				.andExpect(jsonPath("$.offset", equalTo(0)))
				.andExpect(jsonPath("$.total", notNullValue()))
				.andExpect(jsonPath("$.results",hasSize(3)));
	}

	@Test
	public void getProducts_filtering() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/products")
				.param("search", "B")
				.param("category", "CAR");

		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.limit", notNullValue()))
				.andExpect(jsonPath("$.offset", notNullValue()))
				.andExpect(jsonPath("$.total", notNullValue()))
				.andExpect(jsonPath("$.results", hasSize(2)));
	}

	@Test
	public void getProducts_sorting() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/products")
				.param("orderByColumn", "price")
				.param("sortMethod", "desc");

		mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.limit", notNullValue()))
				.andExpect(jsonPath("$.offset", notNullValue()))
				.andExpect(jsonPath("$.total", notNullValue()))
				.andExpect(jsonPath("$.results", hasSize(8)))
				.andExpect(jsonPath("$.results[0].productId", equalTo(6)))
				.andExpect(jsonPath("$.results[1].productId", equalTo(5)))
				.andExpect(jsonPath("$.results[2].productId", equalTo(7)))
				.andExpect(jsonPath("$.results[3].productId", equalTo(4)))
				.andExpect(jsonPath("$.results[4].productId", equalTo(2)));
	}

	@Test
	public void getProducts_pagination() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/products")
				.param("limit", "2")
				.param("offset", "2");

		mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.limit", notNullValue()))
				.andExpect(jsonPath("$.offset", notNullValue()))
				.andExpect(jsonPath("$.total", notNullValue()))
				.andExpect(jsonPath("$.results", hasSize(2)))
				.andExpect(jsonPath("$.results[0].productId", equalTo(5)))
				.andExpect(jsonPath("$.results[1].productId", equalTo(4)));
	}

}
