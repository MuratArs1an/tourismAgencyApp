package com.Model;

import com.Helper.DbConnector;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    private int id;
    private String userName;
    private String password;
    private String userType;

    public User(int id,String userName, String password, String userType) {
        this.id=id;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    public User() {
    }

    public static ArrayList<User> getList(){
        ArrayList<User> users=new ArrayList<>();
        User user;
        try {
            Statement st= DbConnector.getInstance().createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM users");
            while(rs.next()){
                user=new User(rs.getInt("id"),rs.getString("user_name"),rs.getString("password"),rs.getString("type"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public static String searchQuery(String name, String password, String userType){
        String query="SELECT * FROM users WHERE user_name LIKE '%{{name}}%' AND password LIKE '%{{password}}%'";
        query=query.replace("{{name}}",name);
        query=query.replace("{{password}}",password);
        if(!userType.isEmpty()){
            query+=" AND type = '{{type}}'";
            query=query.replace("{{type}}", userType);
        }
        System.out.println(query);
        return query;
    }

    public static ArrayList<User> searchUserlist(String query){
        ArrayList<User> userList=new ArrayList<>();
        User obj;
        try {
            Statement st= DbConnector.getInstance().createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                obj=new User();
                obj.setId(rs.getInt("id"));
                obj.setUserName(rs.getString("user_name"));
                obj.setPassword(rs.getString("password"));
                obj.setUserType(rs.getString("type"));
                userList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public static User getFetch(String userName){
        User obj=null;
        String sql="SELECT * FROM users WHERE user_name=?";
        try {
            PreparedStatement pr=DbConnector.getInstance().prepareStatement(sql);
            pr.setString(1,userName);
            ResultSet rs=pr.executeQuery();
            if(rs.next()){
                obj=new User();
                obj.setId(rs.getInt("id"));
                obj.setUserName(rs.getString("user_name"));
                obj.setPassword(rs.getString("password"));
                obj.setUserType(rs.getString("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static User getFetch(String userName, String password){
        User obj=null;
        String sql="SELECT * FROM users WHERE user_name=? AND password=?";
        try {
            PreparedStatement pr=DbConnector.getInstance().prepareStatement(sql);
            pr.setString(1,userName);
            pr.setString(2,password);
            ResultSet rs=pr.executeQuery();
            if(rs.next()){
                switch (rs.getString("type")){
                    case "operator":
                        obj=new Operator();
                        break;
                    case "agency":
                        obj=new Agency();
                        break;
                    default:
                        obj=new User();
                }
                obj.setId(rs.getInt("id"));
                obj.setUserName(rs.getString("user_name"));
                obj.setPassword(rs.getString("password"));
                obj.setUserType(rs.getString("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
