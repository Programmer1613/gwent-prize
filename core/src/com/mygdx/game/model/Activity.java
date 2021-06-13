package com.mygdx.game.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "activity")
public class Activity implements Serializable {
    @DatabaseField(columnName = "gameName", unique = true, id = true)
    private String gameName;

    @DatabaseField(columnName = "status")
    private int status;

    public static class Status {
        public static final int waiting = 0;
        public static final int pending = 1;
        public static final int ended = 2;
    }

    public Activity() {
        this.gameName = null;
    }

    public int getStatus() {
        return status;
    }

}
