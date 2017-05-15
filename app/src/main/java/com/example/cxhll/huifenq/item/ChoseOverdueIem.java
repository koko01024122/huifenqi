package com.example.cxhll.huifenq.item;

/**
 * Created by cxandpll on 17-2-27.
 */

public class ChoseOverdueIem {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;
    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    boolean check;
    String name;
    String time;

}
