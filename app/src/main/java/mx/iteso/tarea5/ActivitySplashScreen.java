package mx.iteso.tarea5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import mx.iteso.tarea5.beans.User;
import mx.iteso.tarea5.tools.Constants;

public class ActivitySplashScreen extends AppCompatActivity {
    public final static int SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                User user = loadPreferences();
                Intent intent;
                if(user.isLogged()){
                    intent = new Intent(ActivitySplashScreen.this,ActivityMain.class);
                }else {
                    intent = new Intent(ActivitySplashScreen.this,ActivityLogin.class);
                }
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

    public User loadPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.USER_PREFERENCES, MODE_PRIVATE);
        User user = new User();
        user.setName(sharedPreferences.getString(Constants.USER_PREF, null));
        user.setPassword(sharedPreferences.getString(Constants.PASSWORD_PREF, null));
        user.setLogged(sharedPreferences.getBoolean(Constants.LOGGED_PREF, false));
        return user;
    }
}
