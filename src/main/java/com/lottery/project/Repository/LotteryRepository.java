package com.lottery.project.Repository;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lottery.project.entity.History;
import com.lottery.project.entity.Lottery;
import com.lottery.project.model.HistoryModel;
import com.lottery.project.model.LotteryModel;
import com.lottery.project.service.UtilService;

@Repository
public class LotteryRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UtilService utilService;
	
	private static final SimpleDateFormat sdf_ddMMyyyy = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
	
	String startDate = sdf_ddMMyyyy.format(new Date());
	String endDate = sdf_ddMMyyyy.format(new Date());

	public LotteryModel findDashboard() {
		LotteryModel obj = new LotteryModel();
		StringBuilder sql = new StringBuilder();
			sql.append(" SELECT SUM(sum_top_three_price) as sum_top_three_price  "
					+" ,SUM(sum_tod_price) as sum_tod_price  "
					+" ,SUM(sum_top_two_price) as sum_top_two_price  "
					+" ,SUM(sum_under_two_price) as sum_under_two_price  "
					+" ,SUM(sum_price)  as sum_price  "
					+" FROM history  "
					+" WHERE FORMAT (create_date, 'yyyy-MM-dd') >= '"+startDate+"'"
					+" AND FORMAT (create_date, 'yyyy-MM-dd') <= '"+endDate+"'");
			
			Query query = entityManager.createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> dataList = query.getResultList();
			
			for(Object[] data : dataList) {
			
			if(data[0] != null) {
				 obj.setSumTopThreePrice(utilService.formatPrice((int) data[0]));
				 obj.setSumTodPrice(utilService.formatPrice((int) data[1]));
				 obj.setSumTopTwoPrice(utilService.formatPrice((int) data[2]));
				 obj.setSumUnderTwoPrice(utilService.formatPrice((int) data[3]));
				 obj.setSumPrice(utilService.formatPrice((int) data[4]));
			} else {
				obj.setSumTopThreePrice("0");
				 obj.setSumTodPrice("0");
				 obj.setSumTopTwoPrice("0");
				 obj.setSumUnderTwoPrice("0");
				 obj.setSumPrice("0");
			}
			
			}
		
		return  obj;
	}

	public List<LotteryModel> findLotteryTopThree(int userId) {
		List<LotteryModel> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
			sql.append(" SELECT top_three, SUM(top_three_price) as top_three_price "
					+ "FROM lottery "
					+" WHERE FORMAT (create_date, 'yyyy-MM-dd') >= '"+startDate+"'"
					+" AND FORMAT (create_date, 'yyyy-MM-dd') <= '"+endDate+"'");
					if(userId != 0) {
						sql.append(" AND user_id = "+ userId);
					}
					
					sql.append(" group by top_three  order by top_three asc ");
			
			Query query = entityManager.createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> dataList = query.getResultList();
			
			for(Object[] data : dataList) {
				LotteryModel obj = new LotteryModel();
				if(data[0] != null && (int) data[1] != 0) {
					 obj.setTopThree((String) data[0]);
					 obj.setSumTopThreePrice(utilService.formatPrice((int) data[1]));
					 obj.setRisk("0 %");
					 obj.setRecive("0");
					 obj.setSend(utilService.formatPrice((int) data[1]));
					 list.add(obj);
				} 
			
			}
		
		return  list;
	}

	public List<LotteryModel> findLotteryTopTwo(int userId) {
		List<LotteryModel> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
			sql.append(" SELECT top_two, SUM(top_two_price) "
					+ "FROM lottery "
					+" WHERE FORMAT (create_date, 'yyyy-MM-dd') >= '"+startDate+"'"
					+" AND FORMAT (create_date, 'yyyy-MM-dd') <= '"+endDate+"'");
					if(userId != 0) {
						sql.append(" AND user_id = "+ userId);
					}
					sql.append(" group by top_two "
					+" order by top_two asc ");
			
			Query query = entityManager.createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> dataList = query.getResultList();
			
			for(Object[] data : dataList) {
				LotteryModel obj = new LotteryModel();
				if(data[0] != null  && (int) data[1] != 0) {
					 obj.setTopTwo((String) data[0]);
					 obj.setSumTopTwoPrice(utilService.formatPrice((int) data[1]));
					 obj.setRisk("0 %");
					 obj.setRecive("0");
					 obj.setSend(utilService.formatPrice((int) data[1]));
					 list.add(obj);
				} 
			
			}
		
		return  list;
	}

	public List<LotteryModel> findLotteryUnderTwo(int userId) {
		List<LotteryModel> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
			sql.append(" SELECT under_two, SUM(under_two_price) "
					+ "FROM lottery "
					+" WHERE FORMAT (create_date, 'yyyy-MM-dd') >= '"+startDate+"'"
					+" AND FORMAT (create_date, 'yyyy-MM-dd') <= '"+endDate+"'");
					if(userId != 0) {
						sql.append(" AND user_id = "+ userId);
					}
					sql.append(" group by under_two "
					+" order by under_two asc ");
			
			Query query = entityManager.createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> dataList = query.getResultList();
			
			for(Object[] data : dataList) {
				LotteryModel obj = new LotteryModel();
				if(data[0] != null  && (int) data[1] != 0) {
					 obj.setUnderTwo((String) data[0]);
					 obj.setSumUnderTwoPrice(utilService.formatPrice((int) data[1]));
					 obj.setRisk("0 %");
					 obj.setRecive("0");
					 obj.setSend(utilService.formatPrice((int) data[1]));
					 list.add(obj);
				} 
			
			}
		
		return  list;
	}

	public List<LotteryModel> findLotteryTod(int userId) {
		List<LotteryModel> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
			sql.append(" SELECT tod, SUM(tod_price) "
					+ "FROM lottery "
					+" WHERE FORMAT (create_date, 'yyyy-MM-dd') >= '"+startDate+"'"
					+" AND FORMAT (create_date, 'yyyy-MM-dd') <= '"+endDate+"'");
					if(userId != 0) {
						sql.append(" AND user_id = "+ userId);
					}
					sql.append(" group by tod "
					+" order by tod asc ");
			
			Query query = entityManager.createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> dataList = query.getResultList();
			
			for(Object[] data : dataList) {
				LotteryModel obj = new LotteryModel();
				if(data[0] != null  && (int) data[1] != 0) {
					 obj.setTod((String) data[0]);
					 obj.setSumTodPrice(utilService.formatPrice((int) data[1]));
					 obj.setRisk("0 %");
					 obj.setRecive("0");
					 obj.setSend(utilService.formatPrice((int) data[1]));
					 list.add(obj);
				} 
			
			}
		
		return  list;
	}

	public List<Lottery> findByUserId(long group) {
		List<Lottery> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id , group_lottery FROM lottery Where group_lottery = "+group);
			
			Query query = entityManager.createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> dataList = query.getResultList();
			
			for(Object[] data : dataList) {
				Lottery obj = new Lottery();
				BigInteger bigInt = (BigInteger) data[0];
				obj.setId(bigInt.intValue());
				
				list.add(obj);
			
			
			}
		
		return  list;
	}

	public List<Lottery> getListBack(Object[] obj, int userId) {
		List<Lottery> list = new ArrayList<Lottery>();
		StringBuilder sql = new StringBuilder();
		try {
			
			if(null != obj && !"".equals(obj)) {
				sql.append("SELECT a.tod, a.tod_price from ( " 
						+"SELECT DISTINCT tod , SUM(tod_price) As tod_price "
						+"FROM lottery "
						+"WHERE FORMAT (create_date, 'yyyy-MM-dd') >= '"+startDate+"'"
						+"AND FORMAT (create_date, 'yyyy-MM-dd') <= '"+endDate+"'");
						if(userId != 0) {
							sql.append(" AND user_id = "+ userId);
						}
				sql.append(" GROUP BY tod ) As a where tod IN ('"+obj[0]+"','"+obj[1]+"','"+obj[2]+"','"+obj[3]+"','"+obj[4]+"','"+obj[5]+"') order by tod ASC ");
			} 
			
			System.out.println("SQL: "+sql.toString());
			Query query = entityManager.createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> dataList = query.getResultList();
			
			for(Object[] data : dataList) {
				Lottery lottery = new Lottery();
				String str = (String) data[0];
				if("".equals(str)) {
					continue;
				} else {
					lottery.setTod((String) data[0]);
				}
				
				if(data[1] == null) {
					lottery.setTodPrice(0);
				} else {
					lottery.setTodPrice((int) data[1]);
				}
				
				list.add(lottery);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return list;
	}

	public List<Lottery> findViewDetail(Long groupId) {
		List<Lottery> list = new ArrayList<Lottery>();
		StringBuilder sql = new StringBuilder();
		try {
			
				sql.append("SELECT id "
					      +" ,group_lottery "
					      +" ,tod "
					      +" ,tod_price "
					      +" ,top_three "
					      +" ,top_three_price "
					      +" ,top_two "
					      +" ,top_two_price "
					      +" ,under_two "
					      +" ,under_two_price "
					      +" FROM lottery "
					      +" WHERE group_lottery = "+groupId
					      +" AND FORMAT (create_date, 'yyyy-MM-dd') >= '"+startDate+"'"
						  +"AND FORMAT (create_date, 'yyyy-MM-dd') <= '"+endDate+"'");
			
			Query query = entityManager.createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> dataList = query.getResultList();
			
			for(Object[] data : dataList) {
				Lottery lottery = new Lottery();
				BigInteger bigInt = (BigInteger) data[0];
				lottery.setId(bigInt.intValue());
				lottery.setGroupLottery((int) data[1]);
				lottery.setTod((String) data[2]);
				lottery.setTodPrice((int) data[3]);
				lottery.setTopThree((String) data[4]);
				lottery.setTopThreePrice((int) data[5]);
				lottery.setTopTwo((String) data[6]);
				lottery.setTopTwoPrice((int) data[7]);
				lottery.setUnderTwo((String) data[8]);
				lottery.setUnderTwoPrice((int) data[9]);
				
				list.add(lottery);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return list;
	}

}
