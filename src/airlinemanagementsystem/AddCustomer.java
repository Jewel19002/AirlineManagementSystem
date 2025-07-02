
package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCustomer extends JFrame implements ActionListener{
    
    JTextField tfname,tfnationality,tfaadhar,tfaddress,tfphone;
    JRadioButton rbmale,rbfemale;
    JButton save;
    
    AddCustomer(){
        
        setLayout(null);
        
        JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
        heading.setBounds(230, 20, 500, 35);
        heading.setFont(new Font("Tahoma",Font.PLAIN,32));
        heading.setForeground(Color.BLUE);
        add(heading);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 80, 150, 25);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(220, 80, 150, 25);
        add(tfname);
        
        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(60, 130, 150, 25);
        lblnationality.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblnationality );
        
        tfnationality = new JTextField();
        tfnationality.setBounds(220, 130, 150, 25);
        add(tfnationality);
        
        JLabel lblaadhar = new JLabel("Aadhar Number");
        lblaadhar.setBounds(60, 180, 150, 25);
        lblaadhar.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblaadhar );
        
        tfaadhar = new JTextField();
        tfaadhar.setBounds(220, 180, 150, 25);
        add(tfaadhar);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbladdress );
        
        tfaddress = new JTextField();
        tfaddress.setBounds(220, 230, 150, 25);
        add(tfaddress);
        
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblgender );
        
        ButtonGroup gendergroup= new ButtonGroup();
        
        rbmale = new JRadioButton("Male");
        rbmale.setBounds(220, 280, 70, 25);
        rbmale.setBackground(Color.WHITE);
        add(rbmale);
        
        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(300, 280, 70, 25);
        rbfemale.setBackground(Color.WHITE);
        add(rbfemale);
        
        gendergroup.add(rbmale);
        gendergroup.add(rbfemale);
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(60, 330, 150, 25);
        lblphone.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblphone );
        
        tfphone = new JTextField();
        tfphone.setBounds(220, 330, 150, 25);
        add(tfphone);
        
        save = new JButton("Save");
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.setBounds(220, 380, 150, 30);
        save.addActionListener(this);
        add(save);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons_airline/emp.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(450,80,280,400);
        add(image);
                               
        getContentPane().setBackground(Color.WHITE);
        setBounds(300,150,900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
     public void actionPerformed(ActionEvent ae){
         String name = tfname.getText();
         String nationality = tfnationality.getText();
         String aadhar = tfaadhar.getText();
         String address = tfaddress.getText();
         String phone = tfphone.getText();
         
         String gender = null;
         if(rbmale.isSelected()){
             gender = "Male";
         }else{
             gender = "Female";
         }
         
         if(ae.getSource() == save){
             try{
                 Conn c = new Conn();
                 
                 String query = "insert into passenger values('"+name+"','"+nationality+"','"+aadhar+"','"+address+"','"+phone+"','"+gender+"')";
                 c.s.executeUpdate(query); //DML Comand
                 JOptionPane.showMessageDialog(null, "Customer Details Added Successf^ully");
                 setVisible(false);
                 new Home();
             }catch(Exception e){
                 e.printStackTrace();
             }
         }
     }
    
    public static void main(String args[]){
        new AddCustomer();
    }
}
