package fishblog.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//获取当前时间
public class TimeFormat {
	public static final SimpleDateFormat TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	//拿到当前时间戳
	public static Date getTime() {
		Date date = new Date();
		return date;
	}

	//时间戳转换字符串时间
	public static String DateToSimpleTime(Date date) {
		return TIME.format(date);
	}

	//拿到一个字符串时间
	public static String getSimpleTime() {
		Date date = new Date();
		return TIME.format(date);
	}

	//计算时间差
	public static String CalculationTimeDifference(String inputtime,String endtime) throws ParseException {
		Date input = TIME.parse(inputtime);
		Date end = TIME.parse(endtime);
		String fromDate = TIME.format(input);
		String toDate = TIME.format(end);
		long from = TIME.parse(fromDate).getTime();
		long to = TIME.parse(toDate).getTime();
		//天差
		int days = (int) ((to - from)/(1000 * 60 * 60 * 24));
		//时差
		int hours = (int) ((to - from)/(1000 * 60 * 60));
		//分钟
		int minutes = (int) ((to - from)/(1000 * 60));
		String result = days + "天" + hours + "小时" + minutes + "分钟";
		System.out.println(result);
		return result;
	}
}
