package uz.serverapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uz.serverapi.dto.ProductDto;
import uz.serverapi.dto.ResponseDto;
import uz.serverapi.servise.ProductService;
import uz.serverapi.servise.impl.ProductServiceImpl;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServerApiApplicationTests {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductServiceImpl productServiceImpl;

	// Tests for add new Product
	@Test
	@DisplayName("Add product with valid value")
	void addProduct() {
		ProductDto productDto = ProductDto.builder()
				.name("olma")
				.price(10000)
				.amount(21)
				.description("meva");
		ResponseDto<ProductDto> responseDto = productService.addNewProduct(productDto);

		assertTrue(responseDto.isSuccess());
		assertNotNull(responseDto.getData());
		assertNotNull(responseDto.getData().getId());
		assertEquals(responseDto.getData().getName(), productDto.getName());
		assertEquals(responseDto.getData().getDescription(), productDto.getDescription());
	}

	// Tests for update Product
	@Test
	@DisplayName("Test for update check id is null")
	void checkForUpdateProductIdIsNull(){
		ProductDto productDto = ProductDto.builder()
				.amount(11);

		ResponseDto<ProductDto> responseDto = productService.update(productDto);

		assertAll(
				() -> assertEquals(responseDto.getCode(), -2, "Must return -2 because id is null"),
				() -> assertFalse(responseDto.getSuccess(), "Must be false because id is null"),
				() -> assertEquals(responseDto.getInfo(), "Validation Error")
		);
	}

	@Test
	@DisplayName("Test for update check id is not exist in db")
	void checkForUpdateProductIdIsNotExists(){
		ProductDto productDto = ProductDto.builder()
				.id(1000)
				.name("olma")
				.price(10000)
				.amount(21)
				.description("meva");

		ResponseDto<ProductDto> responseDto = productService.update(productDto);

		assertAll(
				() -> assertEquals(responseDto.getCode(), -1, "Must return -1 because product is not exist in db with id"),
				() -> assertFalse(responseDto.getSuccess(), "Must be false because product is not exist in db with id"),
				() -> assertEquals(responseDto.getInfo(), "Not found")
		);
	}

	@Test
	@DisplayName("Test for update check product is update in db")
	void checkForUpdateProductIdIsNull(){
		ProductDto productDto = ProductDto.builder()
				.id(1)
				.name("olma")
				.price(10000);

		ResponseDto<ProductDto> responseDto = productService.update(productDto);

		assertAll(
				() -> assertEquals(responseDto.getCode(), 0, "Must return 0 because product save in db successfully"),
				() -> assertTrue(responseDto.getSuccess(), "Must be true because product save in db successfully"),
				() -> assertEquals(responseDto.getInfo(), "OK")
		);
	}

	// Tests for get product by id method

	@Test
	@DisplayName("Test for get product id is null")
	void getProductIdIsNull(){
		ResponseDto<ProductDto> responseDto = productService.getBYId(null);

		assertAll(
				() -> assertEquals(responseDto.getCode(), -2, "Must return -2 because product id is null"),
				() -> assertFalse(responseDto.getSuccess(), "Must be false because product id is null"),
				() -> assertEquals(responseDto.getInfo(), "Validation Error")
		);
	}

	@Test
	@DisplayName("Test for get product id is not exist")
	void getProductIdIsNotExists(){
		ResponseDto<ProductDto> responseDto = productService.getBYId(1000);

		assertAll(
				() -> assertEquals(responseDto.getCode(), -1, "Must return -1 because product is not exist with id"),
				() -> assertFalse(responseDto.getSuccess(), "Must be false because product is not exist with id"),
				() -> assertEquals(responseDto.getInfo(), "Not found")
		);
	}

	@Test
	@DisplayName("Test for get product by id")
	void getProductIdById(){
		ResponseDto<ProductDto> responseDto = productService.getBYId(1);

		assertAll(
				() -> assertEquals(responseDto.getCode(), 0, "Must return 0 because product is exist with id"),
				() -> assertTrue(responseDto.getSuccess(), "Must be true because product is exist with id"),
				() -> assertEquals(responseDto.getInfo(), "OK"),
				() -> assertNotNull(responseDto.getData())
		);
	}

	// Delete Product method

	@Test
	@DisplayName("Delete product by id is not exists")
	void deleteProductIdIsNotExists(){
		ResponseDto<ProductDto> responseDto = productService.deleteById(12444);

		assertAll(
				() -> assertEquals(responseDto.getCode(), -1, "Must return -1 because is not exist with id"),
				() -> assertFalse(responseDto.getSuccess(), "Must be false because is not exist with id"),
				() -> assertEquals(responseDto.getInfo(), "Not found")
		);
	}

	@Test
	@DisplayName("Delete product by id")
	void deleteProductId(){
		ResponseDto<ProductDto> responseDto = productService.deleteById(52);

		assertAll(
				() -> assertEquals(responseDto.getCode(), 0, "Must return 0 because id is exist"),
				() -> assertTrue(responseDto.getSuccess(), "Must be true because id is exist"),
				() -> assertEquals(responseDto.getInfo(), "OK"),
				() -> assertNotNull(responseDto.getData())
		);
	}

}
