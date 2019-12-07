package com.cyb.blogserver.controller;

import com.cyb.blogserver.service.LoginServices;
import com.cyb.blogserver.service.TecLearningServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/tecLearning")
public class TecLearningController {

	@Autowired
	private TecLearningServices tecLearningServices;

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
