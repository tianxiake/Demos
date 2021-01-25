package com.runningsnail.demos.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.chances.base.utils.StringUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chances on 16/8/26.
 * 整个主界面对应的实体类 Page; Area是每个
 */
public class Page implements Serializable {
    @SerializedName("status")
    @Expose
    public String status;
    /**
     * 进入页面默认选中的标题项
     */
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("subTitle")
    @Expose
    public String subTitle;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("background")
    @Expose
    public String background;
    @SerializedName("defaultFocus")
    @Expose
    public String defaultFocus;
    @SerializedName("defaultMenuFocusIndex")
    @Expose
    public String defaultMenuFocusIndex;
    @SerializedName("defaultFocusIcon")
    @Expose
    public String defaultFocusIcon;
    @SerializedName("activityView")
    @Expose
    public String activityView;
    /**
     * 首页菜单栏挂载的类型
     */
    @SerializedName("indexType")
    @Expose
    public String indexType = "auto";
    @SerializedName("refreshMinute")
    @Expose
    public String refreshMinute; //页面数据刷新间隔时间
    @SerializedName("areaDatas")
    @Expose
    public List<Area> areaDatas = new ArrayList<>();

    public boolean usable() {
        return StringUtils.isEquals("200", status) && !areaDatas.isEmpty();
    }

    public static class Area implements Serializable {
        @SerializedName("areaCode")
        @Expose
        public String areaCode;
        @SerializedName("areaType")
        @Expose
        public String areaType;
        @SerializedName("areaRect")
        @Expose
        public String areaRect;
        @SerializedName("areaTitle")
        @Expose
        public String areaTitle;
        @SerializedName("areaIcon")
        @Expose
        public String areaIcon;
        @SerializedName("dataLink")
        @Expose
        public String dataLink;
        @SerializedName("defaultImgType")
        @Expose
        public String defaultImgType;
        //新加属性字段(是否聚焦放大)
        @SerializedName("isFocusZoom")
        @Expose
        public boolean isFocusZoom = true;
        @SerializedName("items")
        @Expose
        public List<Item> items = new ArrayList<>();
        //默认子项
        @SerializedName("defaultItem")
        @Expose
        public int defaultItem;
        @SerializedName("nextFocusRight")
        @Expose
        public String nextFocusRight;
        @SerializedName("nextFocusLeft")
        @Expose
        public String nextFocusLeft;
        @SerializedName("nextFocusTop")
        @Expose
        public String nextFocusTop;
        @SerializedName("nextFocusBottom")
        @Expose
        public String nextFocusBottom;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Area{");
            sb.append("areaCode='").append(areaCode).append('\'');
            sb.append(", areaType='").append(areaType).append('\'');
            sb.append(", areaRect='").append(areaRect).append('\'');
            sb.append(", areaTitle='").append(areaTitle).append('\'');
            sb.append(", areaIcon='").append(areaIcon).append('\'');
            sb.append(", dataLink='").append(dataLink).append('\'');
            sb.append(", defaultImgType='").append(defaultImgType).append('\'');
            sb.append(", isFocusZoom=").append(isFocusZoom);
            sb.append(", items=").append(items);
            sb.append(", defaultItem=").append(defaultItem);
            sb.append(", nextFocusRight='").append(nextFocusRight).append('\'');
            sb.append(", nextFocusLeft='").append(nextFocusLeft).append('\'');
            sb.append(", nextFocusTop='").append(nextFocusTop).append('\'');
            sb.append(", nextFocusBottom='").append(nextFocusBottom).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * Parcelable由Android SDK提供，常用在跨进程间，Activity间传递对象使用，基于内存，效率高于基于磁盘的Serializable
     * 必须还是要实现Serializable 因为其他地方有用到
     */
    public static class Item implements Serializable, Parcelable {
        @SerializedName("itemTitle")
        @Expose
        public String itemTitle;
        @SerializedName("itemSubTitle")
        @Expose
        public String itemSubTitle;
        @SerializedName("itemSubTitle2")
        @Expose
        public String itemSubTitle2; //一些额外信息
        @SerializedName("poscode")
        @Expose
        public String poscode; //上传探针用的
        @SerializedName("itemCode")
        @Expose
        public String itemCode;
        @SerializedName("itemType")
        @Expose
        public String itemType; //series／vod ／biz ／subject／channel/schedule link
        @SerializedName("itemIcon")
        @Expose
        public String itemIcon;
        @SerializedName("itemIcon2")
        @Expose
        public String itemIcon2;
        @SerializedName("showFlag")
        @Expose
        public String showFlag;
        @SerializedName("opShowFlag")
        @Expose
        public String opShowFlag;
        @SerializedName("hImg")
        @Expose
        public String hImg;
        @SerializedName("vImg")
        @Expose
        public String vImg;
        @SerializedName("opimg1")
        @Expose
        public String optimg1;
        @SerializedName("opimg2")
        @Expose
        public String optimg2;
        @SerializedName("dataLink")
        @Expose
        public String dataLink;
        @SerializedName("linkType")
        @Expose
        public String linkType;

        @SerializedName("score")
        @Expose
        public String score;

        //以下三项 针对节目单
        //itemType=“schedule”是有效

        @SerializedName("startTime")
        @Expose
        public String startTime;   //格式yyyyMMddHHmmss
        @SerializedName("endTime")
        @Expose
        public String endTime;
        @SerializedName("channelCode")
        @Expose
        public String channelCode;
        @SerializedName("oriDate")
        @Expose
        public String oriDate;

        // 区分在一些场景进入 直播 会提示 内容可能与实际不符的提示
        @SerializedName("isPlayNotReal")
        @Expose
        public boolean isPlayNotReal;

        //  新增一个用于首页导航配置图片的字段（选中状态）
        @SerializedName("selectIcon")
        @Expose
        public String selectIcon;

        @SerializedName("viewType")
        @Expose
        public String viewType;

        public Item() {
        }

        protected Item(Parcel in) {
            itemTitle = in.readString();
            itemSubTitle = in.readString();
            itemSubTitle2 = in.readString();
            itemCode = in.readString();
            itemType = in.readString();
            itemIcon = in.readString();
            showFlag = in.readString();
            opShowFlag = in.readString();
            hImg = in.readString();
            vImg = in.readString();
            optimg1 = in.readString();
            optimg2 = in.readString();
            dataLink = in.readString();
            linkType = in.readString();
            score = in.readString();
            startTime = in.readString();
            endTime = in.readString();
            channelCode = in.readString();
            oriDate = in.readString();
            itemIcon2 = in.readString();
        }

        public static final Creator<Item> CREATOR = new Creator<Item>() {
            @Override
            public Item createFromParcel(Parcel in) {
                return new Item(in);
            }

            @Override
            public Item[] newArray(int size) {
                return new Item[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(itemTitle);
            dest.writeString(itemSubTitle);
            dest.writeString(itemSubTitle2);
            dest.writeString(itemCode);
            dest.writeString(itemType);
            dest.writeString(itemIcon);
            dest.writeString(showFlag);
            dest.writeString(opShowFlag);
            dest.writeString(hImg);
            dest.writeString(vImg);
            dest.writeString(optimg1);
            dest.writeString(optimg2);
            dest.writeString(dataLink);
            dest.writeString(linkType);
            dest.writeString(score);
            dest.writeString(startTime);
            dest.writeString(endTime);
            dest.writeString(channelCode);
            dest.writeString(oriDate);
            dest.writeString(itemIcon2);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Item{");
            sb.append("itemTitle='").append(itemTitle).append('\'');
            sb.append(", itemSubTitle='").append(itemSubTitle).append('\'');
            sb.append(", itemSubTitle2='").append(itemSubTitle2).append('\'');
            sb.append(", poscode='").append(poscode).append('\'');
            sb.append(", itemCode='").append(itemCode).append('\'');
            sb.append(", itemType='").append(itemType).append('\'');
            sb.append(", itemIcon='").append(itemIcon).append('\'');
            sb.append(", itemIcon2='").append(itemIcon2).append('\'');
            sb.append(", showFlag='").append(showFlag).append('\'');
            sb.append(", opShowFlag='").append(opShowFlag).append('\'');
            sb.append(", hImg='").append(hImg).append('\'');
            sb.append(", vImg='").append(vImg).append('\'');
            sb.append(", optimg1='").append(optimg1).append('\'');
            sb.append(", optimg2='").append(optimg2).append('\'');
            sb.append(", dataLink='").append(dataLink).append('\'');
            sb.append(", linkType='").append(linkType).append('\'');
            sb.append(", score='").append(score).append('\'');
            sb.append(", startTime='").append(startTime).append('\'');
            sb.append(", endTime='").append(endTime).append('\'');
            sb.append(", channelCode='").append(channelCode).append('\'');
            sb.append(", oriDate='").append(oriDate).append('\'');
            sb.append(", isPlayNotReal=").append(isPlayNotReal);
            sb.append(", selectIcon='").append(selectIcon).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Page{");
        sb.append("status='").append(status).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", subTitle='").append(subTitle).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append(", background='").append(background).append('\'');
        sb.append(", defaultFocus='").append(defaultFocus).append('\'');
        sb.append(", defaultMenuFocusIndex='").append(defaultMenuFocusIndex).append('\'');
        sb.append(", defaultFocusIcon='").append(defaultFocusIcon).append('\'');
        sb.append(", activityView='").append(activityView).append('\'');
        sb.append(", indexType='").append(indexType).append('\'');
        sb.append(", refreshMinute='").append(refreshMinute).append('\'');
        sb.append(", areaDatas=").append(areaDatas);
        sb.append('}');
        return sb.toString();
    }
}
