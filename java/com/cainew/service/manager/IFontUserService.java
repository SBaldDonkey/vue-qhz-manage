package com.cainew.service.manager;

import com.cainew.exception.BusinessException;
import com.cainew.util.JsonResult;
import com.cainew.vo.TbFontUser;

/**
 * 
 * @author jx
 * @
 */
public interface IFontUserService {
	//登陆
	JsonResult fontLogin(TbFontUser user,String code) throws BusinessException;

	//注册
	JsonResult fontRegister(TbFontUser user);

	//忘记密码
	JsonResult findPassword(String username ,String newPassword);


	//修改密码
	JsonResult updatePassword(String username,String password,String newPassword);
	
	
}
