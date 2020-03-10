package com.dao;

import com.DY.getregion;
import com.bean.Bean;
import com.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao
{
    public static void main(String[] args) throws SQLException {
        Address_city();
        Address_area();
        Address_province();
        Address_city_area();
        Address_baidu();
    }

    public static void Address_city_area() throws SQLException {
        ArrayList<Bean> Value_beans=new ArrayList<Bean>();
        Value_beans=Get_address_1();
        for(Bean Value_bean:Value_beans)
        {
            String Name=Value_bean.getName();
            String Code=Get_code(Name,"city_area");
            String Address=Get_Address(Code);
            String ID=Value_bean.getID();
            update(ID,Address,Code);
            System.out.println("ID: "+ID);
            System.out.println("Name: "+Name);
            System.out.println("Code: "+Code);
            System.out.println("Address: "+Address);

        }
    }

    public static void Address_city() throws SQLException {
        ArrayList<Bean> Value_beans=new ArrayList<Bean>();
        Value_beans=Get_address_1();
        for(Bean Value_bean:Value_beans)
        {
            String Name=Value_bean.getName();
            String Code=Get_code(Name,"city");
            String Address=Get_Address(Code);
            String ID=Value_bean.getID();
            update(ID,Address,Code);
            System.out.println("ID: "+ID);
            System.out.println("Name: "+Name);
            System.out.println("Code: "+Code);
            System.out.println("Address: "+Address);
        }
    }
    public static void Address_area() throws SQLException {
        ArrayList<Bean> Value_beans=new ArrayList<Bean>();
        Value_beans=Get_address_1();
        for(Bean Value_bean:Value_beans)
        {
            String Name=Value_bean.getName();
            String Code=Get_code(Name,"area");
            String Address=Get_Address(Code);
            String ID=Value_bean.getID();
            update(ID,Address,Code);
            System.out.println("ID: "+ID);
            System.out.println("Name: "+Name);
            System.out.println("Code: "+Code);
            System.out.println("Address: "+Address);
        }
    }

    public static void Address_province() throws SQLException {
        ArrayList<Bean> Value_beans=new ArrayList<Bean>();
        Value_beans=Get_address_1();
        for(Bean Value_bean:Value_beans)
        {
            String Name=Value_bean.getName();
            String Code=Get_code(Name,"province");
            String Address=Get_Address(Code);
            String ID=Value_bean.getID();
            update(ID,Address,Code);
            System.out.println("ID: "+ID);
            System.out.println("Name: "+Name);
            System.out.println("Code: "+Code);
            System.out.println("Address: "+Address);
        }
    }

    public static void Address_baidu() throws SQLException {
        ArrayList<Bean> Value_beans=new ArrayList<Bean>();
        Value_beans=Get_address_1();
        for(Bean Value_bean:Value_beans)
        {
            String Name=Value_bean.getName();
            Name=getregion.SELECT(Name);
            String Code=Get_code(Name,"code");
            String Address=Get_Address(Code);
            String ID=Value_bean.getID();
            update(ID,Address,Code);
            System.out.println("ID: "+ID);
            System.out.println("Name: "+Name);
            System.out.println("Code: "+Code);
            System.out.println("Address: "+Address);
        }
    }


    public static ArrayList<Bean> Get_Chong() throws SQLException {
        Connection conn = DBUtil.getConn();
        String str=new String();
        ArrayList<Bean> Chong_Beans=new ArrayList<>();
        try {
            Statement state = conn.createStatement();

            ResultSet rs = state.executeQuery("select ID,成果名称,count(*) as count from access_jian group by 成果名称,省市 having count>1");
            while(rs.next()) {
                Bean bean=new Bean();
                //如果有结果，是认为是通过验证了
                bean.setID(rs.getString("ID"));
                bean.setName(rs.getString("成果名称"));
                Chong_Beans.add(bean);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            conn.close();
        }
        return Chong_Beans;
    }

    public static String Get_code(String dy_name,String table_name) throws SQLException {
        String code="";
        int len=dy_name.length();
        if(len>5)
        {
            len=5;
        }
        Connection conn = DBUtil.getConn();
        try {
            while(true)
            {
                String address=dy_name.substring(0,len);
                String sql="select postcode from "+table_name+" where name = '"+address+"' ORDER BY name desc";
                Statement state = conn.createStatement();
                ResultSet rs = state.executeQuery(sql);
                if(rs.next()) {
                    //如果有结果，是认为是通过验证了
                    code=rs.getString("postcode");
                    return code;
                }
                else
                {
                    len-=1;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            conn.close();
        }
        return code;
    }


    public static void update(String ID,String Address,String Code) throws SQLException {

        String sql = "update access_jian set 地域 ='"+Address+"',地域编码 ='"+Code+"' where  ID ='"+ID+"'";
        Connection conn = DBUtil.getConn();
        Statement state = null;
        try {
            state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally
        {
            conn.close();
        }

    }
    public static String Get_Address(String Code) throws SQLException {
        Connection conn = DBUtil.getConn();
        String str=new String();
        try {
            Statement state = conn.createStatement();

            ResultSet rs = state.executeQuery("select name from code where postcode ='"+Code+"'");
            while(rs.next()) {
                Bean bean=new Bean();
                //如果有结果，是认为是通过验证了
                str=rs.getString("name");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            conn.close();
        }
        return str;
    }

    //取出只有第一个完成单位
    public static ArrayList<Bean> Get_address_1() throws SQLException {
        ArrayList<Bean> Beans=new ArrayList<Bean>();
        Connection conn = DBUtil.getConn();
        String str=new String();
        try {
            Statement state = conn.createStatement();

            ResultSet rs = state.executeQuery("select * from access_jian where 地域 is null or  地域 ='' ");
            while(rs.next()) {
                Bean bean=new Bean();
                //如果有结果，是认为是通过验证了
                str=rs.getString("完成单位");
                String newStr=str;
                int i = str.indexOf(" ");

                if(i!=-1)
                {
                    newStr = str.substring(0,i);
                }
                bean.setID(rs.getString("ID"));
                bean.setName(newStr);
                Beans.add(bean);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            conn.close();
        }
        return Beans;
    }
}
