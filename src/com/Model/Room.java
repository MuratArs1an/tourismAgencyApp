package com.Model;

import com.Helper.DbConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {
    private int id;
    private String Type;
    private String features;
    private int stock;
    private int hotelId;
    private Hotel hotel;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room(int id, String type, String features, int stock, int hotelId) {
        this.id = id;
        Type = type;
        this.features = features;
        this.stock = stock;
        this.hotelId = hotelId;
        this.hotel=Hotel.getFetch(hotelId);
    }

    public static ArrayList<Room> getList(){
        ArrayList<Room> rooms=new ArrayList<>();
        Room room;
        try {
            Statement st= DbConnector.getInstance().createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM room");
            while(rs.next()){
                room=new Room(rs.getInt("id"),rs.getString("type"),rs.getString("features"),rs.getInt("stock"),rs.getInt("hotel_id"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }

    public static boolean add( String type, String features, int stock, int hotelId){
        String query="INSERT INTO room (type,stock,hotel_id,features) VALUES(?,?,?,?)";
        try {
            PreparedStatement pr=DbConnector.getInstance().prepareStatement(query);
            pr.setString(1,type);
            pr.setInt(2,stock);
            pr.setInt(3,hotelId);
            pr.setString(4,features);
            return pr.executeUpdate()!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(int id){
        String query="DELETE FROM room WHERE id=?";
        try {
            PreparedStatement pr=DbConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate()!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}
