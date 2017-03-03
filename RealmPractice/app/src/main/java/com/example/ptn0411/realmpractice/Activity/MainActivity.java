package com.example.ptn0411.realmpractice.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.ptn0411.realmpractice.Model.UserModel;
import com.example.ptn0411.realmpractice.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Realmの設定
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        mRealm = Realm.getInstance(config);

        //DBにデータの挿入
        mRealm.executeTransaction(realm -> {
            try {
                UserModel yamada = realm.createObject(UserModel.class);
                yamada.setName("山田");
                yamada.setId(1);

            } catch (io.realm.exceptions.RealmPrimaryKeyConstraintException e) {
                e.printStackTrace();
            }
        });

        //DBからデータの取得
        UserModel pickUpUser = mRealm.where(UserModel.class).equalTo("id", 1).findFirst();
        TextView textView = (TextView)findViewById(R.id.text_view);
        textView.setText(pickUpUser.getName());
    }

    /*------------------------------------------------------
      Activityにrealmを持つ時は、
      1. メンバ変数としてrealmをもたせること
      2. onDestroyのタイミングでcloseすること
      3?.onResumeのとき、realmのnullチェックとかするといいかも？
     ------------------------------------------------------*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRealm != null) mRealm.close();
    }

}
