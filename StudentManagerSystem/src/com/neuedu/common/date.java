package com.neuedu.common;

import java.text.SimpleDateFormat;

	public class date {
		public static String date() {
			long date = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			String _date = sdf.format(date);
			return _date;
		}

}
