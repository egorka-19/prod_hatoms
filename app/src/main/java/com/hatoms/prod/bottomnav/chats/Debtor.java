package com.hatoms.prod.bottomnav.chats;

public class Debtor {
    private String name;
    private String debt;

    public Debtor(String name, String debt) {
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
