package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "user")
public class User implements Serializable {
    @DatabaseField(columnName = "userName", unique = true, id = true)
    private String name;

    @DatabaseField(columnName = "password")
    private String password;

    @DatabaseField(columnName = "isActive")
    private Boolean isActive;

    public User() {
    }

    public User(String account, String password) {
        this.name = account;
        this.password = password;
        this.isActive = false;
    }

    public boolean tryLogin(String account, String password) {
        if (!account.equals(this.name)) {
            return false;
        }
        if (!password.equals(this.password)) {
            return false;
        }
        return this.isActive;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
