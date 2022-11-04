package com.lottery.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lottery.project.Repository.HistoryRepository;
import com.lottery.project.Repository.LotteryRepository;
import com.lottery.project.Repository.LotteryRepositoryJPA;
import com.lottery.project.entity.Lottery;
import com.lottery.project.model.HistoryModel;
import com.lottery.project.model.LotteryModel;

@Service
public class LotterService {

	@Autowired
	private LotteryRepositoryJPA lotteryRepositoryJPA;
	
	@Autowired
	private LotteryRepository lotteryRepository;
	
	@Autowired
	private HistoryRepository historyRepository;

	@Autowired
	private UtilService  utilService;
	
	public List<Lottery> findAll() {
		return lotteryRepositoryJPA.findAll();
	}

	public LotteryModel findDashboard() {
		return lotteryRepository.findDashboard();
		
	}

	public List<LotteryModel> findLotteryTopThree(int userId) {
		return lotteryRepository.findLotteryTopThree(userId);
	}
	
	public List<LotteryModel> findLotteryTopTwo(int userId) {
		return lotteryRepository.findLotteryTopTwo(userId);
	}
	
	public List<LotteryModel> findLotteryUnderTwo(int userId) {
		return lotteryRepository.findLotteryUnderTwo(userId);
	}
	
	public List<LotteryModel> findLotteryTod(int userId) {
		return lotteryRepository.findLotteryTod(userId);
	}
	
	public List<LotteryModel> FindRisk(LotteryModel lotteryModel) {
		int risk = lotteryModel.getRiskSearch();
		List<LotteryModel> list = lotteryRepository.findLotteryTopThree(0);
		List<LotteryModel> listRisk = new ArrayList<>();
        if(risk < 0 || risk > 100) {
			return list;
		}
        
        for(int j = 0 ; j < list.size() ; j++) {
        	LotteryModel obj = new LotteryModel();
        	if(list.get(j).getTopThree() != null) {
        		String sumTopThree = list.get(j).getSumTopThreePrice().replace(",", "");
        		obj.setTopThree(list.get(j).getTopThree());
            	obj.setRecive(String.valueOf( (float) ((float) (risk * Integer.valueOf(sumTopThree)) / 100) ));
            	obj.setSend(String.valueOf( (float) (( (float) (( 100 - risk) * Integer.valueOf(sumTopThree)) / 100))));
            	obj.setRisk(String.valueOf(risk +" %"));
            	obj.setSumTopThreePrice(list.get(j).getSumTopThreePrice());
            	listRisk.add(obj);
        	}
        	
		 }
       
		return listRisk;
	}
	
	public List<LotteryModel> FindRiskTopTwo(LotteryModel lotteryModel) {
		int risk = lotteryModel.getRiskSearch();
		List<LotteryModel> list = lotteryRepository.findLotteryTopTwo(0);
		List<LotteryModel> listRisk = new ArrayList<>();
        if(risk < 0 || risk > 100) {
			return list;
		}
        
        for(int j = 0 ; j < list.size() ; j++) {
        	LotteryModel obj = new LotteryModel();
        	if(list.get(j).getTopTwo() != null) {
        		obj.setTopTwo(list.get(j).getTopTwo());
            	obj.setRecive(String.valueOf( (float) ((float) (risk * Integer.valueOf(list.get(j).getSumTopTwoPrice())) / 100) ));
            	obj.setSend(String.valueOf( (float) (( (float) (( 100 - risk) * Integer.valueOf(list.get(j).getSumTopTwoPrice())) / 100))));
            	obj.setRisk(String.valueOf(risk +" %"));
            	obj.setSumTopTwoPrice(list.get(j).getSumTopTwoPrice());
            	listRisk.add(obj);
        	}
        	
		 }
       
		return listRisk;
	}

	public List<LotteryModel> FindRiskUnderTwo(LotteryModel lotteryModel) {
		int risk = lotteryModel.getRiskSearch();
		List<LotteryModel> list = lotteryRepository.findLotteryUnderTwo(0);
		List<LotteryModel> listRisk = new ArrayList<>();
        if(risk < 0 || risk > 100) {
			return list;
		}
        
        for(int j = 0 ; j < list.size() ; j++) {
        	LotteryModel obj = new LotteryModel();
        	if(list.get(j).getUnderTwo() != null) {
        		obj.setUnderTwo(list.get(j).getUnderTwo());
            	obj.setRecive(String.valueOf( (float) ((float) (risk * Integer.valueOf(list.get(j).getSumUnderTwoPrice())) / 100) ));
            	obj.setSend(String.valueOf( (float) (( (float) (( 100 - risk) * Integer.valueOf(list.get(j).getSumUnderTwoPrice())) / 100))));
            	obj.setRisk(String.valueOf(risk +" %"));
            	obj.setSumUnderTwoPrice(list.get(j).getSumUnderTwoPrice());
            	listRisk.add(obj);
        	}
        	
		 }
       
		return listRisk;
	}

	public List<LotteryModel> FindRiskTod(LotteryModel lotteryModel) {
		int risk = lotteryModel.getRiskSearch();
		List<LotteryModel> list = lotteryRepository.findLotteryTod(0);
		List<LotteryModel> listRisk = new ArrayList<>();
        if(risk < 0 || risk > 100) {
			return list;
		}
        
        for(int j = 0 ; j < list.size() ; j++) {
        	LotteryModel obj = new LotteryModel();
        	if(list.get(j).getTod() != null) {
        		obj.setTod(list.get(j).getTod());
            	obj.setRecive(String.valueOf((float) ((float) (risk * Integer.valueOf(list.get(j).getSumTodPrice())) / 100) ));
            	obj.setSend(String.valueOf( (float) (( (float) (( 100 - risk) * Integer.valueOf(list.get(j).getSumTodPrice())) / 100))));
            	obj.setRisk(String.valueOf(risk +" %"));
            	obj.setSumTodPrice(list.get(j).getSumTodPrice());
            	listRisk.add(obj);
        	}
        	
		 }
       
		return listRisk;
	}

	public List<Lottery> getListBack(Object[] obj, int userId) {
		return lotteryRepository.getListBack(obj, userId);
	}

	public List<Lottery> findViewDetail(Long groupId) {
		return lotteryRepository.findViewDetail(groupId);
	}

}
