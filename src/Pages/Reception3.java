package Pages;

import CustomerAndBooking_DataStore.Booking_data;
import CustomerAndBooking_DataStore.Customer_Data;
import Reception_DataStore.Rep_Bupdate;
import Reception_DataStore.Rep_makeBooking;
import Reception_DataStore.Rep_makeBooking_table;
import Reception_DataStore.Rep_MKremove;
import Reception_DataStore.Rep_billing;
import Reception_DataStore.Rep_update;
import Database.JDBC_customer;
import Database.ReceptionJDBC;
import Reception_DataStore.Rep_Customer_Data;
import Regular_Expression.RegularExpression;
import classes.Global;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.ButtonGroup;
//import javafx.scene.control.DatePicker;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Reception3 implements ActionListener, ItemListener {

    JFrame frame;
    JPanel panel_heading, panel_head, panel_booking, panel1_data, panel1_button, panel1_search, panel1_bookingT, panel_checkIn;
    JLabel logo, lbl_title, lbl_bookingID1, lbl_customerID, lbl_days, lbl_roomClass, lbl_roomNo, lbl_checkIn, lbl_checkOut, lbl_roomType, lbl_status;
    JTextField txt_bookingID1, txt_customerID, txt_checkIn, txt_days, txt_checkOut, txt_searchField;
    JButton btn_signOut, btn_save, btn_update, btn_remove, btn_clear, btn_search;
    ImageIcon img;
    JComboBox cmb_roomClass, cmb_roomNo, cmb_roomType, cmb_status;
    JTable table1;

    DefaultTableModel table_customerT, table_bookingT;
    JButton btn_refresh;
    JScrollPane scrollPane, Sc1_table;
    JPanel panel_checkInD, panel_checkOutD;
//objects of check in tab
    JLabel lbl_search;
    DefaultTableModel tableModel_checkIn;
    JButton btn_search2;
    JTextField txt_searchField2;
    JPanel panel2_checkInT, panel2_search;
    JScrollPane Sc2_table;
    JComboBox cmb_status1;
    //objects of check Out tab
    JPanel panel3_checkOutT, panel3_search;
    JScrollPane Sc3_table_checkOut;
    //objects of billing tab
    JPanel panel_billing, panel4_billingT, panel4_data;
    JScrollPane Sc4_table_billing;
    JLabel lbl_bookingID, lbl_room, lbl_tender, lbl_gap, lbl_bookingStatus, lbl_paymentStatus;
    JTextField txt_bookingID, txt_tender, txt_total;
    JButton btn_add, btn_print, btn5_Refresh;
    JComboBox cmb_bookingStatus, cmb_Room, cmb_status4;
//customer details
    ButtonGroup genderbtngrp;
    DefaultTableModel tableModel_billing;
    JComboBox customer_type;
    JRadioButton rd_genderMale, rd_genderFemale, rd_genderOther;
    JPanel panel_customerDetails, panel5_data, panel5_customerT, panel5_button, panel_gender;
    JButton btn5_update, btn5_delete, btn5_clear, btn5_save;
    JScrollPane Sc5_table;
    JPanel panel5_customerData;
    JLabel lbl5_searchCustomer, lbl_gender, lbl_customerType, lbl_regID, lbl_name, lbl_address, lbl_email, lbl_password, lbl_phone, lbl_discount, lbl_creditCard, lbl_age;
    JTextField txt_customerID5, txt_name, txt_address, txt_email, txt_password, txt_discount, txt_phone, txt_creditCard, txt_age;
    boolean result;

    public void ReceptionI() {
        frame = new JFrame();
        frame.setTitle("Luton Hotel - Reception");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().setBackground(Color.ORANGE);

        //Fonts to select
        Font fTitle = new Font("Segoe UI Black", Font.TRUETYPE_FONT, 70);
        Font font1 = new Font("Courier", Font.PLAIN, 20);
        Font font2 = new Font("Courier", Font.PLAIN, 25);
//********************************heading of the page********************************
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

//*******************************tab make a booking**********************************
        panel1_data = new JPanel();
        lbl_bookingID1 = new JLabel("Booking ID : ");
        lbl_bookingID1.setFont(font1);
        txt_bookingID1 = new JTextField();
        txt_bookingID1.setFont(font1);
        txt_bookingID1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int bookingID = Integer.parseInt(txt_bookingID1.getText());
                    System.out.println(bookingID);

                    ReceptionJDBC receptionjdbc = new ReceptionJDBC();
                    Booking_data booking = new Booking_data();
                    booking = receptionjdbc.search_BookingID(bookingID);

                    String roomType = booking.getRoomTypeBk().trim();
                    if (roomType.equals("Single")) {
                        System.out.print("room is single");
                        cmb_roomType.setSelectedItem("Single");
                    } else if (roomType.equals("Twin")) {
                        cmb_roomType.setSelectedItem("Twin");
                    } else if (roomType.equals("Double")) {
                        cmb_roomType.setSelectedItem("Double");
                    }
                    txt_checkIn.setText(booking.getCheckIn());
                    txt_checkOut.setText(booking.getCheckOut());

                }
            }
        });
        lbl_customerID = new JLabel("Customer ID : ");
        lbl_customerID.setFont(font1);
        txt_customerID = new JTextField();
        txt_customerID.setFont(font1);
        //Room Type 
        /*lbl_roomClass = new JLabel("Room Type");        
        lbl_roomClass.setFont(font1);
        Vector roomClass = new Vector();
        roomClass.add("Basic");
        roomClass.add("Standard");
        roomClass.add("Deluxe");
        cmb_roomClass = new JComboBox(roomClass);
        cmb_roomClass.setFont(font1);*/
//Room Type
        lbl_roomType = new JLabel("Room Type :");
        lbl_roomType.setFont(font1);
        Vector roomType = new Vector();
        roomType.add("-None-");
        roomType.add("Single");
        roomType.add("Twin");
        roomType.add("Double");
        cmb_roomType = new JComboBox(roomType);
        cmb_roomType.addItemListener(this);
        cmb_roomType.setFont(font1);

//Room Number
        lbl_roomNo = new JLabel("Room Number :");
        lbl_roomNo.setFont(font1);
        ReceptionJDBC jdbcr = new ReceptionJDBC();
        Vector roomNos = jdbcr.get_freeRooms();
        roomNos.set(0, "-None-");
        System.out.println(roomNos);
        cmb_roomNo = new JComboBox(roomNos);
        cmb_roomNo.setFont(font1);

//booking status 
        lbl_status = new JLabel("Status :");
        lbl_status.setFont(font1);
        Vector status = new Vector();
        status.add("Booked");
        status.add("Active");
        status.add("Cancelled");
        status.add("Completed");
        cmb_status = new JComboBox(status);
        cmb_status.setFont(font1);
//check in date
        lbl_checkIn = new JLabel("Check In Date :");
        lbl_checkIn.setFont(font1);
        txt_checkIn = new JTextField("DD-MM-YYYY");
        txt_checkIn.setFont(font1);
        lbl_checkOut = new JLabel("Check out Date :");
        lbl_checkOut.setFont(font1);
        txt_checkOut = new JTextField("DD-MM-YYYY");
        txt_checkOut.setFont(font1);

//days the customer is booking for 
        lbl_days = new JLabel("Days : ");
        lbl_days.setFont(font1);
        txt_days = new JTextField();
        txt_days.setFont(font1);

        /*date = new DatePicker();
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
         */
        panel1_data.setLayout(new GridLayout(8, 2, -60, 20));
        panel1_data.add(lbl_bookingID1);
        panel1_data.add(txt_bookingID1);
        panel1_data.add(lbl_customerID);
        panel1_data.add(txt_customerID);
        //panel1_data.add(lbl_roomClass);
        //panel1_data.add(cmb_roomClass);
        panel1_data.add(lbl_checkIn);
        panel1_data.add(txt_checkIn);
        panel1_data.add(lbl_checkOut);
        panel1_data.add(txt_checkOut);
        panel1_data.add(lbl_roomType);
        panel1_data.add(cmb_roomType);
        panel1_data.add(lbl_roomNo);
        panel1_data.add(cmb_roomNo);
        panel1_data.add(lbl_status);
        panel1_data.add(cmb_status);
        panel1_data.add(lbl_days);
        panel1_data.add(txt_days);
        panel1_data.setBackground(Color.getHSBColor(100, 5, 80));//Color.getHSBColor(100, 5, 80)
        panel1_data.setBounds(30, 90, 450, 500);

        btn_refresh = new JButton("Refresh"); btn_refresh.setFont(font1);
        btn_refresh.addActionListener(this);
        btn_refresh.setBounds(1700, 20, 150, 50);
        panel1_button = new JPanel();
        btn_save = new JButton("Save");
        btn_save.setFont(font1);
        btn_save.addActionListener(this);
        btn_update = new JButton("Update");
        btn_update.setFont(font1);
        btn_update.addActionListener(this);
        btn_remove = new JButton("Remove");
        btn_remove.setFont(font1);
        btn_remove.addActionListener(this);
        btn_clear = new JButton("Clear");
        btn_clear.setFont(font1);
        btn_clear.addActionListener(this);

        panel1_button.setLayout(new GridLayout(1, 4, 7, 0));
        panel1_button.add(btn_save);
        panel1_button.add(btn_update);
        panel1_button.add(btn_remove);
        panel1_button.add(btn_clear);
        panel1_button.setBounds(15, 640, 500, 60);
        panel1_button.setBackground(Color.getHSBColor(100, 5, 80));

        panel1_bookingT = new JPanel();
        table_bookingT = new DefaultTableModel();
        JTable table = new JTable(table_bookingT);
        table_bookingT.addColumn("CustomerID");
        table_bookingT.addColumn("BookingID");
        table_bookingT.addColumn("Name");
        table_bookingT.addColumn("Check In");
        table_bookingT.addColumn("Check Out");
        table_bookingT.addColumn("Room Type");
        table_bookingT.addColumn("Room No");
        table_bookingT.addColumn("Booking Status");
        table_bookingT.addColumn("Days");

        Sc1_table = new JScrollPane(table);
        Sc1_table.setPreferredSize(new Dimension(1290, 658));
        panel1_bookingT.add(Sc1_table);
        panel1_bookingT.setBounds(540, 100, 1300, 670);
        panel1_bookingT.setBackground(Color.black);

        ReceptionJDBC jdbcBooking = new ReceptionJDBC();
        List bookings = jdbcBooking.get_bookings();
        System.out.println(bookings.size());
        for (int i = 0; i < bookings.size(); i++) {
            Vector row = new Vector();
            Rep_makeBooking_table tmpCustomer = (Rep_makeBooking_table) bookings.get(i);
            row.add(tmpCustomer.getCustomerID());
            row.add(tmpCustomer.getBookingID());
            row.add(tmpCustomer.getName());
            row.add(tmpCustomer.getCheckIn_date());
            row.add(tmpCustomer.getCheckOut_date());
            row.add(tmpCustomer.getRoomType());
            row.add(tmpCustomer.getRoomNo());
            row.add(tmpCustomer.getBookingStatus());
            row.add(tmpCustomer.getDays());
            table_bookingT.addRow(row);
        }

        panel_booking = new JPanel();
        panel_booking.setLayout(null);
        panel_booking.add(btn_refresh);
        panel_booking.add(panel1_data);
        panel_booking.add(panel1_bookingT);
        panel_booking.add(panel1_button);
        //panel_booking.add(panel1_search);
        panel_booking.setBounds(30, 140, 1860, 880);
        panel_booking.setBackground(Color.getHSBColor(100, 5, 80));

//****************************panel check IN begins ***********************************
        panel_checkInD = new JPanel();

        panel2_checkInT = new JPanel();
        tableModel_checkIn = new DefaultTableModel();
        JTable table_checkIn = new JTable(tableModel_checkIn);
        tableModel_checkIn.addColumn("CustomerID");
        tableModel_checkIn.addColumn("BookingID");
        tableModel_checkIn.addColumn("Name");
        tableModel_checkIn.addColumn("Check In");
        tableModel_checkIn.addColumn("Check Out");
        tableModel_checkIn.addColumn("Room Type");
        tableModel_checkIn.addColumn("Room No");
        tableModel_checkIn.addColumn("Booking Status");
        tableModel_checkIn.addColumn("Days");

        Sc2_table = new JScrollPane(table_checkIn);
        Sc2_table.setPreferredSize(new Dimension(1290, 658));
        panel2_checkInT.add(Sc2_table);
        panel2_checkInT.setBackground(Color.black);
        panel2_checkInT.setBounds(540, 100, 1300, 670);

        ReceptionJDBC jdbcBooking2 = new ReceptionJDBC();
        List bookings2 = jdbcBooking2.get_bookings();
        System.out.println("No of booking found in JDBC get_bookings : " + bookings2.size());
        for (int i = 0; i < bookings2.size(); i++) {
            Vector row = new Vector();
            Rep_makeBooking_table tmpCustomer = (Rep_makeBooking_table) bookings2.get(i);
            row.add(tmpCustomer.getCustomerID());
            row.add(tmpCustomer.getBookingID());
            row.add(tmpCustomer.getName());
            row.add(tmpCustomer.getCheckIn_date());
            row.add(tmpCustomer.getCheckOut_date());
            row.add(tmpCustomer.getRoomType());
            row.add(tmpCustomer.getRoomNo());
            row.add(tmpCustomer.getBookingStatus());
            row.add(tmpCustomer.getDays());
            tableModel_checkIn.addRow(row);
        }
        //Search panel in check in tab and it share txt_searchField from other tabs
        panel2_search = new JPanel();
        txt_searchField2 = new JTextField(20);
        txt_searchField2.setFont(font1);

        lbl_search = new JLabel("Customer ID : ");
        lbl_search.setFont(font1);
        btn_search2 = new JButton("Search");
        btn_search2.setFont(font2);
        btn_search2.addActionListener(this);
        panel2_search.add(lbl_search);
        panel2_search.add(txt_searchField2);
        panel2_search.add(btn_search2);
        panel2_search.setBounds(1000, 30, 600, 50);
        panel2_search.setBackground(Color.getHSBColor(100, 5, 80));

        panel_checkInD.setLayout(null);
        panel_checkInD.add(panel2_checkInT);
        panel_checkInD.add(panel2_search);
        panel_checkInD.setBounds(30, 140, 1860, 880);
        panel_checkInD.setBackground(Color.getHSBColor(100, 5, 80));

//********************************Billing tab*****************************************
        panel4_billingT = new JPanel();
        tableModel_billing = new DefaultTableModel();
        JTable table_billing = new JTable(tableModel_billing);
        tableModel_billing.addColumn("Customer ID");
        tableModel_billing.addColumn("Booking ID");
        tableModel_billing.addColumn("Name");
        tableModel_billing.addColumn("Room No");
        tableModel_billing.addColumn("Days stayed");
        tableModel_billing.addColumn("Room Charge");
        tableModel_billing.addColumn("Extra Charge");
        tableModel_billing.addColumn("Discount");
        tableModel_billing.addColumn("Grand Total");
        tableModel_billing.addColumn("Payment");
        tableModel_billing.addColumn("Booking Status");

        Sc4_table_billing = new JScrollPane(table_billing);
        Sc4_table_billing.setPreferredSize(new Dimension(1290, 658));
        panel4_billingT.add(Sc4_table_billing);
        panel4_billingT.setBackground(Color.black);
        panel4_billingT.setBounds(540, 100, 1300, 670);

        ReceptionJDBC receptionJDBC = new ReceptionJDBC();
        List CustomerBills = receptionJDBC.get_billingTable();
        System.out.println(CustomerBills.size());
        for (int i = 0; i < CustomerBills.size(); i++) {
            Vector row = new Vector();
            Rep_billing tmpCustomer = (Rep_billing) CustomerBills.get(i);
            row.add(tmpCustomer.getCustomerID());
            row.add(tmpCustomer.getBookingID());
            row.add(tmpCustomer.getName());
            row.add(tmpCustomer.getRoomNo());
            row.add(tmpCustomer.getDays());
            row.add(tmpCustomer.getRoomPrice());
            row.add(tmpCustomer.getExtraPrice());
            row.add(tmpCustomer.getDiscount());
            row.add(tmpCustomer.getGrandTotal());
            row.add(tmpCustomer.getPaidAmt());
            row.add(tmpCustomer.getBookingStatus());
            tableModel_billing.addRow(row);
        }
//billing data or input part
        panel4_data = new JPanel();
        lbl_bookingID = new JLabel("Booking ID :");
        lbl_bookingID.setFont(font1);
        txt_bookingID = new JTextField();
        txt_bookingID.setFont(font1);
//        lbl_room = new JLabel("Room No :");
//        lbl_room.setFont(font1);
//        Vector rooms = new Vector();
//        rooms.add("100");
//        rooms.add("101");
//        rooms.add("102");
//        rooms.add("103");
//        rooms.add("104");
//        rooms.add("105");
//        rooms.add("106");
//        rooms.add("107");
//        rooms.add("108");

//        cmb_Room = new JComboBox(rooms);
//        cmb_Room.setFont(font1);
        lbl_tender = new JLabel("Tender :");
        lbl_tender.setFont(font1);
        txt_tender = new JTextField();
        txt_tender.setFont(font1);
        lbl_bookingStatus = new JLabel("Booking Status :");
        lbl_bookingStatus.setFont(font1);

        txt_total = new JTextField();
        txt_total.setFont(font1);
        lbl_paymentStatus = new JLabel("Payment Status:");
        lbl_paymentStatus.setFont(font1);
        Vector billingStatus = new Vector();
        billingStatus.add("Paid");
        billingStatus.add("Not Paid");
        cmb_bookingStatus = new JComboBox(billingStatus);
        cmb_bookingStatus.setFont(font1);
        lbl_gap = new JLabel();
        lbl_gap.setFont(font1);
        btn_add = new JButton("ADD");
        btn_add.setFont(font1);
        btn_add.addActionListener(this);

        btn5_Refresh = new JButton("Refresh");
        btn5_Refresh.setFont(font2);
        btn5_Refresh.addActionListener(this);

        panel4_data.setLayout(new GridLayout(6, 2, -50, 20));

        panel4_data.add(new JLabel());
        panel4_data.add(btn5_Refresh);
        panel4_data.add(lbl_bookingID);
        panel4_data.add(txt_bookingID);
        panel4_data.add(lbl_tender);
        panel4_data.add(txt_tender);
//        panel4_data.add(lbl_room);
//        panel4_data.add(cmb_Room);
//        panel4_data.add(lbl_returned);
//        panel4_data.add(txt_returned);
//        panel4_data.add(lbl_bookingStatus);
//        panel4_data.add(cmb_status4);
        panel4_data.add(lbl_paymentStatus);
        panel4_data.add(cmb_bookingStatus);
        panel4_data.add(lbl_gap);
        panel4_data.add(btn_add);
        panel4_data.setBackground(Color.getHSBColor(100, 5, 80));
        panel4_data.setBounds(20, 130, 450, 450);
        
        btn5_Refresh.setBounds(390, 130, 130, 50);

        panel_billing = new JPanel();
        panel_billing.setLayout(null);
        panel_billing.setBackground(Color.getHSBColor(100, 5, 80));
        panel_billing.add(panel4_billingT);
        panel_billing.add(panel4_data);
        
        //panel_billing.add(btn4_search);

//*****************************tab customer details********************************
//customers details form
        panel5_customerT = new JPanel();
        table_customerT = new DefaultTableModel();
        JTable customerDetails = new JTable(table_customerT);
        table_customerT.addColumn("Reg ID");
        table_customerT.addColumn("Customer Type");
        table_customerT.addColumn("Name");
        table_customerT.addColumn("Address");
        table_customerT.addColumn("Email");
        table_customerT.addColumn("Password");
        table_customerT.addColumn("Phone");
        table_customerT.addColumn("Credit Card");
        table_customerT.addColumn("Age");
        table_customerT.addColumn("Gender");
        table_customerT.addColumn("Discount");

        Sc5_table = new JScrollPane(customerDetails);
        Sc5_table.setPreferredSize(new Dimension(1290, 658));
        panel5_customerT.add(Sc5_table);
        panel5_customerT.setBackground(Color.black);
        panel5_customerT.setBounds(540, 100, 1300, 670);

        customerDetails.getColumnModel().getColumn(0).setPreferredWidth(5);
        customerDetails.getColumnModel().getColumn(4).setPreferredWidth(102);
        customerDetails.getColumnModel().getColumn(5).setPreferredWidth(2);
        customerDetails.getColumnModel().getColumn(7).setPreferredWidth(100);
        customerDetails.getColumnModel().getColumn(8).setPreferredWidth(2);
        customerDetails.getColumnModel().getColumn(9).setPreferredWidth(2);
        customerDetails.getColumnModel().getColumn(10).setPreferredWidth(2);

        JDBC_customer jdbcCustomer = new JDBC_customer();
        List customers = jdbcCustomer.get_all_for_reception();
        System.out.println(customers.size());
        for (int i = 0; i < customers.size(); i++) {
            Vector row = new Vector();
            Customer_Data tmpCustomer = (Customer_Data) customers.get(i);
            row.add(tmpCustomer.getCustomerid());
            row.add(tmpCustomer.getCustomerType());
            row.add(tmpCustomer.getName());
            row.add(tmpCustomer.getAddress());
            row.add(tmpCustomer.getEmail());
            row.add(tmpCustomer.getPassword());
            row.add(tmpCustomer.getPhone());
            row.add(tmpCustomer.getCredit());
            row.add(tmpCustomer.getAge());
            row.add(tmpCustomer.getGender());
            row.add(tmpCustomer.getDiscount());
            table_customerT.addRow(row);
        }

        panel5_customerData = new JPanel();
        panel5_customerData.setLayout(new GridLayout(12, 2, -70, 10));

        //customer Type 
        lbl_customerType = new JLabel("Customer Type : ");
        lbl_customerType.setFont(font1);
        Vector customerType = new Vector();
        customerType.add("Corporate");
        customerType.add("Non-Corporate");
        customer_type = new JComboBox(customerType);
        customer_type.setFont(font1);

        lbl_regID = new JLabel("Customer ID : ");
        lbl_regID.setFont(font1);
        lbl_name = new JLabel("Name : ");
        lbl_name.setFont(font1);
        lbl_address = new JLabel("Address : ");
        lbl_address.setFont(font1);
        lbl_email = new JLabel("Email : ");
        lbl_email.setFont(font1);
        lbl_password = new JLabel("Password : ");
        lbl_password.setFont(font1);
        lbl_phone = new JLabel("Phone : ");
        lbl_phone.setFont(font1);
        lbl_creditCard = new JLabel("Credit Card : ");
        lbl_creditCard.setFont(font1);
        lbl_age = new JLabel("Age : ");
        lbl_age.setFont(font1);

        lbl_gender = new JLabel("Gender : ");
        lbl_gender.setFont(font1);
        rd_genderMale = new JRadioButton("Male");
        rd_genderMale.setFont(font1);
        rd_genderMale.setBackground(Color.getHSBColor(100, 5, 80));
        rd_genderFemale = new JRadioButton("Female");
        rd_genderFemale.setFont(font1);
        rd_genderFemale.setBackground(Color.getHSBColor(100, 5, 80));
        rd_genderOther = new JRadioButton("Others");
        rd_genderOther.setFont(font1);
        rd_genderOther.setBackground(Color.getHSBColor(100, 5, 80));
        genderbtngrp = new ButtonGroup();
        genderbtngrp.add(rd_genderMale);
        genderbtngrp.add(rd_genderFemale);
        genderbtngrp.add(rd_genderOther);

        lbl_discount = new JLabel("Discount : ");
        lbl_discount.setFont(font1);

        txt_customerID5 = new JTextField("0");
        txt_customerID5.setFont(font1);
        //txt_customerID5.addKeyListener(this);
        txt_customerID5.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int customerID = Integer.parseInt(txt_customerID5.getText());
                    System.out.println(customerID);

                    ReceptionJDBC receptionjdbc = new ReceptionJDBC();
                    Rep_Customer_Data customer = new Rep_Customer_Data();
                    customer = receptionjdbc.search_customerID(customerID);

                    String customerType = customer.getCustomerType().trim();
                    if (customerType.equals("Corporate")) {
                        customer_type.setSelectedItem("Corporate");
                    } else if (customerType.equals("Non-Corporate")) {
                        customer_type.setSelectedItem("Non-Corporate");
                    }
                    txt_name.setText(customer.getName());
                    txt_address.setText(customer.getAddress());
                    txt_email.setText(customer.getEmail());
                    txt_password.setText(customer.getPassword());
                    txt_phone.setText(customer.getPhone());
                    txt_creditCard.setText(customer.getCredit());
                    txt_age.setText(customer.getAge());
                    txt_discount.setText(customer.getDiscount());

                    String gender = customer.getGender().trim();
                    switch (gender) {
                        case "Male":
                            rd_genderMale.doClick();
                            break;
                        case "Female":
                            rd_genderFemale.doClick();
                            break;
                        case "Others":
                            rd_genderOther.doClick();
                            break;
                        default:
                            break;
                    }
                    //customer_type.setSelectedItem(customer.getCustomerType());
                    System.out.println(customerType);
                }
            }
        });

        txt_name = new JTextField();
        txt_name.setFont(font1);
        txt_address = new JTextField();
        txt_address.setFont(font1);
        txt_email = new JTextField();
        txt_email.setFont(font1);
        txt_password = new JTextField();
        txt_password.setFont(font1);
        txt_phone = new JTextField();
        txt_phone.setFont(font1);
        txt_creditCard = new JTextField();
        txt_creditCard.setFont(font1);
        txt_age = new JTextField();
        txt_age.setFont(font1);
        txt_discount = new JTextField("0");
        txt_discount.setFont(font1);
        panel5_customerData.add(lbl_regID);
        panel5_customerData.add(txt_customerID5);
        panel5_customerData.add(lbl_customerType);
        panel5_customerData.add(customer_type);
        panel5_customerData.add(lbl_name);
        panel5_customerData.add(txt_name);
        panel5_customerData.add(lbl_address);
        panel5_customerData.add(txt_address);
        panel5_customerData.add(lbl_email);
        panel5_customerData.add(txt_email);
        panel5_customerData.add(lbl_password);
        panel5_customerData.add(txt_password);
        panel5_customerData.add(lbl_phone);
        panel5_customerData.add(txt_phone);
        panel5_customerData.add(lbl_creditCard);
        panel5_customerData.add(txt_creditCard);
        panel5_customerData.add(lbl_age);
        panel5_customerData.add(txt_age);
        panel5_customerData.add(lbl_discount);
        panel5_customerData.add(txt_discount);
        panel5_customerData.add(lbl_gender);

        panel_gender = new JPanel();
        panel_gender.setLayout(new GridLayout(1, 3, -5, 0));
        panel_gender.add(rd_genderMale);
        panel_gender.add(rd_genderFemale);
        panel_gender.add(rd_genderOther);

        panel5_customerData.add(panel_gender);
        panel5_customerData.setBounds(30, 80, 450, 590);
        panel5_customerData.setBackground(Color.getHSBColor(100, 5, 80));

        panel5_button = new JPanel();
        panel5_button.setLayout(new GridLayout(1, 4, 15, 0));
        btn5_save = new JButton("Save");
        btn5_save.setFont(font1);
        btn5_save.addActionListener(this);

        btn5_update = new JButton("Update");
        btn5_update.setFont(font1);
        btn5_update.addActionListener(this);

        btn5_delete = new JButton("Delete");
        btn5_delete.setFont(font1);
        btn5_delete.addActionListener(this);

        btn5_clear = new JButton("clear");
        btn5_clear.setFont(font1);
        btn5_clear.addActionListener(this);

        panel5_button.setBackground(Color.getHSBColor(100, 5, 80));
        panel5_button.setBounds(30, 670, 450, 50);
        panel5_button.add(btn5_save);
        panel5_button.add(btn5_update);
        panel5_button.add(btn5_delete);
        panel5_button.add(btn5_clear);

        panel1_search = new JPanel();
        txt_searchField = new JTextField(20);
        txt_searchField.setFont(font1);
        btn_search = new JButton("Search");
        btn_search.setFont(font2);
        btn_search.addActionListener(this);
        lbl5_searchCustomer = new JLabel("Customer Name : ");
        lbl5_searchCustomer.setFont(font1);
        panel1_search.add(lbl5_searchCustomer);
        panel1_search.add(txt_searchField);
        panel1_search.add(btn_search);
        panel1_search.setBackground(Color.getHSBColor(100, 5, 80));
        panel1_search.setBounds(900, 30, 650, 50);

        panel_customerDetails = new JPanel();
        panel_customerDetails.setLayout(null);
        panel_customerDetails.setBackground(Color.getHSBColor(100, 5, 80));
        panel_customerDetails.add(panel5_customerData);
        panel_customerDetails.add(panel5_customerT);
        panel_customerDetails.add(panel5_button);
        panel_customerDetails.add(panel1_search);

        JTabbedPane tab = new JTabbedPane();
        tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tab.addTab("Make a Booking ", panel_booking);
        tab.addTab("Current Status ", panel_checkInD);
        //tab.addTab("Check-Out Details ", panel_checkOutD);
        tab.addTab(" Billing ", panel_billing);
        tab.addTab(" Customer Details ", panel_customerDetails);
        tab.setBounds(30, 140, 1860, 880);
        tab.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));

        frame.setLayout(null);
        frame.add(panel_head);
        frame.add(tab);
        frame.setVisible(true);
    }

   

    public void update_customerTable() {
        JDBC_customer database = new JDBC_customer();
        List customer = database.get_all_for_reception();
        System.out.println(customer.size());
        table_customerT.setRowCount(0);
        if (customer.size() > 0) {
            for (int i = 0; i < customer.size(); i++) {
                Customer_Data tmp_person = (Customer_Data) customer.get(i);
                Vector tmpPerson = new Vector();
                tmpPerson.add(tmp_person.getCustomerid());
                tmpPerson.add(tmp_person.getCustomerType());
                tmpPerson.add(tmp_person.getName());
                tmpPerson.add(tmp_person.getAddress());
                tmpPerson.add(tmp_person.getEmail());
                tmpPerson.add(tmp_person.getPassword());
                tmpPerson.add(tmp_person.getPhone());
                tmpPerson.add(tmp_person.getCredit());
                tmpPerson.add(tmp_person.getAge());
                tmpPerson.add(tmp_person.getGender());
                tmpPerson.add(tmp_person.getDiscount());
                table_customerT.addRow(tmpPerson);
            }
        }
    }

    public void update_billingTable() {
        ReceptionJDBC database = new ReceptionJDBC();
        List refreshBilling = database.get_billingTable();
        System.out.println(refreshBilling.size());
        tableModel_billing.setRowCount(0);
        if (refreshBilling.size() > 0) {
            for (int i = 0; i < refreshBilling.size(); i++) {
                Rep_billing tmp_person = (Rep_billing) refreshBilling.get(i);
                Vector tmpPerson = new Vector();
                tmpPerson.add(tmp_person.getCustomerID());
                tmpPerson.add(tmp_person.getBookingID());
                tmpPerson.add(tmp_person.getName());
                tmpPerson.add(tmp_person.getRoomNo());
                tmpPerson.add(tmp_person.getDays());
                tmpPerson.add(tmp_person.getRoomPrice());
                tmpPerson.add(tmp_person.getExtraPrice());
                tmpPerson.add(tmp_person.getDiscount());
                tmpPerson.add(tmp_person.getGrandTotal());
                tmpPerson.add(tmp_person.getPaidAmt());
                tmpPerson.add(tmp_person.getBookingStatus());
                tableModel_billing.addRow(tmpPerson);
            }
        }
    }

    public void search_customerName() {
        ReceptionJDBC database = new ReceptionJDBC();
        List customer = database.search_customerName(txt_searchField.getText());
        System.out.println(customer.size());
        table_customerT.setRowCount(0);
        if (customer.size() > 0) {
            for (int i = 0; i < customer.size(); i++) {
                Customer_Data tmp_person = (Customer_Data) customer.get(i);
                Vector tmpPerson = new Vector();
                tmpPerson.add(tmp_person.getCustomerid());
                tmpPerson.add(tmp_person.getCustomerType());
                tmpPerson.add(tmp_person.getName());
                tmpPerson.add(tmp_person.getAddress());
                tmpPerson.add(tmp_person.getEmail());
                tmpPerson.add(tmp_person.getPassword());
                tmpPerson.add(tmp_person.getPhone());
                tmpPerson.add(tmp_person.getCredit());
                tmpPerson.add(tmp_person.getAge());
                tmpPerson.add(tmp_person.getDiscount());
                tmpPerson.add(tmp_person.getGender());
                table_customerT.addRow(tmpPerson);
            }
        }
    }

    private void update_makeAbooking() {
        ReceptionJDBC jdbcBooking = new ReceptionJDBC();
        List bookings = jdbcBooking.get_bookings();
        System.out.println(bookings.size());
        table_bookingT.setRowCount(0);
        if (bookings.size() > 0) {
            for (int i = 0; i < bookings.size(); i++) {
                Rep_makeBooking_table tmp_person = (Rep_makeBooking_table) bookings.get(i);
                Vector tmpPerson = new Vector();
                tmpPerson.add(tmp_person.getCustomerID());
                tmpPerson.add(tmp_person.getBookingID());
                tmpPerson.add(tmp_person.getName());
                tmpPerson.add(tmp_person.getCheckIn_date());
                tmpPerson.add(tmp_person.getCheckOut_date());
                tmpPerson.add(tmp_person.getRoomType());
                tmpPerson.add(tmp_person.getRoomNo());
                tmpPerson.add(tmp_person.getBookingStatus());
                tmpPerson.add(tmp_person.getDays());
                table_bookingT.addRow(tmpPerson);
            }
        }
    }

    private void update_checkInTable() {
        ReceptionJDBC jdbcBooking = new ReceptionJDBC();
        List bookings = jdbcBooking.search(Integer.parseInt(txt_searchField2.getText()));
        System.out.println(bookings.size());
        tableModel_checkIn.setRowCount(0);
        if (bookings.size() > 0) {
            for (int i = 0; i < bookings.size(); i++) {
                Rep_makeBooking_table tmp_person = (Rep_makeBooking_table) bookings.get(i);
                Vector tmpPerson = new Vector();
                tmpPerson.add(tmp_person.getCustomerID());
                tmpPerson.add(tmp_person.getBookingID());
                tmpPerson.add(tmp_person.getName());
                tmpPerson.add(tmp_person.getCheckIn_date());
                tmpPerson.add(tmp_person.getCheckOut_date());
                tmpPerson.add(tmp_person.getRoomType());
                tmpPerson.add(tmp_person.getRoomNo());
                tmpPerson.add(tmp_person.getBookingStatus());
                tmpPerson.add(tmp_person.getDays());
                tableModel_checkIn.addRow(tmpPerson);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RegularExpression re = new RegularExpression();
        //**********************for make a booking tab*************************************
        if(e.getSource() == btn_refresh){
            //this button is to refresh when a customer is 
            //making booking while the receptionist interface is open
            update_makeAbooking();
        }
        
        if (e.getSource() == btn_save) {
            if (!re.isAlphabets(txt_customerID.getText())) {
                String message = "Invalid Customer ID  ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }

            if (!re.isInteger(txt_days.getText())) {
                String message = "Invalid Days ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }

            if (txt_days.getText().length() == 0) {
                String message = " Days can't be Empty ";
                JOptionPane.showMessageDialog(null, message, "Not Accepted", JOptionPane.ERROR_MESSAGE);
            }
            if (txt_checkOut.getText().length() != 10 || txt_checkIn.getText().length() != 10) {
                String message = "Invalid Date ";
                JOptionPane.showMessageDialog(null, message, "Not Accepted", JOptionPane.ERROR_MESSAGE);
            }

            if (re.isInteger(txt_days.getText()) && txt_checkOut.getText().length() == 10 && txt_checkIn.getText().length() == 10) {
                Rep_makeBooking booking = new Rep_makeBooking(Integer.parseInt(txt_customerID.getText()), txt_checkIn.getText(), txt_checkOut.getText(), cmb_roomType.getSelectedItem().toString(), Integer.parseInt(cmb_roomNo.getSelectedItem().toString()), cmb_status.getSelectedItem().toString(), Integer.parseInt(txt_days.getText()));
                System.out.println(booking);

                ReceptionJDBC obj3 = new ReceptionJDBC();
                result = obj3.makeBooking(booking);
                update_makeAbooking();
                System.out.println(Global.receptionID);

                // clear combobx
                // get free room numbers
                // display free room numbers
                ReceptionJDBC jdbcr = new ReceptionJDBC();
                Vector roomNos = jdbcr.get_freeRooms();
                System.out.println(roomNos);
                cmb_roomNo.removeAllItems();
                for (int i = 0; i < roomNos.size(); i++) {
                    Object tmpObj = roomNos.get(i);
                    cmb_roomNo.addItem(tmpObj);
                }
                if (result) {
                    txt_customerID.setText("");
                    String message = "Booking Successfull";
                    JOptionPane.showMessageDialog(null, message, "Sucess", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Booking Incomplete", "Alert", JOptionPane.ERROR_MESSAGE);

                }
            }
        }

        if (e.getSource() == btn_update) {
            if (!re.isInteger(txt_days.getText())) {
                String message = "Invalid Number of Days ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (txt_checkIn.getText().length() != 10 || txt_checkOut.getText().length() != 10) {
                String message = "Invalid Date ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }

            if (txt_bookingID1.getText().length() == 0 || txt_checkIn.getText().length() == 0 || txt_checkOut.getText().length() == 0 || txt_days.getText().length() == 0) {
                String message = "Fill out all the fields ";
                JOptionPane.showMessageDialog(null, message, "Not Accepted", JOptionPane.ERROR_MESSAGE);
            }

            if (re.isInteger(txt_days.getText()) && txt_checkIn.getText().length() == 10 && txt_checkOut.getText().length() == 10 && txt_bookingID1.getText().length() != 0 && txt_checkIn.getText().length() != 0 && txt_days.getText().length() != 0) {
                Rep_update booking = new Rep_update(Integer.parseInt(txt_bookingID1.getText()), cmb_roomType.getSelectedItem().toString(), Integer.parseInt(cmb_roomNo.getSelectedItem().toString()), txt_checkIn.getText(), txt_checkOut.getText(), cmb_status.getSelectedItem().toString(), Integer.parseInt(txt_days.getText()));
                System.out.println(booking);

                ReceptionJDBC update = new ReceptionJDBC();
                result = update.updateBooking(booking);
                update_makeAbooking();//update table contents

                //updating the rooms 
                ReceptionJDBC jdbcr = new ReceptionJDBC();
                Vector roomNos = jdbcr.get_freeRooms();
                System.out.println(roomNos);
                cmb_roomNo.removeAllItems();
                cmb_roomNo = new JComboBox(roomNos);

                if (result) {
                    String message = "Update Successfull";
                    JOptionPane.showMessageDialog(null, message, "Sucess", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Booking Incomplete", "Alert", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (e.getSource() == btn_clear) {
            txt_bookingID1.setText("");
            txt_customerID.setText("");
            txt_checkIn.setText("");
            txt_checkOut.setText("");
            cmb_status.setSelectedIndex(0);
            cmb_roomType.setSelectedIndex(0);
            txt_days.setText("");
        }

        if (e.getSource() == btn_remove) {
            if (!re.isInteger(txt_bookingID1.getText())) {
                String message = "Invalid Booking ID";
                JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (re.isInteger(txt_bookingID1.getText())) {
                update_makeAbooking();
                Rep_MKremove remove = new Rep_MKremove(Integer.parseInt(txt_bookingID1.getText()));
                System.out.println(remove);

                ReceptionJDBC jdbcRemove = new ReceptionJDBC();
                update_makeAbooking();
                result = jdbcRemove.removeBooking(remove);
                if (result) {
                    String message = "Removed Successfull";
                    JOptionPane.showMessageDialog(null, message, "Sucess", JOptionPane.INFORMATION_MESSAGE);
                    txt_bookingID1.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Booking Incomplete", "Alert", JOptionPane.ERROR_MESSAGE);

                }
            }
        }
//*******************************for make a checkIn tab tab*************************************
        if (e.getSource() == btn_search2) {
            int customerID = Integer.parseInt(txt_searchField2.getText());
            System.out.println("customer ID from search field " + customerID);
            ReceptionJDBC obj3 = new ReceptionJDBC();
            obj3.search(customerID);
            update_checkInTable();
        }
//*********************************for make a customer tab*************************************

        if (e.getSource() == btn5_save) {
            if (!re.isAlphabets(txt_name.getText())) {
                String message = "Invalid Name ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isAlphabets(txt_address.getText())) {
                String message = "Invalid Address ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isInteger(txt_phone.getText()) || txt_phone.getText().length() != 10) {
                String message = "Invalid Phone ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isEmail(txt_email.getText())) {
                String message = " Email address Incorrect ! ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isInteger(txt_creditCard.getText()) || txt_creditCard.getText().length() != 16) {
                String message = "Invalid Credit Card ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isInteger(txt_age.getText())) {
                String message = "Invalid Age ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }

            if (txt_name.getText().length() == 0 || txt_address.getText().length() == 0 || txt_email.getText().length() == 0 || txt_password.getText().length() == 0 || txt_phone.getText().length() == 0 || txt_creditCard.getText().length() == 0 || txt_age.getText().length() == 0 || txt_discount.getText().length() == 0) {
                String message = "Fill out all the fields ";
                JOptionPane.showMessageDialog(null, message, "Not Accepted", JOptionPane.ERROR_MESSAGE);
            }
            if (txt_name.getText().length() > 0 && txt_address.getText().length() > 0 && txt_email.getText().length() > 0 && txt_password.getText().length() > 0 && txt_phone.getText().length() > 0 && txt_phone.getText().length() == 10
                    && txt_creditCard.getText().length() > 0 && txt_creditCard.getText().length() == 16 && txt_age.getText().length() > 0 && re.isAlphabets(txt_name.getText()) == true && re.isEmail(txt_email.getText())
                    && re.isInteger(txt_phone.getText()) == true && re.isInteger(txt_creditCard.getText()) == true
                    && re.isInteger(txt_age.getText()) == true) {

                //for customer tab in reception 
                String gender = "None";
                if (rd_genderMale.isSelected()) {
                    gender = "Male";
                } else if (rd_genderFemale.isSelected()) {
                    gender = "Female";
                } else if (rd_genderOther.isSelected()) {
                    gender = "Other";
                }
                int customerID = 0;
                Customer_Data add_customer = new Customer_Data(customerID, customer_type.getSelectedItem().toString(), txt_name.getText(), txt_address.getText(), txt_email.getText(), txt_password.getText(), Long.parseLong(txt_phone.getText()), Long.parseLong(txt_creditCard.getText()), Integer.parseInt(txt_age.getText()), gender, Integer.parseInt(txt_discount.getText()));
                System.out.println(add_customer);

                JDBC_customer obj2 = new JDBC_customer();
                result = obj2.insert(add_customer);

                if (result) {
                    update_customerTable();
                    String message = " Registration Successfull ";
                    JOptionPane.showMessageDialog(null, message, "Sucess", JOptionPane.INFORMATION_MESSAGE);

                    System.out.println("table updated");
                } else {
                    JOptionPane.showMessageDialog(null, "Registration Incomplete", "Alert", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (e.getSource() == btn5_update) {
            //for customer tab in reception
            if (!re.isInteger(txt_customerID5.getText())) {
                String message = "Invalid Age ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isAlphabets(txt_name.getText())) {
                String message = "Invalid Name ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isAlphabets(txt_address.getText())) {
                String message = "Invalid Address ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isInteger(txt_phone.getText()) || txt_phone.getText().length() != 10) {
                String message = "Invalid Phone ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isEmail(txt_email.getText())) {
                String message = " Email address incorrect ! ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isInteger(txt_creditCard.getText()) || txt_creditCard.getText().length() != 16) {
                String message = "Invalid Phone ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isInteger(txt_age.getText())) {
                String message = "Invalid Age ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }

            if (txt_customerID5.getText().length() == 0 || txt_name.getText().length() == 0 || txt_address.getText().length() == 0 || txt_email.getText().length() == 0 || txt_password.getText().length() == 0 || txt_phone.getText().length() == 0 || txt_creditCard.getText().length() == 0 || txt_age.getText().length() == 0 || txt_discount.getText().length() == 0) {
                String message = "Fill out all the fields ";
                JOptionPane.showMessageDialog(null, message, "Not Accepted", JOptionPane.ERROR_MESSAGE);
            }

            if (re.isEmail(txt_email.getText()) == true && txt_address.getText().length() > 0 && re.isAlphabets(txt_name.getText()) == true && txt_name.getText().length() > 0 && re.isInteger(txt_phone.getText()) == true && txt_phone.getText().length() == 10) {

            }

            if (txt_name.getText().length() > 0 && txt_address.getText().length() > 0 && txt_email.getText().length() > 0 && txt_password.getText().length() > 0 && txt_phone.getText().length() > 0 && txt_phone.getText().length() == 10
                    && txt_creditCard.getText().length() > 0 && txt_creditCard.getText().length() == 16 && txt_age.getText().length() > 0 && re.isAlphabets(txt_name.getText()) == true && re.isEmail(txt_email.getText())
                    && re.isInteger(txt_phone.getText()) == true && re.isInteger(txt_creditCard.getText()) == true
                    && re.isInteger(txt_age.getText()) == true) {

                String gender = "None";
                if (rd_genderMale.isSelected()) {
                    gender = "Male";
                } else if (rd_genderFemale.isSelected()) {
                    gender = "Female";
                } else if (rd_genderOther.isSelected()) {
                    gender = "Other";
                }

                Customer_Data add_customer = new Customer_Data(Integer.parseInt(txt_customerID5.getText()), customer_type.getSelectedItem().toString(), txt_name.getText(), txt_address.getText(), txt_email.getText(), txt_password.getText(), Long.parseLong(txt_phone.getText()), Long.parseLong(txt_creditCard.getText()), Integer.parseInt(txt_age.getText()), gender, Integer.parseInt(txt_discount.getText()));
                System.out.println(add_customer);

                ReceptionJDBC obj2 = new ReceptionJDBC();
                result = obj2.update_customerInfo(add_customer);

                if (result) {
                    update_customerTable();
                    String message = "  Successfully Updated ";
                    JOptionPane.showMessageDialog(null, message, "Sucess", JOptionPane.INFORMATION_MESSAGE);

                    System.out.println("table updated");
                } else {
                    JOptionPane.showMessageDialog(null, "Registration Incomplete", "Alert", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (e.getSource() == btn5_delete) {
            String customerID = (txt_customerID5.getText());
            if (re.isInteger(customerID)) {
                int customerid = Integer.parseInt(txt_customerID5.getText());

                ReceptionJDBC obj2 = new ReceptionJDBC();
                obj2.delete_customerInfo(customerid);
                update_customerTable();
                String message = "  Deleted Successfully ";
                JOptionPane.showMessageDialog(null, message, "Sucess", JOptionPane.INFORMATION_MESSAGE);
                //update_tableView();
            } else {
                JOptionPane.showMessageDialog(null, "  Unsuccessful", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == btn5_clear) {
            txt_customerID5.setText("");
            customer_type.setSelectedIndex(0);
            txt_name.setText("");
            txt_address.setText("");
            txt_email.setText("");
            txt_password.setText("");
            txt_phone.setText("");
            txt_creditCard.setText("");
            txt_age.setText("");
            txt_discount.setText("");
            genderbtngrp.clearSelection();
        }

        if (e.getSource() == btn_search) {
            String customerName = txt_searchField.getText();
            System.out.print("customerID is " + customerName);
            ReceptionJDBC obj2 = new ReceptionJDBC();
            obj2.search_customerName(customerName);
            search_customerName(); //updating previous records
            System.out.print("booking updated");

        }

//**************************************for billing tab****************************************
        if (e.getSource() == btn_add) {
            Rep_Bupdate update = new Rep_Bupdate(Integer.parseInt(txt_bookingID.getText()), Integer.parseInt(txt_tender.getText()), cmb_bookingStatus.getSelectedItem().toString());
            System.out.println(update);

            ReceptionJDBC billingJDBC = new ReceptionJDBC();
            result = billingJDBC.Billing_update(update);
            System.out.print(result);
            if (result) {
                update_billingTable();
                String message = "Transaction  Successfull";
                JOptionPane.showMessageDialog(null, message, "Sucess", JOptionPane.INFORMATION_MESSAGE);
                txt_bookingID.setText("");
                txt_tender.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Transaction Unsucessfull", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btn5_Refresh) {
            update_billingTable();
        }
    }
    
    public static void main(String [] args){
        Reception3 obj = new Reception3();
        obj.ReceptionI();
    }
//updating room number
    @Override
    public void itemStateChanged(ItemEvent e
    ) {
        if (e.getSource() == cmb_roomType) {
            String roomType = cmb_roomType.getSelectedItem().toString();
            ReceptionJDBC rjdbc2 = new ReceptionJDBC();
            Vector freeRooms = rjdbc2.get_freeRooms(roomType);
            // System.out.println(freeRooms);
            cmb_roomNo.removeAllItems();
            for (int i = 0; i < freeRooms.size(); i++) {
                Object tmpObj = freeRooms.get(i);
                cmb_roomNo.addItem(tmpObj);
            }
        }
    }
}
