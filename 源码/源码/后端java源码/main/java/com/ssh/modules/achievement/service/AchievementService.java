package com.ssh.modules.achievement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.modules.achievement.dao.AchievementDAO;
import com.ssh.modules.achievement.entity.Achievement;

@Transactional
@Service
public class AchievementService {

	@Autowired
	private AchievementDAO achievementDAO;
	
	public List<Achievement> getAchievementByCsId(String scId){
		return achievementDAO.getAchievementByScId(scId);
	}
}
