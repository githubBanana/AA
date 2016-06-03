package com.xs.aa;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-06-01 11:28
 * @email Xs.lin@foxmail.com
 */
public class User {
    private static final String TAG = "User";

    public String name;
    public int sex;
    public boolean isGuest;

    public boolean isGuest() {
        return isGuest;
    }

    public void setGuest(boolean guest) {
        isGuest = guest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", isGuest=" + isGuest +
                '}';
    }
}
