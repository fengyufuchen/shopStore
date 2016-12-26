package com.itheima.web.domain;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author lenovo 购物车项目
 */
public class CartItem {

	private Integer count=0;// 商品数量
	private Product product;

	private Double subtotal=0.0;// 商品小计

	public CartItem(Integer count, Product product) {
		super();
		this.count = count;
		this.product = product;

	}

	public CartItem() {

	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getSubtotal() {
		return count * product.getShop_price();
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	
	
	public static  void main(String[] args){
		Long ln=301028292893495297L;
		JSONObject jsonObj=new JSONObject();
		try {
			jsonObj.put("a", ln.toString());
			System.out.println(jsonObj);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
