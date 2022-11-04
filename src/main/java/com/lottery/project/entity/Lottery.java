package com.lottery.project.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Lottery")
public class Lottery implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "topThree")
	private String topThree;
	
	@Column(name = "topThreePrice")
	private int topThreePrice;
	
	@Column(name = "tod")
	private String tod;
	
	@Column(name = "todPrice")
	private int todPrice;
	
	@Column(name = "topTwo")
	private String topTwo;
	
	@Column(name = "topTwoPrice")
	private int topTwoPrice;
	
	@Column(name = "underTwo")
	private String underTwo;
	
	@Column(name = "underTwoPrice")
	private int underTwoPrice;
	
	@Column(name = "userId")
	private int userId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate") 
	private Date createDate;
	 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate") 
	private Date updateDate;
	
	@Column(name = "groupLottery")
	private int groupLottery;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTopThree() {
		return topThree;
	}

	public void setTopThree(String topThree) {
		this.topThree = topThree;
	}

	public int getTopThreePrice() {
		return topThreePrice;
	}

	public void setTopThreePrice(int topThreePrice) {
		this.topThreePrice = topThreePrice;
	}

	public String getTod() {
		return tod;
	}

	public void setTod(String tod) {
		this.tod = tod;
	}

	public int getTodPrice() {
		return todPrice;
	}

	public void setTodPrice(int todPrice) {
		this.todPrice = todPrice;
	}

	public String getTopTwo() {
		return topTwo;
	}

	public void setTopTwo(String topTwo) {
		this.topTwo = topTwo;
	}

	public int getTopTwoPrice() {
		return topTwoPrice;
	}

	public void setTopTwoPrice(int topTwoPrice) {
		this.topTwoPrice = topTwoPrice;
	}

	public String getUnderTwo() {
		return underTwo;
	}

	public void setUnderTwo(String underTwo) {
		this.underTwo = underTwo;
	}

	public int getUnderTwoPrice() {
		return underTwoPrice;
	}

	public void setUnderTwoPrice(int underTwoPrice) {
		this.underTwoPrice = underTwoPrice;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getGroupLottery() {
		return groupLottery;
	}

	public void setGroupLottery(int groupLottery) {
		this.groupLottery = groupLottery;
	}

	
}

