package com.cainew.service.manager;

import com.cainew.exception.BusinessException;
import com.cainew.util.JsonResult;
import com.cainew.vo.SystemLogin;
/**
 * 后台登录的接口
 * @author Administrator
 *
 */
public interface ISystemLoginService {
	JsonResult SystemLogin(SystemLogin sustemLogin) throws BusinessException;
}
