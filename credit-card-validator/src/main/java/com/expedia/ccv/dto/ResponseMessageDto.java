package com.expedia.ccv.dto;

import lombok.*;
import org.springframework.http.HttpHeaders;


/**
 * @author Ashish.Patel
 *
 */

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseMessageDto {

	private int responseCode;

	private boolean isError;

	private String responseMessage;

	private String errorDiscription;
	
	private HttpHeaders httpHeaders;

}
