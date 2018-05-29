package com.pecuyu.mvplearn.presenter;

import android.text.TextUtils;

import com.pecuyu.mvplearn.model.ILoginListener;
import com.pecuyu.mvplearn.model.UIModelImpl;

import java.lang.ref.WeakReference;

/**
 * <br/>Author: pecuyu
 * <br/>Date: 2018/5/25
 * <br/>TODO:
 */

public class UIPresenter {
    private UIModelImpl mUiModel;

    private WeakReference<IView> mUiRef; // 防止内存泄漏

    public UIPresenter(IView ui) {
        mUiRef = new WeakReference<>(ui);
        mUiModel = new UIModelImpl();

    }


    public void login(String username, String password) {
        if (mUiRef == null || mUiRef.get() == null) return;
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            mUiRef.get().showLoginFailedMsg(username, "输入不能为空!");
            return;
        }
        mUiRef.get().showLoginDialog();

        mUiModel.verifyLogin(username, password, new ILoginListener() {
            @Override
            public void onLoginSuccess(String username) {
                if (mUiRef.get() != null) {
                    IView ui = mUiRef.get();
                    ui.dismissLoginDialog();
                    ui.showLoginSuccessMsg(username, "登录成功");
                }
            }

            @Override
            public void onLoginFailed(String username, String errorMsg) {
                if (mUiRef.get() != null) {
                    IView ui = mUiRef.get();
                    ui.dismissLoginDialog();
                    ui.showLoginFailedMsg(username, "登录失败! " + errorMsg);
                }
            }
        });

    }

    public void onDetachView() {
        mUiRef.clear();
        mUiRef = null;
        mUiModel.onDetachView();
        mUiModel=null;
    }

}
