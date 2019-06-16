package com.ssh.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.exception.BusinessException;
import com.ssh.login.bean.UserLogin;
import com.ssh.login.dao.UserLoginDAO;

@Transactional
@Service
public class UserLoginService {
	@Autowired
	private UserLoginDAO userLoginDAO;
	

	public UserLogin getUserLoginBypinyin(String pinyin) {
		return userLoginDAO.getUserLoginBypinyin(pinyin);
	}
	public UserLogin logincheck(String pinyin, String password) {
		UserLogin userLogin = userLoginDAO.getUserLoginBypinyin(pinyin);
		if(userLogin == null) {
			throw new BusinessException("没有该用户");
		}else if(userLogin.getPassword().equals(password)) {
			return userLogin;
		}else {
			throw new BusinessException("密码错误");
		}
	}
	
}
