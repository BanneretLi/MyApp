package com.example.a51044.myfirstapp.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/319:28<p>
 * <p>更改时间：2019/1/319:28<p>
 * <p>版本号：1<p>
 */
public class MyCircle {

    /**
     * result : [{"commodityId":1,"content":"检测","createTime":1545146624000,"greatNum":1,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":223,"image":"http://172.17.8.100/images/small/circle_pic/2018-12-18/1832620181218092344.jpg","nickName":"啊啊啊啊啊啊","userId":23,"whetherGreat":2},{"commodityId":1,"content":"好的一天","createTime":1545096773000,"greatNum":9,"headPic":"http://172.17.8.100/images/small/head_pic/2018-12-20/20181220194510.jpg","id":222,"image":"http://172.17.8.100/images/small/circle_pic/2018-12-17/3156020181217193253.mp4","nickName":"35_N3B12","userId":107,"whetherGreat":2},{"commodityId":1,"content":"幸运者","createTime":1545096697000,"greatNum":4,"headPic":"http://172.17.8.100/images/small/head_pic/2018-12-20/20181220194510.jpg","id":221,"image":"http://172.17.8.100/images/small/circle_pic/2018-12-17/0258320181217193137.jpg","nickName":"35_N3B12","userId":107,"whetherGreat":2},{"commodityId":1,"content":"贾大","createTime":1545086611000,"greatNum":2,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":220,"image":"","nickName":"y0_R3a4P","userId":78,"whetherGreat":2},{"commodityId":1,"content":"贾大","createTime":1545086605000,"greatNum":2,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":219,"image":"","nickName":"y0_R3a4P","userId":78,"whetherGreat":2},{"commodityId":1,"content":"珠峰傻","createTime":1545086377000,"greatNum":1,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":218,"image":"","nickName":"il_33MO8","userId":47,"whetherGreat":2},{"commodityId":1,"content":"珠峰傻","createTime":1545086376000,"greatNum":1,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":217,"image":"","nickName":"il_33MO8","userId":47,"whetherGreat":2},{"commodityId":1,"content":"珠峰傻","createTime":1545086349000,"greatNum":3,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":216,"image":"","nickName":"il_33MO8","userId":47,"whetherGreat":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 1
         * content : 检测
         * createTime : 1545146624000
         * greatNum : 1
         * headPic : http://172.17.8.100/images/small/default/user.jpg
         * id : 223
         * image : http://172.17.8.100/images/small/circle_pic/2018-12-18/1832620181218092344.jpg
         * nickName : 啊啊啊啊啊啊
         * userId : 23
         * whetherGreat : 2
         */

        private int commodityId;
        private String content;
        private long createTime;
        private int greatNum;
        private String headPic;
        private int id;
        private String image;
        private String nickName;
        private int userId;
        private int whetherGreat;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }
    }
}
