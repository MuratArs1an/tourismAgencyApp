package com.View;

import com.Helper.Config;
import com.Helper.DbConnector;
import com.Helper.Helper;
import com.Helper.Item;
import com.Model.Operator;
import com.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class OperatorGUI extends JFrame{
    private JPanel wrapper;
    private JComboBox cmb_users;
    private JTextField fld_name;
    private JTextField fld_password;
    private JTable tbl_users;
    private JLabel lbl_welcome;
    private JButton btn_search;
    private JButton btn_add;
    private JButton btn_delete;
    private Operator operator;
    private DefaultTableModel mdl_users;
    private Object [] row_users;

    public OperatorGUI(Operator operator){
        this.operator = operator;
        add(wrapper);
        setSize(600,400);
        int x= Helper.screenCenter("x",getSize());
        int y=Helper.screenCenter("y",getSize());
        setLocation(x,y);
        // x bastıgımızda javanın arkada calısmasını iptal etme
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TİTLE);
        setVisible(true);
        lbl_welcome.setText(operator.getUserName());
        DbConnector.getInstance();
        loadUserTypeCmb();

        mdl_users=new DefaultTableModel();
        Object[] col_users={"ID","Kullanıcı Adı","Şifre","Kullanıcı Tipi"};
        mdl_users.setColumnIdentifiers(col_users);
        row_users=new Object[col_users.length];
        tbl_users.setModel(mdl_users);
        tbl_users.getTableHeader().setReorderingAllowed(false);
        tbl_users.getColumnModel().getColumn(0).setMaxWidth(50);
        loadUserModel();

        btn_search.addActionListener(e ->  {
            String name=fld_name.getText();
            String password=fld_password.getText();
            String type=cmb_users.getSelectedItem().toString();
            String query=User.searchQuery(name,password,type);
            ArrayList<User> searchingUser=User.searchUserlist(query);
            loadUserModel(searchingUser);
        });
    }


    private void loadUserTypeCmb(){
        cmb_users.removeAllItems();
        for(User user: User.getList()){
            cmb_users.addItem(new Item(user.getId(), user.getUserType()));
        }
    }

    private void loadUserModel(){
        DefaultTableModel clearModel= (DefaultTableModel) tbl_users.getModel();
        clearModel.setRowCount(0);
        for(User user: User.getList()){
            row_users[0]=user.getId();
            row_users[1]=user.getUserName();
            row_users[2]=user.getPassword();
            row_users[3]=user.getUserType();
            mdl_users.addRow(row_users);
        }
    }

    private void loadUserModel(ArrayList<User> list){
        DefaultTableModel clearModel= (DefaultTableModel) tbl_users.getModel();
        clearModel.setRowCount(0);
        for(User user: list){
            row_users[0]=user.getId();
            row_users[1]=user.getUserName();
            row_users[2]=user.getPassword();
            row_users[3]=user.getUserType();
            mdl_users.addRow(row_users);
        }
    }

}
