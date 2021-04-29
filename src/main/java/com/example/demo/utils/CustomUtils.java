package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;

import com.example.demo.exception.CustomeException;

public class CustomUtils {
	private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	
	private CustomUtils() {}
	public static String minusThreeMonth(Calendar cal) {
		cal.add(Calendar.MONTH, -3);
		return format.format(cal.getTime());
	}
	
	public static String formateDate(Calendar cal) {
		return format.format(cal.getTime());
	}
	
	public static String fomateDateWithdot(String date) {
		if (StringUtils.isEmpty(date)) {
			return "";
		}
		SimpleDateFormat currentFormate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("dd.MM.yyyy");
		try {
			Date dateObj = currentFormate.parse(date);
			return format2.format(dateObj);	
		} catch (ParseException e) {
			throw new CustomeException(HttpStatus.BAD_REQUEST, "Invalid Date formate expected is yyyy-MM-dd");
		}
	}
	
	public static boolean isNumeric(String strData) {
		if (StringUtils.isEmpty(strData)) {
			return true;
		}
		String regex = "^\\d*[.]?\\d*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(strData);
		return matcher.find();
	}
	public static boolean isNumber(String strData) {
		String regex = "^[0-9]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(strData);
		return matcher.find();
	}
	public static String encode(String strData) {	
		if (StringUtils.isEmpty(strData)) {
			return "";
		}
		return Base64.getEncoder().encodeToString(strData.getBytes());
	}
	
}
