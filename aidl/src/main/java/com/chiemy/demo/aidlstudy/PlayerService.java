package com.chiemy.demo.aidlstudy;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.widget.Toast;

import com.chiemy.demo.aidl.Player;

public class PlayerService extends Service {
    private static final String TAG = PlayerService.class.getSimpleName();
    private Handler handler;
    public PlayerService() {
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return player;
    }

    /**
     * 注意：这里的方法不是在主线程中执行的
     */
    private IBinder player = new Player.Stub(){

        @Override
        public void play(Music music) throws RemoteException {
            showToast("播放 " + music.getName());
        }

        @Override
        public void current(Music music) throws RemoteException {
            music.setName("name:test");
            music.setUrl("url:test");
        }

        @Override
        public void pause() throws RemoteException {
            showToast("暂停");
        }

        @Override
        public void next() throws RemoteException {
            showToast("下一曲");
        }

        @Override
        public void prev() throws RemoteException {
            showToast("上一曲");
        }
    };

    private void showToast(final String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
