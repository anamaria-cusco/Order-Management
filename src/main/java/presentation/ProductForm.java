package presentation;



import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class ProductForm extends JPanel {

    public JButton btnSubmit;
    private final JTextField nameTextField;
    private final JTextField priceTextField;
    private final JTextField stockQuantityTextField;

    public ProductForm() {

        this.setBackground(Color.LIGHT_GRAY);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc =new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets=new Insets(10,10,10,10);
        gbc.ipady=10;

        gbc.gridx=1;
        gbc.gridy=1;
        JLabel title=new JLabel("Product Form");
        title.setFont(new Font("Hello Sans",Font.BOLD,32));
        this.add(title,gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        JLabel nameLabel = new JLabel("Name:");
        this.add(nameLabel,gbc);

        gbc.gridx=2;
        gbc.gridy=2;
        nameTextField = new JTextField();
        nameTextField.setColumns(15);
        this.add(nameTextField,gbc);

        gbc.gridx=1;
        gbc.gridy=3;
        JLabel priceLabel = new JLabel("Price:");
        this.add(priceLabel,gbc);

        gbc.gridx=2;
        gbc.gridy=3;
        priceTextField = new JTextField();
        priceTextField.setColumns(15);
        this.add(priceTextField,gbc);

        gbc.gridx=1;
        gbc.gridy=4;
        JLabel stockQuantityLabel = new JLabel("Stock Quantity: ");
        this.add(stockQuantityLabel,gbc);

        gbc.gridx=2;
        gbc.gridy=4;
        stockQuantityTextField = new JTextField();
        stockQuantityTextField.setColumns(10);
        this.add(stockQuantityTextField,gbc);


        btnSubmit = new JButton("Submit");
        gbc.gridx=1;
        gbc.gridy=7;
        btnSubmit.setBackground(Color.BLUE);
        btnSubmit.setForeground(Color.WHITE);
        this.add(btnSubmit,gbc);


        JButton btnClear = new JButton("Clear");
        gbc.gridx=2;
        gbc.gridy=7;
        this.add(btnClear,gbc);


        btnClear.addActionListener(e -> {
            nameTextField.setText(null);
            priceTextField.setText(null);
            stockQuantityTextField.setText(null);

        });

    }

    //Listeners
    public void addSubmitProductButtonListener(ActionListener a){

        btnSubmit.addActionListener(a);
    }

    public void setNameTextField(String text) {
        this.nameTextField.setText(text);
    }

    public void setPriceTextField(String text) {
        this.priceTextField.setText(text);
    }

    public void setStockQuantityTextField(String text) {
        this.stockQuantityTextField.setText(text);
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getPriceTextField() {
        return priceTextField;
    }

    public JTextField getStockQuantityTextField() {
        return stockQuantityTextField;
    }
}
