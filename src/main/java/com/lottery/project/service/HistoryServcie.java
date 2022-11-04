package com.lottery.project.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lottery.project.Repository.HistoryRepository;
import com.lottery.project.Repository.HistoryRepositoryJPA;
import com.lottery.project.Repository.LotteryRepositoryJPA;
import com.lottery.project.entity.History;
import com.lottery.project.entity.Lottery;
import com.lottery.project.model.HistoryModel;
import com.lottery.project.model.LotteryModel;

@Service
public class HistoryServcie {
	
	@Autowired
	private HistoryRepositoryJPA historyRepositoryJPA;
	
	@Autowired
	private HistoryRepository historyRepository;
	
	@Autowired
	private LotteryRepositoryJPA lotteryRepositoryJPA;

	public List<History> findAll() {
		return historyRepositoryJPA.findAll();
	}

	public void save(History history, List<LotteryModel> listThree, List<LotteryModel> listTwo, int groupLottery, HttpSession session) {
		
		// Lottery
		for(LotteryModel three:listThree) {
			Lottery lotThree = new Lottery();
			lotThree.setTopThree(three.getThree());
			lotThree.setTod(three.getThree());
			if(three.getTopThreePrice() == null) {
				lotThree.setTopThreePrice(0);
			} else {
				lotThree.setTopThreePrice(Integer.valueOf(three.getTopThreePrice()));
			}
			if(three.getTodPrice() == null) {
				lotThree.setTodPrice(0);
			} else {
				lotThree.setTodPrice(Integer.valueOf(three.getTodPrice()));
			}
			
			lotThree.setUserId((int) session.getAttribute("userId"));
			lotThree.setCreateDate(new Date());
			lotThree.setGroupLottery(groupLottery);
			lotteryRepositoryJPA.save(lotThree);
		}
		
		for(LotteryModel two:listTwo) {
			Lottery lotTwo = new Lottery();
			lotTwo.setTopTwo(two.getTwo());
			lotTwo.setUnderTwo(two.getTwo());
			
			if(two.getTopTwoPrice() == null) {
				lotTwo.setTopTwoPrice(0);
			} else {
				lotTwo.setTopTwoPrice(Integer.valueOf(two.getTopTwoPrice()));
			}
			
	        if(two.getUnderTwoPrice() == null) {
	        	lotTwo.setUnderTwoPrice(0);
			} else {
				lotTwo.setUnderTwoPrice(Integer.valueOf(two.getUnderTwoPrice()));
			}
	        lotTwo.setUserId((int) session.getAttribute("userId"));
			lotTwo.setCreateDate(new Date());
			lotTwo.setGroupLottery(groupLottery);
			lotteryRepositoryJPA.save(lotTwo);
		}
		
		historyRepositoryJPA.save(history);
		
	}

	public List<HistoryModel> findAllDesc() {
		return historyRepository.findAllDesc();
	}

	public void delete(History history) {
		historyRepositoryJPA.delete(history);
	}

	public List<History> findViewDetail(Long groupId) {
		return historyRepository.findViewDetail(groupId);
	}

}
