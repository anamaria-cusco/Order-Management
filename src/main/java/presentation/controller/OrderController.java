package presentation.controller;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.OrderDetailsBLL;
import bll.ProductBLL;
import model.Client;
import model.Order_details;
import model.Orders;
import model.Product;
import presentation.InsertQuantityFrame;
import presentation.OrdersFrame;
import utils.FileWriterClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.List;

public class OrderController {

    double total=0;
    List<Integer> productsIDs=new ArrayList<>();
    List<Integer> productsSelectedQuantities=new ArrayList<>();

    public String orderDetails=new String("ORDER DETAILS\n\n");
    private final ClientBLL clientBLL;
    private final ProductBLL productBLL;
    private final OrderBLL orderBLL;
    private final OrderDetailsBLL orderDetailsBLL;

    private Product selectedProduct;
    private Client selectedClient;
    private final Orders insertOrder=new Orders();

    protected OrdersFrame ordersFrame;
    public OrderController(){

        ordersFrame=new OrdersFrame();
        clientBLL=new ClientBLL();
        productBLL=new ProductBLL();
        orderBLL=new OrderBLL();
        orderDetailsBLL=new OrderDetailsBLL();

        ordersFrame.addClientSearchButtonListener(new ClientSearchListener());
        ordersFrame.addProductSearchButtonListener(new ProductSearchListener());
        ordersFrame.addSelectClientButtonListener(new SelectClientListener());
        ordersFrame.addSelectProductButtonListener(new SelectProductListener());
        ordersFrame.addSelectOrderDateButtonListener(new SelectOrderDateListener());
        ordersFrame.addPlaceOrderButtonListener(new PlaceOrderListener());
    }

    class ClientSearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String searchText = ordersFrame.getSearchClientsTextField().getText();
            List<Client> clientsList = clientBLL.searchClient(searchText);
            if (clientsList != null) {
                ordersFrame.clientsTable.clientsList = clientsList;
                ordersFrame.clientsTable.tableModel.setObjectRows(clientsList);
                ordersFrame.clientsTable.refresh();
                ordersFrame.tabelsPanel.revalidate();
                ordersFrame.tabelsPanel.repaint();
            }
        }
    }

    class ProductSearchListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String searchText = ordersFrame.getSearchProductsTextField().getText();
            List<Product> productsList = productBLL.searchProduct(searchText);
            if (productsList != null) {
                ordersFrame.productsTable.productsList = productsList;
                ordersFrame.productsTable.tableModel.setObjectRows(productsList);
                ordersFrame.productsTable.refresh();
                ordersFrame.tabelsPanel.revalidate();
                ordersFrame.tabelsPanel.repaint();
            }

        }
    }

    class SelectClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = ordersFrame.clientsTable.table.getSelectedRow();
            selectedClient= ordersFrame.clientsTable.tableModel.getObjectFromSelectedRow(selectedRow);
            insertOrder.setClient_id(selectedClient.getId());
            orderDetails +=selectedClient.displayClient();
            orderDetails+="\nProducts:\n";
            orderDetails+=String.format("%-15s %-15s %-15s\n","name","price","quantity");
            ordersFrame.getDownTextArea().setText(orderDetails);

        }
    }

    class SelectProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Icon errorIcon = new ImageIcon("src/main/java/presentation/icons/errorIcon.png");
            if (selectedClient == null) {
                JOptionPane.showMessageDialog(null, "Please select a client first!", "Error", JOptionPane.ERROR_MESSAGE, errorIcon);
            } else {
                int selectedRow = ordersFrame.productsTable.table.getSelectedRow();
                selectedProduct = ordersFrame.productsTable.tableModel.getObjectFromSelectedRow(selectedRow);
                productsIDs.add(selectedProduct.getId());
                InsertQuantityFrame insertQuantityFrame = new InsertQuantityFrame(selectedProduct.getStock_quantity());
                insertQuantityFrame.setLocationRelativeTo(ordersFrame);
                int selectedQuantity = insertQuantityFrame.showInsertQuantityFrame();
                if (selectedQuantity == 0) {
                    selectedQuantity++;
                }
                total += selectedQuantity * selectedProduct.getPrice();
                productsSelectedQuantities.add(selectedQuantity);

                int remainedQuantity = selectedProduct.getStock_quantity() - selectedQuantity;
                if (remainedQuantity > 0) {
                    selectedProduct.setStock_quantity(remainedQuantity);
                    productBLL.editProduct(selectedProduct);
                } else {
                    ordersFrame.productsTable.tableModel.deleteObjectFromSelectRow(selectedRow);
                }


                orderDetails += String.format("%-15s %-15s %-15s\n", selectedProduct.getName(), selectedProduct.getPrice(), selectedQuantity);
                ordersFrame.getDownTextArea().setText(orderDetails);

                ordersFrame.productsTable.refresh();
                ordersFrame.tabelsPanel.revalidate();
                ordersFrame.tabelsPanel.repaint();
                ordersFrame.revalidate();
                ordersFrame.repaint();

            }
        }
    }

    class SelectOrderDateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            java.util.Date selectedDate = (java.util.Date) ordersFrame.datePicker.getModel().getValue();
            Timestamp ts=new Timestamp(selectedDate.getTime());
            insertOrder.setOrder_date(ts.toString());
        }
    }

    class PlaceOrderListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Integer> remainedQuantities = new ArrayList<>();
            Icon errorIcon = new ImageIcon("src/main/java/presentation/icons/errorIcon.png");
            if (insertOrder.getClient_id() == 0) {
                JOptionPane.showMessageDialog(null, "Please select a client first!", "Error", JOptionPane.ERROR_MESSAGE, errorIcon);
            } else if (insertOrder.getOrder_date() == null) {
                JOptionPane.showMessageDialog(null, "Please select an order date!", "Error", JOptionPane.ERROR_MESSAGE, errorIcon);
            } else if (productsSelectedQuantities.size() == 0) {
                JOptionPane.showMessageDialog(null, "Please choose some products!", "Error", JOptionPane.ERROR_MESSAGE, errorIcon);
            } else {
               // orderDetails += "\nTotal: " + total;
                orderDetails += String.format("\nTotal: %.2f",total);
                orderDetails += "\nDate: " + insertOrder.getOrder_date();
                ordersFrame.getDownTextArea().setText(orderDetails);

                System.out.println(insertOrder);
                int placedOrderID = orderBLL.placeOrder(insertOrder);
                List<Order_details> orderDetailsList = new ArrayList<>();
                for (int i = 0; i < productsIDs.size(); i++) {
                    orderDetailsList.add(new Order_details(productsIDs.get(i), placedOrderID, productsSelectedQuantities.get(i)));
                    remainedQuantities.add(selectedProduct.getStock_quantity() - productsSelectedQuantities.get(i));

                }

                orderDetailsBLL.insertOrderDetails(orderDetailsList);

                for (int i = 0; i < productsIDs.size(); i++) {
                    if (remainedQuantities.get(i) == 0) {
                        productBLL.deleteProduct(selectedProduct.getId());
                        System.out.println("Product have been deleted!");
                    }
                }
                FileWriterClass.printBill(orderDetails);

            }
        }

    }
}

