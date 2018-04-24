package com.ciicsh.gto.demo;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CamelConvert {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    /**下划线转驼峰*/
    public static String lineToHump(String str){
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    /**驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})*/
    public static String humpToLine(String str){
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }
    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    /**驼峰转下划线,效率比上面高*/
    public static String humpToLine2(String str){
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    public  static void generalMapStr(String str){
        String s1="",s2="";
        String[] aStr=str.split(",");
        Arrays.stream(aStr).forEach(
            s->{
                String S1="",S2="";
                S1=s.substring(s.indexOf(".")+1,s.length()).trim();
                S2=lineToHump(S1);
                System.out.println("<result column=\""+S1+"\" property=\""+S2+"\"/>");
            }
        );
        Arrays.stream(aStr).forEach(
            s->{
                String S1="",S2="";
                S1=s.substring(s.indexOf(".")+1,s.length()).trim();
                S2=lineToHump(S1);
                System.out.println("private String "+S2+";");
            }
        );
        Arrays.stream(aStr).forEach(
            s->{
                String S1="",S2="";
                S1=s.substring(s.indexOf(".")+1,s.length()).trim();
                S2=lineToHump(S1);
                System.out.println("<if test=\""+S2+" != null\">\n AND"+ s.trim() +" = #{"+S2+"}" +"\n</if>");
            }
        );
        Arrays.stream(aStr).forEach(
            s->{
                String S1="",S2="";
                S1=s.substring(s.indexOf(".")+1,s.length()).trim();
                S2=lineToHump(S1);
                System.out.println(S2+" : "  +"'',");
            }
        );
    }
    public static void main(String[] args) {
        String str=" ca.com_account_name,cac.hf_com_account,p.payment_month,CURDATE() as curdate,sba.bank_name\n" +
            "        ,curMonth.remitted_amount_add,curMonth.remitted_amount_reduce,curMonth.remitted_amount,\n" +
            "        curMonth.remitted_count_emp_add,curMonth.remitted_count_emp_reduce,curMonth.remitted_count_emp,\n" +
            "        lastMonth.remitted_amount_last,lastMonth.remitted_count_emp_last" ;
        generalMapStr(str);

//        String lineToHump = lineToHump(str);
//        System.out.println(lineToHump);//fParentNoLeader

    }
}
