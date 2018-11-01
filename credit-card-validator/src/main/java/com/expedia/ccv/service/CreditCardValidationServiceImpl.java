package com.expedia.ccv.service;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.expedia.ccv.dto.CreditCardInfoDto;
import com.expedia.ccv.dto.ResponseMessageDto;
import com.expedia.ccv.utility.CommonUtils;
import com.expedia.ccv.utility.constants.CommonConstants;

/**
 * @author Ashish.Patel
 *
 */

@Service
public class CreditCardValidationServiceImpl implements CreditCardValidationService {

	private static final Logger log = LoggerFactory.getLogger(CreditCardValidationServiceImpl.class);

	/**
	 * This method is used for validateCreditCard.
	 * @param CreditCardInfoDto This is the first parameter to validateCard
	 * @return ResponseMessageDto
	 */
	@Override
	public ResponseMessageDto validateCard(CreditCardInfoDto cardInfo) {
		log.info("Inside validateCard method of CreditCardValidationServiceImpl");
		String result = "";
		String cardNumber = "";
		boolean isError = true;

		ResponseMessageDto responsedto = new ResponseMessageDto();
		try {
			if (null != cardInfo.getCardNumber() && !cardInfo.getCardNumber().isEmpty()
					&& null != cardInfo.getExpiryDate() && !cardInfo.getExpiryDate().isEmpty()) {

				cardNumber = cardInfo.getCardNumber().replaceAll("\\s", "");
				if (!CommonUtils.getBlackListNumber().contains(cardNumber)) {

					if (Pattern.matches(CommonConstants.VISA_CARD_REGEX, cardNumber)
							|| Pattern.matches(CommonConstants.MASTER_CARD_REGEX, cardNumber)) {
						if (Pattern.matches(CommonConstants.EXPIRY_DATE_REGEX, cardInfo.getExpiryDate())
								&& !CommonUtils.validateCardExpiryDate(cardInfo.getExpiryDate())) {
							if (CommonUtils.validateCreditCardByLuhn(cardNumber)) {
								isError = false;
							} else {
								result = CommonConstants.INVALID_CARD_MESSAGE;
							}
						} else {
							result = CommonConstants.INVALID_EXPIRY_DATE_MESSAGE;
						}
					} else {
						result = CommonConstants.VISA_MASTER_CARD_ACCEPTED;
					}

				} else {
					result = CommonConstants.BLACK_LIST_MESSAGE;
				}

			} else {
				result = CommonConstants.CARD_DATE_EMPTY_MESSAGE;
			}
		} catch (Exception e) {
			log.error(CommonConstants.INTERNAL_SERVER_ERROR);
			result = CommonConstants.INTERNAL_SERVER_ERROR;
		}
		responsedto.setError(isError);
		responsedto.setErrorDiscription(result);
		return responsedto;
	}

}
