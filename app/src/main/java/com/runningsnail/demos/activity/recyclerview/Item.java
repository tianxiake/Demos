package com.runningsnail.demos.activity.recyclerview;

/**
 * @author yongjie created on 2019-05-14.
 */
public class Item {

    public String type;

    public String time;

    public String imageUrl = "";

    public Item(String type, String time, String imageUrl) {
        this.type = type;
        this.time = time;
        this.imageUrl = imageUrl;
    }


    @Override
    public String toString() {
        return "Item{" +
                "type='" + type + '\'' +
                ", time='" + time + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
