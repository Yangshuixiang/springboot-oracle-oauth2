package example.demo.entity;

import java.io.Serializable;
import java.util.Arrays;

//@Data
public class OauthCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private byte[] authentication;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }

    @Override
    public String toString() {
        return "OauthCode{" +
                "code='" + code + '\'' +
                ", authentication=" + Arrays.toString(authentication) +
                '}';
    }
}
