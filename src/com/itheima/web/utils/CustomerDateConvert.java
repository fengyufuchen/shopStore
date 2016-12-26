package com.itheima.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class CustomerDateConvert implements Converter {

	@Override
	public Object convert(Class arg0, Object arg1) {
		// TODO Auto-generated method stub
		if (arg1 == null)
			return null;
		if (!(arg1 instanceof String)) {
			return arg1;
		}
		String val = (String) arg1;

		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
		try {
			Date date = (Date) sdf.parse(val);

			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
