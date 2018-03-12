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
public class TransferException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransferException(String message) {
		super(message);
	}

	public TransferException(int errorCode) {

		super(errorCode);

	}

	public TransferException(BusinessExceptionEnum business) {

		super(business);

	}

}
