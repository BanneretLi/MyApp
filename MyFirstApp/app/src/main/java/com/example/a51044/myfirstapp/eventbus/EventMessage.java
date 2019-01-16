package com.example.a51044.myfirstapp.eventbus;

import com.example.a51044.myfirstapp.bean.QueryGoods;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/310:57<p>
 * <p>更改时间：2019/1/310:57<p>
 * <p>版本号：1<p>
 */
public class EventMessage {
    private String name;

    private List<QueryGoods.ResultBean>mList;

    public List<QueryGoods.ResultBean> getmList() {
        return mList;
    }

    public void setmList(List<QueryGoods.ResultBean> mList) {
        this.mList = mList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "name='" + name + '\'' +
                ", mList=" + mList +
                '}';
    }

    public EventMessage(String name) {
        this.name = name;
    }

    public EventMessage(List<QueryGoods.ResultBean> mList) {
        this.mList = mList;
    }

    public EventMessage() {
    }
}
