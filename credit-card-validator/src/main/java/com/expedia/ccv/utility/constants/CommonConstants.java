package com.expedia.ccv.utility.constants;

/**
 * @author Ashish.Patel
 *
 */
public class CommonConstants {
	
	public static final String VISA_CARD_REGEX = "^4[0-9]{15}$";
	public static final String MASTER_CARD_REGEX = "^5[1-5][0-9]{14}$";
	public static final String EXPIRY_DATE_REGEX = "(?:0[1-9]|1[0-2])/[0-9]{2}";
	public static final String VALIDATE_SUCCESS_MESSAGE = "Your Credit Card is Valid";
	public static final String INVALID_CARD_MESSAGE = "The Credit Card Number You Provided is Invalid";
	public static final String INVALID_EXPIRY_DATE_MESSAGE = "Invalid Expiration Date or Date is Expired";
	public static final String VISA_MASTER_CARD_ACCEPTED = "Only Visa/Mastercard are Accepted";
	public static final String BLACK_LIST_MESSAGE = "The Credit Card Number You Provided is in Blacklist";
	public static final String CARD_DATE_EMPTY_MESSAGE = "Credit Card Number or Date found Empty";
	public static final String INTERNAL_SERVER_ERROR = "Internal Server Error While Validating the Credit Card";
	public static final String DATE_FORMAT = "MM/YY";
	public static final String VALIDATION_FAIL_MESSAGE = "Credit Card Validation Fail";
	
	public static final String VALIDATE_CARD_URI = "/creditCard";
}
