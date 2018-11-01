package com.expedia.ccv.dto;

import java.io.Serializable;

import lombok.*;

/**
 * @author Ashish.Patel
 *
 */

@Builder
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardInfoDto implements Serializable {
	
	private static final long serialVersionUID = 112940570436386809L;
	
	private String cardNumber;
	private String expiryDate;

}
