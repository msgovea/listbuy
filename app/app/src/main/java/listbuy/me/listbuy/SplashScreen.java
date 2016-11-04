package listbuy.me.listbuy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Aline on 01/10/2016.
 */

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, WelcomeScreen.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override

    protected void onPause(){
        super.onPause();
        finish();
    }
}