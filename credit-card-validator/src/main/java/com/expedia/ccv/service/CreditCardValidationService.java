package com.expedia.ccv.service;

import com.expedia.ccv.dto.CreditCardInfoDto;
import com.expedia.ccv.dto.ResponseMessageDto;

/**
 * @author Ashish.Patel
 *
 */
public interface CreditCardValidationService {

	public ResponseMessageDto validateCard(CreditCardInfoDto creditCardInfoDto);

}
