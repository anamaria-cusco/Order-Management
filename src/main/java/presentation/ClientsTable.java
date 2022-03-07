package presentation;


import model.Client;
import utils.DisplayableObjectTableModel;
import utils.ObjectTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClientsTable extends JPanel {

    public List<Client> clientsList;
    public ObjectTableModel<Client> tableModel;
    public JTable table;


    public ClientsTable() {

        this.setLayout(new GridLayout());
        tableModel = new DisplayableObjectTableModel<>(Client.class);
        tableModel.setObjectRows(clientsList);
        tableModel.fireTableDataChanged();

        this.table = new JTable(tableModel);
        table.setRowSelectionAllowed(true);


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



