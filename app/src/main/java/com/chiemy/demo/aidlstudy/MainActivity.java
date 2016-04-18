package com.chiemy.demo.aidlstudy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chiemy.demo.aidl.Calculator;
import com.chiemy.demo.aidl.Player;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText num1Et;
    private EditText num2Et;
    private TextView resultTv;

    private Calculator caculator;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bindCalculateService();
        bindPlayerService();
        initView();
    }

    private void initView(){
        num1Et = (EditText) findViewById(R.id.et_num1);
        num2Et = (EditText) findViewById(R.id.et_num2);
        resultTv = (TextView) findViewById(R.id.tv_result);

        findViewById(R.id.btn_caculate).setOnClickListener(this);
        findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_next).setOnClickListener(this);
        findViewById(R.id.btn_prev).setOnClickListener(this);
    }

    private void bindCalculateService(){
        bindAndStartService("CalculatorService", calculatorServiceCon);
    }

    private void bindPlayerService(){
        bindAndStartService("PlayerService", playerServiceCon);
    }

    private static final String PACKAGE = "com.chiemy.demo.aidlstudy";
    public void bindAndStartService(String serviceName, ServiceConnection connection) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(PACKAGE, PACKAGE + "." + serviceName));
        // 因为远程服务所在的进程没有界面，所以需要自己启动服务
        startService(intent);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection calculatorServiceCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            caculator = Calculator.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private ServiceConnection playerServiceCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            player = Player.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_caculate:
                calculate();
                break;
            case R.id.btn_play:
                play();
                break;
            case R.id.btn_next:
                next();
                break;
            case R.id.btn_prev:
                prev();
                break;
        }
    }

    private void calculate(){
        int num1 = Integer.parseInt(num1Et.getText().toString());
        int num2 = Integer.parseInt(num2Et.getText().toString());
        try {
            if (caculator != null){
                int result = caculator.add(num1, num2);
                resultTv.setText(String.valueOf(result));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            resultTv.setText("Error");
        }
    }

    private void play() {
        Music music = new Music();
        music.setName("My Song");
        try {
            player.play(music);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void next() {
        try {
            player.next();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void prev() {
        try {
            player.prev();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(calculatorServiceCon);
        unbindService(playerServiceCon);
    }
}
