package com.ssh.Util.tokenToUserName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.login.bean.UserLogin;
import com.ssh.login.dao.UserLoginDAO;
@Transactional
@Component
public class TokenToUser {
	@Autowired
	private UserLoginDAO userLoginDAO;
	/**
	 * @param token
	 * @return UserLogin的id
	 */
	public String tokenToUserLoginId(String token){
		//解析出userId和uuid
		if(token==null || "".equals(token)){
			return "";
		}
		String[] arr1 = token.split("_");
		if(arr1.length != 2){
			return "";
		}
		return arr1[0];
	}
	public UserLogin tokenToLoginUser(String token) {
		String id = tokenToUserLoginId(token);
		UserLogin u = userLoginDAO.getUserLoginById(id);
		return u;
	}
	
}
