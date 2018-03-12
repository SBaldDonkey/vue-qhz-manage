package com.cainew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/")
	public String showIndex() {
	/*	return "/WEB-INF/index.jsp";*/
		return "index";
	}

	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}

	/*
	 * @RequestMapping("/login") public String showLogin() { return "/"; }
	 * 
	 * @RequestMapping("/manage/goods") public String showShop() { return "/"; }
	 * 
	 * @RequestMapping(" /brand") public String showBrand() { return "/"; }
	 */
}
