package com.yvdedu.guan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yvdedu.guan.entity.Access;

public interface AccessMap {
	public String find(@Param("rid") int rid);
	public List<Access> findAll();
}
