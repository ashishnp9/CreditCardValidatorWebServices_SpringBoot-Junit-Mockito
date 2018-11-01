package com.expedia.ccv.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.expedia.ccv.dto.CreditCardInfoDto;
import com.expedia.ccv.dto.ResponseMessageDto;
import com.expedia.ccv.utility.constants.CommonConstants;

/**
 * This Class will be test for creditCardValidationService layer.
 */
public class CreditCardValidationServiceTest {

	@Mock
	private CreditCardValidationServiceImpl service;

	@InjectMocks
	private CreditCardValidationServiceImpl creditCardValidationService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test cases for valid Visa Card.
	 * @result isError will be passed.
	 */
	@Test
	public void validateCardTestForVisaCard() {
		CreditCardInfoDto parameter = new CreditCardInfoDto("4790017555921691", "04/22");
		Mockito.when(service.validateCard(Mockito.any(CreditCardInfoDto.class))).thenReturn(new ResponseMessageDto());
		ResponseMessageDto actualResult = creditCardValidationService.validateCard(parameter);
		assertEquals(false, actualResult.isError());
	}

	/**
	 * Test cases for valid Master Card.
	 * @result isError will be passed.
	 */
	@Test
	public void validateCardTestForMasterCard() {
		CreditCardInfoDto parameter = new CreditCardInfoDto("5450 4907 81871378", "04/22");
		Mockito.when(service.validateCard(Mockito.any(CreditCardInfoDto.class))).thenReturn(new ResponseMessageDto());
		ResponseMessageDto actualResult = creditCardValidationService.validateCard(parameter);
		assertEquals(false, actualResult.isError());
	}

	/**
	 * Test cases for Input parameters values are empty
	 * @result isError and ErrorDiscription will be passed.
	 */
	@Test
	public void validateCardTestWhenValueIsEmpty() {
		CreditCardInfoDto parameter = new CreditCardInfoDto("", "");
		Mockito.when(service.validateCard(Mockito.any(CreditCardInfoDto.class))).thenReturn(new ResponseMessageDto());
		ResponseMessageDto actualResult = creditCardValidationService.validateCard(parameter);
		assertEquals(CommonConstants.CARD_DATE_EMPTY_MESSAGE, actualResult.getErrorDiscription());
		assertEquals(true, actualResult.isError());
	}

	/**
	 * Test cases when ExpiryDateIsBlank
	 * @result isError and ErrorDiscription will be passed.
	 */
	@Test
	public void validateCardTestWhenExpiryDateIsBlank() {
		CreditCardInfoDto parameter = new CreditCardInfoDto("4790017555921691", "");
		Mockito.when(service.validateCard(Mockito.any(CreditCardInfoDto.class))).thenReturn(new ResponseMessageDto());
		ResponseMessageDto actualResult = creditCardValidationService.validateCard(parameter);
		assertEquals(CommonConstants.CARD_DATE_EMPTY_MESSAGE, actualResult.getErrorDiscription());
		assertEquals(true, actualResult.isError());
	}

	/**
	 * Test cases when CardNumberIsBlank
	 * @result isError and ErrorDiscription will be passed.
	 */
	@Test
	public void validateCardTestWhenCardNumberIsBlank() {
		CreditCardInfoDto parameter = new CreditCardInfoDto("", "15/22");
		Mockito.when(service.validateCard(Mockito.any(CreditCardInfoDto.class))).thenReturn(new ResponseMessageDto());
		ResponseMessageDto actualResult = creditCardValidationService.validateCard(parameter);
		assertEquals(CommonConstants.CARD_DATE_EMPTY_MESSAGE, actualResult.getErrorDiscription());
		assertEquals(true, actualResult.isError());
	}

	/**
	 * Test cases when CardIsBlackListed
	 * @result isError and ErrorDiscription will be passed.
	 */
	@Test
	public void validateCardTestWhenCardIsBlackListed() {
		CreditCardInfoDto parameter = new CreditCardInfoDto("4788384538552446", "12/22");
		Mockito.when(service.validateCard(Mockito.any(CreditCardInfoDto.class))).thenReturn(new ResponseMessageDto());
		ResponseMessageDto actualResult = creditCardValidationService.validateCard(parameter);
		assertEquals(CommonConstants.BLACK_LIST_MESSAGE, actualResult.getErrorDiscription());
		assertEquals(true, actualResult.isError());
	}

	/**
	 * Test cases when CardNotVisaOrMaster
	 * @result isError and ErrorDiscription will be passed.
	 */
	@Test
	public void validateCardTestWhenCardNotVisaOrMaster() {
		CreditCardInfoDto parameter = new CreditCardInfoDto("347964771140380", "12/22");
		Mockito.when(service.validateCard(Mockito.any(CreditCardInfoDto.class))).thenReturn(new ResponseMessageDto());
		ResponseMessageDto actualResult = creditCardValidationService.validateCard(parameter);
		assertEquals(CommonConstants.VISA_MASTER_CARD_ACCEPTED, actualResult.getErrorDiscription());
		assertEquals(true, actualResult.isError());
	}

	/**
	 * Test cases when CardIsExpired
	 * @result isError and ErrorDiscription will be passed.
	 */
	@Test
	public void validateCardTestWhenCardIsExpired() {
		CreditCardInfoDto parameter = new CreditCardInfoDto("4790017555921691", "04/12");
		Mockito.when(service.validateCard(Mockito.any(CreditCardInfoDto.class))).thenReturn(new ResponseMessageDto());
		ResponseMessageDto actualResult = creditCardValidationService.validateCard(parameter);
		assertEquals(CommonConstants.INVALID_EXPIRY_DATE_MESSAGE, actualResult.getErrorDiscription());
		assertEquals(true, actualResult.isError());
	}

	/**
	 * Test cases when ExpiryDateIsInvalid
	 * @result isError and ErrorDiscription will be passed.
	 */
	@Test
	public void validateCardTestWhenExpiryDateIsInvalid() {
		CreditCardInfoDto parameter = new CreditCardInfoDto("4790017555921691", "250/150");
		Mockito.when(service.validateCard(Mockito.any(CreditCardInfoDto.class))).thenReturn(new ResponseMessageDto());
		ResponseMessageDto actualResult = creditCardValidationService.validateCard(parameter);
		assertEquals(CommonConstants.INVALID_EXPIRY_DATE_MESSAGE, actualResult.getErrorDiscription());
		assertEquals(true, actualResult.isError());
	}

}
