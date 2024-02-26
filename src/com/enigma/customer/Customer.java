package com.enigma.customer;

public class Customer {
    private int customer_id;
    private String name;
    private String address;
    private  String birth_date;
    private String status;
    private int phone;

    public Customer(int customer_id, String name, String address, String birth_date, String status, int phone){
        this.customer_id = customer_id;
        this.name = name;
        this.address = address;
        this.birth_date = birth_date;
        this.status = status;
        this.phone = phone;
    }

    @Override
    public String toString(){
        return "Cuctomer{" +
                "id=" + customer_id +
                ", name='" + name  +
                ", address=" + address +
                ", birth_date=" + birth_date +
                ", status=" + status +
                ", phone=" + phone +
                '}';
    }
}
