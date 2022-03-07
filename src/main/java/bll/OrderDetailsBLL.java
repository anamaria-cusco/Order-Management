package bll;

import dao.OrderDetailsDAO;
import model.Order_details;

import java.util.List;

public class OrderDetailsBLL {

    OrderDetailsDAO orderDetailsDAO;

    public OrderDetailsBLL(){
        orderDetailsDAO=new OrderDetailsDAO();
    }

    /**
     * Inserarea detaliilor unei comenzi in baza de date
     * @param orderDetails - o lista de obiecte de tip Order_details ce contine id-urile produselor comandate
     *                       id-ul clientului care a comandat si id-ul comenzii
     */
    public void insertOrderDetails(List<Order_details> orderDetails){
        for(Order_details it:orderDetails){
            orderDetailsDAO.insert(it);
        }


    }



}
