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
@Table(name = "history")
public class History  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "sumTopThreePrice")
	private int sumTopThreePrice;
	
	@Column(name = "sumTodPrice")
	private int sumTodPrice;
	
	@Column(name = "sumTopTwoPrice")
	private int sumTopTwoPrice;
	
	@Column(name = "sumUnderTwoPrice")
	private int sumUnderTwoPrice;
	
	@Column(name = "sumPrice")
	private int  sumPrice;
	
	@Column(name = "userId")
	private int  userId;
	
	@Column(name = "createBy")
	private String  createBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate")
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate")
	private Date updateDate; 
	
	@Column(name = "groupLottery")
	private int groupLottery;
	
	
	public String getCreateBy() {
		return createBy;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

