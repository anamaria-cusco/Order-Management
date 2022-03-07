package presentation.controller;

import presentation.IntroFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppController {
    IntroFrame introFrame;
    ClientControlller clientControlller;
    ProductController productController;
    OrderController orderController;
    public AppController(IntroFrame introFrame,ClientControlller clientControlller,ProductController productController,OrderController orderController){
        this.introFrame=introFrame;
        this.clientControlller=clientControlller;
        this.productController=productController;
        this.orderController=orderController;

        introFrame.addClientButtonListener(new ClientListener());
        introFrame.addProductsButtonListener(new ProductsListener());
        introFrame.addOrdersButtonListener(new OrdersListener());

    }

    class ClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clientControlller.clientsFrame.setVisible(true);
        }
    }

    class ProductsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            productController.productsFrame.setVisible(true);
        }
    }

    class OrdersListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            orderController.ordersFrame.setVisible(true);
        }
    }
}
