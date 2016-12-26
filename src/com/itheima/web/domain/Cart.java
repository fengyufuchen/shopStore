package com.itheima.web.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5464019262194785800L;
	private Map<String, CartItem> cartMap = new LinkedHashMap<String, CartItem>();
	private Double sumTotal = 0.0;

	public Collection<CartItem> getItems() {
		return cartMap.values();
	}

	// 添加到购物车
	public boolean addToCar(CartItem cartItem) {
		// 先判断购物车中有没有该s商品
		CartItem goodItem = cartMap.get(cartItem.getProduct().getPid());
		if (goodItem != null) {
			goodItem.setCount(goodItem.getCount() + cartItem.getCount());

		} else {
			cartMap.put(cartItem.getProduct().getPid(), cartItem);
		}

		double subTotal = cartItem.getSubtotal();

		sumTotal += subTotal;
		return true;
	}

	public boolean removeFromCart(String pid) {

		if (cartMap.containsKey(pid)) {
			sumTotal -= cartMap.remove(pid).getSubtotal();
			cartMap.values();
		}
		return true;
	}

	public void clearCart() {
		cartMap.clear();
		sumTotal = 0.0;

	}

	public Map<String, CartItem> getCartMap() {
		return cartMap;
	}

	public void setCartMap(Map<String, CartItem> cartMap) {
		this.cartMap = cartMap;
	}

	public Double getSumTotal() {
		return sumTotal;
	}

	public void setSumTotal(Double sumTotal) {
		this.sumTotal = sumTotal;
	}

	public int getCount() {

		return cartMap.size();
	}

}
