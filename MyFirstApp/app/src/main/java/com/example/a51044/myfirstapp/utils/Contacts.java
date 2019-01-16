package com.example.a51044.myfirstapp.utils;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/410:04<p>
 * <p>更改时间：2019/1/410:04<p>
 * <p>版本号：1<p>
 */
public class Contacts {
    public static final String BASE_URL="http://mobile.bwstudent.com/";
    public static final String BASE_INSIDE="http://mobile.bwstudent.com/";
    public static final String BASE_EXTERNAL="http://172.17.8.100/";
    public static final String BASE_KEYWORD="small/commodity/v1/findCommodityByKeyword?count=10&page=";
    public static final String BASE_HOME="small/commodity/v1/commodityList";
    public static final String BASE_Banner="small/commodity/v1/bannerShow";
    public static final String BASE_Login="small/user/v1/login";
    public static final String BASE_Register="small/user/v1/register";
    public static final String BASE_Circle="small/circle/v1/findCircleList?count=10&page=";
    public static final String BASE_Sync="small/order/verify/v1/syncShoppingCart";
    public static final String BASE_MySelf="small/circle/verify/v1/findMyCircleById?count=10&pager=";
    public static final String BASE_Shopping="small/order/verify/v1/findShoppingCart";
    public static final String BASE_DianZan="small/circle/verify/v1/addCircleGreat";
    public static final String BASE_BuZan="small/circle/verify/v1/cancelCircleGreat";
    public static final String BASE_HomeOne="small/commodity/v1/findFirstCategory";
    public static final String BASE_HomeTwo="small/commodity/v1/findSecondCategory?firstCategoryId=";
    public static final String BASE_AddAddress="small/user/verify/v1/addReceiveAddress";
    public static final String BASE_Default_Address="small/user/verify/v1/setDefaultReceiveAddress";
    public static final String BASE_Establish="small/order/verify/v1/createOrder";
    //全部订单
    public static final String BASE_AllList="small/order/verify/v1/findOrderListByStatus?status=0&page=1&count=5";
    //待付款
    public static final String BASE_WaitPay="small/order/verify/v1/findOrderListByStatus?status=1&page=1&count=5";
    //支付
    public static final String BASE_Pay="small/order/verify/v1/pay?payType=1&orderId=";
    //待收货
    public static final String BASE_WaitGoods="small/order/verify/v1/findOrderListByStatus?status=2&page=1&count=5";
    //确认收货
    public static final String BASE_OkGoods="small/order/verify/v1/confirmReceipt?orderId=";
    //创建订单
    public static final String BASE_CreateList="small/order/verify/v1/createOrder";
    //查询收货地址
    public static final String BASE_QueryAddress="small/user/verify/v1/receiveAddressList";
    //待评价
    public static final String BASE_WaitTalk="small/order/verify/v1/findOrderListByStatus?status=3&page=1&count=5";




















}
