package com.chiemy.demo.aidlstudy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.chiemy.demo.aidl.Calculator;


public class CalculatorService extends Service {
    public CalculatorService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private IBinder binder = new Calculator.Stub(){

        @Override
        public int add(int num1, int num2) throws RemoteException {
            return num1 + num2;
        }
    };

}
