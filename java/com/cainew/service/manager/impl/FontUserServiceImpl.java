package com.cainew.service.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cainew.enums.BusinessExceptionEnum;
import com.cainew.exception.BusinessException;
import com.cainew.mapper.TbFontUserExample;
import com.cainew.mapper.TbFontUserExample.Criteria;
import com.cainew.mapper.TbFontUserMapper;
import com.cainew.service.manager.IFontUserService;
import com.cainew.util.JsonResult;
import com.cainew.util.ListUtils;
import com.cainew.vo.TbFontUser;
@Service
public class FontUserServiceImpl implements IFontUserService{
	
	@Autowired
	private TbFontUserMapper userMapper;
	
	public JsonResult fontLogin(TbFontUser user, String code) throws BusinessException {
		TbFontUserExample example = new TbFontUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername());
		criteria.andPasswordEqualTo(user.getPassword());
		List<TbFontUser> list = userMapper.selectByExample(example);
		if(ListUtils.isEmpty(list)){
			throw new BusinessException(BusinessExceptionEnum.USER_NO.getMessage());
		}
		
		return JsonResult.ok();
	}
	public JsonResult fontRegister(TbFontUser user) {
		
		TbFontUser tbUser = new TbFontUser();
		tbUser.setUsername(user.getUsername());
		tbUser.setPassword(user.getPassword());
		Date date = new Date();
		tbUser.setCreated(date);
		userMapper.insert(tbUser);
		return JsonResult.ok();
	}
	//忘记密码
	public JsonResult findPassword(String username,String newPassword) {
		//根据用户名来查询
		TbFontUserExample example = new TbFontUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbFontUser> list = userMapper.selectByExample(example);
		TbFontUser user = list.get(0);
		return null;
	}

	public JsonResult updatePassword(String username, String password, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}
}
