package com.yvdedu.guan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yvdedu.guan.entity.Access;

public interface AccessMap {

	public List<Access> findMenuByRole(@Param("rid") int rid);
}
