

package Pages;

import Reception_DataStore.Rep_makeBooking_table;
import HotelService_DataStore.Staff_Service_Table;
import HotelService_DataStore.Staff_Service_add;
import Database.ReceptionJDBC;
import Database.ServiceJDBC;
import Database.StaffJDBC;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class StaffI implements ActionListener, KeyListener {
    JFrame frame;
//for customer tab
    JPanel panel_head, panel1_customerT, panel_customer, panel_button, panel_search;
    ImageIcon img;
    JLabel logo, lbl_title,lbl_customerID;
    JTextField txt_search;
    JButton btn_signOut, btn_search, btn_update, btn_delete;
    JScrollPane Sc1_service, Sc2_customer;
//for extra services panel
    JPanel panel_service, panel1_data, panel_serviceT;
    JLabel lbl_bookingID, lbl_service, lbl_laundry, lbl_gap, lbl_telephone, lbl_swimming, lbl_gym, lbl_bar;
    JTextField txt_bookingID, txt_service, txt_laundry, txt_telephone, txt_swimming, txt_gym, txt_bar;
    JButton btn_add, btn_clear, btn_refresh;;
    boolean result;
    DefaultTableModel table_customerT, table_bookingT;

    public void StaffI() {
        frame = new JFrame("Staff");
        frame.setResizable(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.getHSBColor(100, 5, 80));
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

//***********************************************customer tab***********************************
        
        panel_customer = new JPanel();
//customer information table
        panel1_customerT = new JPanel();
        table_customerT = new DefaultTableModel();
        JTable customerDetails = new JTable(table_customerT);
        table_customerT.addColumn("CustomerID");
        table_customerT.addColumn("BookingID");
        table_customerT.addColumn("Name");
        table_customerT.addColumn("Check In");
        table_customerT.addColumn("Check Out");
        table_customerT.addColumn("Room Type");
        table_customerT.addColumn("Room No");
        table_customerT.addColumn("Booking Status");
        table_customerT.addColumn("Days");

        Sc2_customer = new JScrollPane(customerDetails);
        Sc2_customer.setPreferredSize(new Dimension(1290, 670));
        panel1_customerT.add(Sc2_customer);
        panel1_customerT.setBackground(Color.black);
        panel1_customerT.setBounds(540, 100, 1300, 670);
//adding items to table
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
            table_customerT.addRow(row);
        }

        panel_search = new JPanel();
        txt_search = new JTextField(20);
        txt_search.setFont(font1);
        btn_search = new JButton("Search");
        btn_search.setFont(font1);
        lbl_customerID = new JLabel(" Customer ID : "); lbl_customerID.setFont(font1);
        panel_search.add(lbl_customerID);
        panel_search.add(txt_search);
        panel_search.add(btn_search);
        btn_search.addActionListener(this);
        panel_search.setBounds(1000, 30, 700, 50);
        panel_search.setBackground(Color.getHSBColor(100, 5, 80));
//adding all panel of customer details into a single panel
        panel_customer.setLayout(null);
        panel_customer.add(panel1_customerT);
        panel_customer.add(panel_search);
        panel_customer.setBackground(Color.getHSBColor(100, 5, 80));

        
//**************************************service tab*****************************************
        
        panel_service = new JPanel();
        
//form to add extra service charge into customer account
        panel1_data = new JPanel();

        lbl_bookingID = new JLabel("Booking ID : ");
        lbl_bookingID.setFont(font1);
        txt_bookingID = new JTextField();
        txt_bookingID.setFont(font1);
        txt_bookingID.addKeyListener(this);
        lbl_laundry = new JLabel("Laundry service : ");
        lbl_laundry.setFont(font1);
        txt_laundry = new JTextField();
        txt_laundry.setFont(font1);
        lbl_telephone = new JLabel("Telephone service:");
        lbl_telephone.setFont(font1);
        txt_telephone = new JTextField();
        txt_telephone.setFont(font1);
        lbl_swimming = new JLabel("Swimming service :");
        lbl_swimming.setFont(font1);
        txt_swimming = new JTextField();
        txt_swimming.setFont(font1);
        lbl_gym = new JLabel("GYM service :");
        lbl_gym.setFont(font1);
        txt_gym = new JTextField();
        txt_gym.setFont(font1);
        lbl_bar = new JLabel("Bar service : ");
        lbl_bar.setFont(font1);
        txt_bar = new JTextField();
        txt_bar.setFont(font1);

        panel1_data.setLayout(new GridLayout(6, 2, -40, 20));
        panel1_data.add(lbl_bookingID);
        panel1_data.add(txt_bookingID);
        panel1_data.add(lbl_laundry);
        panel1_data.add(txt_laundry);
        panel1_data.add(lbl_telephone);
        panel1_data.add(txt_telephone);
        panel1_data.add(lbl_swimming);
        panel1_data.add(txt_swimming);
        panel1_data.add(lbl_gym);
        panel1_data.add(txt_gym);
        panel1_data.add(lbl_bar);
        panel1_data.add(txt_bar);

        panel1_data.setBounds(30, 130, 450, 500);
        panel1_data.setBackground(Color.getHSBColor(100, 5, 80));

//service charge table
        panel_serviceT = new JPanel();
        table_bookingT = new DefaultTableModel();
        JTable table = new JTable(table_bookingT);
        table_bookingT.addColumn("Booking ID");
        table_bookingT.addColumn("Name");
        table_bookingT.addColumn("Laundry");
        table_bookingT.addColumn("Telephone");
        table_bookingT.addColumn("Swimming");
        table_bookingT.addColumn("GYM");
        table_bookingT.addColumn("Bar");

        Sc1_service = new JScrollPane(table);
        Sc1_service.setPreferredSize(new Dimension(1290, 670));
        panel_serviceT.add(Sc1_service);
        panel_serviceT.setBounds(540, 100, 1300, 670);
        panel_serviceT.setBackground(Color.black);
//adding items to table
        StaffJDBC JDBCcustomer = new StaffJDBC();
        List bookings1 = JDBCcustomer.get_bookings();
        System.out.println(bookings1.size());
        for (int i = 0; i < bookings1.size(); i++) {
            Vector row = new Vector();
            Staff_Service_Table tmpCustomer = (Staff_Service_Table) bookings1.get(i);
            row.add(tmpCustomer.getBookingId());
            row.add(tmpCustomer.getName());
            row.add(tmpCustomer.getLaundryPrice());
            row.add(tmpCustomer.getTelephone());
            row.add(tmpCustomer.getSwimmingPrice());
            row.add(tmpCustomer.getGymPrice());
            row.add(tmpCustomer.getBarPrice());
            table_bookingT.addRow(row);
        }

        panel_button = new JPanel();
        panel_button.setLayout(new GridLayout(1, 3, 10, 0));
        btn_add = new JButton("Update");
        btn_add.setFont(font1);
        btn_add.addActionListener(this);
        
        btn_delete = new JButton("Delete");
        btn_delete.setFont(font1);
        btn_delete.addActionListener(this);
        btn_clear = new JButton("Clear");
        btn_clear.setFont(font1);
        btn_clear.addActionListener(this);
        panel_button.add(btn_add);
        panel_button.add(btn_delete);
        panel_button.add(btn_clear);
        panel_button.setBounds(70, 650, 380, 50);
        panel_button.setBackground(Color.getHSBColor(100, 5, 80));

        btn_refresh = new JButton("Refresh"); btn_refresh.setFont(font1);
        btn_refresh.addActionListener(this);
        btn_refresh.setBounds(1700, 20, 150, 50);
        
        panel_service.setLayout(null);
        panel_service.add(btn_refresh);
        panel_service.add(panel1_data);
        panel_service.add(panel_button);
        panel_service.add(panel_serviceT);

        panel_service.setBackground(Color.getHSBColor(100, 5, 80));

        JTabbedPane tab = new JTabbedPane();
        tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tab.addTab(" Service ", panel_service);
        tab.addTab(" Customer Details", panel_customer);
        tab.setBounds(30, 140, 1860, 880);
        tab.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));

        frame.setLayout(null);
        frame.add(panel_head);
        frame.add(tab);
        frame.setVisible(true);
    }

//updating service table
    private void update_customerT() {
        ServiceJDBC jdbcBooking = new ServiceJDBC();
        List bookings = jdbcBooking.search(Integer.parseInt(txt_search.getText()));
        System.out.println(bookings.size());
        table_customerT.setRowCount(0);
        if (bookings.size() > 0) {
            for (int i = 0; i < bookings.size(); i++) {
                Rep_makeBooking_table tmp_person = (Rep_makeBooking_table) bookings.get(i);
                Vector tmpPerson = new Vector();
                tmpPerson.add(tmp_person.getCustomerID());
                tmpPerson.add(tmp_person.getBookingID());
                tmpPerson.add(tmp_person.getName());
                tmpPerson.add(tmp_person.getRoomType());
                tmpPerson.add(tmp_person.getCheckIn_date());
                tmpPerson.add(tmp_person.getCheckOut_date());
                tmpPerson.add(tmp_person.getRoomNo());
                tmpPerson.add(tmp_person.getBookingStatus());
                table_customerT.addRow(tmpPerson);
            }
        }
    }
    
    private void update_serviceTable() {
        StaffJDBC JDBCcustomer = new StaffJDBC();
        List bookings1 = JDBCcustomer.get_bookings();
        System.out.println(bookings1.size());
        table_bookingT.setRowCount(0);
        for (int i = 0; i < bookings1.size(); i++) {
            Staff_Service_Table tmpCustomer = (Staff_Service_Table) bookings1.get(i);
            Vector row = new Vector();
            row.add(tmpCustomer.getBookingId());
            row.add(tmpCustomer.getName());
            row.add(tmpCustomer.getLaundryPrice());
            row.add(tmpCustomer.getTelephone());
            row.add(tmpCustomer.getSwimmingPrice());
            row.add(tmpCustomer.getGymPrice());
            row.add(tmpCustomer.getBarPrice());
            table_bookingT.addRow(row);
        }
    }

    public static void main(String[] args) {
        StaffI obj = new StaffI();
        obj.StaffI();
    }

//adding to function to each button
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btn_refresh){
            //this button is to refresh when a customer is making booking while the receptionist interface is open
           update_serviceTable();
        }
        if (e.getSource() == btn_add) {

            Staff_Service_add staffservice = new Staff_Service_add(Integer.parseInt(txt_bookingID.getText()), Integer.parseInt(txt_laundry.getText()), Integer.parseInt(txt_telephone.getText()), Integer.parseInt(txt_swimming.getText()), Integer.parseInt(txt_gym.getText()), Integer.parseInt(txt_bar.getText()));
            System.out.println(staffservice);

            ServiceJDBC obj = new ServiceJDBC();
            result = obj.insert(staffservice);
            if (result) {
                update_serviceTable();
                String message = " Service Added ";
                JOptionPane.showMessageDialog(null, message, "Sucess", JOptionPane.INFORMATION_MESSAGE);
                // update_tableView();
            } else {
                JOptionPane.showMessageDialog(null, " Unsucessful", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btn_delete) {
            int booking_ID = Integer.parseInt(txt_bookingID.getText());

            ServiceJDBC obj2 = new ServiceJDBC();
            result = obj2.delete(booking_ID);
            if (result) {
                update_serviceTable();
                String message = "  Service Cancelled ";
                JOptionPane.showMessageDialog(null, message, "Sucess", JOptionPane.INFORMATION_MESSAGE);
                //update_tableView();
            } else {
                JOptionPane.showMessageDialog(null, "  Cancel Incomplete", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btn_clear) {
            txt_bookingID.setText("0");
            txt_laundry.setText("0");
            txt_telephone.setText("0");
            txt_swimming.setText("0");
            txt_gym.setText("0");
            txt_bar.setText("0");
        }

//********************************************customer tab***************************************
        if (e.getSource() == btn_search) {

            int customer_ID = Integer.parseInt(txt_search.getText());
            System.out.println("customerID is " + customer_ID);
            ServiceJDBC obj2 = new ServiceJDBC();
            obj2.search(customer_ID);
            update_customerT();
            System.out.print("booking updated");

        }

    }

  

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            txt_laundry.setText("200");
            txt_telephone.setText("150");
            txt_swimming.setText("500");
            txt_gym.setText("300");
            txt_bar.setText("1000");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        }

    @Override
    public void keyReleased(KeyEvent e) {
        }

    
}
