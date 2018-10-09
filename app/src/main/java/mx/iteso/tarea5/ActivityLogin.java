package mx.iteso.tarea5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mx.iteso.tarea5.beans.User;
import mx.iteso.tarea5.tools.Constants;

public class ActivityLogin extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.activity_login_usuario);
        password = findViewById(R.id.activity_login_contrasenia);
        login = findViewById(R.id.activity_login_login);

        login.setOnClickListener((View v)-> {
                savePreferences();
                Intent intent = new Intent(ActivityLogin.this,ActivityMain.class);
                startActivity(intent);
                finish();
            }
        );
    }

    public void savePreferences(){
        User user = new User();
        user.setName(username.getText().toString());
        user.setPassword(password.getText().toString());
        user.setLogged(true);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.USER_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.USER_PREF, user.getName());
        editor.putString(Constants.PASSWORD_PREF, user.getPassword());
        editor.putBoolean(Constants.LOGGED_PREF, user.isLogged());
        editor.apply();
    }
}