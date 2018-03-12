package com.cainew.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cainew.exception.BusinessException;
import com.cainew.service.manager.IFontUserService;
import com.cainew.util.JsonResult;
import com.cainew.vo.TbFontUser;

/**
 * 
 * @author jx
 *
 */
@Controller
public class FontUserController {
	
	@Autowired
	private IFontUserService fontUserService;
	
	@RequestMapping("/font/login")
	@ResponseBody
	public JsonResult fontLogin(TbFontUser user,String code) throws BusinessException{
		JsonResult result = fontUserService.fontLogin(user, code);
		return result;
	}
	
	@RequestMapping("/font/regist")
	@ResponseBody
	/**
	 * 
	 * @param user
	 * @param code 暂时没有
	 * @return
	 */
	public JsonResult fontRegister(TbFontUser user){
		JsonResult result = fontUserService.fontRegister(user);
		return result;
	}

}
