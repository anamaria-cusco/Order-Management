package presentation.controller;

import bll.ClientBLL;
import model.Client;
import presentation.ClientsFrame;
import java.util.List;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientControlller {
    private final ClientBLL clientBLL;
    private List<Client> clientsList;
    private Client selectedClient;
    protected ClientsFrame clientsFrame;




    public ClientControlller(){
        clientBLL=new ClientBLL();

        clientsFrame=new ClientsFrame();
        clientsFrame.addPanel.addSubmitClientButtonListener(new SubmitClientListener());
        clientsFrame.editPanel.addClientOperationButtonListener(new EditClientListener());
        clientsFrame.editPanel.addSubmitEditClientBtnListener(new SubmitClientListener());
        clientsFrame.editPanel.addClientSearchBtnListener(new ClientSearchListener());
        clientsFrame.deletePanel.addClientSearchBtnListener(new ClientSearchListener());
        clientsFrame.deletePanel.addClientOperationButtonListener(new DeleteClientListener());
        clientsFrame.viewAllClientsBtnListener(new ViewAllClientsListener());


    }


    class EditClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           int selectedRow = clientsFrame.editPanel.foundPanel.table.getSelectedRow();
            selectedClient= clientsFrame.editPanel.foundPanel.tableModel.getObjectFromSelectedRow(selectedRow);
            clientsFrame.editPanel.foundPanel.add(clientsFrame.editPanel.clientForm);
            clientsFrame.editPanel.clientForm.setFirstNameTextField(selectedClient.getFirstName());
            clientsFrame.editPanel.clientForm.setLastNameTextField(selectedClient.getLastName());
            clientsFrame.editPanel.clientForm.setPhoneTextField(selectedClient.getPhone_number());
            clientsFrame.editPanel.clientForm.setEmailTextField(selectedClient.getEmail());
            clientsFrame.editPanel.clientForm.setAddressTextArea(selectedClient.getAddress());

        }
    }

    class DeleteClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = clientsFrame.deletePanel.foundPanel.table.getSelectedRow();
            selectedClient= clientsFrame.deletePanel.foundPanel.tableModel.getObjectFromSelectedRow(selectedRow);
            clientBLL.deleteClient(selectedClient.getId());
            clientsFrame.deletePanel.foundPanel.tableModel.deleteObjectFromSelectRow(selectedRow);
            clientsFrame.deletePanel.foundPanel.tableModel.setObjectRows(clientsFrame.deletePanel.foundPanel.tableModel.getObjectRows());
            clientsFrame.deletePanel.foundPanel.table.setModel(clientsFrame.deletePanel.foundPanel.tableModel);
            clientsFrame.deletePanel.foundPanel.table.repaint();

        }
    }



    class SubmitClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(ClientsFrame.insertOp);
            System.out.println("Submit Cliked");
            Icon errorIcon =new ImageIcon("src/main/java/presentation/icons/errorIcon.png");
            String firstName;
            String lastName;
            String phoneNumber;
            String email;
            String address;

            Client newClient;
            if(ClientsFrame.insertOp) {
                firstName = clientsFrame.addPanel.getFirstNameTextField().getText();
                lastName = clientsFrame.addPanel.getLastNameTextField().getText();
                phoneNumber = clientsFrame.addPanel.getPhoneTextField().getText();
                email = clientsFrame.addPanel.getEmailTextField().getText();
                address = clientsFrame.addPanel.getAddressTextArea().getText();
                newClient=new Client(firstName,lastName,address,phoneNumber,email);
            }else{
                int id=selectedClient.getId();
                firstName = clientsFrame.editPanel.clientForm.getFirstNameTextField().getText();
                lastName = clientsFrame.editPanel.clientForm.getLastNameTextField().getText();
                phoneNumber = clientsFrame.editPanel.clientForm.getPhoneTextField().getText();
                email = clientsFrame.editPanel.clientForm.getEmailTextField().getText();
                address = clientsFrame.editPanel.clientForm.getAddressTextArea().getText();
                newClient=new Client(id,firstName,lastName,address,phoneNumber,email);
            }

            System.out.println(newClient);
            int returnedValue;
            if(ClientsFrame.insertOp) {
                returnedValue = clientBLL.addClient(newClient);
            }
            else{
                returnedValue=clientBLL.editClient(newClient);
            }

            switch (returnedValue){

                case 1:
                    JOptionPane.showMessageDialog(null,"First Name is not valid!","Error",JOptionPane.ERROR_MESSAGE,errorIcon);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null,"Last Name is not valid!","Error",JOptionPane.ERROR_MESSAGE,errorIcon);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null,"Phone Number is not valid!","Error",JOptionPane.ERROR_MESSAGE,errorIcon);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null,"Email is not valid!","Error",JOptionPane.ERROR_MESSAGE,errorIcon);
                    break;
                case -1:
                    JOptionPane.showMessageDialog(null,"This client already exists!","Error",JOptionPane.ERROR_MESSAGE,errorIcon);
                    break;
                default:break;
            }
        }
    }




    class ClientSearchListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(ClientsFrame.editOp);

               if(ClientsFrame.editOp) {
                   String searchText= clientsFrame.editPanel.getSearchTextField().getText();
                   clientsList=clientBLL.searchClient(searchText);
                   System.out.println(clientsList);
                   if(clientsList!=null) {
                       clientsFrame.editPanel.foundPanel.clientsList = clientsList;
                       clientsFrame.editPanel.foundPanel.tableModel.setObjectRows(clientsList);
                       clientsFrame.editPanel.foundPanel.refresh();
                       clientsFrame.editPanel.foundPanel.revalidate();
                       clientsFrame.editPanel.foundPanel.repaint();
                   }
               }else{
                   String searchText= clientsFrame.deletePanel.getSearchTextField().getText();
                   clientsList=clientBLL.searchClient(searchText);
                   System.out.println(clientsList);
                   if(clientsList!=null) {
                       clientsFrame.deletePanel.foundPanel.clientsList = clientsList;
                       clientsFrame.deletePanel.foundPanel.tableModel.setObjectRows(clientsList);
                       clientsFrame.deletePanel.foundPanel.refresh();
                       clientsFrame.deletePanel.foundPanel.revalidate();
                       clientsFrame.deletePanel.foundPanel.repaint();
                   }

               }
        }
    }


    class ViewAllClientsListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            clientsList=clientBLL.viewAll();
            if(clientsList!=null) {
                clientsFrame.viewAllPanel.clientsList = clientsList;
                clientsFrame.viewAllPanel.tableModel.setObjectRows(clientsList);
                clientsFrame.viewAllPanel.refresh();
                clientsFrame.viewAllPanel.revalidate();
                clientsFrame.viewAllPanel.repaint();
            }
        }
    }



}
