package presentation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProductsFrame extends JFrame {

    private final JPanel cardsPanel;
    public static boolean insertOp;
    public static boolean editOp;

    // Card Layout
    public ProductForm addPanel;
    public ProductEditDeletePanel editPanel;
    public ProductEditDeletePanel deletePanel;
    public ProductsTable viewAllPanel;
    public JButton viewAllBtn;

    public ProductsFrame(){
        JPanel mainPanel;
        JPanel leftPanel;

       String ADDPANEL = "Add";
       String EDITPANEL = "Edit";
       String DELETEPANEL = "Delete";
       String VIEWPANEL ="View";


       JButton addBtn;
       JButton editBtn;
       JButton deleteBtn;



        GridLayout gridLayout=new GridLayout(4,1);
        gridLayout.setVgap(5);
        mainPanel= new JPanel(new BorderLayout());
        leftPanel=new JPanel(gridLayout);

        leftPanel.setBackground(Color.LIGHT_GRAY);
        cardsPanel=new JPanel(new CardLayout());
        cardsPanel.setBackground(Color.LIGHT_GRAY);
        Border border = BorderFactory.createLineBorder(Color.BLACK,2);

        addBtn=new JButton("Add new product");
        addBtn.setBorder(border);
        addBtn.setBackground(new Color(244, 186, 26));
        addBtn.setFont(new Font("Hello Sans",Font.BOLD,18));


        editBtn=new JButton("Edit an existing product");
        editBtn.setBorder(border);
        editBtn.setBackground(new Color(244, 186, 26));
        editBtn.setFont(new Font("Hello Sans",Font.BOLD,18));

        deleteBtn=new JButton("Delete product");
        deleteBtn.setBorder(border);
        deleteBtn.setBackground(new Color(244, 186, 26));
        deleteBtn.setFont(new Font("Hello Sans",Font.BOLD,18));

        viewAllBtn=new JButton("View all products");
        viewAllBtn.setBorder(border);
        viewAllBtn.setBackground(new Color(244, 186, 26));
        viewAllBtn.setFont(new Font("Hello Sans",Font.BOLD,18));

        leftPanel.add(addBtn);
        leftPanel.add(editBtn);
        leftPanel.add(deleteBtn);
        leftPanel.add(viewAllBtn);


        addPanel = new ProductForm();
        editPanel=new ProductEditDeletePanel("Edit Product");
        deletePanel=new ProductEditDeletePanel("Delete Row");
        viewAllPanel = new ProductsTable();

        addPanel=new ProductForm();

        //adding cards to cards holder
        cardsPanel.add(addPanel,ADDPANEL);
        cardsPanel.add(editPanel,EDITPANEL);
        cardsPanel.add(deletePanel,DELETEPANEL);
        cardsPanel.add(viewAllPanel,VIEWPANEL);


        mainPanel.add(leftPanel, BorderLayout.LINE_START);
        mainPanel.add(cardsPanel,BorderLayout.CENTER);



        this.setContentPane(mainPanel);
        this.setSize(800,800);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);



        addBtn.addActionListener(e -> {   insertOp=true;
            CardLayout cl = (CardLayout) (cardsPanel.getLayout());

            cl.show(cardsPanel, ADDPANEL);
        });

        editBtn.addActionListener(e -> {   insertOp=false;
            editOp=true;
            CardLayout cl = (CardLayout) (cardsPanel.getLayout());

            cl.show(cardsPanel, EDITPANEL);
        });

        deleteBtn.addActionListener(e -> {
            editOp=false;
            CardLayout cl = (CardLayout) (cardsPanel.getLayout());

            cl.show(cardsPanel, DELETEPANEL);
        });

        viewAllBtn.addActionListener(e -> {
            CardLayout cl = (CardLayout) (cardsPanel.getLayout());

            cl.show(cardsPanel, VIEWPANEL);
        });

    }
    public void viewAllProductsBtnListener(ActionListener a){
        viewAllBtn.addActionListener(a);
    }


}
