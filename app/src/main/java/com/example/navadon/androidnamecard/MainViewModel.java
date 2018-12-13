package com.example.navadon.androidnamecard;
import android.arch.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private String name;
    private String id;
    private String email;
    private String phone;

    public MainViewModel() {
        this.name = "-------------------------------------";
        this.id = "Welcome Tenno";
        this.email = "-------------------------------------------------------";
        this.phone = "--------------------------------";
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return "Email: " + email;
    }

    public String getPhone() {
        return "Phone: " + phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
