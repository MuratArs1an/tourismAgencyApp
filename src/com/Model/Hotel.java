package com.Model;

import com.Helper.DbConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String eMail;
    private String star;
    private String facilityFeatures;
    private int prize;

    public Hotel(int id,String name, String address, String phoneNumber, String eMail, String star, String facilityFeatures, int prize) {
        this.id=id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.star = star;
        this.facilityFeatures = facilityFeatures;
        this.prize = prize;
    }
    public Hotel(){}

    public static ArrayList<Hotel> getList(){
        ArrayList<Hotel> hotels=new ArrayList<>();
        Hotel hotel;
        try {
            Statement st= DbConnector.getInstance().createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM hotel");
            while(rs.next()){
                hotel=new Hotel(rs.getInt("id"),rs.getString("name"),rs.getString("address"),rs.getString("phone_number"),rs.getString("email"),
                        rs.getString("star"),rs.getString("facility_features"),rs.getInt("prize"));
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotels;
    }

    public static boolean add( String name, String address, String phoneNumber, String eMail,String star, String facilityFeatures,int prize){
        String query="INSERT INTO hotel (name,address,phone_number,email,star,facility_features,prize) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr=DbConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,address);
            pr.setString(3,phoneNumber);
            pr.setString(4,eMail);
            pr.setString(5,star);
            pr.setString(6,facilityFeatures);
            pr.setInt(7,prize);

            return pr.executeUpdate()!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(int id){
        String query="DELETE FROM hotel WHERE id=?";
        try {
            PreparedStatement pr=DbConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate()!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String searchQuery(String name){
        String query="SELECT * FROM hotel WHERE name LIKE '%{{name}}%'";
        query=query.replace("{{name}}",name);
        return query;
    }

    public static ArrayList<Hotel> searchUserlist(String query){
        ArrayList<Hotel> hotels=new ArrayList<>();
        Hotel hotel;
        try {
            Statement st= DbConnector.getInstance().createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                hotel=new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setAddress(rs.getString("address"));
                hotel.seteMail(rs.getString("email"));
                hotel.setPhoneNumber(rs.getString("phone_number"));
                hotel.setStar(rs.getString("star"));
                hotel.setFacilityFeatures(rs.getString("facility_features"));
                hotel.setPrize(rs.getInt("prize"));
                hotels.add(hotel);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotels;
    }

    public static Hotel getFetch(int id){
        Hotel hotel=null;
        String query="SELECT * FROM hotel WHERE id=?";
        try {
            PreparedStatement pr=DbConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs=pr.executeQuery();
            if (rs.next()){
                hotel=new Hotel(rs.getInt("id"),rs.getString("name"),rs.getString("address"),rs.getString("phone_number"),rs.getString("email"),
                        rs.getString("star"),rs.getString("facility_features"),rs.getInt("prize"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getFacilityFeatures() {
        return facilityFeatures;
    }

    public void setFacilityFeatures(String facilityFeatures) {
        this.facilityFeatures = facilityFeatures;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }
}
