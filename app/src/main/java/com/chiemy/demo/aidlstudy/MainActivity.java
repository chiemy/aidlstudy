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

public class MainActivity extends AppCompatActivity {
    private EditText num1Et;
    private EditText num2Et;
    private TextView resultTv;

    private Calculator caculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bindCalculateService();

        num1Et = (EditText) findViewById(R.id.et_num1);
        num2Et = (EditText) findViewById(R.id.et_num2);
        resultTv = (TextView) findViewById(R.id.tv_result);

        findViewById(R.id.btn_caculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
    }

    private void bindCalculateService(){
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.chiemy.demo.aidlstudy", "com.chiemy.demo.aidlstudy.CalculatorService"));
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            caculator = Calculator.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
