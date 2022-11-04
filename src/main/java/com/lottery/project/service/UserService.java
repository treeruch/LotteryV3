package com.lottery.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lottery.project.Repository.HistoryRepository;
import com.lottery.project.Repository.HistoryRepositoryJPA;
import com.lottery.project.Repository.LotteryRepository;
import com.lottery.project.Repository.LotteryRepositoryJPA;
import com.lottery.project.Repository.UserRepository;
import com.lottery.project.Repository.UserRepositoryJPA;
import com.lottery.project.entity.History;
import com.lottery.project.entity.Lottery;
import com.lottery.project.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRepositoryJPA userRepositoryJPA;
	
	@Autowired
	private LotteryRepositoryJPA lotteryRepositoryJPA;
	
	@Autowired
	private LotteryRepository lotteryRepository;
	
	@Autowired
	private HistoryRepositoryJPA historyRepositoryJPA;
	
	@Autowired
	private HistoryRepository historyRepository;

	public List<User> findAll() {
		return userRepositoryJPA.findAll();
	}

	public void save(User userSave) {
		userRepositoryJPA.save(userSave);
	}

	public void delete(User user, long userId) {
		
		List<History> lisHistory = historyRepository.findByUserId(userId);
		for (History history : lisHistory) {
			
			// Delete Lottery
			List<Lottery> lisLottery = lotteryRepository.findByUserId(history.getGroupLottery());
			lotteryRepositoryJPA.deleteAll(lisLottery);
		}
		
		// Delete History
		historyRepositoryJPA.deleteAll(lisHistory);
		
		// Delete User
		userRepositoryJPA.delete(user);
	}

	public List<User> findAllDesc(String startDate, String endDate) {
		return userRepository.findAllDesc(startDate,endDate);
	}

	public long findAllById(Long id) {
		Optional<User> user =	userRepositoryJPA.findById(id);
		int idDelete = user.get().getUserId();
		return idDelete;
	}

}
