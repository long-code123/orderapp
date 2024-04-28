package com.app.orderfood.models;

public class User {
    String id;
    String name;
    String linkAvt;
    String phone;
    String address;

    // 1 - User
    // 2 - seller
    // 3 - Ship
    int role;
    int status;
    long create_time;

    public User() {

    }

    public User(String id, String name, String linkAvt, String phone, String address, int role, int status, long create_time) {
        this.id = id;
        this.name = name;
        this.linkAvt = linkAvt;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.status = status;
        this.create_time = create_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkAvt() {
        return linkAvt;
    }

    public void setLinkAvt(String linkAvt) {
        this.linkAvt = linkAvt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }
}
