package presentation;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class ClientForm extends JPanel {


    public JButton btnSubmit;
    private final JTextField firstNameTextField;
    private final JTextField lastNameTextField;
    private final JTextField phoneTextField;
    private final JTextField emailTextField;
    private final JTextArea addressTextArea;


    public ClientForm() {

        this.setBackground(Color.LIGHT_GRAY);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc =new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets=new Insets(5,5,5,5);
        gbc.ipady=10;

        gbc.gridx=1;
        gbc.gridy=1;
        JLabel title=new JLabel("Client Form");
        title.setFont(new Font("Hello Sans",Font.BOLD,18));
        this.add(title,gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        JLabel firstNameLabel = new JLabel("First Name:");
        this.add(firstNameLabel,gbc);

        gbc.gridx=2;
        gbc.gridy=2;
        firstNameTextField = new JTextField();
        firstNameTextField.setColumns(15);
        this.add(firstNameTextField,gbc);

        gbc.gridx=1;
        gbc.gridy=3;
        JLabel lastNameLabel = new JLabel("Last Name:");
        this.add(lastNameLabel,gbc);

        gbc.gridx=2;
        gbc.gridy=3;
        lastNameTextField = new JTextField();
        lastNameTextField.setColumns(15);
        this.add(lastNameTextField,gbc);

        gbc.gridx=1;
        gbc.gridy=4;
        JLabel phoneLabel = new JLabel("Phone Number: ");
        this.add(phoneLabel,gbc);

        gbc.gridx=2;
        gbc.gridy=4;
        phoneTextField = new JTextField();
        phoneTextField.setColumns(10);
        this.add(phoneTextField,gbc);

        gbc.gridx=1;
        gbc.gridy=5;
        JLabel emailLabel = new JLabel("Email:");
        this.add(emailLabel,gbc);

        gbc.gridx=2;
        gbc.gridy=5;
        emailTextField = new JTextField();
        emailTextField.setColumns(25);
        this.add(emailTextField,gbc);


        gbc.gridx=1;
        gbc.gridy=6;
        JLabel addressLabel = new JLabel("Address:");
        this.add(addressLabel,gbc);

        gbc.gridx=2;
        gbc.gridy=6;
        addressTextArea = new JTextArea(3,25);
        this.add(addressTextArea,gbc);

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
            firstNameTextField.setText(null);
            lastNameTextField.setText(null);
            phoneTextField.setText(null);
            emailTextField.setText(null);
            addressTextArea.setText(null);

        });



    }
    //Listeners
    public void addSubmitClientButtonListener(ActionListener a){

        btnSubmit.addActionListener(a);
    }

    public JTextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public void setFirstNameTextField(String text) {
        this.firstNameTextField.setText(text);
    }

    public JTextField getLastNameTextField() {
        return lastNameTextField;
    }

    public void setLastNameTextField(String text) {
        this.lastNameTextField.setText(text);
    }

    public JTextField getPhoneTextField() {
        return phoneTextField;
    }

    public void setPhoneTextField(String text) {
        this.phoneTextField.setText(text);
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public void setEmailTextField(String text) {
        this.emailTextField.setText(text);
    }

    public JTextArea getAddressTextArea() {
        return addressTextArea;
    }

    public void setAddressTextArea(String text) {
        this.addressTextArea.setText(text);
    }
}
