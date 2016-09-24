package com.sanfrancisco.msghandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final int WHAT = 1;
    private static int count = 0;
    TextView threadID;
    private Handler handlr = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT:
                    MainActivity.this.threadID.setText("msgWHAT:" + count++);
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Snack bar messages", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        gotoContentView();
    }

    protected void gotoContentView() {

        MainActivity.this.threadID = (TextView) findViewById(R.id.info);//primary thread ID
        Timer timer = new Timer();
        timer.schedule(new MyTask(), 0, 2000);   //???timer
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class MyTask extends TimerTask {

        private Handler handler2 = new Handler() {
            @Override
            public void handleMessage(Message msg2) {
                switch (msg2.what) {
                    case WHAT:
                        MainActivity.this.threadID.setText("msg2WHAT-" + count++);
                }
                super.handleMessage(msg2); //point 2 point
            }
        };


        @Override
        public void run() {

            Message message2 = new Message();
            message2.what = WHAT;
            MyTask.this.handler2.sendMessage(message2);//???this????Set????timer
        }
    }

    class MyHandler extends Handler {
        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT:
                    MainActivity.this.threadID.setText("msgWHAT" + count++);
            }
            super.handleMessage(msg);
        }


    }
}
