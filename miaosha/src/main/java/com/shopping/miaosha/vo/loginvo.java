package com.shopping.miaosha.vo;

import com.shopping.miaosha.validator.isMobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class loginvo {

    @NotNull
    @isMobile
    private String mobile;

    @NotNull
    @Length(min=32)//
    private String password;


    public String getMobile(){
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "loginvo{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
