package com.cainew.controller.manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cainew.enums.BusinessExceptionEnum;
import com.cainew.exception.BusinessException;
import com.cainew.service.manager.ISystemLoginService;
import com.cainew.util.JsonResult;
import com.cainew.util.StringUtil;
import com.cainew.util.VerifyCodeUtils;
import com.cainew.vo.SystemLogin;

@Controller
@RequestMapping(value = "/system")
public class SystemLoginController {

	@Autowired
	private ISystemLoginService systemLoginService;

	@RequestMapping(value = "/verificationcode")
	@ResponseBody
	public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 生成随机字符串
		String code = VerifyCodeUtils.generateVerifyCode(4);
		// 存入回话
		HttpSession session = request.getSession(true);
		session.setAttribute("rand", code);
		// 生成图片
		int w = 200, h = 80;
		VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), code);
	}

	// 登陆+校验
	@RequestMapping(value = "/login")
	@ResponseBody
	public JsonResult systemLogin(SystemLogin systemLogin, HttpServletRequest request) throws BusinessException {
		String code = (String) request.getSession().getAttribute("rand");


		if (StringUtil.isNullOrEmpty(systemLogin.getImgCode())) {
			throw new BusinessException(BusinessExceptionEnum.login_IMAGECODE_NULL.getMessage());
		}
		// 验证码校验
		if (!code.equalsIgnoreCase(systemLogin.getImgCode())) {
			throw new BusinessException(BusinessExceptionEnum.login_IMAGECODE_ERROR.getMessage());
		}
		JsonResult result = systemLoginService.SystemLogin(systemLogin);
		return result;
	}

}
