package com.pecuyu.mvplearn.presenter;

/**
 * <br/>Author: pecuyu
 * <br/>Email: yu.qin@ck-telecom.com
 * <br/>Date: 2018/5/25
 * <br/>TODO:
 */

public interface IView {
    /**
     * 显示登录提示Dialog
     */
    void showLoginDialog();

    /**
     * 关闭登录提示Dialog
     */
    void dismissLoginDialog();

    /**
     * 显示登录失败信息
     * @param username
     * @param msg
     */
    void showLoginFailedMsg(String username,String msg);

    /**
     * 显示登录成功信息
     * @param username
     * @param msg
     */
    void showLoginSuccessMsg(String username, String msg);
}
