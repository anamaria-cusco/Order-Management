package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class InsertQuantityFrame extends JDialog  {

    JComboBox<Integer> quantityComboBox;

    int selectedQuantity;


    public InsertQuantityFrame(int stockQuantity){

        this.setModal(true);
        JPanel contentPane=new JPanel(new FlowLayout());


        quantityComboBox= new JComboBox<>();
        JLabel quantityLabel = new JLabel("Please choose the quantity that you want:");
        quantityLabel.setFont(new Font("Hello Sans",Font.BOLD,18));


        JButton OKBtn=new JButton("OK");
        Vector<Integer> v= new Vector<>();
        for (int i = 1; i <= stockQuantity; i++) {
            v.add(i);
        }
        quantityComboBox.setModel(new DefaultComboBoxModel<>(v));
        quantityComboBox.setSelectedItem(1);


        quantityComboBox.setFont(new Font("Hello Sans",Font.BOLD,18));
        quantityComboBox.setPreferredSize(new Dimension(100,50));
        contentPane.add(quantityLabel);
        contentPane.add(quantityComboBox);
        contentPane.add(OKBtn);


        this.add(contentPane);
        this.setSize(new Dimension(400,150));

        quantityComboBox.addItemListener(e -> selectedQuantity=(int) e.getItem());
        OKBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });

    }

    public int showInsertQuantityFrame(){
        setVisible(true);
        return  selectedQuantity;
    }

}
