package presentation;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;




public class OrdersFrame extends JFrame {


    private final JButton searchClientsBtn;
    private final JTextField searchClientsTextField;
    private final JButton searchProductsBtn;
    private final JTextField searchProductsTextField;

    public JPanel tabelsPanel;
    public ProductsTable productsTable;
    public ClientsTable clientsTable;


    private final JTextArea downTextArea;
    private final JButton selectDateBtn;
    private final JButton selectClientBtn;
    private final JButton selectProductBtn;
    private final JButton placeOrderBtn;


    public UtilDateModel model = new UtilDateModel();
    public JDatePanelImpl datePanel = new JDatePanelImpl(model);
    public JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

    public OrdersFrame(){
        JPanel searchPanel;

        JPanel downPanel;
        JScrollPane downScrollPane;

        selectDateBtn=new JButton("Select Order Date");

        Border border = BorderFactory.createLineBorder(Color.BLACK,2);
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(244, 186, 26));

        GridBagConstraints gbc=new GridBagConstraints();
        tabelsPanel=new JPanel(new GridBagLayout());


        tabelsPanel.setBackground(new Color(244, 186, 26));
        searchPanel=new JPanel();
        searchPanel.setBackground(new Color(244, 186, 26));


        searchClientsTextField=new JTextField(25);
        searchClientsTextField.setText("Insert an ID or a client name");
        searchClientsTextField.setFont(new Font("Hello Sans",Font.BOLD,12));
        searchClientsTextField.setPreferredSize(new Dimension(25,60));
        searchClientsTextField.setBorder(border);
        searchClientsTextField.setBackground(Color.LIGHT_GRAY);



        searchClientsBtn=new JButton(new ImageIcon("src/main/java/presentation/icons/searchIcon.png"));
        searchClientsBtn.setBorder(border);
        searchClientsBtn.setBackground(new Color(244, 186, 26));


        searchPanel.add(searchClientsTextField);
        searchPanel.add(searchClientsBtn);


        searchProductsTextField=new JTextField(25);
        searchProductsTextField.setText("Insert an ID or a product name");
        searchProductsTextField.setFont(new Font("Hello Sans",Font.BOLD,12));
        searchProductsTextField.setPreferredSize(new Dimension(30,60));
        searchProductsTextField.setBorder(border);
        searchProductsTextField.setBackground(Color.LIGHT_GRAY);

        searchProductsTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                searchProductsTextField.setText(null); // Empty the text field when it receives focus
            }

            @Override
            public void focusLost(FocusEvent e) {
                searchProductsTextField.setText(searchProductsTextField.getText());
            }

        });

        searchProductsBtn=new JButton(new ImageIcon("src/main/java/presentation/icons/searchIcon.png"));
        searchProductsBtn.setBorder(border);
        searchProductsBtn.setBackground(new Color(244, 186, 26));
        searchPanel.add(searchProductsTextField);
        searchPanel.add(searchProductsBtn);

        searchPanel.add(datePicker);
        searchPanel.add(selectDateBtn);

        add(searchPanel,BorderLayout.NORTH);

        selectClientBtn=new JButton("Select Client");
        selectClientBtn.setPreferredSize(new Dimension(200,30));
        selectProductBtn=new JButton("Select Product");
        selectProductBtn.setPreferredSize(new Dimension(200,30));

        gbc.gridx=0;
        gbc.gridy=0;
        tabelsPanel.add(selectClientBtn,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        tabelsPanel.add(selectProductBtn,gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        clientsTable=new ClientsTable();
        clientsTable.setPreferredSize(new Dimension(600,300));
        tabelsPanel.add(clientsTable,gbc);


        gbc.gridx=1;
        gbc.gridy=1;
        productsTable=new ProductsTable();
        productsTable.setPreferredSize(new Dimension(400,300));
        tabelsPanel.add(productsTable,gbc);


        add(tabelsPanel);


        placeOrderBtn=new JButton("Place Order");
        downPanel=new JPanel(new GridBagLayout());
        downPanel.setBackground(new Color(244, 186, 26));
        downTextArea=new JTextArea();
        downTextArea.setBackground(new Color(244, 186, 26));
        downTextArea.setFont(new Font("Hello Sans",Font.BOLD,16));
        downTextArea.setEditable(false);
        downTextArea.setText("Order Details\n");


        gbc.gridx=0;
        gbc.gridy=0;
        downScrollPane=new JScrollPane (downTextArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        downScrollPane.setBorder(border);
        downScrollPane.setPreferredSize(new Dimension(800,250));
        downPanel.add(downScrollPane,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        downPanel.add(placeOrderBtn,gbc);
        add(downPanel,BorderLayout.SOUTH);


        this.setSize(1100,800);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }


    public void addProductSearchButtonListener(ActionListener a){

        searchProductsBtn.addActionListener(a);
    }


    public void addClientSearchButtonListener(ActionListener a){

        searchClientsBtn.addActionListener(a);
    }


    public void addSelectClientButtonListener(ActionListener a){

        selectClientBtn.addActionListener(a);
    }

    public void addSelectProductButtonListener(ActionListener a){

        selectProductBtn.addActionListener(a);
    }

    public void addSelectOrderDateButtonListener(ActionListener a){

        selectDateBtn.addActionListener(a);
    }
    public void addPlaceOrderButtonListener(ActionListener a){

        placeOrderBtn.addActionListener(a);
    }
    public JTextField getSearchClientsTextField() {
        return searchClientsTextField;
    }


    public JTextField getSearchProductsTextField() {
        return searchProductsTextField;
    }


    public JTextArea getDownTextArea() {
        return downTextArea;
    }





}
