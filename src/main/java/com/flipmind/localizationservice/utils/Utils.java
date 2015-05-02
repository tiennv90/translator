package com.flipmind.localizationservice.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static Date getDateFromString(String string) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm");
		Date date = null;
		try {
			date = formatter.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
