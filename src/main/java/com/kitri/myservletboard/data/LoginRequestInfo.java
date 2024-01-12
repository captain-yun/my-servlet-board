package com.kitri.myservletboard.data;

public class LoginRequestInfo {
    private String loginId;
    private String password;
    public LoginRequestInfo() {
    }

    public LoginRequestInfo(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }
}
