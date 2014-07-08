package com.knife.blog.interceptor;


import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class SessionInterceptor implements Interceptor {

	@Override
	public void intercept(ActionInvocation ai) 
	{
		Object user = ai.getController().getSessionAttr("user");
		if (user == null) 
		{
			ai.getController().render("/view/admin/sign-in.html");
		}
		else
		{
			ai.invoke();
		}
	}

}
