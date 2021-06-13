package com.mygdx.game.dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import com.mygdx.game.model.User;

public class UserDao {
    private static Dao<User, String> userDao;

    public static Dao<User, String> get() throws SQLException{
        if (userDao == null) {
            init();
            userDao = DaoManager.createDao(
                    GetConnectionSource(),
                    User.class
            );
        }
        return userDao;
    }

    public static void init() {
        try {
            TableUtils.createTable(GetConnectionSource(), User.class);
        } catch (Exception ignored) {}
    }

    public static ConnectionSource GetConnectionSource() throws SQLException {
        String connectionString = "jdbc:sqlite:data.db";
        return new JdbcConnectionSource(connectionString);
    }
}