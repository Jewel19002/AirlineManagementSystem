
package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.print.*;


public class BoardingPass extends JFrame implements ActionListener{
    
    JTextField tfpnrdetails;
    JButton enter,print;
    JLabel tfname,tfnationality,tfsource,tfdestination,tffname,tffcode,lblboardingdate;
     
    
    BoardingPass(){
        
        setLayout(null);
        
        JLabel heading = new JLabel("AIR INDIA");
        heading.setBounds(380, 10, 450, 35);
        heading.setFont(new Font("Tahoma",Font.PLAIN,32));
        add(heading);
        
        JLabel subheading = new JLabel("Boarding Pass");
        subheading.setBounds(360, 50, 300, 30);
        subheading.setFont(new Font("Tahoma",Font.PLAIN,24));
        subheading.setForeground(Color.BLUE);
        add(subheading);
        
        JLabel lblpnrdetails = new JLabel("PNR DETAILS");
        lblpnrdetails.setBounds(60, 100, 150, 25);
        lblpnrdetails.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblpnrdetails );
        
        tfpnrdetails = new JTextField();
        tfpnrdetails.setBounds(220, 100, 150, 25);
        add(tfpnrdetails);
        
        enter = new JButton("Enter");
        enter.setBackground(Color.BLACK);
        enter.setForeground(Color.WHITE);
        enter.setBounds(380, 100, 120, 25);
        enter.addActionListener(this);
        add(enter);
        
        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(60, 140, 150, 25);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(220, 140, 150, 25);
        add(tfname);
        
        JLabel lblnationality = new JLabel("NATIONALITY");
        lblnationality.setBounds(60, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblnationality );
        
        tfnationality = new JLabel();
        tfnationality.setBounds(220, 180, 150, 25);
        add(tfnationality);
        
               
        JLabel lblsource = new JLabel("SRC");
        lblsource.setBounds(60, 220, 150, 25);
        lblsource.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblsource );
        
        tfsource = new JLabel();
        tfsource.setBounds(220, 220, 150, 25);
        add(tfsource);
        
        JLabel lbldestination = new JLabel("DEST");
        lbldestination.setBounds(380, 220, 150, 25);
        lbldestination.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbldestination );
        
        tfdestination = new JLabel();
        tfdestination.setBounds(540, 220, 150, 25);
        add(tfdestination);
                              
               
        JLabel lblfname = new JLabel("FLIGHT NAME");
        lblfname.setBounds(60, 260, 150, 25);
        lblfname.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblfname);
        
        tffname = new JLabel();
        tffname.setBounds(220, 260, 150, 25);
        add(tffname);
        
        JLabel lblfcode = new JLabel("FLIGHT CODE");
        lblfcode.setBounds(380, 260, 150, 25);
        lblfcode.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblfcode);
        
        tffcode = new JLabel();
        tffcode.setBounds(540, 260, 150, 25);
        add(tffcode);
        
        JLabel lbldate = new JLabel("DATE");
        lbldate.setBounds(60, 300, 150, 25);
        lbldate.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbldate);
        
        lblboardingdate = new JLabel();
        lblboardingdate.setBounds(220, 300, 150, 25);
        add(lblboardingdate);
        
        print = new JButton("PRINT");
        print.setBackground(Color.BLACK);
        print.setForeground(Color.WHITE);
        print.setBounds(160, 350, 150, 25);
        print.addActionListener(this);
        add(print);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons_airline/airindia.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 230, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        JLabel image = new JLabel(i3);
        image.setBounds(600,0,300,300);
        add(image);
                     
                                  
        getContentPane().setBackground(Color.WHITE);
        setBounds(300,150,1000,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    public void print() {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setJobName("Print Boarding Pass");

                job.setPrintable(new Printable() {
                    public int print(Graphics pg, PageFormat pf, int pageNum) {
                        if (pageNum > 0) {
                            return Printable.NO_SUCH_PAGE;   
                        }

                        Graphics2D g2 = (Graphics2D) pg;
                        g2.translate(pf.getImageableX(), pf.getImageableY());
                        g2.scale(0.9, 0.9); // scale to fit the page
                        getContentPane().printAll(g2); // prints the full UI of the JFrame
                        return Printable.PAGE_EXISTS;
                    }
                });

                boolean ok = job.printDialog();
                if (ok) {
                    try {
                        job.print();
                    } catch (PrinterException ex) {
                        ex.printStackTrace();
                    }
                }
    }

     public void actionPerformed(ActionEvent ae){
         if(ae.getSource() == enter){
                String pnr = tfpnrdetails.getText();
                try{
                    Conn c = new Conn();

                    String query = "select * from reservation where PNR = '"+pnr+"'";

                    ResultSet rs = c.s.executeQuery(query);

                    if(rs.next()){
                        tfname.setText(rs.getString("name"));
                        tfnationality.setText(rs.getString("nationality"));
                        tfsource.setText(rs.getString("src"));
                        tfdestination.setText(rs.getString("dest"));
                        tffname.setText(rs.getString("flightname"));
                        tffcode.setText(rs.getString("flightcode"));
                        lblboardingdate.setText(rs.getString("ddate"));
                    }


                }catch(Exception e){
                    e.printStackTrace();
                }
         
         }else if(ae.getSource() == print){
             print();
         }
     }
    
    public static void main(String args[]){
        new BoardingPass();
    }
}
