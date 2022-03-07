package model;

public class Orders {
    private int id;
    private String order_date;
    private int client_id;

    public Orders(){}
    public Orders(int id, String order_date, int client_id) {
        this.id = id;
        this.order_date = order_date;
        this.client_id = client_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_date='" + order_date + '\'' +
                ", client_id=" + client_id +
                '}';
    }
}



