package com.hatoms.prod.bottomnav.chats;

public class Member {
    private String name;
    private String dolg;

    public Member(String name, String dolg) {
        this.name = name;
        this.dolg = dolg;
    }

    public String getName() {
        return name;
    }

    public String getDolg() {
        return dolg;
    }
}
