package com.pecuyu.mvplearn.model;

/**
 * <br/>Author: pecuyu
 * <br/>Date: 2018/5/25
 * <br/>TODO:
 */

public interface ILoginListener {
    void onLoginSuccess(String username);
    void onLoginFailed(String username,String errorMsg);
}
