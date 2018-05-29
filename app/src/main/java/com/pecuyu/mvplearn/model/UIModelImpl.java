package com.pecuyu.mvplearn.model;

import android.os.SystemClock;
import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <br/>Author: pecuyu
 * <br/>Date: 2018/5/25
 * <br/>TODO:
 */

public class UIModelImpl implements IUIModel {

    public static final String TAG = UIModelImpl.class.getSimpleName();
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 3,
            1000, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(5));

    @Override
    public void verifyLogin(final String username, final String password, final ILoginListener loginListener) {
        if (loginListener == null)
            throw new NullPointerException("loginListener should not be null");

        executor.execute(new LoginRunnable(username, password, loginListener));
    }

    class LoginRunnable implements Runnable {

        private final String username;
        private final String password;
        private final ILoginListener loginListener;

        public LoginRunnable(String username, String password, ILoginListener loginListener) {

            this.username = username;
            this.password = password;
            this.loginListener = loginListener;
        }

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
    }

    @Override
    public void onDetachView() {
        if (executor != null && !executor.isTerminating()) {
            executor.shutdown();
            Log.d(TAG, TAG + "-> onDetachView : pool shutdown");
        }
    }
}
