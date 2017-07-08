package demo.com.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    MyBinder myBinder = new MyBinder();

    public MyService() {
    }

    private int i;
    Timer timer;
    TimerTask timerTask;

    public int getNum(){
        return i;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("MyService onCreate-----");

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("i=" + i);
                i++;
            }
        };
        timer.schedule(timerTask, 1000, 1000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("MyService onDestroy-----");
        timer.cancel();

    }

    public class MyBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }
}
