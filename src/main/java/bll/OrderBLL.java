package bll;

import dao.OrderDAO;
import model.Orders;

public class OrderBLL {
    private OrderDAO orderDAO;

    public OrderBLL(){
        orderDAO=new OrderDAO();
    }

    /**
     * Metoda de plasare a unei noi comenzi
     * @param order - comanda care va fi inserata
     * @return - id-ul comenzii ce tocmai a fost inserata
     */
    public int placeOrder(Orders order){
        return orderDAO.insert(order);
    }




}
