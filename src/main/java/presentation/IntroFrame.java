package presentation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class IntroFrame extends JFrame {

    private final JButton clientsBtn;
    private final JButton productsBtn;
    private final JButton ordersBtn;
    public IntroFrame(){

        Border border = BorderFactory.createLineBorder(Color.BLACK,3);
        JPanel contentPanel=new JPanel(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.weighty=4;
        gbc.weightx=10;
        contentPanel.setBackground(new Color(244, 186, 26));

        gbc.gridx=1;
        gbc.gridy=1;
        JLabel title = new JLabel("Warehouse Managment System");
        title.setFont(new Font("Hello Sans",Font.BOLD,32));
        contentPanel.add(title,gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        clientsBtn=new JButton(" Clients",new ImageIcon("src/main/java/presentation/icons/clientsIcon.png"));
        clientsBtn.setBackground(new Color(	244, 186, 26));
        clientsBtn.setBorder(border);
        clientsBtn.setPreferredSize(new Dimension(600,200));
        clientsBtn.setFont(new Font("Hello Sans",Font.BOLD,32));
        contentPanel.add(clientsBtn,gbc);

        gbc.gridx=1;
        gbc.gridy=3;
        productsBtn=new JButton("Products",new ImageIcon("src/main/java/presentation/icons/productsIcon.png"));
        productsBtn.setBackground(new Color(	244, 186, 26));
        productsBtn.setBorder(border);
        productsBtn.setPreferredSize(new Dimension(600,200));
        productsBtn.setFont(new Font("Hello Sans",Font.BOLD,32));
        contentPanel.add(productsBtn,gbc);

        gbc.gridx=1;
        gbc.gridy=4;
        ordersBtn=new JButton("Place an order",new ImageIcon("src/main/java/presentation/icons/ordersIcon.png"));
        ordersBtn.setBackground(new Color(	244, 186, 26));
        ordersBtn.setBorder(border);
        ordersBtn.setPreferredSize(new Dimension(600,200));
        ordersBtn.setFont(new Font("Hello Sans",Font.BOLD,32));
        contentPanel.add(ordersBtn,gbc);



        this.setContentPane(contentPanel);
        this.setSize(800,800);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    //Listeners
    public void addClientButtonListener(ActionListener a){

        clientsBtn.addActionListener(a);
    }
    public void addProductsButtonListener(ActionListener a){
        productsBtn.addActionListener(a);
    }

    public void addOrdersButtonListener(ActionListener a){
        ordersBtn.addActionListener(a);
    }

}
