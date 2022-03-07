package presentation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;


public class ClientEditDeletePanel extends JPanel{


    private final JTextField searchTextField;
    private final JButton operationButton;
    private final JButton searchBtn;
    public ClientsTable foundPanel;
    public ClientForm clientForm;

    public ClientEditDeletePanel(String buttonName){


        this.setLayout(new BorderLayout());

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        clientForm=new ClientForm();
        foundPanel=new ClientsTable();
        foundPanel.setLayout(new GridLayout(2,1));
        //foundPanel.setBackground(Color.LIGHT_GRAY);


        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(new Color(244, 186, 26));

        searchTextField=new JTextField(25);
        searchTextField.setText("Insert an ID or a client name");
        searchTextField.setFont(new Font("Hello Sans",Font.BOLD,16));
        searchTextField.setPreferredSize(new Dimension(30,60));
        searchTextField.setBorder(border);
        searchTextField.setBackground(Color.LIGHT_GRAY);


        operationButton= new JButton(buttonName);
        searchTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                searchTextField.setText(null); // Empty the text field when it receives focus
            }

            @Override
            public void focusLost(FocusEvent e) {
                searchTextField.setText(searchTextField.getText());
            }
        });


        searchBtn=new JButton(new ImageIcon("src/main/java/presentation/icons/searchIcon.png"));
        searchBtn.setBorder(border);
        searchBtn.setBackground(new Color(244, 186, 26));

        searchPanel.add(operationButton,BorderLayout.LINE_START);
        searchPanel.add(searchTextField, BorderLayout.NORTH);
        searchPanel.add(searchBtn,BorderLayout.CENTER);

        this.add(searchPanel,BorderLayout.NORTH);
        this.add(foundPanel,BorderLayout.CENTER);


    }

    //Listeners

    public void addClientSearchBtnListener(ActionListener a){
        searchBtn.addActionListener(a);
    }

    public void addClientOperationButtonListener(ActionListener a){

        operationButton.addActionListener(a);
    }

    public void addSubmitEditClientBtnListener(ActionListener a){
        this.clientForm.btnSubmit.addActionListener(a);
    }

    public JTextField getSearchTextField() {
        return searchTextField;
    }

}
