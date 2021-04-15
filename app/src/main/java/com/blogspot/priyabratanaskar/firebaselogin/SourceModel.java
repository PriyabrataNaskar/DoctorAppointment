package com.blogspot.priyabratanaskar.firebaselogin;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Priyabrata Naskar on 15-04-2021.
 */
public class SourceModel {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public SourceModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
