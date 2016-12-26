package com.test;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.web.utils.DataSourceUtils;
import com.itheima.web.utils.UUIDUtils;

public class TestLimit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into testlimit values(?,?)";
		for (int i = 0; i < 100; i++) {
			try {
				qr.update(sql, UUIDUtils.getId(), i);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		String sql2 = "select num from  testlimit  where 1=1 order by num asc  limit 1, 10";
		try {
		
			List<Number> listBean = qr.query(sql2, new BeanListHandler<Number>(Number.class));

			for (Number n : listBean) {
				System.out.println(n.getNum());
			}
			//String sq3 = "delete from  testlimit where num=3";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	class Number {

		private int num;

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public Number(int num) {
			super();
			this.num = num;
		}

		public Number() {
			super();

		}

	}

}
