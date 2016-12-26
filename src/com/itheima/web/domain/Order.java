package com.itheima.web.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {

	private User user;// 属于哪个用户
	private String oid;
	private Date orderTime;
	private Double total = 0.0;
	private Integer state = 0;// 0 未支付，1 已经支付，
	private String address;
	private String name;

	private String telephon;
	private List<OrderItem> listOrderItem = new ArrayList<>();

	public Order() {

	}

	public int getCount() {

		return listOrderItem.size();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephon() {
		return telephon;
	}

	public void setTelephon(String telephon) {
		this.telephon = telephon;
	}

	public List<OrderItem> getListOrderItem() {
		return listOrderItem;
	}

	public void setListOrderItem(List<OrderItem> listOrderItem) {
		this.listOrderItem = listOrderItem;
	}
	
	

}
