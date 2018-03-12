/**
 * 
 */
package com.cainew.exception;

import com.cainew.enums.BusinessExceptionEnum;

/**
 * @author jx
 * @date 2018-1-26
 * 
 */
public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(int errorCode) {

		this(BusinessExceptionEnum.getBusiness(errorCode));

	}

	public BusinessException(BusinessExceptionEnum business) {

		super(((business != null) ? business.getMessage() : "") + "@@" + ((business != null) ? business.getCode() : "0000"));

	}
}
