package model;

import utils.DisplayAs;

public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone_number;
    private String email;

    public Client(){};

    public Client(int id, String firstName, String lastName, String address, String phone_number, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
    }

    public Client(String firstName, String lastName, String address, String phone_number, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
    }
    @DisplayAs(value = "ID ", index = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @DisplayAs(value = "First Name ", index = 1)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @DisplayAs(value = "Last Name ", index = 2)
    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DisplayAs(value = "Address ", index = 3)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @DisplayAs(value = "Phone Number ", index = 4)
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @DisplayAs(value = "Email ", index = 5)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                "}\n";
    }
    public String displayClient(){
       String str="";
       str+="Client \n";
       str+= "Name: "+this.firstName + " "+this.lastName+ "\n";
       str+= "Address: "+this.address +"\n";
       str+= "Phone Number: "+this.phone_number +"\n";
       str+= "Email: "+this.email +"\n";

       return str;
    }
}
