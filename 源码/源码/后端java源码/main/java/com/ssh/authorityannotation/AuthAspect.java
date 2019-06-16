package com.ssh.authorityannotation;

import java.lang.annotation.Annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ssh.Util.tokenToUserName.TokenToUser;
import com.ssh.answer.CommonRsp;
import com.ssh.answer.Result;
import com.ssh.login.bean.UserLogin;
import com.ssh.login.service.UserLoginService;
import com.ssh.modules.finance.entity.FinanceTask;

import java.lang.reflect.Method;

@Aspect
@Component
public class AuthAspect {
	@Autowired
	private TokenToUser tokenToUser;
	@Autowired
	private UserLoginService userLoginService;
	 @Around(value = "@annotation(com.ssh.authorityannotation.AuthFilter)")
	 public Object aroundMethod(ProceedingJoinPoint joinPoint) {
		 MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		 AuthFilter filter = signature.getMethod().getAnnotation(AuthFilter.class);
		 String needAuth = filter.needAuth();
		 System.out.println("切面中输出进入该方法需要的权限为："+needAuth);
		 int index = find(signature.getMethod());
		 String usertoken = (String) joinPoint.getArgs()[index];
		 System.out.println("切面中获取了token："+usertoken);
		 UserLogin user  = tokenToUser.tokenToLoginUser(usertoken);
		 String[] myAuth = user.getAuthorities().split(",");
		 boolean flag = false;
		 for(int i = 0; i < myAuth.length; i++) {
			 if(myAuth[i].equals(needAuth)) {
				 flag = true;
			 }
		 }
		 if(flag) {
			 try {
					Object ret = joinPoint.proceed();
					return ret;
				} catch (Throwable e) {
					e.printStackTrace();
				}
		 }else {
			    CommonRsp<String> rsp = new CommonRsp<>();
			    Result r = new Result();
			    r.setRet(-1);
			    r.setMsg("失败");
			    rsp.setData("没有"+needAuth+"权限");
			    rsp.setType("data");
			    rsp.setResult(r); 
				return rsp;
		 }

		return null;
		 
	 }
	 
	    private int find(Method method) {
	        Annotation[][] arrArr = method.getParameterAnnotations();
	        for (int i = 0; i < arrArr.length; i++) {
	            Annotation[] arr = arrArr[i];
	            for (int j = 0; j < arr.length; j++) {
	                if (arr[j] instanceof RequestHeader) {
	                	RequestHeader param = (RequestHeader) arr[j];
	                    if (param.value().equals("token")) {
	                        return i;
	                    }
	                }
	            }
	        }
	        return -1;
	    }

}
