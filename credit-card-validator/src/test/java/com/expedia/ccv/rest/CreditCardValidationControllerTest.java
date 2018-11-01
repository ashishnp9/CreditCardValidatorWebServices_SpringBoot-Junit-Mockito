package com.expedia.ccv.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.expedia.ccv.CreditCardValidatorApplication;
import com.expedia.ccv.utility.constants.CommonConstants;


/**
 * @author Ashish.Patel
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CreditCardValidatorApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/**
 * This Class will be test for CreditCardValidationController layer.
 */
public class CreditCardValidationControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	private static final String UTF8 = "utf-8";
	private static final String responseCode = "$.responseCode";
	private static final String responseMessage = "$.responseMessage";
	private static final String error = "$.error";
	private static final String errorDiscription = "$.errorDiscription";
	private static final String URl = "/creditCard/";

	/**
	 * Test cases for valid Visa Card.
	 * 
	 * @throws Exception
	 * @result isError will be passed.
	 */
	@Test
	public void validateCardTestForVisaCard() throws Exception {
		String input = "{" + "\"cardNumber\": \"4790017555921691\"," + "\"expiryDate\": \"12/20\"" + "}";
		mockMvc.perform(MockMvcRequestBuilders.post(URl).contentType(MediaType.APPLICATION_JSON)
				.content(input).characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.OK.value()))
				.andExpect(jsonPath(responseMessage).value(CommonConstants.VALIDATE_SUCCESS_MESSAGE))
				.andExpect(jsonPath(error).value(false));

	}

	/**
	 * Test cases for valid Master Card.
	 * 
	 * @throws Exception
	 * @result isError will be passed.
	 */
	@Test
	public void validateCardTestForMasterCard() throws Exception {
		String input = "{" + "\"cardNumber\": \"5450 4907 81871378\"," + "\"expiryDate\": \"12/20\"" + "}";
		mockMvc.perform(MockMvcRequestBuilders.post(URl).contentType(MediaType.APPLICATION_JSON)
				.content(input).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.responseCode").value(HttpStatus.OK.value()))
				.andExpect(jsonPath(responseMessage).value(CommonConstants.VALIDATE_SUCCESS_MESSAGE))
				.andExpect(jsonPath(error).value(false));
	}

	/**
	 * Test cases for Input parameters values are empty
	 * 
	 * @throws Exception
	 * @result isError and ErrorDiscription will be passed.
	 */
	@Test
	public void validateCardTestWhenValueIsEmpty() throws Exception {
		String input = "{" + "\"cardNumber\": \"\"," + "\"expiryDate\": \"\"" + "}";
		mockMvc.perform(MockMvcRequestBuilders.post(URl).contentType(MediaType.APPLICATION_JSON)
				.content(input).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.responseCode").value(HttpStatus.UNPROCESSABLE_ENTITY.value()))
				.andExpect(jsonPath(errorDiscription).value(CommonConstants.CARD_DATE_EMPTY_MESSAGE))
				.andExpect(jsonPath(error).value(true));
	}

	/**
	 * Test cases when ExpiryDateIsBlank
	 * 
	 * @throws Exception
	 * @result isError and ErrorDiscription will be passed.
	 */
	@Test
	public void validateCardTestWhenExpiryDateIsBlank() throws Exception {
		String input = "{" + "\"cardNumber\": \"4790017555921691\"," + "\"expiryDate\": \"\"" + "}";
		mockMvc.perform(MockMvcRequestBuilders.post(URl).contentType(MediaType.APPLICATION_JSON)
				.content(input).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.responseCode").value(HttpStatus.UNPROCESSABLE_ENTITY.value()))
				.andExpect(jsonPath(errorDiscription).value(CommonConstants.CARD_DATE_EMPTY_MESSAGE))
				.andExpect(jsonPath(error).value(true));
	}

	/**
	 * Test cases when CardNumberIsBlank
	 * 
	 * @throws Exception
	 * @result isError and ErrorDiscription will be passed.
	 */
	@Test
	public void validateCardTestWhenCardNumberIsBlank() throws Exception {
		String input = "{" + "\"cardNumber\": \"\"," + "\"expiryDate\": \"12/20\"" + "}";
		mockMvc.perform(MockMvcRequestBuilders.post(URl).contentType(MediaType.APPLICATION_JSON)
				.content(input).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.responseCode").value(HttpStatus.UNPROCESSABLE_ENTITY.value()))
				.andExpect(jsonPath(errorDiscription).value(CommonConstants.CARD_DATE_EMPTY_MESSAGE))
				.andExpect(jsonPath(error).value(true));
	}

	/**
	 * Test cases when CardIsBlackListed
	 * 
	 * @throws Exception
	 * @result isError and ErrorDiscription will be passed.
	 */
	@Test
	public void validateCardTestWhenCardIsBlackListed() throws Exception {
		String input = "{" + "\"cardNumber\": \"4788384538552446\"," + "\"expiryDate\": \"12/20\"" + "}";
		mockMvc.perform(MockMvcRequestBuilders.post(URl).contentType(MediaType.APPLICATION_JSON)
				.content(input).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.responseCode").value(HttpStatus.UNPROCESSABLE_ENTITY.value()))
				.andExpect(jsonPath(errorDiscription).value(CommonConstants.BLACK_LIST_MESSAGE))
				.andExpect(jsonPath(error).value(true));
	}

	/**
	 * Test cases when CardNotVisaOrMaster
	 * 
	 * @throws Exception
	 * @result isError and ErrorDiscription will be passed.
	 */
	@Test
	public void validateCardTestWhenCardNotVisaOrMaster() throws Exception {
		String input = "{" + "\"cardNumber\": \"347964771140380\"," + "\"expiryDate\": \"12/20\"" + "}";
		mockMvc.perform(MockMvcRequestBuilders.post(URl).contentType(MediaType.APPLICATION_JSON)
				.content(input).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.responseCode").value(HttpStatus.UNPROCESSABLE_ENTITY.value()))
				.andExpect(jsonPath(errorDiscription).value(CommonConstants.VISA_MASTER_CARD_ACCEPTED))
				.andExpect(jsonPath(error).value(true));
	}

	/**
	 * Test cases when CardIsExpired
	 * 
	 * @throws Exception
	 * @result isError and ErrorDiscription will be passed.
	 */
	@Test
	public void validateCardTestWhenCardIsExpired() throws Exception {
		String input = "{" + "\"cardNumber\": \"4790017555921691\"," + "\"expiryDate\": \"04/12\"" + "}";
		mockMvc.perform(MockMvcRequestBuilders.post(URl).contentType(MediaType.APPLICATION_JSON)
				.content(input).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.responseCode").value(HttpStatus.UNPROCESSABLE_ENTITY.value()))
				.andExpect(jsonPath(errorDiscription).value(CommonConstants.INVALID_EXPIRY_DATE_MESSAGE))
				.andExpect(jsonPath(error).value(true));
	}

	/**
	 * Test cases when ExpiryDateIsInvalid
	 * 
	 * @throws Exception
	 * @result isError and ErrorDiscription will be passed.
	 */
	@Test
	public void validateCardTestWhenExpiryDateIsInvalid() throws Exception {
		String input = "{" + "\"cardNumber\": \"4790017555921691\"," + "\"expiryDate\": \"250/150\"" + "}";
		mockMvc.perform(MockMvcRequestBuilders.post(URl).contentType(MediaType.APPLICATION_JSON)
				.content(input).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.responseCode").value(HttpStatus.UNPROCESSABLE_ENTITY.value()))
				.andExpect(jsonPath(errorDiscription).value(CommonConstants.INVALID_EXPIRY_DATE_MESSAGE))
				.andExpect(jsonPath(error).value(true));
	}

}