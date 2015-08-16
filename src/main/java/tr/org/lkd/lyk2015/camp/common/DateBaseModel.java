package tr.org.lkd.lyk2015.camp.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
*cengizhan - Aug 16, 2015
*/
public class DateBaseModel {

	public static String getCurrentTimeAsString(Calendar cal) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// get current date time with Date()
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		System.out.println(dateFormat.format(cal.getTime()));
		
		return dateFormat.format(cal.getTime());

	}
	
	public static Calendar getCurrentTimeAsCalendar() 
	{

		return Calendar.getInstance();
	}
}
