package net.cofares.domain;

public class User {

    private String username;
    private String data; //data pour simuler un bean d' une application

    public User() {
        
    }
    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}