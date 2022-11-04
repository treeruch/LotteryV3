package com.lottery.project.model;

public class UserModel {
	
	private long id;
	
	private String name;
	
	private int userId;
	
	private String startDate;
	
	private String endDate;

	private String three;
	private String[] threeArray;
	private String[] topThreePriceArray;
	private String[] todPriceArray;
	private String topThreePrice;
	private String todPrice;
	
	private String two;
	private String[] twoArray;
	private String[] topTwoPriceArray;
	private String[] underTwoPriceArray;
	private String topTwoPrice;
	private String underTwoPrice;
	
	public String[] getTopThreePriceArray() {
		return topThreePriceArray;
	}

	public void setTopThreePriceArray(String[] topThreePriceArray) {
		this.topThreePriceArray = topThreePriceArray;
	}

	public String[] getTodPriceArray() {
		return todPriceArray;
	}

	public void setTodPriceArray(String[] todPriceArray) {
		this.todPriceArray = todPriceArray;
	}

	public String[] getTopTwoPriceArray() {
		return topTwoPriceArray;
	}

	public void setTopTwoPriceArray(String[] topTwoPriceArray) {
		this.topTwoPriceArray = topTwoPriceArray;
	}

	public String[] getUnderTwoPriceArray() {
		return underTwoPriceArray;
	}

	public void setUnderTwoPriceArray(String[] underTwoPriceArray) {
		this.underTwoPriceArray = underTwoPriceArray;
	}

	public String[] getThreeArray() {
		return threeArray;
	}

	public void setThreeArray(String[] threeArray) {
		this.threeArray = threeArray;
	}

	public String[] getTwoArray() {
		return twoArray;
	}

	public void setTwoArray(String[] twoArray) {
		this.twoArray = twoArray;
	}

	public String getThree() {
		return three;
	}

	public void setThree(String three) {
		this.three = three;
	}


	public String getTwo() {
		return two;
	}

	public void setTwo(String two) {
		this.two = two;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTopThreePrice() {
		return topThreePrice;
	}

	public void setTopThreePrice(String topThreePrice) {
		this.topThreePrice = topThreePrice;
	}

	public String getTodPrice() {
		return todPrice;
	}

	public void setTodPrice(String todPrice) {
		this.todPrice = todPrice;
	}

	public String getTopTwoPrice() {
		return topTwoPrice;
	}

	public void setTopTwoPrice(String topTwoPrice) {
		this.topTwoPrice = topTwoPrice;
	}

	public String getUnderTwoPrice() {
		return underTwoPrice;
	}

	public void setUnderTwoPrice(String underTwoPrice) {
		this.underTwoPrice = underTwoPrice;
	}
	

}
