package com.cainew.exception;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;

/**
 * 全局异常捕获
 * @author Administrator
 *
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {  
	  private static Logger log = LoggerFactory.getLogger(GlobalExceptionResolver.class);  
      
	    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  Exception ex) {    
	            ModelAndView mv = new ModelAndView();  
	            FastJsonJsonView view = new FastJsonJsonView();  
	            Map<String, Object> attributes = new HashMap<String, Object>();  
	            attributes.put("code", "500");  
	            attributes.put("msg", ex.getMessage());  
	            view.setAttributesMap(attributes);  
	            mv.setView(view);   
	            log.debug("异常:" + ex.getMessage(), ex);  
	            return mv;  
	    }  
	 
}
