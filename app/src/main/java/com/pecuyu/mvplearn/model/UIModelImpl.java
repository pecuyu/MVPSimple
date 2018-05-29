package com.pecuyu.mvplearn.model;

import android.os.SystemClock;

/**
 * <br/>Author: pecuyu
 * <br/>Email: yu.qin@ck-telecom.com
 * <br/>Date: 2018/5/25
 * <br/>TODO:
 */

public class UIModelImpl implements IUIModel {
    @Override
    public void verifyLogin(final String username, final String password, final ILoginListener loginListener) {
        if (loginListener == null)
            throw new NullPointerException("loginListener should not be null");

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1500);  // 模拟耗时
                if ("qy".equals(username) && "lj".equals(password)) {
                    loginListener.onLoginSuccess(username);
                } else if ("qy".equals(username) && !"lj".equals(password)) {
                    loginListener.onLoginFailed(username, "密码错误!");
                } else {
                    loginListener.onLoginFailed(username, "连接超时!");
                }
            }

        }).start();

    }
}
