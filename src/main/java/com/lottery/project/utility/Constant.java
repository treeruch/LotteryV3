package com.lottery.project.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Constant {
	
	public static final String API = "/api";
	public static final String USER = "/User";
	public static final String ADMIN = "/Admin";
	public static final String ROLE_1 = "Merchant";
	public static final String ROLE_9 = "SystemAdmin";
	public static final String REDIRECT = "redirect:";
	
	public class CONTROLLER {
		public static final String INDEX = "/index";
		public static final String LOGIN = "/login";
		public static final String ACTIVITY ="/addActivity";
		public static final String DASHBOARD = "/dashboard";
		public static final String SAVE_ACTIVITY ="/saveActivity";
		public static final String COL_ACCOUNT_TYPE ="/collAcctivityType";
	}
	
	public class PAGE {
		public static final String LOGIN = "/app/view/login/Login";
		public static final String DASHBOARD = "/app/view/login/dashboard";
		public static final String ACTIVITY = "/app/view/activity/activity";
		public static final String COL_ACCOUNT_TYPE = "/app/view/collAccountType/collAccountType";
		
	}
	
	
	private static final SimpleDateFormat sdf_ddMMyyyy = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
	public static final String startDate = sdf_ddMMyyyy.format(new Date());
	public static final String endDate = sdf_ddMMyyyy.format(new Date());
	
	public class GET {
		public static final String GET_MAP_USER = "/user";
	}
	

	public class POST {
		public static final String POST_MAP_INSERT_USER = "/insertUser";

	}
	
	public class VIEW {
		public static final String VIEW_MAP_USER = "/lotteryUser";

	}

}
