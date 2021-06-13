package com.mygdx.game.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mygdx.game.model.Activity;

import java.sql.SQLException;

public class ActivityDao {
    private static Dao<Activity, String> dao;

    public static Dao<Activity, String> get() throws SQLException{
        if (dao == null) {
            init();
            dao = DaoManager.createDao(
                    GetConnectionSource(),
                    Activity.class
            );
        }
        return dao;
    }

    public static void init() {
        try {
            TableUtils.createTable(GetConnectionSource(), Activity.class);
        } catch (Exception ignored) {}
    }

    public static ConnectionSource GetConnectionSource() throws SQLException {
        String connectionString = "jdbc:sqlite:data.db";
        return new JdbcConnectionSource(connectionString);
    }
}