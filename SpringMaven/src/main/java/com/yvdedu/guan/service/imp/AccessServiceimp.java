package com.yvdedu.guan.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yvdedu.guan.entity.Access;
import com.yvdedu.guan.mapper.AccessMap;
import com.yvdedu.guan.service.AccessService;

@Service
public class AccessServiceimp implements AccessService{

	@Resource
	private AccessMap accessMap;
	
	public String findbyId(int id) {
		
		return accessMap.find(id);
	}
	
	public List<Access> findAll(){
		return accessMap.findAll();
	}
}
