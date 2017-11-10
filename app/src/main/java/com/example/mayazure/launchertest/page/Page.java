package com.example.mayazure.launchertest.page;

import java.io.Serializable;

/**
 * Created by Mayazure on 3/11/2017.
 */

public class Page implements Serializable{
    private int id;
    private String name;

    public Page(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
