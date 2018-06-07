package com.xiaoxin.demo.bean;

import java.util.List;

/**
 * Created by Chris Ching on 2017/8/23.
 */

public class ClassroomBean {

    /**
     * response : {"msg":"OK","next_id":-1,"classroom_list":[{"classroom_id":"5","classroom_name":"会议室1","seat_num":"31","flag":"0","url":"http://jiaowu-test.xiaoheiban.cn/school/classrooms/classroomDetails?classroom_id=1&school_id=10008","device_name":"电脑,电视,投影仪,网络,音响,空调,","approval_teacher":"10255","approval_teacher_name":"曹婷婷","photo":"","teacher_info":[{"teacher_name":"潘雨666","photo":"http://file.xiaoheiban.cn/1,167617a03354"},{"teacher_name":"曹婷婷","photo":""}]}]}
     */

    private ResponseBean response;

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

    public static class ResponseBean {
        /**
         * msg : OK
         * next_id : -1
         * classroom_list : [{"classroom_id":"5","classroom_name":"会议室1","seat_num":"31","flag":"0","url":"http://jiaowu-test.xiaoheiban.cn/school/classrooms/classroomDetails?classroom_id=1&school_id=10008","device_name":"电脑,电视,投影仪,网络,音响,空调,","approval_teacher":"10255","approval_teacher_name":"曹婷婷","photo":"","teacher_info":[{"teacher_name":"潘雨666","photo":"http://file.xiaoheiban.cn/1,167617a03354"},{"teacher_name":"曹婷婷","photo":""}]}]
         */

        private String msg;
        private int next_id;
        private List<ClassroomListBean> classroom_list;

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

        public List<ClassroomListBean> getClassroom_list() {
            return classroom_list;
        }

        public void setClassroom_list(List<ClassroomListBean> classroom_list) {
            this.classroom_list = classroom_list;
        }

        public static class ClassroomListBean {
            /**
             * classroom_id : 5
             * classroom_name : 会议室1
             * seat_num : 31
             * flag : 0
             * url : http://jiaowu-test.xiaoheiban.cn/school/classrooms/classroomDetails?classroom_id=1&school_id=10008
             * device_name : 电脑,电视,投影仪,网络,音响,空调,
             * approval_teacher : 10255
             * approval_teacher_name : 曹婷婷
             * photo :
             * teacher_info : [{"teacher_name":"潘雨666","photo":"http://file.xiaoheiban.cn/1,167617a03354"},{"teacher_name":"曹婷婷","photo":""}]
             */

            private String classroom_id;
            private String classroom_name;
            private String seat_num;
            private String flag;
            private String url;
            private String device_name;
            private String approval_teacher;
            private String approval_teacher_name;
            private String photo;
            private List<TeacherInfoBean> teacher_info;

            public String getClassroom_id() {
                return classroom_id;
            }

            public void setClassroom_id(String classroom_id) {
                this.classroom_id = classroom_id;
            }

            public String getClassroom_name() {
                return classroom_name;
            }

            public void setClassroom_name(String classroom_name) {
                this.classroom_name = classroom_name;
            }

            public String getSeat_num() {
                return seat_num;
            }

            public void setSeat_num(String seat_num) {
                this.seat_num = seat_num;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDevice_name() {
                return device_name;
            }

            public void setDevice_name(String device_name) {
                this.device_name = device_name;
            }

            public String getApproval_teacher() {
                return approval_teacher;
            }

            public void setApproval_teacher(String approval_teacher) {
                this.approval_teacher = approval_teacher;
            }

            public String getApproval_teacher_name() {
                return approval_teacher_name;
            }

            public void setApproval_teacher_name(String approval_teacher_name) {
                this.approval_teacher_name = approval_teacher_name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public List<TeacherInfoBean> getTeacher_info() {
                return teacher_info;
            }

            public void setTeacher_info(List<TeacherInfoBean> teacher_info) {
                this.teacher_info = teacher_info;
            }

            @Override
            public String toString() {
                return "ClassroomListBean{" +
                        "classroom_id='" + classroom_id + '\'' +
                        ", classroom_name='" + classroom_name + '\'' +
                        ", seat_num='" + seat_num + '\'' +
                        ", flag='" + flag + '\'' +
                        ", url='" + url + '\'' +
                        ", device_name='" + device_name + '\'' +
                        ", approval_teacher='" + approval_teacher + '\'' +
                        ", approval_teacher_name='" + approval_teacher_name + '\'' +
                        ", photo='" + photo + '\'' +
                        ", teacher_info=" + teacher_info +
                        '}';
            }

            public static class TeacherInfoBean {
                /**
                 * teacher_name : 潘雨666
                 * photo : http://file.xiaoheiban.cn/1,167617a03354
                 */

                private String teacher_name;
                private String photo;

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
