package presentation;


import model.Product;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;


public class ProductEditDeletePanel extends JPanel{
    public ProductsTable foundPanel;
    public ProductForm productForm;


    private final JTextField searchTextField;
    private final JButton operationButton;
    private final JButton searchBtn;

    public ProductEditDeletePanel(String buttonName){
       JPanel searchPanel;

        this.setLayout(new BorderLayout());

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        productForm=new ProductForm();
        foundPanel=new ProductsTable();
        foundPanel.setLayout(new GridLayout(2,1));



        searchPanel=new JPanel(new BorderLayout());
        searchPanel.setBackground(new Color(244, 186, 26));

        searchTextField=new JTextField(25);
        searchTextField.setText("Insert an ID or a product name");
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

        foundPanel.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int selectedRow = foundPanel.table.getSelectedRow();
                System.out.println("Selected row:"+selectedRow);
                Product p= foundPanel.tableModel.getObjectFromSelectedRow(selectedRow);
                System.out.println(p);

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

    public void addProductSearchBtnListener(ActionListener a){
       searchBtn.addActionListener(a);
    }
    //Listeners
    public void addProductOperationButtonListener(ActionListener a){

        operationButton.addActionListener(a);
    }


    public void addSubmitEditProductBtnListener(ActionListener a){
        this.productForm.btnSubmit.addActionListener(a);
    }

    public JTextField getSearchTextField() {
        return searchTextField;
    }


}
