package com.gap.messenger.listDialogs;

public class User {

    private String id;
    private String name;
    private String lastName;
    private String age;
    private Boolean isOnline;

    public User(String id, String name, String lastName, String age, Boolean isOnline) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.isOnline = isOnline;
    }

    public User() {
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public Boolean isOnline() {
        return isOnline;
    }
}
