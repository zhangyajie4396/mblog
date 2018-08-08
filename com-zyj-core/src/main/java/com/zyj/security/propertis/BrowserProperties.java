package com.zyj.security.propertis;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 16:06 2018/6/29
 */
public class BrowserProperties {

    private String signUpUrl ;

    private String loginPage ;

    //记住我过期时间默认1小时
    private int rememberMeSeconds = 3600;

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
