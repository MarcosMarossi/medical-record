package com.example.emr.Models;

public class User {

    private String name;
    private String password;
    private String token;
    private String email;
    private String cpf;
    private String message;
    private String profile;
    private String id;
    private String url;
    private String heartbeat;
    private String brdate;
    private String newpassword;
    private String renewpassword;



    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getRenewpassword() {
        return renewpassword;
    }

    public void setRenewpassword(String renewpassword) {
        this.renewpassword = renewpassword;
    }

    public String getBrdate() {
        return brdate;
    }

    public void setBrdate(String brdate) {
        this.brdate = brdate;
    }

    public String getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(String heartbeat) {
        this.heartbeat = heartbeat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(){

    }

    public User(String name, String cpf, String email, String password) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
    }

  public User(String email, String password, String newpassword, String renewpassword, int a){
      this.email = email;
      this.password = password;
      this.newpassword = newpassword;
      this.renewpassword = renewpassword;
  }

    public User(String email){
        this.email = email;
    }

    @Override
    public String toString() {
        return email;
    }
}
