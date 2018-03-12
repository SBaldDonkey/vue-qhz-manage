package com.cainew.service.manager.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cainew.mapper.TbItemMapper;
import com.cainew.service.manager.ItemService;
import com.cainew.util.JsonResult;
import com.cainew.vo.TbItem;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	public JsonResult additem(TbItem item) {
		int insert = itemMapper.insert(item);
		if(insert!=1){
			return JsonResult.build(400, "添加失败");
		}
		return JsonResult.ok();
	}

	public JsonResult updateItem(long id, TbItem item) {
		TbItem tbItem = itemMapper.selectByPrimaryKey(id);
		tbItem.setColor(item.getColor());
		tbItem.setNetwork(item.getNetwork());
	 	tbItem.setPicture(item.getPicture());
		tbItem.setReprice(item.getReprice());
		tbItem.setTitle(item.getTitle());
		tbItem.setVersion(item.getVersion());
		tbItem.setVolume(item.getVolume());
		Date date = new Date();
		tbItem.setUpdated(date);
		
		itemMapper.updateByPrimaryKey(tbItem);
		return JsonResult.ok();
	}

}
