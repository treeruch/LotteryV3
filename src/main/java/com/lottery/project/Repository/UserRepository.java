package com.lottery.project.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lottery.project.entity.User;
import com.lottery.project.service.UtilService;

@Repository
public class UserRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Autowired
	private UtilService utilService;

	public List<User> findAllDesc(String startDate, String endDate) {
		List<User> list = new ArrayList<User>();
		StringBuilder sql = new StringBuilder();
		try {
			sql.append(" SELECT us.id , ISNULL(hi.sum_price, 0 ) , us.user_id , us.name "
					+" FROM user_lottery us "
					+" LEFT JOIN (Select SUM(sum_price) as sum_price , user_id , create_by "
					+" from history "
					+" WHERE FORMAT (create_date, 'yyyy-MM-dd') >= '"+startDate+"'"
				    +" AND FORMAT (create_date, 'yyyy-MM-dd') <= '"+endDate+"'"
					+" group by user_id, create_by) hi "
					+" ON us.user_id = hi.user_id ");
			
			System.out.println("SQL: "+sql.toString());
			Query query = entityManager.createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> dataList = query.getResultList();
			
			for(Object[] data : dataList) {
				User history = new User();
				BigInteger bigInt = (BigInteger) data[0];
				history.setId(bigInt.intValue());
				
				history.setSum(utilService.formatPrice((int) data[1]));
				history.setUserId((int) data[2]);
				history.setCreateBy((String) data[3]);
				//history.setUpdateDate((Date) data[9]);
				list.add(history);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return list;
	}

}
