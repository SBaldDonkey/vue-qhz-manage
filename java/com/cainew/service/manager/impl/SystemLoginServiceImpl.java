package com.cainew.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cainew.enums.BusinessExceptionEnum;
import com.cainew.exception.BusinessException;
import com.cainew.mapper.TbSysUserMapper;
import com.cainew.service.manager.ISystemLoginService;
import com.cainew.util.JsonResult;
import com.cainew.util.ListUtils;
import com.cainew.util.MD5;
import com.cainew.vo.SystemLogin;
import com.cainew.vo.TbSysUser;
import com.cainew.vo.TbSysUserExample;
import com.cainew.vo.TbSysUserExample.Criteria;
@Service
public class SystemLoginServiceImpl implements ISystemLoginService {
	@Autowired
	private TbSysUserMapper userMapper;
	//登陆校验
	public JsonResult SystemLogin(SystemLogin systemLogin) throws BusinessException {
		if(systemLogin == null){
			throw new BusinessException(BusinessExceptionEnum.USER_NO.getMessage());
		}
		TbSysUserExample example = new TbSysUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(systemLogin.getUsername());
		criteria.andPasswordEqualTo(MD5.enCode(systemLogin.getPassword()));
		List<TbSysUser> list = userMapper.selectByExample(example);
		
		System.out.println("-------------" + "list" +"--------------" + list);
		if (ListUtils.isEmpty(list)) {
			throw new BusinessException(BusinessExceptionEnum.USER_LOGIN_ERR.getMessage());
		}
		return JsonResult.ok(list.get(0).getName());
	}
	
}
