package com.cainew.service.manager;

import com.cainew.util.JsonResult;
import com.cainew.vo.TbItem;

public interface ItemService {
	
	JsonResult additem(TbItem item);
	
	JsonResult updateItem(long id,TbItem item);
}
