package com.itheima.web.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lenovo
 *
 *         分页查询的时候实际上是做了两次查询，第一次sql查询是是查询出指定数量的Bean，使用了limit语句； 然后第二次查询是查询总的数量，
 * @param <T>
 */
public class PageBean<T> {

	private Integer pageSize;// 每页显示的记录的 条数
	private List<T> list = new ArrayList();// 数据集合
	private Integer currPage;
	private Integer totalPage;
	private Integer totalCount;// 记录总数

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getTotalPage() {
		return (int) Math.ceil(totalCount * 1.0 / pageSize);
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public PageBean(Integer pageSize, List<T> list, Integer currPage, Integer totalCount) {
		super();
		this.pageSize = pageSize;
		this.list = list;
		this.currPage = currPage;
		this.totalCount = totalCount;
	}

	public PageBean() {

	}

}
