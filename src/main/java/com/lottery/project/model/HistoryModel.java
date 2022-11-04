package com.lottery.project.model;

public class HistoryModel {
	
	private long id;
	private int sumTopThreePrice;
	private int sumTodPrice;
	private int sumTopTwoPrice;
	private int sumUnderTwoPrice;
	private int  sumPrice;
	
	private String sumTopThreePriceStr;
	private String sumTodPriceStr;
	private String sumTopTwoPriceStr;
	private String sumUnderTwoPriceStr;
	private String  sumPriceStr;
	
	private String createDate;
	private String updateDate; 
	private String createBy;
	private int groupLottery;
	
	public String getSumTopThreePriceStr() {
		return sumTopThreePriceStr;
	}
	public void setSumTopThreePriceStr(String sumTopThreePriceStr) {
		this.sumTopThreePriceStr = sumTopThreePriceStr;
	}
	public String getSumTodPriceStr() {
		return sumTodPriceStr;
	}
	public void setSumTodPriceStr(String sumTodPriceStr) {
		this.sumTodPriceStr = sumTodPriceStr;
	}
	public String getSumTopTwoPriceStr() {
		return sumTopTwoPriceStr;
	}
	public void setSumTopTwoPriceStr(String sumTopTwoPriceStr) {
		this.sumTopTwoPriceStr = sumTopTwoPriceStr;
	}
	public String getSumUnderTwoPriceStr() {
		return sumUnderTwoPriceStr;
	}
	public void setSumUnderTwoPriceStr(String sumUnderTwoPriceStr) {
		this.sumUnderTwoPriceStr = sumUnderTwoPriceStr;
	}
	public String getSumPriceStr() {
		return sumPriceStr;
	}
	public void setSumPriceStr(String sumPriceStr) {
		this.sumPriceStr = sumPriceStr;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getSumTopThreePrice() {
		return sumTopThreePrice;
	}
	public void setSumTopThreePrice(int sumTopThreePrice) {
		this.sumTopThreePrice = sumTopThreePrice;
	}
	public int getSumTodPrice() {
		return sumTodPrice;
	}
	public void setSumTodPrice(int sumTodPrice) {
		this.sumTodPrice = sumTodPrice;
	}
	public int getSumTopTwoPrice() {
		return sumTopTwoPrice;
	}
	public void setSumTopTwoPrice(int sumTopTwoPrice) {
		this.sumTopTwoPrice = sumTopTwoPrice;
	}
	public int getSumUnderTwoPrice() {
		return sumUnderTwoPrice;
	}
	public void setSumUnderTwoPrice(int sumUnderTwoPrice) {
		this.sumUnderTwoPrice = sumUnderTwoPrice;
	}
	public int getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}
	public int getGroupLottery() {
		return groupLottery;
	}
	public void setGroupLottery(int groupLottery) {
		this.groupLottery = groupLottery;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
}
