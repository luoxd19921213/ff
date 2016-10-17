package com.yvdedu.guan.service;

import java.util.List;

import com.yvdedu.guan.entity.Access;

public interface AccessService {

	public String findbyId(int id);
	public List<Access> findAll();
}
