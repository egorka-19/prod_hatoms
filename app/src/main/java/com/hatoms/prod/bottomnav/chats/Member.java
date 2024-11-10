package com.hatoms.prod.bottomnav.chats;

public class Member {
    private String name;
    private String debt;

    public Member(String name, String debt) {
        this.name = name;
        this.debt = debt;
    }

    public String getName() {
        return name;
    }

    public String getDebt() {
        return debt;
    }
}

