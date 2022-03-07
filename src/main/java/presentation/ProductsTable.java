package presentation;


import model.Product;
import utils.DisplayableObjectTableModel;
import utils.ObjectTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductsTable extends JPanel {

    public List<Product> productsList;
    public ObjectTableModel<Product> tableModel;
    public JTable table;

    public ProductsTable() {

        this.setLayout(new GridLayout());
        tableModel = new DisplayableObjectTableModel<>(Product.class);
        tableModel.setObjectRows(productsList);
        table = new JTable(tableModel);
        table.setBackground(Color.LIGHT_GRAY);
        table.setRowHeight(40);
        table.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
        JScrollPane pane = new JScrollPane(table);
        this.add(pane);

    }
    public void  refresh() {
        tableModel.setObjectRows(tableModel.getObjectRows());
        table.setModel(tableModel);
        table.revalidate();
        table.repaint();
    }
}
