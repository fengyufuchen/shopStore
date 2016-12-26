package com.itheima.web.domain;

/**
 * @author lenovo 订单项属于哪个订单，订单项中包含哪类商品
 */
public class OrderItem {
	private String itemId;

	private Integer count = 0;
	private Double subTotal = 0.0;
	private Product product;// 包含哪个商品
	private Order order;// 属于哪个订单

	public OrderItem() {

	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
