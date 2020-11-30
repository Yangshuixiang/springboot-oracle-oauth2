package example.demo.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 数据格式转化类
 * 
 *
 */
public class ConvertUtil {
	/**
	 * 转化逗号分隔的id到long数组，通常用于批量操作
	 * @param str
	 * @return
	 */
	public static List<Long> str2longs(String str){
		//System.out.println(str);
		if(str.length()==0){
			return Collections.EMPTY_LIST;
		}
		String[] array = str.split(",");
		List<Long> rets = new ArrayList(array.length);
		int i = 0;
		for(String id:array){
			try{
				rets.add(Long.valueOf(id));
			}catch(Exception ex){
				throw new RuntimeException("转化 "+str+ " 到Long数组出错");
			}
			
		}
		return rets;
	}

	public static List<String> str2strs(String str){
		//System.out.println(str);
		if(str.length()==0){
			return Collections.EMPTY_LIST;
		}
		String[] array = str.split(",");
		List<String> rets = new ArrayList(array.length);
		rets = Arrays.asList(array);
		return rets;
	}

	public static String[] str2arrays(String str){
		//System.out.println(str);
		if(str.length()==0){
			return new String[0];
		}
		String[] array = str.split(",");
		return array;
	}
}
