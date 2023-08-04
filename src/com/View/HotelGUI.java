package com.View;

import com.Helper.Config;
import com.Helper.Helper;
import com.Model.Hotel;
import com.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_name;
    private JTextField fld_address;
    private JTextField fld_phone;
    private JTextField fld_mail;
    private JTextField fld_star;
    private JTextField fld_features;
    private JTextField fld_prize;
    private JButton EKLEButton;

    public HotelGUI(){
        add(wrapper);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setTitle(Config.PROJECT_TİTLE);
        setResizable(false);
        setVisible(true);


        EKLEButton.addActionListener(e -> {
            String name=fld_name.getText();
            String address=fld_address.getText();
            String phoneNumber=fld_phone.getText();
            String eMail=fld_mail.getText();
            String star=fld_star.getText();
            String features=fld_features.getText();
            String prize=fld_prize.getText();
            Hotel.add(name,address,phoneNumber,eMail,star,features,Integer.parseInt(prize));
            fld_name.setText(null);
            fld_prize.setText(null);
            fld_address.setText(null);
            fld_features.setText(null);
            fld_phone.setText(null);
            fld_star.setText(null);
            fld_mail.setText(null);
            dispose();

        });
    }
}
