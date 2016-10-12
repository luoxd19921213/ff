package com.yvdedu.guan.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({ @Signature(type = Executor.class, method = "query", args = {
		MappedStatement.class, Object.class,RowBounds.class,
		ResultHandler.class}) })
public class MyPlugin implements Interceptor{

	//@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object p = invocation.getArgs()[1];
		if(p instanceof ParamMap){
			invocation.getArgs()[1] = new HashMap<String,Object>((Map) p);
		}
		Object o = invocation.proceed();
		return o;
	}

	public Object plugin(Object target) {
		if(target instanceof Executor){
			return Plugin.wrap(target, this);
		}
		return target;
	}

	//@Override
	public void setProperties(Properties arg0) {
		
	}
		
}
