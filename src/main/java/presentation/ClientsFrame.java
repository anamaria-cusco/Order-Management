package presentation;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;


public class ClientsFrame extends JFrame {

    // Cards
    public static boolean insertOp=true;
    public static boolean editOp;
    public ClientForm addPanel;
    public ClientEditDeletePanel editPanel;
    public ClientEditDeletePanel deletePanel;
    public  ClientsTable viewAllPanel;

    private final JPanel cardsPanel;
    public JButton viewAllBtn;

    public ClientsFrame() {

        JPanel mainPanel;
        JPanel leftPanel;
        CardLayout cardLayout;



        String ADDPANEL = "Add";
        String EDITPANEL = "Edit";
        String DELETEPANEL = "Delete";
        String VIEWPANEL = "View";

        JButton addBtn;
        JButton editBtn;
        JButton deleteBtn;



        GridLayout gridLayout = new GridLayout(4, 1);
        gridLayout.setVgap(5);
        mainPanel = new JPanel(new BorderLayout());
        leftPanel = new JPanel(gridLayout);

        leftPanel.setBackground(Color.LIGHT_GRAY);
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        cardsPanel.setBackground(Color.LIGHT_GRAY);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        addBtn = new JButton("Add new client");
        addBtn.setBorder(border);
        addBtn.setBackground(new Color(244, 186, 26));
        addBtn.setFont(new Font("Hello Sans", Font.BOLD, 18));


        editBtn = new JButton("Edit an existing client");
        editBtn.setBorder(border);
        editBtn.setBackground(new Color(244, 186, 26));
        editBtn.setFont(new Font("Hello Sans", Font.BOLD, 18));


        deleteBtn = new JButton("Delete client");
        deleteBtn.setBorder(border);
        deleteBtn.setBackground(new Color(244, 186, 26));
        deleteBtn.setFont(new Font("Hello Sans", Font.BOLD, 18));


        viewAllBtn = new JButton("View all clients");
        viewAllBtn.setBorder(border);
        viewAllBtn.setBackground(new Color(244, 186, 26));
        viewAllBtn.setFont(new Font("Hello Sans", Font.BOLD, 18));


        leftPanel.add(addBtn);
        leftPanel.add(editBtn);
        leftPanel.add(deleteBtn);
        leftPanel.add(viewAllBtn);


        /////////////////////////////
        addPanel = new ClientForm();
        editPanel = new ClientEditDeletePanel("Edit Client");
        deletePanel = new ClientEditDeletePanel("Delete Row");
        viewAllPanel = new ClientsTable();


        //adding cards to cards holder
        cardsPanel.add(addPanel, ADDPANEL);
        cardsPanel.add(editPanel, EDITPANEL);
        cardsPanel.add(deletePanel, DELETEPANEL);
        cardsPanel.add(viewAllPanel, VIEWPANEL);

        mainPanel.add(leftPanel, BorderLayout.LINE_START);
        mainPanel.add(cardsPanel, BorderLayout.CENTER);


        this.setContentPane(mainPanel);
        this.setSize(800, 800);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        addBtn.addActionListener(e -> {
            insertOp=true;
            CardLayout cl = (CardLayout) (cardsPanel.getLayout());

            cl.show(cardsPanel, ADDPANEL);
        });

        editBtn.addActionListener(e -> {
            insertOp=false;
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


    public void itemStateChanged (ItemEvent evt)
    {
        CardLayout cl = (CardLayout) (cardsPanel.getLayout());

        cl.show(cardsPanel, (String) evt.getItem());
    }
    public void viewAllClientsBtnListener(ActionListener a){
        viewAllBtn.addActionListener(a);
    }


}
