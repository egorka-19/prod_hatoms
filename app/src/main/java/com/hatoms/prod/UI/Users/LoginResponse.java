package com.hatoms.prod.UI.Users;

public class LoginResponse {
    private int result; // или другой тип, в зависимости от того, что именно возвращает сервер

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}

