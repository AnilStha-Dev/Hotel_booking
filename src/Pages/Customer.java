
package Pages;

import CustomerAndBooking_DataStore.BookingDetails_update;
import CustomerAndBooking_DataStore.Booking_data;
import CustomerAndBooking_DataStore.Booking_view_data;
import Database.JDBC_booking;
import classes.Global;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raaz4
 */
public class Customer implements ActionListener {

    JFrame frame;
//heading of the page
    JLabel lbl_welcome, lbl_name;
    JPanel panel_head;
    ImageIcon img;
    JLabel logo, lbl_title;
//customer booking tab
    JPanel panel_center, panel_single, panel_middle, panel_last, panel4;
    JLabel lbl1_img, lbl_s, lbl2_img, lbl_t, lbl3_img, lbl6, lbl7, lbl8;
    JButton btn_signOut, btn_book, btn2_book, btn3_book, close_button, tb_book, tb_view;
    ImageIcon img1, img2, img3;
    JToolBar toolbar;
    JPanel panel1_middle,panel_data_store, panel_data;
    JLabel lbl_roomType, lbl_checkIn, lbl_checkOut, lbl_price;
    JTextField txt_checkIn, txt_checkOut, txt_price;
    JComboBox cmb_roomType;
//view tab
    JPanel panel_view, panel_viewT, panel2_data, panel2_data_store, panel2_button;
    JScrollPane Sc2_table;
    JLabel lbl2_booking, lbl2_roomType, lbl2_checkIn, lbl2_checkOut;
    JTextField txt2_booking, txt2_checkIn, txt2_checkOut;
    JButton btn2_clear, btn2_cancel, btn2_update;
    JComboBox cmb2_roomType;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JTable table1;
    boolean result;
    //connection to database
    String HOST = "localhost";
    int PORT = 3306;
    String USER = "root";
    String PASSWORD = "";
    String DBNAME = "hotel_booking_system";
    String DRIVER = "com.mysql.cj.jdbc.Driver";
    String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME;

    public void customerN() {
        frame = new JFrame();
        frame.setTitle("Customer Interface");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.ORANGE);
        frame.setLayout(null);

        //Fonts to select
        Font fTitle = new Font("Segoe UI Black", Font.TRUETYPE_FONT, 70);
        Font font1 = new Font("Courier", Font.PLAIN, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 25);
//*****************************************Heading************************************
        //header part
        panel_head = new JPanel();
        img = new ImageIcon("src\\Image\\Hlogo.jpg");
        logo = new JLabel(img);
        logo.setPreferredSize(new Dimension(100, 100));
        logo.setBounds(650, 5, 100, 100);
        lbl_title = new JLabel("Luton Hotel");
        lbl_title.setFont(fTitle);
        lbl_title.setBounds(850, 0, 600, 100);
        btn_signOut = new JButton("Sign Out");
        btn_signOut.setFont(font1);
        btn_signOut.addActionListener((ActionEvent e) -> {
            Home obj = new Home();
            obj.HomePage();
            this.frame.dispose();
        });
        btn_signOut.setBounds(1700, 50, 120, 50);
        panel_head.setLayout(null);
        panel_head.add(logo);
        panel_head.add(lbl_title);
        panel_head.add(btn_signOut);
        panel_head.setBackground(Color.orange);
        panel_head.setBounds(0, 0, 1920, 110);

        //center part
        toolbar = new JToolBar();
        toolbar.setRollover(true);
        tb_book = new JButton("Book A Room");
        tb_book.setFont(font1);
        toolbar.add(tb_book);
        toolbar.addSeparator();
        tb_view = new JButton("View Bookings");
        tb_view.setFont(font1);
        toolbar.add(tb_view);
        toolbar.setBounds(0, 0, 1920, 50);

//center panel
        panel_center = new JPanel();
//****************************************Make a Booking tab*******************************

        //booking data
        panel_data_store = new JPanel();
        panel_data = new JPanel();
        lbl_roomType = new JLabel("Room Type : ");
        lbl_roomType.setFont(font1);
        Vector roomType = new Vector();
        roomType.add("Single");
        roomType.add("Twin");
        roomType.add("Double");
        cmb_roomType = new JComboBox(roomType);
        cmb_roomType.setFont(font1);
        lbl_checkIn = new JLabel("Check-In Date : ");
        lbl_checkIn.setFont(font1);
        txt_checkIn = new JTextField("DD-MM-YYYY");
        txt_checkIn.setFont(font1);
        lbl_checkOut = new JLabel("Check-Out Date : ");
        lbl_checkOut.setFont(font1);
        txt_checkOut = new JTextField("DD-MM-YYYY");
        txt_checkOut.setFont(font1);
        lbl_price = new JLabel("Room Price : ");
        lbl_price.setFont(font1);
        txt_price = new JTextField();
        txt_price.setFont(font1);

        btn_book = new JButton(" BOOK NOW");
        btn_book.setFont(font1);
        btn_book.setBounds(100, 550, 170, 50);
        btn_book.addActionListener(this);

        panel_data.setLayout(new GridLayout(4, 2, -10, 80));
        panel_data.add(lbl_roomType);
        panel_data.add(cmb_roomType);
        panel_data.add(lbl_checkIn);
        panel_data.add(txt_checkIn);
        panel_data.add(lbl_checkOut);
        panel_data.add(txt_checkOut);
        panel_data.setBackground(Color.red);
        panel_data.setBounds(0, 0, 370, 500);
        panel_data_store.add(btn_book);
        panel_data_store.add(panel_data);
        panel_data_store.setLayout(null);
        panel_data_store.setBounds(20, 100, 370, 750);
        panel_data_store.setBackground(Color.red);

//panel for single room type 
        panel_single = new JPanel();
        lbl1_img = new JLabel();
        img1 = new ImageIcon("src\\Image\\basic.jpg");
        lbl1_img = new JLabel(img1);
        lbl1_img.setPreferredSize(new Dimension(400, 450));
        lbl_s = new JLabel();
        lbl_s.setText("<html>     Services offered in this Booking <br> "
                + " -Number of bed = Single <br> "
                + "  -A/C = NO  <br> "
                + "  -Heater = NO  <br>"
                + "-Room Price : Rs 3000/- </html>");
        lbl_s.setFont(new Font("Boulder", Font.PLAIN, 20));

        panel_single.add(lbl1_img);
        panel_single.add(lbl_s);
        panel_single.setBounds(420, 50, 454, 750);

//panel middle for twin room
        panel_middle = new JPanel();
        lbl2_img = new JLabel();
        img2 = new ImageIcon("src\\Image\\standard.jpg");
        lbl2_img = new JLabel(img2);
        lbl2_img.setPreferredSize(new Dimension(500, 400));
        lbl_t = new JLabel();
        lbl_t.setText("<html><br><br>Services offered in this Booking <br> "
                + " -Number of bed = Twin <br> "
                + "-Heater = NO  <br>  "
                + " -A/C = YES  <br> "
                + "-Room Price : Rs 4000/- </html> ");
        lbl_t.setFont(new Font("Boulder", Font.PLAIN, 20));

        panel_middle.add(lbl2_img);
        panel_middle.add(lbl_t);
        panel_middle.setBounds(920, 50, 454, 750);

//last panel for room type (double)
        panel_last = new JPanel();
        lbl3_img = new JLabel();
        img3 = new ImageIcon("src\\Image\\deluxe.jpg");
        lbl3_img = new JLabel(img3);
        lbl3_img.setPreferredSize(new Dimension(500, 450));
        lbl3_img.setBounds(480, 50, 1300, 350);
        

        lbl6 = new JLabel();
        lbl6.setText("<html>  Services offered in this Booking<br>"
                + "  -Number of Bed : Double <br> "
                + "  -A/C = YES  <br>   "
                + "  -Heater = YES   <br>"
                + "Room Price : Rs 5000/- </html>");
        lbl6.setBounds(1050, 300, 909, 400);
        lbl6.setFont(new Font("Boulder", Font.PLAIN, 20));

        panel_last.add(lbl3_img);
        panel_last.add(lbl6);
        panel_last.setBounds(1420, 50, 454, 750);

        panel_center.add(panel_data_store);
        panel_center.add(panel_single);
        panel_center.add(panel_middle);
        panel_center.add(panel_last);
        panel_center.setBackground(Color.red);
        panel_center.setLayout(null);
        
        
//****************************************** view tab************************************************

        
        panel2_data = new JPanel();
        lbl2_booking = new JLabel("Booking No. : ");
        lbl2_booking.setFont(font1);
        txt2_booking = new JTextField();
        txt2_booking.setFont(font1);
        lbl2_roomType = new JLabel("Room Type : ");
        lbl2_roomType.setFont(font1);
        Vector viewtab = new Vector();
        viewtab.add("Single");
        viewtab.add("Twin");
        viewtab.add("Double");
        cmb2_roomType = new JComboBox(viewtab);
        cmb2_roomType.setFont(font1);
        lbl2_checkIn = new JLabel("Check-In Date : ");
        lbl2_checkIn.setFont(font1);
        txt2_checkIn = new JTextField("DD-MM-YYYY");
        txt2_checkIn.setFont(font1);
        lbl2_checkOut = new JLabel("Check-Out Date : ");
        lbl2_checkOut.setFont(font1);
        txt2_checkOut = new JTextField("DD-MM-YYYY");
        txt2_checkOut.setFont(font1);

        panel2_data.setLayout(new GridLayout(4, 2, -30, 50));
        panel2_data.add(lbl2_booking);
        panel2_data.add(txt2_booking);
        panel2_data.add(lbl2_roomType);
        panel2_data.add(cmb2_roomType);
        panel2_data.add(lbl2_checkIn);
        panel2_data.add(txt2_checkIn);
        panel2_data.add(lbl2_checkOut);
        panel2_data.add(txt2_checkOut);
        panel2_data.setBounds(20, 70, 450, 400);
        panel2_data.setBackground(Color.red);

//button panel consisting of button for view tab
        panel2_button = new JPanel();
        btn2_update = new JButton("Update");
        btn2_update.setFont(font1);
        btn2_update.addActionListener(this);

        btn2_cancel = new JButton("Cancel BK");
        btn2_cancel.setFont(font1);
        btn2_cancel.addActionListener(this);

        btn2_clear = new JButton("Clear");
        btn2_clear.setFont(font1);
        btn2_clear.addActionListener(this);

        panel2_button.setLayout(new GridLayout(1, 3, 20, 0));
        panel2_button.add(btn2_update);
        panel2_button.add(btn2_cancel);
        panel2_button.add(btn2_clear);
        panel2_button.setBounds(15, 620, 450, 50);
        panel2_button.setBackground(Color.red);

//view bookings table
        panel_view = new JPanel();
        panel_viewT = new JPanel();
        tableModel = new DefaultTableModel();
        table1 = new JTable(tableModel);

        table1.setFillsViewportHeight(true);

        tableModel.addColumn("Booking No");
        tableModel.addColumn("NAME");
        tableModel.addColumn("Room Type");
        tableModel.addColumn("Check In date");
        tableModel.addColumn("Check Out date");
        tableModel.addColumn("Room No");
        tableModel.addColumn("Booking Status");
        //tableModel.addRow(new Object[]{1, Global.customerName, "single", "someday", "tomorow"});

        scrollPane = new JScrollPane(table1);
        table1.setPreferredScrollableViewportSize(new Dimension(1290, 635));
        panel_viewT.add(scrollPane);
        panel_viewT.setBackground(Color.black);
        panel_viewT.setBounds(540, 100, 1300, 670);

        JDBC_booking jdbcCustomer = new JDBC_booking();
        List booking = jdbcCustomer.get_bookings();
        System.out.println("No of bookings from this customer " + booking.size());
        for (int i = 0; i < booking.size(); i++) {
            Vector row = new Vector();
            Booking_view_data tbl_booking = (Booking_view_data) booking.get(i);
            row.add(tbl_booking.getBookingID());
            row.add(tbl_booking.getName());
            row.add(tbl_booking.getRoomType());
            row.add(tbl_booking.getCheckIn_date());
            row.add(tbl_booking.getCheckOut_date());
            row.add(tbl_booking.getRoomNo());
            row.add(tbl_booking.getBookingStatus());
            tableModel.addRow(row);
        }

        panel2_data_store = new JPanel();
        panel2_data_store.setLayout(null);
        panel2_data_store.add(panel2_data);
        panel2_data_store.add(panel2_button);
        panel2_data_store.setBounds(20, 50, 500, 750);
        panel2_data_store.setBackground(Color.red);
        
        panel_view.setLayout(null);
        panel_view.add(panel2_data_store);
        panel_view.add(panel_viewT);
        panel_view.setBackground(Color.red);
         
        JTabbedPane tab = new JTabbedPane();
        tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tab.addTab("Make a Booking ", panel_center);
        tab.addTab("View Booking ", panel_view);
        tab.setBounds(0, 120, 1920, 880);
        tab.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));

        lbl_welcome = new JLabel("Wel Come " + "[ " + Global.customerName + " ]");
        lbl_welcome.setBounds(20, 50, 500, 50);
        lbl_welcome.setFont(font1);

        //adding all contents on frame
        frame.add(lbl_welcome);
        frame.add(panel_head);
        frame.add(tab);
        frame.setVisible(true);

    }
//update table method
    private void update_tableView() {
        JDBC_booking database = new JDBC_booking();
        List customer = database.get_bookings();
        //System.out.println(persons.size());
        tableModel.setRowCount(0);
        if (customer.size() > 0) {
            for (int i = 0; i < customer.size(); i++) {
                Booking_view_data tmp_person = (Booking_view_data) customer.get(i);
                Vector tmpPerson = new Vector();
                tmpPerson.add(tmp_person.getBookingID());
                tmpPerson.add(tmp_person.getName());
                tmpPerson.add(tmp_person.getCheckIn_date());
                tmpPerson.add(tmp_person.getCheckOut_date());
                tmpPerson.add(tmp_person.getRoomType());
                tmpPerson.add(tmp_person.getRoomNo());
                tmpPerson.add(tmp_person.getBookingStatus());
                tableModel.addRow(tmpPerson);
            }
        }
    }

//button functions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_book) {

            if (txt_checkIn.getText().length() != 10 || txt_checkOut.getText().length() != 10) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Date", "Alert", JOptionPane.WARNING_MESSAGE);
            }
            if (txt_checkIn.getText().length() == 10 && txt_checkOut.getText().length() == 10) {
                Booking_data booking = new Booking_data(Global.customerID, cmb_roomType.getSelectedItem().toString(), txt_checkIn.getText(), txt_checkOut.getText());
                System.out.println(booking);

                JDBC_booking obj = new JDBC_booking();
                result = obj.insert(booking);
                int bookingID = obj.get_bookingID(booking);
                System.out.println("bookingID : " + bookingID);
                if (result) {
                    update_tableView();
                    
                    String message = " Booking Successfull ";
                    JOptionPane.showMessageDialog(null, message);
                    update_tableView();
                } else {
                    JOptionPane.showMessageDialog(null, "Booking unsucessful", "Alert", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
//********************************************** view tab*****************************************
        if (e.getSource() == btn2_update) {
            BookingDetails_update booking_update = new BookingDetails_update(Integer.parseInt(txt2_booking.getText()), cmb2_roomType.getSelectedItem().toString(), txt2_checkIn.getText(), txt2_checkOut.getText());
            System.out.println(booking_update);

            JDBC_booking obj2 = new JDBC_booking();
            result = obj2.update_booking(booking_update);
            if (result) {
                update_tableView();
                String message = " Booking Update Successfull ";
                JOptionPane.showMessageDialog(null, message, "Sucess", JOptionPane.INFORMATION_MESSAGE);
                update_tableView();
            } else {
                JOptionPane.showMessageDialog(null, "Booking Update Incomplete", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btn2_cancel) {
            int booking_ID = Integer.parseInt(txt2_booking.getText());

            JDBC_booking obj2 = new JDBC_booking();
            result = obj2.delete(booking_ID);
            if (result) {
                update_tableView();
                String message = " Booking cancelled Successfull ";
                JOptionPane.showMessageDialog(null, message, "Sucess", JOptionPane.INFORMATION_MESSAGE);
                update_tableView();
            } else {
                JOptionPane.showMessageDialog(null, "Booking Cancellation Incomplete", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == btn2_clear) {
            txt2_booking.setText("");
            cmb2_roomType.setSelectedIndex(0);
            txt2_checkIn.setText("");
            txt2_checkOut.setText("");
        }

    }
}
