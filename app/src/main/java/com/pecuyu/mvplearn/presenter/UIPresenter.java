package com.pecuyu.mvplearn.presenter;

import android.text.TextUtils;

import com.pecuyu.mvplearn.model.ILoginListener;
import com.pecuyu.mvplearn.model.UIModelImpl;

/**
 * <br/>Author: pecuyu
 * <br/>Date: 2018/5/25
 * <br/>TODO:
 */

public class UIPresenter {
    private IView mUi;
    private UIModelImpl mUiModel;

    public UIPresenter(IView ui) {
        mUi = ui;
        mUiModel = new UIModelImpl();
    }


    public void login(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            mUi.showLoginFailedMsg(username, "输入不能为空!");
            return;
        }
        mUi.showLoginDialog();

        mUiModel.verifyLogin(username, password, new ILoginListener() {
            @Override
            public void onLoginSuccess(String username) {
                mUi.dismissLoginDialog();
                mUi.showLoginSuccessMsg(username, "登录成功");
            }

            @Override
            public void onLoginFailed(String username, String errorMsg) {
                mUi.dismissLoginDialog();
                mUi.showLoginFailedMsg(username, "登录失败! "+errorMsg);
            }
        });

    }

}
