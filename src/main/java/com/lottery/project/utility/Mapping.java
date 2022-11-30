package com.lottery.project.utility;

public class Mapping {
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
		public static final String LOGIN = "Login";
		public static final String DASHBOARD = "dashboard";
		public static final String ACTIVITY = "/app/view/activity/activity";
		public static final String COL_ACCOUNT_TYPE = "/app/view/collAccountType/collAccountType";
		
	}
	

	public class GET {
		public static final String GET_MAP_USER = "/user";
		public static final String GET_MAP_LOTTERY_ADD = "/lotteryAdd";
		public static final String GET_MAP_LOTTERY_LIST = "/lotteryList";
		public static final String GET_MAP_LOTTERY_TOP_THREE = "/lotteryTopThree";
		public static final String GET_MAP_LOTTERY_TOD = "/lotteryTod";
		public static final String GET_MAP_LOTTERY_TOP_TWO = "/lotteryTopTwo";
		public static final String GET_MAP_LOTTERY_UNDER_TWO = "/lotteryUnderTwo";
		
		public static final String GET_DOWNLOAD_PDF = "/downloadPdf";
		
		public static final String EXPORT_LOTTERY = "/exportLottery";
	}
	

	public class POST {
		public static final String POST_MAP_USER = "/insertUser";
		public static final String POST_MAP_LOTTERY_ADD = "/lotteryAdd";
		public static final String POST_MAP_INSERT_LOTTERY = "/insertLottery";
		public static final String POST_MAP_LOTTERY_LIST = "/lotteryList";
		public static final String POST_MAP_LOTTERY_TOP_THREE = "/riskTopThree";
		public static final String POST_MAP_LOTTERY_TOD = "/riskTod";
		public static final String POST_MAP_LOTTERY_TOP_TWO = "/riskTopTwo";
		public static final String POST_MAP_LOTTERY_UNDER_TWO = "/riskUnderTwo";
		
	}
	
	public class VIEW {
		public static final String VIEW_MAP_USER = "lotteryUser";
		public static final String VIEW_MAP_LOTTERY_ADD = "lotteryAdd";
		public static final String VIEW_MAP_LOTTERY_SHOW = "lotteryShowListAdd";
		public static final String VIEW_MAP_LOTTERY_LIST = "lotteryList";
		public static final String VIEW_MAP_LOTTERY_TOP_THREE = "lotteryTopThree";
		public static final String VIEW_MAP_LOTTERY_TOD = "lotteryTod";
		public static final String VIEW_MAP_LOTTERY_TOP_TWO = "lotteryTopTwo";
		public static final String VIEW_MAP_LOTTERY_UNDER_TWO = "lotteryUnderTwo";
		public static final String VIEW_MAP_LOTTERY_VIEW = "lotteryView";
		public static final String VIEW_DOWNLOAD_PDF = "downloadPDF";
	}

	 
}
