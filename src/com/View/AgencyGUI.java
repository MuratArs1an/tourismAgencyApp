package com.View;

import com.Helper.Config;
import com.Helper.Helper;
import com.Helper.Item;
import com.Model.Hotel;
import com.Model.Room;
import com.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class AgencyGUI extends JFrame {
    private JTextField fld_otelName;
    private JButton btn_search;
    private JTable tbl_otels;
    private JTable tbl_rooms;
    private JPanel wrapper;
    private JButton btn_delete;
    private JButton btn_add;
    private JComboBox cmb_hotels;
    private JComboBox cmb_roomType;
    private JTextField fld_roomFeatures;
    private JButton btn_addRoom;
    private JButton btn_deleteRoom;
    private JTextField fld_stock;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton btn_searchRooms;
    private DefaultTableModel mdl_hotels;
    private Object [] row_hotels;
    private DefaultTableModel mdl_rooms;
    private Object [] row_rooms;

    public AgencyGUI(User user){
        add(wrapper);
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setTitle(Config.PROJECT_TİTLE);
        setResizable(false);
        setVisible(true);

        mdl_hotels=new DefaultTableModel();
        Object [] col_hotels={"ID","ADI","ADRES","TEL NO","MAİL","YILDIZ","TESİSLER"};
        mdl_hotels.setColumnIdentifiers(col_hotels);
        row_hotels=new Object[col_hotels.length];
        tbl_otels.setModel(mdl_hotels);
        tbl_otels.getTableHeader().setReorderingAllowed(false);
        tbl_otels.getColumnModel().getColumn(0).setMaxWidth(30);
        tbl_otels.getColumnModel().getColumn(5).setMaxWidth(50);
        loadHotelModel();

        mdl_rooms=new DefaultTableModel();
        Object [] col_rooms={"ID","ODA TİPİ","ODA SAYISI","OTEL ADI","ÖZELLİKLERİ"};
        mdl_rooms.setColumnIdentifiers(col_rooms);
        row_rooms=new Object[col_rooms.length];
        tbl_rooms.setModel(mdl_rooms);
        tbl_rooms.getTableHeader().setReorderingAllowed(false);
        tbl_rooms.getColumnModel().getColumn(0).setMaxWidth(30);
        tbl_rooms.getColumnModel().getColumn(2).setMaxWidth(40);
        loadHotelCmb();





        btn_add.addActionListener(e -> {
            HotelGUI hotelGui=new HotelGUI();
            loadHotelModel();
        });

        btn_delete.addActionListener(e -> {
            if(Helper.confirm("sure")){
                int selectedId=Integer.parseInt(tbl_otels.getValueAt(tbl_otels.getSelectedRow(),0).toString());
                Hotel.delete(selectedId);
                Helper.showMsg("success");
                loadHotelModel();
            }
        });

        btn_search.addActionListener(e ->  {
            String name=fld_otelName.getText();
            String query=Hotel.searchQuery(name);
            ArrayList<Hotel> searchingUser=Hotel.searchUserlist(query);
            loadHotelModel(searchingUser);
        });



        cmb_hotels.addActionListener(e -> {
            Item hotelCombo=(Item) cmb_hotels.getSelectedItem();
            int selectedHotel= hotelCombo.getKey();
            loadRoomModel(selectedHotel);
        });

        btn_addRoom.addActionListener(e -> {
            String roomType=cmb_roomType.getSelectedItem().toString();
            String roomfeatures=fld_roomFeatures.getText();
            int roomStock=Integer.parseInt(fld_stock.getText());
            Item hotelCombo=(Item) cmb_hotels.getSelectedItem();
            int selectedHotel= hotelCombo.getKey();
            Room.add(roomType,roomfeatures,roomStock,selectedHotel);
            fld_roomFeatures.setText(null);
            fld_stock.setText(null);
            loadRoomModel(selectedHotel);
        });

        btn_deleteRoom.addActionListener(e -> {
            int selectedRoom=Integer.parseInt(tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(),0).toString());
            Room.delete(selectedRoom);
            Item hotelCombo=(Item) cmb_hotels.getSelectedItem();
            int selectedHotel= hotelCombo.getKey();
            loadRoomModel(selectedHotel);
        });
    }

    private void loadHotelCmb(){
        cmb_hotels.removeAllItems();
        for(Hotel hotel: Hotel.getList()){
            cmb_hotels.addItem(new Item(hotel.getId(), hotel.getName()));
        }
    }

    private void loadHotelModel(){
        DefaultTableModel clearModel= (DefaultTableModel) tbl_otels.getModel();
        clearModel.setRowCount(0);
        for(Hotel hotel: Hotel.getList()){
            row_hotels[0]=hotel.getId();
            row_hotels[1]=hotel.getName();
            row_hotels[2]=hotel.getAddress();
            row_hotels[3]=hotel.getPhoneNumber();
            row_hotels[4]=hotel.geteMail();
            row_hotels[5]=hotel.getStar();
            row_hotels[6]=hotel.getFacilityFeatures();
            mdl_hotels.addRow(row_hotels);
        }
    }

    private void loadRoomModel(int id){
        DefaultTableModel clearModel= (DefaultTableModel) tbl_rooms.getModel();
        clearModel.setRowCount(0);
        for(Room room: Room.getList()){
            if(room.getHotel().getId()==id){
                row_rooms[0]=room.getId();
                row_rooms[1]=room.getType();
                row_rooms[2]=room.getStock();
                row_rooms[3]=room.getHotel().getName();
                row_rooms[4]=room.getFeatures();
                mdl_rooms.addRow(row_rooms);
            }
        }
    }

    private void loadHotelModel(ArrayList<Hotel> hotels){
        DefaultTableModel clearModel= (DefaultTableModel) tbl_otels.getModel();
        clearModel.setRowCount(0);
        for(Hotel hotel: hotels){
            row_hotels[0]=hotel.getId();
            row_hotels[1]=hotel.getName();
            row_hotels[2]=hotel.getAddress();
            row_hotels[3]=hotel.getPhoneNumber();
            row_hotels[4]=hotel.geteMail();
            row_hotels[5]=hotel.getStar();
            row_hotels[6]=hotel.getFacilityFeatures();
            mdl_hotels.addRow(row_hotels);
        }
    }

}
