package com.pecuyu.mvplearn.model;

/**
 * <br/>Author: pecuyu
 * <br/>Email: yu.qin@ck-telecom.com
 * <br/>Date: 2018/5/25
 * <br/>TODO:
 */

public interface IUIModel {
    void verifyLogin(String username,String password,ILoginListener loginListener);
}
