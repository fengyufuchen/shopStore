package com.itheima.web.domain;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -120771630564986818L;
	private String pid;
	private String pname;
	private Double market_price;
	private Double shop_price;
	private String pimage;
	private Date pdate;
	private Integer is_hot = 0;// 0:非热门， 1 热门，
	private String pdesc;
	private Integer pflag = 0;// 是否下架，1表示下架 0表示没有下架
	private Category category;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Double getMarket_price() {
		return market_price;
	}

	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}

	public Double getShop_price() {
		return shop_price;
	}

	public void setShop_price(Double shop_price) {
		this.shop_price = shop_price;
	}

	public String getPimage() {
		return pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public Integer getIs_hot() {
		return is_hot;
	}

	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public Integer getPflag() {
		return pflag;
	}

	public void setPflag(Integer pflag) {
		this.pflag = pflag;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Product(String pid, String pname, Double market_price, Double shop_price, String pimage, Date pdate,
			Integer is_hot, String pdesc, Integer pflag, Category category) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.market_price = market_price;
		this.shop_price = shop_price;
		this.pimage = pimage;
		this.pdate = pdate;
		this.is_hot = is_hot;
		this.pdesc = pdesc;
		this.pflag = pflag;
		this.category = category;
	}

	public Product() {

	}

	public JSONObject toJSONObject() {
		JSONObject object = new JSONObject();
		try {
			object.put("pid", this.pid);
			object.put("name", this.pname);
			object.put("market_price", this.market_price);
			object.put("shop_price", this.shop_price);
			object.put("pimage", this.pimage);
			object.put("pdate", this.pdate);
			object.put("is_hot", this.is_hot);

			object.put("pdesc", this.pdesc);
			object.put("pflg", this.pflag);

			JSONObject categoryObj = new JSONObject();
			categoryObj.put("cname", this.category.getCname());
			categoryObj.put("cid", this.category.getCid());

			object.put("category", this.category.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;

	}

}
