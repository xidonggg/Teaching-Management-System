package com.ssh.login.utils;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.login.dao.UserDAO;
@Transactional
@Component
public class TokenManager {
	
	@Autowired
	private UserDAO userDAO;
	/**
	 * 生成一个令牌
	 * @param string 用户ID
	 * @return 返回令牌
	 */
	public String createToken(String string){
		//生成token
		UUID uuid = UUID.randomUUID();
		String token = string+"_"
			+uuid.toString().replaceAll("-", "");
		//将token存入redis
		String key = string+"_token";
		//如果已经存在，则直接返回token 
		String r_token = userDAO.getRedisValue(key);
		if(r_token != null)
			return r_token;
		userDAO.setRedisValue(key, token);
//		redisTemplate.opsForValue().set(key, token, 
//			Constants.TOKEN_EXPIRE_HOUR, TimeUnit.HOURS);
		return token;
	}
	
	/**
	 * 检查token是否正确
	 * @param token 令牌
	 * @return true正确;false失败
	 */
	public boolean checkToken(String token){
		//解析出userId和uuid
		if(token==null || "".equals(token)){
			return false;
		}
		String[] arr1 = token.split("_");
		if(arr1.length != 2){
			return false;
		}
		//根据redis进行检查
		String key = arr1[0]+"_token";
//		String r_token = (String)redisTemplate.opsForValue().get(key);
		String r_token = userDAO.getRedisValue(key);
		if(r_token==null){
			return false;
		}
		if(!token.equals(r_token)){
			return false;
		}
		//返回检测结果,更新token时间
		userDAO.updateRedis(key, token);
//		redisTemplate.opsForValue().set(key, token, 
//				Constants.TOKEN_EXPIRE_HOUR, TimeUnit.HOURS);
		return true;
	}
	
	/**
	 * 注销Token
	 * @param token 令牌
	 * @return true正确;false失败
	 */
	public boolean clearToken(String token){
		//解析出userId和uuid
		if(token==null || "".equals(token)){
			return false;
		}
		String[] arr1 = token.split("_");
		if(arr1.length != 2){
			return false;
		}
		//根据redis进行检查
		String key = arr1[0]+"_token";
//		String r_token = (String)redisTemplate.opsForValue().get(key);
		String r_token = userDAO.getRedisValue(key);
		if(r_token==null){
			return false;
		}
		//注销token
//		redisTemplate.delete(key);
		userDAO.deleteRedis(key);
		return true;
	}
	

}
