package com.cainew.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 处理数字的公共方法
 * @author Jason
 */
public class NumberUtil {

    /**
     * 双精度数字格式转换
     * @param obj 原始对象
     * @return 转换后双精度数字
     */
    public static double parseDouble(Object obj) {
        if (obj == null || "".equals(obj)) {
            return 0;
        }
        try {
            return new Double(obj.toString()).doubleValue();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * int类型数字格式转换
     * @param obj 原始对象
     * @return 转换后int数字
     */
    public static int parseInt(Object obj) {
        return parseInt(obj, 0);
    }

    /**
     * int类型数字格式转换
     * @param obj 原始对象
     * @param def 默认值
     * @return 转换后int数字
     */
    public static int parseInt(Object obj, int defaultVal) {
        if (obj == null || "".equals(obj)) {
            return defaultVal;
        }
        try {
            return new Integer(obj.toString()).intValue();
        } catch (Exception e) {
            return defaultVal;
        }
    }
    /**
     * 单精度类型数字格式转换
     * @param obj 原始对象
     * @return 转换后单精度数字
     */
    public static float parseFloat(Object obj) {
        if (obj == null || "".equals(obj)) {
            return 0;
        }
        try {
            return new Float(obj.toString()).floatValue();
        } catch (Exception e) {
            return 0;
        }
    }
    
   public static String formatBigDecimal(BigDecimal b){
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);
		nf.setMaximumFractionDigits(2);
       return nf.format(b);  
   }
   
   public static BigDecimal dealBigDecimal(Object b){
	   if(b == null){
		   return new BigDecimal(0);
	   }
	   return new BigDecimal(b.toString());
  }
   /**
    * Fortmat BigDecimal while the number is too long.
    * for example: the param value = 1222222.222222, 
    * and then the return will be 1,222,222.22 . It depends on NumberFormat.getInstance() .
    * @param value
    * @return
    */
   public static String formatValue(Object value){
	   try{
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
		    value = dealBigDecimal(value);
			//System.out.println(value);
			return formatter.format(value);
	   }catch(Exception e){
		   //e.printStackTrace();
		   return "0";
	   }
   }
   
   
   public static void main(String[] agrs){
	   System.out.println(formatValue(122222));
   }
   
  /* public static void main(String[] args) { 
       //   The   0   symbol   shows   a   digit   or   0   if   no   digit   present 
       NumberFormat   formatter   =   new   DecimalFormat( "000000 "); 
       String   s   =   formatter.format(-1234.567);     //   -001235 
       //   notice   that   the   number   was   rounded   up 

       //   The   #   symbol   shows   a   digit   or   nothing   if   no   digit   present 
       formatter   =   new   DecimalFormat( "## "); 
       s   =   formatter.format(-1234.567);                   //   -1235 
       s   =   formatter.format(0);                                   //   0 
       formatter   =   new   DecimalFormat( "##00 "); 
       s   =   formatter.format(0);                                   //   00 


       //   The   .   symbol   indicates   the   decimal   point 
       formatter   =   new   DecimalFormat( ".00 "); 
       s   =   formatter.format(-.567);                           //   -.57 
       formatter   =   new   DecimalFormat( "0.00 "); 
       s   =   formatter.format(-.567);                           //   -0.57 
       formatter   =   new   DecimalFormat( "#.# "); 
       s   =   formatter.format(-1234.567);                   //   -1234.6 
       formatter   =   new   DecimalFormat( "#.###### "); 
       s   =   formatter.format(-1234.567);                   //   -1234.567 
       formatter   =   new   DecimalFormat( ".###### "); 
       s   =   formatter.format(-1234.567);                   //   -1234.567 
       formatter   =   new   DecimalFormat( "#.000000 "); 
       s   =   formatter.format(-1234.567);                   //   -1234.567000 


       //   The   ,   symbol   is   used   to   group   numbers 
       formatter   =   new   DecimalFormat( "#,###,### "); 
       s   =   formatter.format(-1234.567);                   //   -1,235 
       s   =   formatter.format(-1234567.890);             //   -1,234,568 
       System.out.println(s);

       //   The   ;   symbol   is   used   to   specify   an   alternate   pattern   for   negative   values 
       formatter   =   new   DecimalFormat( "#;(#) "); 
       s   =   formatter.format(-1234.567);                   //   (1235) 

       //   The   '   symbol   is   used   to   quote   literal   symbols 
       formatter   =   new   DecimalFormat( " '# '# "); 
       s   =   formatter.format(-1234.567);                   //   -#1235 
       formatter   =   new   DecimalFormat( " 'abc '# "); 
       s   =   formatter.format(-1234.567);                   //   -abc1235 
       System.out.println(s);

   } */

}
