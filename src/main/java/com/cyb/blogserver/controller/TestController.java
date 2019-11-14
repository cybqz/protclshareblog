package com.cyb.blogserver.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/testController")
public class TestController {
	
	@RequestMapping(value="/test")
	@ResponseBody
	public List test () {
		List list = new ArrayList<Object>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "cyb");
		list.add(map);
		return list;
	}
}
