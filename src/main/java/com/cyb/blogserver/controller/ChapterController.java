package com.cyb.blogserver.controller;

import com.cyb.blogserver.service.ChapterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/chapter")
public class ChapterController {

	@Autowired
	private ChapterServices chapterServices;

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
