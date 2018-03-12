package com.cainew.util;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;

import com.cainew.util.YXDate;

public class StringUtil extends TextUtils{
	public static boolean isNullOrEmpty(Object obj) {	
		return obj == null || "".equals(obj.toString());
	}
	public static String toString(Object obj){
		if(obj == null) return "null";
		return obj.toString();
	}
	public static String join(Collection s, String delimiter) {
        StringBuffer buffer = new StringBuffer();
        Iterator iter = s.iterator();
        while (iter.hasNext()) {
            buffer.append(iter.next());
            if (iter.hasNext()) {
                buffer.append(delimiter);
            }
        }
        return buffer.toString();
    }
	
	/**
	 * 字符替换 %s
	 * @param str
	 * @param objArr
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String format(String str, Object[] objArr){
		return str.format(str, objArr);
	}
	
	public static String format(String str, Object arg){
		return format(str, new Object[]{arg});
	}
	
	public static String format(String str, Object arg, Object arg2){
		return format(str, new Object[]{arg, arg2});
	}
	
	public static String format(String str, Object arg, Object arg2, Object arg3){
		return format(str, new Object[]{arg, arg2, arg3});
	}
	
	public static String format(String str, Object arg, Object arg2, Object arg3,Object arg4){
		return format(str, new Object[]{arg, arg2, arg3,arg4});
	}
	
	/**
	 * 把null值替换为空
	 * @param text
	 * @return
	 */
	public static String formatString(String text) {
		String str = (text == null || "null".equals(text)) ? "" : text.trim();
		return str;
	}
	
	
	
	
	public static String replaceStr(int indexStart,String res,String replaceStr){
		
		if(res==null)
			return "";
		
		return StringUtil.replaceStr(indexStart, res.length(), res, replaceStr);
		
	} 
	
	
	/**
	 * 
	 * @param indexStart  开始位置  0开始算
	 * @param indexEnd  结束位置
	 * @param res  原字符
	 * @param replaceStr 换成字符
	 * @return
	 */
	
	public static String replaceStr(int indexStart,int indexEnd,String res,String replaceStr){
		
		try{
			
			int size=res.length();
			
			if(indexEnd>size)
				indexEnd=size;	
			if(indexStart>size)
				return res;
			String temp=res.substring(0, indexStart);	
			for(int i=indexStart;i<indexEnd;i++){
				temp=temp+replaceStr;
			}			
			if(indexEnd<size)			
				temp=temp+res.substring(indexEnd);			
			return temp;
		}catch(Exception e){
			
		}
		
		return res;
		
		
	}
	
	public static String createRandomNumeric(int length){
		
		return new RandomStringUtils().randomNumeric(length);

		
	}
	
	/**
	 * 生成合同编号
	 * @param sourceFlag
	 * @param date
	 * @param tenderId
	 * @return
	 */
	
	public static String createContractNum(int  sourceFlag,Date date,int tenderId ){
		
		String prefix="BD";
		if(sourceFlag==1){//标
			prefix="SBTZ";
		}else if(sourceFlag==2){//理财
			prefix="DQLC";
		}else if(sourceFlag==3){//债权
			prefix="ZQZR";
		}
		String num="";
		if(tenderId<10){
			num="00"+tenderId;
		}else if(tenderId<100){
			num="0"+tenderId;
		}else{
			num=tenderId+"";
		}
		
		return prefix+YXDate.getFormatDate2String(date,"yyyyMMdd")+num;
		
	}
	
	/**
	 * 生成订单号
	 * @param length：订单长度
	 * @return
	 */
	public static String createOrderNo(int length){
		
		String orderNo = "";
		
		if(length<=6){
			orderNo = createRandomNumeric(length);
		}else{
			orderNo = new SimpleDateFormat("yyMMdd").format(new Date())+createRandomNumeric(length-6);
		}
		return orderNo;
	}
	
	
	 public static String delHTMLTag(String htmlStr){ 
	        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
	        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
	        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
	         
	        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
	        Matcher m_script=p_script.matcher(htmlStr); 
	        htmlStr=m_script.replaceAll(""); //过滤script标签 
	         
	        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
	        Matcher m_style=p_style.matcher(htmlStr); 
	        htmlStr=m_style.replaceAll(""); //过滤style标签 
	         
	        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	        Matcher m_html=p_html.matcher(htmlStr); 
	        htmlStr=m_html.replaceAll(""); //过滤html标签 

	        return htmlStr.trim(); //返回文本字符串 
	    } 
	
	 public static void main(String[] args) {
System.out.println(delHTMLTag("<p class='p' style='margin-left:0.0000pt;text-indent:0.0000pt;background:#FFFFFF;'>“闲财宝”投资计划是菜牛在线电子商务有限公司推出的全新理财工具，以投资菜牛钱袋P2P平台的特定优质债权的产品。通过严格筛选优质债权，以保障用户达成预期收益率。</p><p class='MsoNormal' style='text-indent:21.0000pt;text-align:left;'><br />"));
	}
	
	
	
}
