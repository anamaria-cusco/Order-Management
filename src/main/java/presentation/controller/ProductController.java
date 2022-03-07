package presentation.controller;

import bll.ProductBLL;
import model.Product;
import presentation.ProductsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductController {
    private final ProductBLL productBLL;
    private List<Product> productsList;
    private Product selectedProduct;
    public ProductsFrame productsFrame;

    public ProductController(){
        productBLL=new ProductBLL();
        productsFrame=new ProductsFrame();

     
        productsFrame.addPanel.addSubmitProductButtonListener(new SubmitProductListener());
        productsFrame.editPanel.addProductOperationButtonListener(new EditProductListener());
        productsFrame.editPanel.addSubmitEditProductBtnListener(new SubmitProductListener());
        productsFrame.editPanel.addProductSearchBtnListener(new ProductSearchListener());
        productsFrame.deletePanel.addProductSearchBtnListener(new ProductSearchListener());
        productsFrame.deletePanel.addProductOperationButtonListener(new DeleteProductListener());
        productsFrame.viewAllProductsBtnListener(new ViewAllProductsListener());
    }

    class EditProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = productsFrame.editPanel.foundPanel.table.getSelectedRow();
            selectedProduct= productsFrame.editPanel.foundPanel.tableModel.getObjectFromSelectedRow(selectedRow);
            productsFrame.editPanel.foundPanel.add(productsFrame.editPanel.productForm);
            productsFrame.editPanel.productForm.setNameTextField(selectedProduct.getName());
            productsFrame.editPanel.productForm.setPriceTextField(Float.toString(selectedProduct.getPrice()));
            productsFrame.editPanel.productForm.setStockQuantityTextField(Integer.toString(selectedProduct.getStock_quantity()));
            productsFrame.editPanel.foundPanel.revalidate();
            productsFrame.editPanel.foundPanel.repaint();

        }
    }

    class DeleteProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = productsFrame.deletePanel.foundPanel.table.getSelectedRow();
            selectedProduct= productsFrame.deletePanel.foundPanel.tableModel.getObjectFromSelectedRow(selectedRow);
            productBLL.deleteProduct(selectedProduct.getId());
            productsFrame.deletePanel.foundPanel.tableModel.deleteObjectFromSelectRow(selectedRow);
            productsFrame.deletePanel.foundPanel.tableModel.setObjectRows(productsFrame.deletePanel.foundPanel.tableModel.getObjectRows());
            productsFrame.deletePanel.foundPanel.table.setModel(productsFrame.deletePanel.foundPanel.tableModel);
            productsFrame.deletePanel.foundPanel.table.repaint();

        }
    }

    class SubmitProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Icon errorIcon =new ImageIcon("src/main/java/presentation/icons/errorIcon.png");

            int id=-1;
            String name;
            String priceStr;
            String stockQuantityStr;

            float price=0;
            int stockQuantity=0;

            Product newProduct;
            if(ProductsFrame.insertOp) {
                name = productsFrame.addPanel.getNameTextField().getText();
                priceStr = productsFrame.addPanel.getPriceTextField().getText();
                stockQuantityStr = productsFrame.addPanel.getStockQuantityTextField().getText();
            }else{
                id=selectedProduct.getId();
                name = productsFrame.editPanel.productForm.getNameTextField().getText();
                priceStr = productsFrame.editPanel.productForm.getPriceTextField().getText();
                stockQuantityStr = productsFrame.editPanel.productForm.getStockQuantityTextField().getText();

            }


            try{
                price=Float.parseFloat(priceStr);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Price must be a positive real number","Error",JOptionPane.ERROR_MESSAGE,errorIcon);
            }
            try{
                stockQuantity=Integer.parseInt(stockQuantityStr);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Stock Quantity must be a non zero natural number","Error",JOptionPane.ERROR_MESSAGE,errorIcon);
            }

            if(ProductsFrame.insertOp){
                newProduct=new Product(name,price,stockQuantity);
            }else{
                newProduct=new Product(id,name,price,stockQuantity);
            }


            int returnedValue;
            if(ProductsFrame.insertOp) {
                returnedValue = productBLL.addProduct(newProduct);
            }
            else{
                returnedValue=productBLL.editProduct(newProduct);
            }


            switch (returnedValue){

                case 1:
                    JOptionPane.showMessageDialog(null,"Name is not valid!","Error",JOptionPane.ERROR_MESSAGE,errorIcon);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null,"Price is not valid!","Error",JOptionPane.ERROR_MESSAGE,errorIcon);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null,"Stock Quantity is not valid!","Error",JOptionPane.ERROR_MESSAGE,errorIcon);
                    break;
                case -1:
                    JOptionPane.showMessageDialog(null,"This product already exists!","Error",JOptionPane.ERROR_MESSAGE,errorIcon);
                    break;
                default:break;
            }
        }
    }

    class ProductSearchListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(ProductsFrame.editOp);

            if(ProductsFrame.editOp) {
                String searchText= productsFrame.editPanel.getSearchTextField().getText();
                productsList=productBLL.searchProduct(searchText);
                if(productsList!=null) {
                    productsFrame.editPanel.foundPanel.productsList = productsList;
                    productsFrame.editPanel.foundPanel.tableModel.setObjectRows(productsList);
                    productsFrame.editPanel.foundPanel.refresh();
                    productsFrame.editPanel.foundPanel.revalidate();
                    productsFrame.editPanel.foundPanel.repaint();
                }
            }else{
                String searchText= productsFrame.deletePanel.getSearchTextField().getText();
                productsList=productBLL.searchProduct(searchText);

                if(productsList!=null) {
                    productsFrame.deletePanel.foundPanel.productsList = productsList;
                    productsFrame.deletePanel.foundPanel.tableModel.setObjectRows(productsList);
                    productsFrame.deletePanel.foundPanel.refresh();
                    productsFrame.deletePanel.foundPanel.revalidate();
                    productsFrame.deletePanel.foundPanel.repaint();
                }

            }
        }
    }

    class ViewAllProductsListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            productsList=productBLL.viewAll();
            if(productsList!=null) {
                productsFrame.viewAllPanel.productsList = productsList;
                productsFrame.viewAllPanel.tableModel.setObjectRows(productsList);
                productsFrame.viewAllPanel.refresh();
                productsFrame.viewAllPanel.revalidate();
                productsFrame.viewAllPanel.repaint();
            }
        }
    }
}
