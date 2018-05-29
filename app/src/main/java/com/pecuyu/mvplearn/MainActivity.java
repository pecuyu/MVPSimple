package com.pecuyu.mvplearn;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pecuyu.mvplearn.presenter.IView;
import com.pecuyu.mvplearn.presenter.UIPresenter;

public class MainActivity extends AppCompatActivity implements IView {

    private EditText mUserName;
    private EditText mPassword;

    private Button mLogin;
    private ProgressDialog mLoginDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserName = findViewById(R.id.id_username);
        mPassword = findViewById(R.id.id_password);
        mLogin = findViewById(R.id.id_btn_login);

        final UIPresenter mUiPresenter = new UIPresenter(this);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = mUserName.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                mUiPresenter.login(username, password);
            }
        });

    }

    /**
     * 显示登录提示Dialog
     */
    @Override
    public void showLoginDialog() {
        if (mLoginDialog == null) {
            mLoginDialog = new ProgressDialog(this);
            mLoginDialog.setTitle("Info");
            mLoginDialog.setMessage("logining...");
        }

        if (!mLoginDialog.isShowing()) mLoginDialog.show();
    }

    /**
     * 关闭登录提示Dialog
     */
    @Override
    public void dismissLoginDialog() {
        if (mLoginDialog != null && mLoginDialog.isShowing()) {
            mLoginDialog.dismiss();
        }
    }

    @Override
    public void showLoginFailedMsg(String username, final String msg) {
        if (msg == null) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showLoginSuccessMsg(String username, final String msg) {
        if (msg == null) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
