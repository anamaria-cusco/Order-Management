package model;

import utils.DisplayAs;

public class Product {
    private int id;
    private String name;
    private float price;
    private int stock_quantity;
    public Product(){}
    public Product( String name, float price, int stock_quantity) {
        this.name = name;
        this.price = price;
        this.stock_quantity = stock_quantity;
    }
    public Product(int id, String name, float price, int stock_quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock_quantity = stock_quantity;
    }
    @DisplayAs(value = "ID ", index = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @DisplayAs(value = "Name ", index = 1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @DisplayAs(value = "Price ", index = 2)
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    @DisplayAs(value = "Stock Quantity ", index = 3)
    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock_quantity=" + stock_quantity +
                "}\n";
    }

}
