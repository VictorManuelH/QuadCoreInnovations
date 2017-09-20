package quadcore.androidapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by victor on 9/19/2017.
 */

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void LogIn(View view) {
        Intent intent = new Intent(login.this, dashboard.class);
        startActivity(intent);
    }
}
