package com.rupesh.baji.model;

public class User {
    private String _id;
    private String fname;
    private String email;
    private String uname;
    private String pass;
    private String amt;
    private String phone;
    private String proImg;

    public User(String _id, String fname, String email, String uname, String pass, String amt, String phone, String proImg) {
        this._id = _id;
        this.fname = fname;
        this.email = email;
        this.uname = uname;
        this.pass = pass;
        this.amt = amt;
        this.phone = phone;
        this.proImg = proImg;
    }

//    public User(String _id, String fname, String email, String uname, String pass, String amt, String proImg) {
//        this._id = _id;
//        this.fname = fname;
//        this.email = email;
//        this.uname = uname;
//        this.pass = pass;
//        this.amt = amt;
//        this.proImg = proImg;
//    }

    public User(String fname, String email, String uname, String pass, String amt, String proImg) {
        this.fname = fname;
        this.email = email;
        this.uname = uname;
        this.pass = pass;
        this.amt = amt;
        this.proImg = proImg;
    }

    public User(String fname, String email, String uname, String pass, String amt, String phone, String proImg) {
        this.fname = fname;
        this.email = email;
        this.uname = uname;
        this.pass = pass;
        this.amt = amt;
        this.phone = phone;
        this.proImg = proImg;
    }

    public User(String fname, String email, String uname, String phone, String proImg) {
        this.fname = fname;
        this.email = email;
        this.uname = uname;
        this.phone = phone;
        this.proImg = proImg;
    }

    public User(String fname, String amt) {
        this.fname = fname;
        this.amt = amt;
    }

    public User(String _id){
        this._id = _id;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProImg() {
        return proImg;
    }

    public void setProImg(String proImg) {
        this.proImg = proImg;
    }
}
