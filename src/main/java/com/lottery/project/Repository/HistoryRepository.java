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
import com.lottery.project.service.UtilService;

@Repository
public class HistoryRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UtilService utilService;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",Locale.US);
	private static final SimpleDateFormat sdf_ddMMyyyy = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
	
	String startDate = sdf_ddMMyyyy.format(new Date());
	String endDate = sdf_ddMMyyyy.format(new Date());

	public List<HistoryModel> findAllDesc() {
		List<HistoryModel> list = new ArrayList<HistoryModel>();
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("SELECT id "
				      +" ,create_date " 
				      +" ,group_lottery " 
				      +" ,create_by "
				      +" ,sum_price "
				      +" ,sum_tod_price "
				      +" ,sum_top_three_price "
				      +" ,sum_top_two_price "
				      +" ,sum_under_two_price "
				      +" FROM history "
				      +" WHERE FORMAT (create_date, 'yyyy-MM-dd') >= '"+startDate+"'"
					  +" AND FORMAT (create_date, 'yyyy-MM-dd') <= '"+endDate+"'"
					 +" order by create_date desc ");
			
			Query query = entityManager.createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> dataList = query.getResultList();
			
			for(Object[] data : dataList) {
				HistoryModel history = new HistoryModel();
				BigInteger bigInt = (BigInteger) data[0];
				history.setId(bigInt.intValue());
				
				Date createDate = (Date) data[1];
				history.setCreateDate(sdf.format(createDate));
				
				history.setGroupLottery((int) data[2]);
				history.setCreateBy((String) data[3]);
				history.setSumPriceStr(utilService.formatPrice((int) data[4]));
				history.setSumTodPriceStr(utilService.formatPrice((int) data[5]));
				history.setSumTopThreePriceStr(utilService.formatPrice((int) data[6]));
				history.setSumTopTwoPriceStr(utilService.formatPrice((int) data[7]));
				history.setSumUnderTwoPriceStr(utilService.formatPrice((int) data[8]));
				//history.setUpdateDate((Date) data[9]);
				list.add(history);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return list;
	}

	public List<History> findByUserId(long userId) {
		List<History> list = new ArrayList<History>();
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("SELECT id , group_lottery FROM history Where user_id = "+userId);
			
			System.out.println("SQL: "+sql.toString());
			Query query = entityManager.createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> dataList = query.getResultList();
			
			for(Object[] data : dataList) {
				History history = new History();
				BigInteger bigInt = (BigInteger) data[0];
				history.setId(bigInt.intValue());
				history.setGroupLottery((int) data[1]);
				list.add(history);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return list;
	}

	public List<History> findViewDetail(Long groupId) {
		List<History> list = new ArrayList<History>();
		StringBuilder sql = new StringBuilder();
		try {
			sql.append(" SELECT id "
					+" ,create_by "
					+" ,group_lottery "
					+" ,sum_price "
					+" ,sum_tod_price "
					+" ,sum_top_three_price "
					+" ,sum_top_two_price "
					+" ,sum_under_two_price "
					+" ,user_id "
					+" ,create_date "
					+" FROM history "
					+" where group_lottery = "+groupId
				    +" AND FORMAT (create_date, 'yyyy-MM-dd') >= '"+startDate+"'"
					+"AND FORMAT (create_date, 'yyyy-MM-dd') <= '"+endDate+"'");
			
			System.out.println("SQL: "+sql.toString());
			Query query = entityManager.createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> dataList = query.getResultList();
			
			for(Object[] data : dataList) {
				History history = new History();
				BigInteger bigInt = (BigInteger) data[0];
				history.setId(bigInt.intValue());
				history.setCreateBy((String) data[1]);
				history.setGroupLottery((int) data[2]);
				history.setSumPrice((int) data[3]);
				history.setSumTodPrice((int) data[4]);
				history.setSumTopThreePrice((int) data[5]);
				history.setSumTopTwoPrice((int) data[6]);
				history.setSumUnderTwoPrice((int) data[7]);
				history.setUserId((int) data[8]);
				list.add(history);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return list;
	}

}
