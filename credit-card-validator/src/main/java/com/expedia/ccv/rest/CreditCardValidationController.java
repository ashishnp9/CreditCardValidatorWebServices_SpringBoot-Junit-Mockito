package com.expedia.ccv.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.expedia.ccv.dto.CreditCardInfoDto;
import com.expedia.ccv.dto.ResponseMessageDto;
import com.expedia.ccv.service.CreditCardValidationService;
import com.expedia.ccv.utility.constants.CommonConstants;
/**
 * @author Ashish.Patel
 *
 */


@RestController
public class CreditCardValidationController {

	@Autowired
	private CreditCardValidationService creditCardValidationService;

	@PostMapping(CommonConstants.VALIDATE_CARD_URI)
	public ResponseEntity<ResponseMessageDto> validateCreditCard(@RequestBody CreditCardInfoDto dto, UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path(CommonConstants.VALIDATE_CARD_URI).buildAndExpand().toUri());
		ResponseMessageDto rmd = creditCardValidationService.validateCard(dto);
		rmd.setHttpHeaders(headers);
		if (rmd.isError()) {
			rmd.setResponseCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
			rmd.setResponseMessage(CommonConstants.VALIDATION_FAIL_MESSAGE);
			return new ResponseEntity<ResponseMessageDto>(rmd, HttpStatus.UNPROCESSABLE_ENTITY);
		} else {
			rmd.setResponseCode(HttpStatus.OK.value());
			rmd.setResponseMessage(CommonConstants.VALIDATE_SUCCESS_MESSAGE);
		}
		return new ResponseEntity<ResponseMessageDto>(rmd, HttpStatus.OK);
	}
}
