
package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class BookFlight extends JFrame implements ActionListener{
    
    JTextField tfaadhar;
    JButton flight,fetch,bookflight;
    JLabel tfname,tfnationality,tfaddress,tfgender,tffname,tffcode;
    Choice csource,cdestination;
    JDateChooser dcdate;
    
    
    BookFlight(){
        
        setLayout(null);
        
        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(420, 20, 500, 40);
        heading.setFont(new Font("Tahoma",Font.PLAIN,32));
        heading.setForeground(Color.BLUE);
        add(heading);
        
        JLabel lblaadhar = new JLabel("Aadhar Number");
        lblaadhar.setBounds(60, 80, 150, 25);
        lblaadhar.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblaadhar );
        
        tfaadhar = new JTextField();
        tfaadhar.setBounds(220, 80, 150, 25);
        add(tfaadhar);
        
        fetch = new JButton("Fetch User");
        fetch.setBackground(Color.BLACK);
        fetch.setForeground(Color.WHITE);
        fetch.setBounds(380, 80, 120, 25);
        fetch.addActionListener(this);
        add(fetch);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(220, 130, 150, 25);
        add(tfname);
        
        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(60, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblnationality );
        
        tfnationality = new JLabel();
        tfnationality.setBounds(220, 180, 150, 25);
        add(tfnationality);
        
               
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbladdress );
        
        tfaddress = new JLabel();
        tfaddress.setBounds(220, 230, 150, 25);
        add(tfaddress);
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblgender );
        
        tfgender = new JLabel();
        tfgender.setBounds(220, 280, 150, 25);
        add(tfgender);
        
        JLabel lblsource = new JLabel("Source");
        lblsource.setBounds(60, 330, 150, 25);
        lblsource.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblsource );
        
        csource = new Choice();
        csource.setBounds(220, 330, 150, 25);
        add(csource);
        
        JLabel lbldestination = new JLabel("Destination");
        lbldestination.setBounds(60, 380, 150, 25);
        lbldestination.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbldestination );
        
        cdestination = new Choice();
        cdestination.setBounds(220, 380, 150, 25);
        add(cdestination);
        
        try{
            Conn c = new Conn();
            
            //USE FOR AVOID DUPLICITY IN CHOICE DROPDOWN
            
            // For Source
            ResultSet rs1 = c.s.executeQuery("SELECT DISTINCT source FROM flight");
            while(rs1.next()){
                csource.add(rs1.getString("source"));
            }

            // For Destination
            ResultSet rs2 = c.s.executeQuery("SELECT DISTINCT destination FROM flight");
            while(rs2.next()){
                cdestination.add(rs2.getString("destination"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
                
        flight = new JButton("Fetch Flights");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(380, 380, 120, 25);
        flight.addActionListener(this);
        add(flight);
        
        JLabel lblfname = new JLabel("Flight Name");
        lblfname.setBounds(60, 430, 150, 25);
        lblfname.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblfname);
        
        tffname = new JLabel();
        tffname.setBounds(220, 430, 150, 25);
        add(tffname);
        
        JLabel lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(60, 480, 150, 25);
        lblfcode.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblfcode);
        
        tffcode = new JLabel();
        tffcode.setBounds(220, 480, 150, 25);
        add(tffcode);
        
        JLabel lbldate = new JLabel("Date of Travel");
        lbldate.setBounds(60, 530, 150, 25);
        lbldate.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbldate);
        
        dcdate = new JDateChooser();
        dcdate.setBounds(220, 530, 150, 25);
        add(dcdate);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons_airline/details.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        JLabel image = new JLabel(i3);
        image.setBounds(550,80,500,410);
        add(image);
        
        
        bookflight = new JButton("Book Flight");
        bookflight.setBackground(Color.BLACK);
        bookflight.setForeground(Color.WHITE);
        bookflight.setBounds(220, 580, 150, 25);
        bookflight.addActionListener(this);
        add(bookflight);
                               
        getContentPane().setBackground(Color.WHITE);
        setBounds(200,50,1100,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
     public void actionPerformed(ActionEvent ae){
         
         if(ae.getSource() == fetch){
             String aadhar = tfaadhar.getText();
             
             try{
                 Conn c = new Conn();
                 
                 String query = "select * from passenger where aadhar = '"+aadhar+"'";
                 
                 ResultSet rs = c.s.executeQuery(query);
                 
                 if(rs.next()){
                     tfname.setText(rs.getString("name"));
                     tfnationality.setText(rs.getString("nationality"));
                     tfaddress.setText(rs.getString("address"));
                     tfgender.setText(rs.getString("gender"));
                 }else{
                     JOptionPane.showMessageDialog(null, "Please enter correct aadhar");
                 }
             }catch(Exception e){
                 e.printStackTrace();
             }
            
         }else if(ae.getSource() == flight){
             String src = csource.getSelectedItem();
             String dest = cdestination.getSelectedItem();
             
             try{
                 Conn c = new Conn();
                 
                 String query = "select * from flight where source = '"+src+"'and destination = '"+dest+"'";
                 
                 ResultSet rs = c.s.executeQuery(query);
                 
                 if(rs.next()){
                     tffname.setText(rs.getString("f_name"));
                     tffcode.setText(rs.getString("f_code"));
                 }else{
                     JOptionPane.showMessageDialog(null, "No Flights Found");
                 }
             }catch(Exception e){
                 e.printStackTrace();
             }
            
         }else if(ae.getSource() == bookflight){
             
             Random random = new Random();
             
                String aadhar = tfaadhar.getText();
                String name = tfname.getText();
                String nationality = tfnationality.getText();
                String flightname = tffname.getText();
                String flightcode = tffcode.getText();
                String src = csource.getSelectedItem();
                String dest = cdestination.getSelectedItem();
                String ddate = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
                
                try{
                 Conn c = new Conn();
                 
                 String query = "insert into reservation values('PNR-"+random.nextInt(1000000)+"','TIC-"+random.nextInt(10000)+"','"+aadhar+"','"+name+"','"+nationality+"','"+flightname+"','"+flightcode+"','"+src+"','"+dest+"','"+ddate+"')";
                 
                 c.s.executeUpdate(query);
                 
                 JOptionPane.showMessageDialog(null, "Ticket Booked Successfully");
                 setVisible(false);
                
                }catch(Exception e){
                        e.printStackTrace();
                       }
                
         }
         
     }
    
    public static void main(String args[]){
        new BookFlight();
    }
}
