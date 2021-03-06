package com.itheima.web.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.web.dao.IOrderDao;
import com.itheima.web.domain.Order;
import com.itheima.web.domain.OrderItem;
import com.itheima.web.domain.Product;
import com.itheima.web.utils.DataSourceUtils;

public class OrderDao implements IOrderDao {

	@Override
	public void addOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner();
		/*
		 * CREATE TABLE `orders` ( `oid` varchar(32) NOT NULL, `ordertime`
		 * datetime DEFAULT NULL, `total` double DEFAULT NULL, `state` int(11)
		 * DEFAULT NULL, `address` varchar(30) DEFAULT NULL, `name` varchar(20)
		 * DEFAULT NULL, `telephone` varchar(20) DEFAULT NULL, `uid` varchar(32)
		 * DEFAULT NULL, PRIMARY KEY (`oid`) ) ENGINE=InnoDB DEFAULT
		 * CHARSET=utf8;
		 */
		String sql = "insert into orders  values(?,?,?,?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(), sql, order.getOid(), order.getOrderTime(), order.getTotal(),
				order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid());

	}

	/*
	 * 
	 * (CREATE TABLE `orderitem` ( `itemid` varchar(32) NOT NULL, `count`
	 * int(11) DEFAULT NULL, `subtotal` double DEFAULT NULL, `pid` varchar(32)
	 * DEFAULT NULL, `oid` varchar(32) DEFAULT NULL, PRIMARY KEY (`itemid`), KEY
	 * `fk_0001` (`pid`), KEY `fk_0002` (`oid`), CONSTRAINT `fk_0001` FOREIGN
	 * KEY (`pid`) REFERENCES `product` (`pid`), CONSTRAINT `fk_0002` FOREIGN
	 * KEY (`oid`) REFERENCES `orders` (`oid`) ) ENGINE=InnoDB DEFAULT
	 * CHARSET=utf8;
	 */
	@Override
	public void addOrderItem(OrderItem or) throws Exception {
		// TODO Auto-generated method stub

		QueryRunner qr = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(), sql, or.getItemid(), or.getCount(), or.getSubTotal(),
				or.getProduct().getPid(), or.getOrder().getOid());

	}

	public List<Order> findAllByPage(String uid, int currPage, int pageSize)
			throws IllegalAccessException, InvocationTargetException, SQLException {
		List<Order> listOrder = new ArrayList<Order>();
		String sql = "select * from orders where uid=? limit ?,?";

		QueryRunner qy = new QueryRunner(DataSourceUtils.getDataSource());

		listOrder = qy.query(sql, new BeanListHandler<Order>(Order.class), uid, (currPage - 1) * pageSize, pageSize);
		String sql3 = "select * from (select * from orderitem od where od.oid=?) as suborderitem,product pd where suborderitem.pid=pd.pid";

		String sqlOrIt = "select * from orderitem od, product pd where od.pid=od.pid and od.oid=?";
		for (Order or : listOrder) {

			List<Map<String, Object>> listMap = qy.query(sql3, new MapListHandler(), or.getOid());

			for (Map<String, Object> map : listMap) {
				Product product = new Product();

				BeanUtils.populate(product, map);

				OrderItem orderItem = new OrderItem();
				BeanUtils.populate(orderItem, map);
				orderItem.setProduct(product);

				or.getListOrderItem().add(orderItem);

			}

		}

		return listOrder;

	}

	@Override
	public int getOrderCount(String uid) throws Exception {
		// TODO Auto-generated method stub

		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from orders where uid=?";

		Long cout = (Long) qr.query(sql, new ScalarHandler(), uid);
		return cout.intValue();
	}

	@Override
	public Order findOrderById(String oid) {
		// TODO Auto-generated method stub
		String sql1 = "select * from orders where  orders.oid=?";

		String sql = "select * from (select * from orderitem od where od.oid=?) as suborderitem,product pd where suborderitem.pid=pd.pid";

		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		Order order = null;
		try {
			order = qr.query(sql1, new BeanHandler<Order>(Order.class), oid);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (order != null) {
			try {
				List<Map<String, Object>> listMap = qr.query(sql, new MapListHandler(), oid);
				for (Map<String, Object> map : listMap) {
					Product product = new Product();

					BeanUtils.populate(product, map);

					OrderItem orderItem = new OrderItem();
					BeanUtils.populate(orderItem, map);
					orderItem.setProduct(product);

					order.getListOrderItem().add(orderItem);

				}
				return order;

			} catch (SQLException | IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 修改订单
	 */
	@Override
	public void update(Order order) throws Exception {
		/*
		 * `state` int(11) DEFAULT NULL, `address` varchar(30) DEFAULT NULL,
		 * `name` varchar(20) DEFAULT NULL,
		 * 
		 * `telephone` varchar(20) DEFAULT NULL, `uid` varchar(32) DEFAULT NULL,
		 */
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update orders set state=?,address=?,name=?,telephone=? where oid=?";
		qr.update(sql, order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getOid());
	}

}
