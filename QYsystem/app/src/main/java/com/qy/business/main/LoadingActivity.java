package com.qy.business.main;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.TextView;

import com.qy.business.activity.R;
import com.qy.business.main.login.LoginActivity;
import com.qy.business.tools.AppUtils;
import com.qy.business.tools.SPUtils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by zhangyu on 2016/5/9.
 */

public class LoadingActivity extends AppCompatActivity {
    private static final String SHORT_KEY = "isAdd";
    private Subscription mSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        /**
         * 1.获取版本号
         */
        TextView textView = (TextView) findViewById(R.id.loading_textView);
        String version = AppUtils.getVersionName(LoadingActivity.this);// 当前版本
        textView.setText("版本号:" + version);
        /**
         * 2.添加快捷方式
         */
        if ((int) SPUtils.get(this, SHORT_KEY, 0) == 0) {
            addShortcut();
        }
        /**
         * 延迟两秒页面跳转
         * 使用了RxAndroid
         */
        mSubscription = Observable.timer(4, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).map(new Func1<Long, Object>() {
            @Override
            public Object call(Long aLong) {
                //跳转到登陆界面
                startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
                finish();
                return null;
            }
        }).subscribe();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev){
        //用户触摸跳过
        if(ev.getAction() == MotionEvent.ACTION_UP){
            mSubscription.unsubscribe();
            startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
            finish();
        }
        return super.onTouchEvent(ev);
    }

    private void addShortcut() {
        Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "奇易商家"); //
        intent.putExtra("duplicate", false); // 不允许重复创建
        // 指定当前的Activity为快捷方式启动的对象: 如 com.everest.video.VideoPlayer
        // 注意: ComponentName的第二个参数必须加上点号(.)，否则快捷方式无法启动相应程序 //
        ComponentName comp = new ComponentName(this.getPackageName(), "." + this.getLocalClassName()); //
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(comp));
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(this, LoadingActivity.class)); // 快捷方式的图标
        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(this, R.mipmap.ic_launcher);
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
        // //让卸载应用的时候一起卸载掉快捷方式
        // intent.setAction("android.intent.action.MAIN");
        // intent.addCategory("android.intent.category.LAUNCHER");
        sendBroadcast(intent);

        SPUtils.put(this, "isAdd", 1);
    }
}
