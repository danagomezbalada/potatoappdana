package dam2021.mp08.uf1.p1.GOMEZ_DANA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import dam2021.mp08.uf1.p1.GOMEZ_DANA.ui.login.LoginActivity;

public class SpalshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        scheduleSplashScreen();
    }
    private void scheduleSplashScreen(){
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SpalshActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 3*1000);
    }
}