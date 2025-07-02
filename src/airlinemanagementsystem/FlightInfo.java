
package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class FlightInfo extends JFrame implements ActionListener{
    
    JTable table; 
    
    FlightInfo(){
        
        
        setLayout(null);
        
        table = new JTable();
        
        try{
            Conn c = new Conn();
            
            ResultSet rs = c.s.executeQuery("select * from flight");
            //we import jar file for show the data in table form.
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0,0,800,500);
        add(jsp);
        
        
        setBounds(400,200,800,500);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        
        
    }

    public static void main(String args[]){
        new FlightInfo();
    }
}
