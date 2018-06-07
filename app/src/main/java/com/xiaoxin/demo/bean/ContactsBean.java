package com.xiaoxin.demo.bean;

import java.util.List;

/**
 * Created by Chris Ching on 2017/8/24.
 */

public class ContactsBean {

    /**
     * response : {"msg":"OK","next_id":10611,"group_list":[{"c_group_id":"30","contacts_name":"even though"},{"c_group_id":"31","contacts_name":"Helen"},{"c_group_id":"82","contacts_name":"556612"}],"list":[{"teacher_id":"22188","teacher_name":"胡杰","photo":"","xhb_user_token":true},{"teacher_id":"22187","teacher_name":"顾美桃","photo":"","xhb_user_token":false},{"teacher_id":"22186","teacher_name":"朱春轩","photo":"","xhb_user_token":false},{"teacher_id":"22185","teacher_name":"张建萍","photo":"","xhb_user_token":false},{"teacher_id":"22184","teacher_name":"郭惠娥","photo":"","xhb_user_token":false},{"teacher_id":"22183","teacher_name":"顾景香","photo":"","xhb_user_token":true},{"teacher_id":"22182","teacher_name":"顾荣华","photo":"","xhb_user_token":true},{"teacher_id":"21902","teacher_name":"孙文台5","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21901","teacher_name":"孙文台4","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21900","teacher_name":"孙文台3","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21899","teacher_name":"孙文台2","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21898","teacher_name":"孙文台1","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21897","teacher_name":"孙文台0","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21614","teacher_name":"潘雨666","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21460","teacher_name":"潘雨露9","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21459","teacher_name":"潘雨露8","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21458","teacher_name":"潘雨露7","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21457","teacher_name":"潘雨露6","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21456","teacher_name":"潘雨露5","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21455","teacher_name":"潘雨露4","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21454","teacher_name":"吴根海","photo":"","xhb_user_token":true},{"teacher_id":"21453","teacher_name":"潘雨露3","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21452","teacher_name":"孙娜新3","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21289","teacher_name":"张辉","photo":"","xhb_user_token":true},{"teacher_id":"20419","teacher_name":"丛雪如","photo":"","xhb_user_token":false},{"teacher_id":"10618","teacher_name":"江霞","photo":"http://file.xiaoheiban.cn/3,0ccf2da82f22","xhb_user_token":true},{"teacher_id":"10617","teacher_name":"吴思祺","photo":"","xhb_user_token":false},{"teacher_id":"10615","teacher_name":"蒋伟芬","photo":"","xhb_user_token":false},{"teacher_id":"10614","teacher_name":"范月婷","photo":"","xhb_user_token":true},{"teacher_id":"10612","teacher_name":"傅幸蕾","photo":"","xhb_user_token":true}]}
     * worder_id : 00000000491e29ba000000005debcb1d_7_4
     */

    private ResponseBean response;
    private String worder_id;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    private ErrorResponse error_response;

    public ErrorResponse getError_response() {
        return error_response;
    }

    public void setError_response(ErrorResponse error_response) {
        this.error_response = error_response;
    }
    public String getWorder_id() {
        return worder_id;
    }

    public void setWorder_id(String worder_id) {
        this.worder_id = worder_id;
    }

    public static class ResponseBean {
        /**
         * msg : OK
         * next_id : 10611
         * group_list : [{"c_group_id":"30","contacts_name":"even though"},{"c_group_id":"31","contacts_name":"Helen"},{"c_group_id":"82","contacts_name":"556612"}]
         * list : [{"teacher_id":"22188","teacher_name":"胡杰","photo":"","xhb_user_token":true},{"teacher_id":"22187","teacher_name":"顾美桃","photo":"","xhb_user_token":false},{"teacher_id":"22186","teacher_name":"朱春轩","photo":"","xhb_user_token":false},{"teacher_id":"22185","teacher_name":"张建萍","photo":"","xhb_user_token":false},{"teacher_id":"22184","teacher_name":"郭惠娥","photo":"","xhb_user_token":false},{"teacher_id":"22183","teacher_name":"顾景香","photo":"","xhb_user_token":true},{"teacher_id":"22182","teacher_name":"顾荣华","photo":"","xhb_user_token":true},{"teacher_id":"21902","teacher_name":"孙文台5","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21901","teacher_name":"孙文台4","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21900","teacher_name":"孙文台3","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21899","teacher_name":"孙文台2","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21898","teacher_name":"孙文台1","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21897","teacher_name":"孙文台0","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21614","teacher_name":"潘雨666","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21460","teacher_name":"潘雨露9","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21459","teacher_name":"潘雨露8","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21458","teacher_name":"潘雨露7","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21457","teacher_name":"潘雨露6","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21456","teacher_name":"潘雨露5","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21455","teacher_name":"潘雨露4","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21454","teacher_name":"吴根海","photo":"","xhb_user_token":true},{"teacher_id":"21453","teacher_name":"潘雨露3","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21452","teacher_name":"孙娜新3","photo":"http://file.xiaoheiban.cn/1,167617a03354","xhb_user_token":true},{"teacher_id":"21289","teacher_name":"张辉","photo":"","xhb_user_token":true},{"teacher_id":"20419","teacher_name":"丛雪如","photo":"","xhb_user_token":false},{"teacher_id":"10618","teacher_name":"江霞","photo":"http://file.xiaoheiban.cn/3,0ccf2da82f22","xhb_user_token":true},{"teacher_id":"10617","teacher_name":"吴思祺","photo":"","xhb_user_token":false},{"teacher_id":"10615","teacher_name":"蒋伟芬","photo":"","xhb_user_token":false},{"teacher_id":"10614","teacher_name":"范月婷","photo":"","xhb_user_token":true},{"teacher_id":"10612","teacher_name":"傅幸蕾","photo":"","xhb_user_token":true}]
         */

        private String msg;
        private int next_id;
        private List<GroupListBean> group_list;
        private List<ListBean> list;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getNext_id() {
            return next_id;
        }

        public void setNext_id(int next_id) {
            this.next_id = next_id;
        }

        public List<GroupListBean> getGroup_list() {
            return group_list;
        }

        public void setGroup_list(List<GroupListBean> group_list) {
            this.group_list = group_list;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class GroupListBean {
            /**
             * c_group_id : 30
             * contacts_name : even though
             */

            private String c_group_id;
            private String contacts_name;

            public String getC_group_id() {
                return c_group_id;
            }

            public void setC_group_id(String c_group_id) {
                this.c_group_id = c_group_id;
            }

            public String getContacts_name() {
                return contacts_name;
            }

            public void setContacts_name(String contacts_name) {
                this.contacts_name = contacts_name;
            }
        }

        public static class ListBean {
            /**
             * teacher_id : 22188
             * teacher_name : 胡杰
             * photo :
             * xhb_user_token : true
             */

            private String teacher_id;
            private String teacher_name;
            private String photo;
            private boolean xhb_user_token;

            public String getTeacher_id() {
                return teacher_id;
            }

            public void setTeacher_id(String teacher_id) {
                this.teacher_id = teacher_id;
            }

            public String getTeacher_name() {
                return teacher_name;
            }

            public void setTeacher_name(String teacher_name) {
                this.teacher_name = teacher_name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public boolean isXhb_user_token() {
                return xhb_user_token;
            }

            public void setXhb_user_token(boolean xhb_user_token) {
                this.xhb_user_token = xhb_user_token;
            }
        }
    }

    public static class ErrorResponse {
        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
