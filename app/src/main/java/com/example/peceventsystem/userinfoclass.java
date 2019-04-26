package com.example.peceventsystem;

public class userinfoclass {
    private String key,name,email,sid,branch,year,contact;

    public userinfoclass(String key,String name, String email, String sid, String branch, String year, String contact) {
        this.key=key;
        this.name = name;
        this.email = email;
        this.sid = sid;
        this.branch = branch;
        this.year = year;
        this.contact = contact;

    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSid() {
        return sid;
    }

    public String getBranch() {
        return branch;
    }

    public String getYear() {
        return year;
    }

    public String getContact() {
        return contact;
    }
}
