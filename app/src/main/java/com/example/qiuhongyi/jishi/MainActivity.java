package com.example.qiuhongyi.jishi;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Button mButton;
    private TextView mTextView;
    private boolean isRunning = true;
    private Thread mThread;
    private int timer = 0;
    private Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.button1);
        mTextView = (TextView) findViewById(R.id.textView1);

        mButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(isRunning==true)
                isRunning = false;
                else
                    isRunning=true;
            }
        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        mTextView.setText("逝去了:" + msg.obj);
                }
            }
        };
        mThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    while (isRunning) {
                        Thread.currentThread().sleep(1000);
                        timer++;
                        Message message = new Message();
                        message.obj = timer;
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        mThread.start();
    }
}