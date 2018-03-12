/**
 * 
 */
package com.cainew.exception;

import com.cainew.enums.BusinessExceptionEnum;

/**
 * @author sunke
 * @date 2015-10-12
 * 
 */
public class NotFindObjectException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFindObjectException(String message) {
		super(message);
	}

	public NotFindObjectException(int errorCode) {

		super(errorCode);

	}

	public NotFindObjectException(BusinessExceptionEnum business) {

		super(business);

	}

}
