package com.example.kamarol.infoten_v1.Tools;

/**
 * Created by musyrif on 24-Nov-17.
 */

public class Lecturer {
    int id;
    String name, email, phone, dept;
    public Lecturer(int id, String name, String email, String phone, String dept){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dept = dept;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getDept() {
        return dept;
    }
}
