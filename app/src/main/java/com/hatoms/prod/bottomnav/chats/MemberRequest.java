package com.hatoms.prod.bottomnav.chats;

import java.util.List;
public class MemberRequest {
    private int userId;
    private String title;
    private int amount;
    private List<String> emails;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
// Конструкторы, геттеры и сеттеры
}
