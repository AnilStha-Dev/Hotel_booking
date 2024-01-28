
package Pages;

import classes.Global;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author raaz4
 */
public class Receptionist_Login implements ActionListener {

    JFrame frame;
    JPanel panel1, panel2;
    JLabel lbl_pic;
    JLabel lbl_heading, lbl_underscore1, lbl_underscore2;
    JLabel lbl_email, lbl_password;
    JLabel gap, gap1, gap2;
    JButton btn_login, btn_cancel, btn_back;
    JTextField txt_email;
    JPasswordField password;
    JSplitPane splitPane;
    ImageIcon img;

    String HOST = "localhost";
    int PORT = 3306;
    String USER = "root";
    String PASSWORD = "";
    String DBNAME = "hotel_booking_system";
    String DRIVER = "com.mysql.cj.jdbc.Driver";
    String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME;

    public void Reception_LoginForm() {
        frame = new JFrame();
        frame.setTitle("Luton Hotel - Reception Login ");
        frame.setSize(1500, 891);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.ORANGE);
//fonts used this in frame
        Font fTitle = new Font("Segoe UI Black", Font.TRUETYPE_FONT, 50);
        Font fEmail = new Font("Courier", Font.PLAIN, 20);

//picture of the hotel in panel1
        panel1 = new JPanel();
        img = new ImageIcon("src\\Image\\login1.jpg");
        lbl_pic = new JLabel(img);
        lbl_pic.setPreferredSize(new Dimension(1000, 891));
        panel1.add(lbl_pic);
        panel1.setBackground(Color.getHSBColor(100, 5, 80));

        panel2 = new JPanel();
        lbl_underscore1 = new JLabel("_________________________________");
        lbl_heading = new JLabel(" Log In - Form ");
        lbl_underscore2 = new JLabel("______________________________________________________");
        lbl_heading.setFont(fTitle);
        img = new ImageIcon("src\\Image\\LoginR logo.jpg");
        lbl_pic = new JLabel(img);
        lbl_pic.setPreferredSize(new Dimension(250, 250));

//Enter email and password 
        lbl_email = new JLabel("Enter Email : ");
        lbl_email.setFont(fEmail);
        txt_email = new JTextField();
        txt_email.setFont(fEmail);

        txt_email.setPreferredSize(new Dimension(300, 35));

        gap = new JLabel("                                                      "
                + "                                                             ");
        gap1 = new JLabel("                                                      "
                + "                                                             ");
        gap2 = new JLabel("                                                         "
                + "                                                                 ");
        lbl_password = new JLabel("Password   : ");
        lbl_password.setFont(fEmail);
        password = new JPasswordField();
        password.setFont(fEmail);
        password.setPreferredSize(new Dimension(300, 35));

//buttons
        btn_login = new JButton("  Login  ");
        btn_login.setFont(fEmail);
        btn_login.addActionListener(this);

        btn_back = new JButton("Back");
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home();
                home.HomePage();
                frame.dispose();
            }

        });
        btn_back.setFont(fEmail);

        panel2.setBackground(Color.getHSBColor(100, 5, 80));
        panel2.add(lbl_pic);
        panel2.add(lbl_underscore1);
        panel2.add(lbl_heading);
        panel2.add(lbl_underscore2);
        panel2.add(gap);
        panel2.add(lbl_email);
        panel2.add(txt_email);
        panel2.add(gap1);
        panel2.add(lbl_password);
        panel2.add(password);
        panel2.add(gap2);
        panel2.add(btn_login);
        panel2.add(btn_back);

        splitPane = new JSplitPane(SwingConstants.VERTICAL, panel1, panel2);
        frame.setContentPane(splitPane);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_login) {
            Connection conn = null;
            try {
                //connect with database
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.print("connection sucessfull");
            } catch (Exception ex) {
                System.out.println("Error : " + ex.getMessage());
            }
            String Email = txt_email.getText();
            String psd = String.valueOf(password.getPassword());

            try {
                //check if receptionist ID is registered or not 
                String sql = "SELECT * FROM receptionist where Email = ? AND Password = ?";
                PreparedStatement pstat = conn.prepareStatement(sql);
                pstat.setString(1, Email);
                pstat.setString(2, psd);
                ResultSet rs = pstat.executeQuery();
                if (rs.next()) {
                    Global.receptionEmail = rs.getString("Email");
                    System.out.println(" \n user found");
                    Reception3 obj = new Reception3();
                    obj.ReceptionI();
                    frame.dispose();

                } else {
                    String message = " User Not Found ";
                    JOptionPane.showMessageDialog(null, message, "Invalid credential", JOptionPane.ERROR_MESSAGE);
                }
                //getting the receptionist ID from database 
                try {

                    String query = "SELECT receptionistId FROM receptionist where Email = ?";
                    PreparedStatement prest = conn.prepareStatement(query);
                    prest.setString(1, Global.receptionEmail);
                    ResultSet re = prest.executeQuery();
                    if (re.next()) {
                        Global.receptionID = re.getInt("ReceptionistId");
                        System.out.println("Reception ID : " + Global.receptionID);
                        System.out.println(" Receptionist ID saved in global.receptionID");
                    }
                    prest.close();
                    re.close();

                } catch (Exception ex) {
                    System.out.println("Error : " + ex.getMessage());
                }

                pstat.close();
                rs.close();
                conn.close();
            } catch (Exception ex) {
                //if any errors occured error message will display
                System.out.println("Error : " + ex.getMessage());
            }
        }
    }
}
