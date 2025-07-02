
package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    
    JTextField tfusername;
    JPasswordField tfpass;
    JButton reset,submit,close;
    
    Login(){
        
        setLayout(null);
        
        
        JLabel lbllogin = new JLabel("Username");
        lbllogin.setBounds(20, 20, 100, 20);
        add(lbllogin);
        
        tfusername = new JTextField();
        tfusername.setBounds(130, 20, 200, 20);
        add(tfusername);
        
        JLabel lblpass = new JLabel("Password");
        lblpass.setBounds(20, 60, 100, 20);
        add(lblpass);
        
        tfpass = new JPasswordField();
        tfpass.setBounds(130, 60, 200, 20);
        add(tfpass);
        
        reset = new JButton("Reset");
        reset.setBounds(40,120,120,20);
        reset.addActionListener(this); 
        add(reset);
        
        submit = new JButton("Submit");
        submit.setBounds(190,120,120,20);
        submit.addActionListener(this); 
        add(submit);
        
        close = new JButton("Close");
        close.setBounds(120,160,120,20);
        close.addActionListener(this); 
        add(close);
        
        setBounds(600,250,400,250);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == reset){
            tfusername.setText("");
            tfpass.setText("");
        }else if(ae.getSource() == submit){
            String username = tfusername.getText();
            String pass = tfpass.getText();
            
            try{
                Conn c = new Conn();
                
                String query = "select * from login where username = '"+username+"' and password = '"+pass+"'";
                ResultSet rs = c.s.executeQuery(query);
                if(rs.next()){
                    new Home();
                    setVisible(false);

                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    setVisible(false);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource() == close){
            setVisible(false);
        }
        
        
    }
    
    public static void main(String args[]){
        
        new Login(); //Anonymous Object
    }
}
