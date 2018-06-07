package com.xiaoxin.demo.bean;

/**
 * Created by Chris Ching on 2017/8/18.
 */

public class LoginBean {

    /**
     * response : {"msg":"登录成功！","token":"YTNmYmJ6emNmY1A3eWpod1NUUXRPN1BkSXIxL2Z1bzl1ZVd6SWRITzVHM0lBaUIwQUFjV2ZBR0pMY0JCMUtRODNnTUd2WVI2NTV0bXdLNFB4QUptZ2xqeVkrbjVaZDZRTVBMRDNKdWhXNTAwalJB","flag":false,"school":{"school_id":"10008","school_name":"上海市普陀区武宁路小学","school_img":"http://jiaowu.xiaoheiban.cn/upload/201702221323073793.png"},"teacher_info":{"teacher_id":"10233","teacher_name":"金宇","photo":"","account_number":"1370000002","teacher_type":"2"}}
     * worder_id : 0000000057b1cdca00000000f7d505d5_2_10
     */


    /**
     * msg : 登录成功！
     * token : YTNmYmJ6emNmY1A3eWpod1NUUXRPN1BkSXIxL2Z1bzl1ZVd6SWRITzVHM0lBaUIwQUFjV2ZBR0pMY0JCMUtRODNnTUd2WVI2NTV0bXdLNFB4QUptZ2xqeVkrbjVaZDZRTVBMRDNKdWhXNTAwalJB
     * flag : false
     * school : {"school_id":"10008","school_name":"上海市普陀区武宁路小学","school_img":"http://jiaowu.xiaoheiban.cn/upload/201702221323073793.png"}
     * teacher_info : {"teacher_id":"10233","teacher_name":"金宇","photo":"","account_number":"1370000002","teacher_type":"2"}
     */


    private String token;
    private boolean flag;
    private SchoolBean school;
    private TeacherInfoBean teacher_info;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public SchoolBean getSchool() {
        return school;
    }

    public void setSchool(SchoolBean school) {
        this.school = school;
    }

    public TeacherInfoBean getTeacher_info() {
        return teacher_info;
    }

    public void setTeacher_info(TeacherInfoBean teacher_info) {
        this.teacher_info = teacher_info;
    }

    public static class SchoolBean {
        /**
         * school_id : 10008
         * school_name : 上海市普陀区武宁路小学
         * school_img : http://jiaowu.xiaoheiban.cn/upload/201702221323073793.png
         */

        private String school_id;
        private String school_name;
        private String school_img;

        public String getSchool_id() {
            return school_id;
        }

        public void setSchool_id(String school_id) {
            this.school_id = school_id;
        }

        public String getSchool_name() {
            return school_name;
        }

        public void setSchool_name(String school_name) {
            this.school_name = school_name;
        }

        public String getSchool_img() {
            return school_img;
        }

        public void setSchool_img(String school_img) {
            this.school_img = school_img;
        }
    }

    public static class TeacherInfoBean {
        /**
         * teacher_id : 10233
         * teacher_name : 金宇
         * photo :
         * account_number : 1370000002
         * teacher_type : 2
         */

        private String teacher_id;
        private String teacher_name;
        private String photo;
        private String account_number;
        private String teacher_type;

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

        public String getAccount_number() {
            return account_number;
        }

        public void setAccount_number(String account_number) {
            this.account_number = account_number;
        }

        public String getTeacher_type() {
            return teacher_type;
        }

        public void setTeacher_type(String teacher_type) {
            this.teacher_type = teacher_type;
        }
    }

}
