package com.View;

import com.Helper.Config;
import com.Helper.Helper;
import com.Model.Agency;
import com.Model.Operator;
import com.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_userName;
    private JTextField fld_password;
    private JButton btn_login;

    public LoginGUI() {
        add(wrapper);
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setTitle(Config.PROJECT_TİTLE);
        setResizable(false);
        setVisible(true);


        btn_login.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_userName )|| Helper.isFieldEmpty(fld_password)){
                Helper.showMsg("fill");
            }else{
                User user= User.getFetch(fld_userName.getText(),fld_password.getText());
                if(user==null){
                    Helper.showMsg("Kullanıcı Bulunamadı");
                }else{
                    switch (user.getUserType()){
                        case "operator":
                            OperatorGUI opGUI=new OperatorGUI((Operator) user);
                            break;
                        case "agency":
                            AgencyGUI agcGUI=new AgencyGUI((Agency) user);
                            break;
                }
                dispose();
                }
            }
        });
    }
}
