package edu.xawl.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.impl.triggers.CronTriggerImpl;

/**
 * 获取一个时间的Cron表达式
 * @author Administrator
 *
 */
public class CronUtils {
	
	/**
	 * 根据毫秒值获取
	 * @return
	 */
	public static String getCronWithSeconds(Long seconds){
		
		if(seconds>0){
			Date date = new Date(seconds);
			SimpleDateFormat format = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
			return format.format(date);
		}
		return null;
	}

	
	/**
	 * 根据日期对象获取Cron表达式
	 * @param date
	 * @return
	 */
	public static String getCronWithDate(Date date){
		if(date!=null){
			SimpleDateFormat format = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
			return format.format(date);
		}
		
		return null;
	}
	
	/**
	 * 判断一个Cron表达式是否是有效的
	 * 
	 * 如果Cron表达式正确，并且Cron的时间晚于执行时间，都会判别为正确的表达式，否则判别为错误的表达式
	 * 
	 * @param cronExpression
	 * @return
	 */
	public static boolean isValidExpression(final String cronExpression){
        CronTriggerImpl trigger = new CronTriggerImpl();
        try {
            trigger.setCronExpression(cronExpression);
            Date date = trigger.computeFirstFireTime(null);
            return date != null && date.after(new Date());
        } catch (Exception e) {
        	System.out.println("不是有效的cron表达式");
        }
        return false;
    }
}
