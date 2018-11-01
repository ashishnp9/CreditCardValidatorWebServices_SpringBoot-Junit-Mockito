package com.expedia.ccv.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.expedia.ccv.utility.constants.CommonConstants;

/**
 * @author Ashish.Patel
 *
 */
public class CommonUtils {
	
	private static final Logger log = LoggerFactory.getLogger(CommonUtils.class);

	/**
	 * This method is used for validateCreditCardByLuhn.
	 * @param number(String) This is the first parameter(Credit Card Number) to validateCreditCardByLuhn
	 * @return boolean
	 */
	public static boolean validateCreditCardByLuhn(String number) {
		log.info("Inside validateCreditCardByLuhn method of CommonUtils Class");
		boolean isValid = false;

		int cardEvenNumberSum = 0;
		int cardOddNumberSum = 0;

		for (int i = number.length() - 2; i >= 0; i -= 2) {
			int count = Character.getNumericValue(number.charAt(i)) * 2;
			if (count > 9) {
				cardEvenNumberSum += count % 10 + 1;
			} else {
				cardEvenNumberSum += count;
			}

		}

		for (int i = number.length() - 1; i >= 0; i -= 2) {
			cardOddNumberSum += Character.getNumericValue(number.charAt(i));
		}

		if ((cardEvenNumberSum + cardOddNumberSum) % 10 == 0) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * This method is used for validateCardExpiryDate.
	 * @param number(String) This is the first parameter(Credit Card date) to validateCardExpiryDate
	 * @return boolean
	 */
	public static boolean validateCardExpiryDate(String date) {
		log.info("Inside validateCardExpiryDate method of CommonUtils Class");
		boolean isCardExpired = false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
			Date cardExpiryDate = sdf.parse(date);
			isCardExpired = cardExpiryDate.before(new Date());
		} catch (ParseException e) {
			log.error("Date ParseException");
			isCardExpired = true;
		}
		return isCardExpired;

	}

	/**
	 * This method is used for getting getBlackListNumber.
	 * @return List<String>
	 */
	public static List<String> getBlackListNumber() {
		log.info("Inside getBlackListNumber method of CommonUtils Class");
		List<String> blackListNumber = new ArrayList<>();
		blackListNumber.add("4788384538552446");
		blackListNumber.add("5144385438523845");
		return blackListNumber;
	}
	
	
}
