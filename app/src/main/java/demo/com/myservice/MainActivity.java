package demo.com.myservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyService myService;
    private MyBoadcast myBoadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button10 = (Button) findViewById(R.id.button10);
        Button button9 = (Button) findViewById(R.id.button9);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button5 = (Button) findViewById(R.id.button5);


        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(serviceConnection);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("得到当前Service中i=" + myService.getNum());
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myBoadcast = new MyBoadcast();
                IntentFilter intentFilter = new IntentFilter("dome.com.myservice.testReceiver");
                registerReceiver(myBoadcast, intentFilter);

                Intent intent = new Intent("dome.com.myservice.testReceiver");
                intent.putExtra("msg", "aha ok");
                sendBroadcast(intent);


            }
        });
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            System.out.println("serviceConnection=======");
            MyService.MyBinder binder = (MyService.MyBinder) iBinder;
            myService = binder.getService();


        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            System.out.println("onServiceDisconnected=======");
        }
    };
}
