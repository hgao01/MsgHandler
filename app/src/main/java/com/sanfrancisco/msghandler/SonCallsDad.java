package com.sanfrancisco.msghandler;

/**
 * Created by hgao01 on 9/26/2016.
 */

public class SonCallsDad extends Thread {


        MainActivity dady;
        int what = 1;
        public SonCallsDad(MainActivity dady2wins){
            this.dady = dady2wins;
        }

        @Override
        public void run() {
            while(true){
                dady.mHandler.sendEmptyMessage((what++)%2);
                try{
                    Thread.sleep(2000);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


