
package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Home extends JFrame implements ActionListener{
    
       
    Home(){
        
        setLayout(null);
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons_airline/front.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0,0,1600,800);
        add(image);
        
        JLabel heading = new JLabel("AIR INDIA WELCOMES YOU");
        heading.setBounds(500,40,1000,40);
        heading.setForeground(Color.BLUE);
        heading.setFont(new Font("Tahoma",Font.PLAIN,36));
        image.add(heading);
        
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        JMenu details = new JMenu("Details");
        menubar.add(details);
        
        JMenuItem flightdetails = new JMenuItem("Flight Details");
        flightdetails.addActionListener(this);
        details.add(flightdetails);
        
        JMenuItem customerdetails = new JMenuItem("Add Customer Details");
        customerdetails.addActionListener(this);
        details.add(customerdetails);
        
        JMenuItem bookflight = new JMenuItem("Book Flight");
        bookflight.addActionListener(this);
        details.add(bookflight);
        
        JMenuItem journeydetails = new JMenuItem("Journey Details");
        journeydetails.addActionListener(this);
        details.add(journeydetails);
        
        JMenuItem ticketcancellation = new JMenuItem("Cancel Ticket");
        details.add(ticketcancellation);
        
        
        JMenu ticket = new JMenu("Ticket");
        menubar.add(ticket);
        
        JMenuItem boardingpass = new JMenuItem("Boarding Pass");
        ticket.add(boardingpass);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH); //For Open Full Screen
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        
        String text = ae.getActionCommand();
        
        if(text.equals("Add Customer Details")){
            new AddCustomer();
        }else if(text.equals("Flight Details")){
            new FlightInfo();
        }else if(text.equals("Book Flight")){
            new BookFlight();
        }else if(text.equals("Journey Details")){
            new JourneyDetails();
        }
            
    }
    
    public static void main(String args[]){
        
        new Home(); //Anonymous Object
    }
}
