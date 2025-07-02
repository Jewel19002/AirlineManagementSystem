
package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Cancel extends JFrame implements ActionListener{
    
    JTextField tfpnr;
    JButton show,cancel;
    JLabel tfname,tfcancellation,tfflightcode,tfdateoftravel,tffcode;
    
    
    Cancel(){
        
        setLayout(null);
        
        Random ran = new Random();
        
        JLabel heading = new JLabel("CANCELLATION");
        heading.setBounds(180, 20, 250, 30);
        heading.setFont(new Font("Tahoma",Font.PLAIN,32));
        add(heading);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons_airline/cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(470,120,250,250);
        add(image);
        
        JLabel lblpnr = new JLabel("PNR Number");
        lblpnr.setBounds(60, 80, 150, 25);
        lblpnr.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblpnr );
        
        tfpnr = new JTextField();
        tfpnr.setBounds(220, 80, 150, 25);
        add(tfpnr);
        
        show = new JButton("Show Details");
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
        show.setBounds(380, 80, 120, 25);
        show.addActionListener(this);
        add(show);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(220, 130, 150, 25);
        add(tfname);
        
        JLabel lblcancellation = new JLabel("Cancellation No");
        lblcancellation.setBounds(60, 180, 150, 25);
        lblcancellation.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblcancellation );
        
        tfcancellation = new JLabel("" + ran.nextInt(1000000));
        tfcancellation.setBounds(220, 180, 150, 25);
        add(tfcancellation);
        
               
        JLabel lblflightcode = new JLabel("Flight Code");
        lblflightcode.setBounds(60, 230, 150, 25);
        lblflightcode.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblflightcode );
        
        tfflightcode = new JLabel();
        tfflightcode.setBounds(220, 230, 150, 25);
        add(tfflightcode);
        
        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60, 280, 150, 25);
        lbldate.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbldate );
        
        tfdateoftravel = new JLabel();
        tfdateoftravel.setBounds(220, 280, 150, 25);
        add(tfdateoftravel);
      
        cancel = new JButton("Confirm Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(220, 330, 150, 25);
        cancel.addActionListener(this);
        add(cancel);
        
                                    
        getContentPane().setBackground(Color.WHITE);
        setBounds(350,150,800,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
     public void actionPerformed(ActionEvent ae){
         
         if(ae.getSource() == show){
             String pnr = tfpnr.getText();
             try{
                 Conn c = new Conn();
                 
                 String query = "select * from reservation where PNR = '"+pnr+"'";
                 
                 ResultSet rs = c.s.executeQuery(query);
                 
                 if(rs.next()){
                     tfname.setText(rs.getString("name"));
                     tfflightcode.setText(rs.getString("flightcode"));
                     tfdateoftravel.setText(rs.getString("ddate"));
                 }
             }catch(Exception e){
                 e.printStackTrace();
             }
         }else if(ae.getSource() == cancel){
             String pnr = tfpnr.getText();
             String name = tfname.getText();
             String cancelno = tfcancellation.getText();
             String flightcode = tfflightcode.getText();
             String date = tfdateoftravel.getText();
             try{
                 Conn c = new Conn();
                 
                 String query = "insert into cancel values('"+pnr+"','"+name+"','"+cancelno+"','"+flightcode+"','"+date+"')";
                 
                 c.s.executeUpdate(query);
                 
                 c.s.executeUpdate("delete from reservation where PNR = '"+pnr+"'");
                 
                 JOptionPane.showMessageDialog(null, "Ticket Cancelled Successfully");
                 setVisible(false);
                 new Home();
                 
                }catch(Exception e){
                 e.printStackTrace();
                 }
         }
     }
    
    public static void main(String args[]){
        new Cancel();
    }
}

