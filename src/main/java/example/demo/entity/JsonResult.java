package example.demo.entity;

import com.alibaba.fastjson.JSONObject;
import example.demo.utils.Constant;

/**
 * @Author: YangShuiXiang
 * @Date: 2020/11/26 16:39
 * @Email: shuixiang.yang@tcl.com
 */
public class JsonResult<T> {

    private String code;
    private String msg;
    private T data;

    public JsonResult() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data == null ? (T) new JSONObject() : this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "code=" + this.code + " message=" + this.msg + " data=" + this.data;
    }

    public static <T> JsonResult<T> fail() {
        JsonResult<T> ret = new JsonResult();
        ret.setCode(Constant.MyJsonReturnCode.FAIL.getCode());
        ret.setMsg(Constant.MyJsonReturnCode.FAIL.getDesc());
        return ret;
    }

    public static <T> JsonResult<T> fail(T data) {
        JsonResult<T> ret = fail();
        ret.setData(data);
        return ret;
    }

    public static <T> JsonResult<T> failMessage(String msg) {
        JsonResult<T> ret = fail();
        ret.setMsg(msg);
        return ret;
    }

    public static <T> JsonResult<T> failMessage(String msg, String code) {
        JsonResult<T> ret = fail();
        ret.setMsg(msg);
        ret.setCode(code);
        return ret;
    }

    public static <T> JsonResult<T> successMessage(String msg) {
        JsonResult<T> ret = success();
        ret.setMsg(msg);
        return ret;
    }

    public static <T> JsonResult<T> success() {
        JsonResult<T> ret = new JsonResult();
        ret.setCode(Constant.MyJsonReturnCode.SUCCESS.getCode());
        ret.setMsg(Constant.MyJsonReturnCode.SUCCESS.getDesc());
        return ret;
    }

    public static <T> JsonResult<T> success(T data) {
        JsonResult<T> ret = success();
        ret.setData(data);
        return ret;
    }

    public static <T> JsonResult<T> http404(T data) {
        JsonResult<T> ret = new JsonResult();
        ret.setCode(Constant.MyJsonReturnCode.NOT_FOUND.getCode());
        ret.setMsg(Constant.MyJsonReturnCode.NOT_FOUND.getDesc());
        ret.setData(data);
        return ret;
    }

    public static <T> JsonResult<T> http403(T data) {
        JsonResult<T> ret = new JsonResult();
        ret.setCode(Constant.MyJsonReturnCode.ACCESS_ERROR.getCode());
        ret.setMsg(Constant.MyJsonReturnCode.ACCESS_ERROR.getDesc());
        ret.setData(data);
        return ret;
    }
}
