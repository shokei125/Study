package com.example.student.study;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;



/**
 * Serviceクラス ※タイマーの時刻表示サンプル
 */
public class StartService extends Service {

    // T i m e r オブジェクト
    private Timer mTimer = null;
    //経過時間
    private int mCountTime;
    //終了時間
    private int mStopTime;

    public static final String EXTRA_STOP_TIME = "EXTRA_STOP_TIME";

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Toast.makeText(StartService.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
        }
    };

    private TimerTask mtask = new TimerTask() {
        @Override
        public void run() {
            mCountTime += 10;

            if (mCountTime / 60 == mStopTime) {
                stopSelf();
            } else {
                mHandler.sendMessage(Message.obtain(mHandler, 0, mCountTime / 60 + "分" + mCountTime % 60 + "秒経過しました。"));
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "サービスを起動します。", Toast.LENGTH_LONG).show();
        mTimer = new Timer();
        mCountTime = 0;

    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Toast.makeText(this, "サービスを開始します。", Toast.LENGTH_LONG).show();
        mTimer.schedule(mtask, 10000, 10000);
        //バンドル生成　※呼び出し元の値を取得するため
        Bundle bundle = intent.getExtras();
        mStopTime = Integer.parseInt(bundle.getString(EXTRA_STOP_TIME));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "サービス終了イベントハンドラ。", Toast.LENGTH_LONG).show();
        //タイマー解除
        mTimer.cancel();
        mTimer.purge();
    }


}
