package com.kitri.myservletboard.data;

import java.time.LocalDateTime;

public class Member {
    String id;
    String loginId;
    String password;
    String name;
    String email;
    LocalDateTime createdAt;

    public Member() {
    }

    public Member(String id, String password, String name, String email) {
        this.loginId = id;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public Member(String id, String loginId, String password, String name, String email, LocalDateTime createdAt) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
