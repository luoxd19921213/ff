package com.yvdedu.guan.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yvdedu.guan.entity.Access;
import com.yvdedu.guan.mapper.AccessMap;

@Service
public class AccessServiceimp implements AccessService{

	@Resource
	private AccessMap accessMap;
	
	public List<Access> findbyId(int id) {
		
		List<Access> mm = accessMap.findMenuByRole(id);
		return mm;
	}
}