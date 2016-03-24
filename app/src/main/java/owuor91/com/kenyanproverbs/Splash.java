package owuor91.com.kenyanproverbs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       Thread spl = new Thread(){
         public void run(){
             try {
                 sleep(2500);
                 Intent intent = new Intent(Splash.this, Proverbs.class);
                 startActivity(intent);
                 finish();
             }
             catch (Exception e) {
                e.printStackTrace();
             }
         }
       };

        spl.start();
    }
}
