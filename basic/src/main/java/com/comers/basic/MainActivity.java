package com.comers.basic;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;

import com.comers.basic.http.HttpHelper;
import com.comers.basic.http.CallBack;
import com.comers.basic.http.HttpResult;
import com.comers.basic.request.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getData() {
        Map<String, Object> map = new HashMap<>();
        HttpHelper.doForm("fd")
                .showDialog(false)
                .add("fd", "dfasdf")
                .addFiles(new ArrayList<String>())
                .params(map)
                .add("fsdf","fsd")
                .showDialog(false)
                .execute(new CallBack<User>() {
                    @Override
                    public void onSuccess(HttpResult<User> sResult) {

                    }
                });
        HttpHelper.doGet("gdfg")
                .add("fsd","dfd")
                .add("fdffd","dfad")
                .params(new HashMap<String, Object>())
                .execute(new CallBack<User>() {
                    @Override
                    public void onSuccess(HttpResult<User> sResult) {

                    }
                });
        HttpHelper.doPost("ddfgs")
                .add("jfdaodf","fasdf")
                .execute(new CallBack<User>() {
                    @Override
                    public void onSuccess(HttpResult<User> sResult) {

                    }
                });
        HttpHelper.doForm("dofaod")
                .add("sdfs","fsd")
                .execute(new CallBack<ContactsContract.Data>() {
                    @Override
                    public void onSuccess(HttpResult<ContactsContract.Data> sResult) {

                    }
                });
    }

}
