package com.yvdedu.guan.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yvdedu.guan.entity.Access;
import com.yvdedu.guan.service.imp.AccessService;

@Controller
@RequestMapping("manager")
public class Mycontroller {

	@Resource 
	private AccessService as;
	
	@RequestMapping("index")
	public String index(Model model , HttpSession http){
		List<Access> mm = as.findbyId(1);
		model.addAttribute("all", mm.get(0).getName());
		return "index";
		
	}
}
