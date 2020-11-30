package example.demo.utils;

public interface Constant {

    enum MyJsonReturnCode {

        PARAM_TYPE_ERROR("400", "请求参数类型错误"),
        SUCCESS("0", "操作成功"),
        NOT_LOGIN("401", "未登录"),
        ACCESS_ERROR("403", "禁止访问"),
        FAIL("500", "内部失败"),
        NOT_FOUND("404", "页面未发现"),
        FAILURE("400", "业务异常"),
        MSG_NOT_READABLE("400", "消息不能读取"),
        METHOD_NOT_SUPPORTED("405", "不支持当前请求方法"),
        MEDIA_TYPE_NOT_SUPPORTED("415", "不支持当前媒体类型"),
        INTERNAL_SERVER_ERROR("500", "服务器异常"),
        PARAM_MISS("400", "缺少必要的请求参数"),
        PARAM_VALID_ERROR("400", "参数校验失败");

        private String code;
        private String desc;

        private MyJsonReturnCode(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return this.desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

}
