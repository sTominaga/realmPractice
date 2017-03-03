package com.example.ptn0411.realmpractice.Model;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ptn0411 on 2017/03/03.
 */

public class UserModel extends RealmObject {
    //主キーを指定するアノテーション
    @PrimaryKey
    private int id;

    @Index
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }



}