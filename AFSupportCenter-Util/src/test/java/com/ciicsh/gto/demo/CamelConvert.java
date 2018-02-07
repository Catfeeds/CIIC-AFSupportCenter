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
        String str=" et.emp_task_id,\n" +
            "        et.customer_id,\n" +
            "        et.emp_archive_id,\n" +
            "        et.task_category,\n" +
            "        et.task_category_special,\n" +
            "        et.urgent,\n" +
            "        et.submitter_id,\n" +
            "        et.submitter_name,\n" +
            "        et.submitter_dept_id,\n" +
            "        et.submitter_dept_name,\n" +
            "        et.submit_time,\n" +
            "        et.expire_date,\n" +
            "        et.submitter_remark,\n" +
            "        et.task_form_content,\n" +
            "        et.salary,\n" +
            "        et.handle_user_id,\n" +
            "        et.handle_user_name,\n" +
            "        et.emp_ss_serial,\n" +
            "        et.is_change,\n" +
            "        et.handle_way,\n" +
            "        et.handle_month,\n" +
            "        et.handle_remark,\n" +
            "        et.handle_remark_man,\n" +
            "        et.handle_remark_date,\n" +
            "        et.rejection_remark,\n" +
            "        et.rejection_remark_man,\n" +
            "        et.rejection_remark_date,\n" +
            "        et.task_status,\n" +
            "        et.handle_status,\n" +
            "        et.start_handle_date,\n" +
            "        et.send_check_date,\n" +
            "        et.finish_date,\n" +
            "        et.start_month,\n" +
            "        et.end_month,\n" +
            "        et.chat_history,\n" +
            "        et.business_interface_id,\n" +
            "        e.employee_name,\n" +
            "        e.employee_id,\n" +
            "        e.id_num,\n" +
            "        ca.com_account_id,\n" +
            "        ca.ss_pwd,\n" +
            "        ca.settlement_area,\n" +
            "        ca.ss_account_type,\n" +
            "        ca.ss_account,\n" +
            "        ca.supplier_id,\n" +
            "        c.company_id,\n" +
            "        c.title" ;
        generalMapStr(str);

//        String lineToHump = lineToHump(str);
//        System.out.println(lineToHump);//fParentNoLeader

    }
}
