/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

/**
 *
 * @author raaz4
 */
public class Home {
    JFrame frame;
    JPanel panel_head, panel_bottom, panel_button, panel2_button, panel_img, panel_center, panel1;
    JLabel lbl_title, lbl_tSub, logo, mainImg, lbl_contact, lbl_facebook, lbl_twitter, lbl_pic;
    JButton btn_signIn, btn_signUp, btn_recep_regis, btn_recep_login, btn_staff_regis, btn_staff_login;
    ImageIcon img, img1, back;
    JScrollBar scrollPane;

    // JScrollPane scrollPane;
    public void HomePage() {
        frame = new JFrame();
        frame.setTitle("Hotel Luton");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.black);

        // Fonts to select
        Font fTitle = new Font("Segoe UI Black", Font.TRUETYPE_FONT, 70);
        Font font1 = new Font("Courier", Font.PLAIN, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 25);

        // header part
        panel_head = new JPanel();
        img = new ImageIcon("src\\Image\\Hlogo.jpg");
        logo = new JLabel(img);
        logo.setPreferredSize(new Dimension(100, 100));
        panel_head.add(logo);
        lbl_title = new JLabel("Luton Hotel");
        lbl_title.setFont(fTitle);
        panel_head.add(lbl_title);
        panel_head.setBackground(Color.orange);
        panel_head.setBounds(0, 0, 1920, 110);

        // panel button
        panel2_button = new JPanel();
        btn_recep_regis = new JButton("Reception Registration");
        btn_recep_regis.setFont(font1);
        btn_recep_regis.addActionListener((ActionEvent e) -> {
            Recep_registration register_reception = new Recep_registration();
            register_reception.RegistrationForm();
            frame.dispose();
        });
        btn_recep_login = new JButton("Reception Login");
        btn_recep_login.setFont(font1);
        btn_recep_login.addActionListener((ActionEvent e) -> {
            Receptionist_Login login_reception = new Receptionist_Login();
            login_reception.Reception_LoginForm();
            frame.dispose();
        });
        btn_staff_regis = new JButton("Staff Registration");
        btn_staff_regis.setFont(font1);
        btn_staff_regis.addActionListener((ActionEvent e) -> {
            Staff_Registration staff_registration = new Staff_Registration();
            staff_registration.RegistrationForm();
            frame.dispose();
        });
        btn_staff_login = new JButton("Staff Login");
        btn_staff_login.setFont(font1);
        btn_staff_login.addActionListener((ActionEvent e) -> {
            Staff_Login staff_login = new Staff_Login();
            staff_login.Staff_LoginForm();
            frame.dispose();
        });

        panel2_button.setLayout(new GridLayout(4, 1, 10, 10));
        panel2_button.add(btn_recep_regis);
        panel2_button.add(btn_recep_login);
        panel2_button.add(btn_staff_regis);
        panel2_button.add(btn_staff_login);
        panel2_button.setBounds(0, 0, 250, 200);

        // button panel
        panel_button = new JPanel();
        btn_signIn = new JButton("Sign In");
        btn_signIn.setFont(font1);
        btn_signIn.addActionListener((ActionEvent e) -> {
            Login1 log = new Login1();
            log.Login1();
            frame.dispose();

        });
        btn_signUp = new JButton("Sign Up");
        btn_signUp.setFont(font1);
        btn_signUp.addActionListener((ActionEvent e) -> {
            Registration1 reg = new Registration1();
            reg.RegistrationForm();
            frame.dispose();
        });

        panel_button.add(btn_signIn);
        panel_button.add(btn_signUp);
        panel_button.setBounds(800, 0, 220, 50);
        panel_button.setBackground(Color.black);

        // background image panel
        panel_img = new JPanel();
        img1 = new ImageIcon("src\\Image\\background.jpg");
        mainImg = new JLabel(img1);
        panel_img.add(mainImg);
        panel_img.setBounds(0, 0, 1920, 900);

        // center panel consit of elements in center
        panel_center = new JPanel();
        panel_center.setLayout(null);
        panel_center.add(panel_button);
        panel_center.add(panel2_button);
        panel_center.add(panel_img);
        panel_center.setBackground(Color.red);
        panel_center.setBounds(0, 115, 1920, 900);

        // bottom content
        panel_bottom = new JPanel();
        lbl_contact = new JLabel("Contact :  + 977  98XXXXXXXX          ");
        lbl_contact.setFont(font2);
        panel_bottom.add(lbl_contact);
        lbl_twitter = new JLabel("    Web Page : www.HotelLuton.com        ");
        lbl_twitter.setFont(font2);
        panel_bottom.add(lbl_twitter);
        lbl_facebook = new JLabel("    Facebook :Hotel Luton           ");
        lbl_facebook.setFont(font2);
        panel_bottom.add(lbl_facebook);
        panel_bottom.setBounds(0, 960, 1920, 40);

        // frame.setLayout(new FlowLayout());
        frame.setLayout(null);
        frame.add(panel_head);
        frame.add(panel_bottom);
        frame.add(panel_center);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Home obj = new Home();
        obj.HomePage();
    }

}
