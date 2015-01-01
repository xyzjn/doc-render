/**
 * <h3>Class description</h3>
 * <h4>日期处理�?/h4>
 *
 * <h4>Special Notes</h4>
 * 
 *
 * @ver 0.1
 * @author lihengjun
* 2008-8-11
 */
package main.java.cn.com.sinosoft.app.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil
{
	/**
	 * һ���е�����
	 */
	public static long millionSecondsOfDay = 86400000;
	/**
	 * һ���е�Сʱ
	 */
	public static long millionSecondsOfHour = 3600000;
	
	/**���ڸ�ʽ**/
	public static final String FORMATTER_OF_DATE = "yyyy-MM-dd";
	/**ʱ���ʽ**/
	public static final String FORMATTER_OF_TIME = "HH:mm:ss";
	/**����ʱ���ʽ**/
	public static final String FORMATTER_OF_DATE_AND_TIME = "yyyy-MM-dd HH:mm:ss";
	/**
	 * ����
	 * @return ����date
	 */
	public static Date getToday() {
		return new Date();
		
	}
	/**
	 * �õ���������֮�������,��ͷ����,ȡ����ݺ󣬿��Ը����Ҫ�ټ�
	 * 
	 * @author lihengjun
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDay(Date date1, Date date2) {
		Long d2 = date2.getTime();
		Long d1 = date1.getTime();
		return (int) ((d2 - d1) / millionSecondsOfDay);
	}
	
	/**
	 * 
	 * ����2��ʱ��֮������Сʱ�ͷ������XXСʱXX��
	 * ע��date1��ʽΪyyyy-MM-dd
	 * ע��date2��ʽΪyyyy-MM-dd
	 * ע��time1��ʽΪHH:mm
	 * ע��time2��ʽΪHH:mm
	 * date1<date2
	 * @param date1
	 * @param time1
	 * @param date2
	 * @param time2
	 * @return
	 */
	public static String getHourAndMinute(String date1,String time1, String date2,String time2) {
		Date dateTime1_tmp = DateUtil.parse(date1+" "+time1, "yyyy-MM-dd HH:mm");
		Date dateTime2_tmp = DateUtil.parse(date2+" "+time2, "yyyy-MM-dd HH:mm");		
		Long d2 = dateTime2_tmp.getTime();
		Long d1 = dateTime1_tmp.getTime();
		int hours = (int) ((d2 - d1) / millionSecondsOfHour);
		int mins = (int) ((d2 - d1) % millionSecondsOfHour);
		mins=mins/60000;
		return String.valueOf(hours)+"Сʱ"+String.valueOf(mins)+"��";
	}
	/**
	 * �������ڼ�����
	 * 
	 * @author lihengjun
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDay(Date date , int days){
		Calendar c = new GregorianCalendar();
		c.setTime( date );
		c.add(Calendar.DAY_OF_MONTH,days);
		return c.getTime();
	}
	public static Date addMinutes(Date date , int minutes){
		Calendar c = new GregorianCalendar();
		c.setTime( date );
		c.add(Calendar.MINUTE,minutes);
		return c.getTime();
	}
	
	/**
	 * ���ָ�����ڸ�ʽ��ʽ������
	 * 
	 * @author lihengjun
	 * @param date
	 * @param formater
	 * @return
	 */
	public static String format(Date date, String formater){
		if(date==null)
			return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat( formater );
		return sdf.format(date);
	}
	
	/**
	 * ��ʽ������
	 * 
	 * @author lihengjun
	 * @param date
	 * @param formater
	 * @return
	 */
	public static Date parse(String date, String formater) {
		if(date==null||"".equals(date))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		Date result = null;
		try {
			result = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * �������ȡ�������ڼ�
	 * 
	 * @param date
	 *            Date
	 * @return int ����1-7
	 */
	public static int getWeekOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (calendar.get(Calendar.DAY_OF_WEEK) - 1) == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}
	
	public static String getWeekTextOfDate(Date date){
		String dayNames[] = { "������", "����һ", "���ڶ�", "������", "������", "������",
	    "������" };
		int t=getWeekOfDate(date);
		if(t==7) t=0;
		return dayNames[t];
	}

	public static String getWeekTextOfDate(String strDate, String format){
		String dayNames[] = { "������", "����һ", "���ڶ�", "������", "������", "������",
	    "������" };
		int t=getWeekOfDate( parse(strDate, format) );
		if(t==7) t=0;
		return dayNames[t];
	}
	
	/**
	 * format "yyyy-MM-dd HH:mm:ss"
	 */
	public static String dateToString(Date date,String format) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date).trim();

	}
	
}
