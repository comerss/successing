package com.comers.basic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.comers.basic.http.CallBack;
import com.comers.basic.http.HttpHelper;
import com.comers.basic.http.HttpResult;
import com.comers.basic.request.User;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
    }

    public void getData() {
        Map<String, Object> map = new HashMap<>();
        String url="https://app2.liangyibang.com/doctor/login/pass";
        HttpHelper.doForm(url)
                .add("mobile", "15810490516")
                .add("version","1.5")
                .add("app","android")
                .add("password","123456")
                .showDialog(false)
                .execute(new CallBack<User>() {
                    @Override
                    public void onSuccess(HttpResult<User> sResult) {
                        Log.i("sresult-->",sResult.toString());
                    }
                });
       /* HttpHelper.doGet("gdfg")
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
               .execute(new CallBack<List<ContactsContract.Data>>() {
                   @Override
                   public void onSuccess(HttpResult<List<ContactsContract.Data>> sResult) {

                   }
               });*/
    }

}
