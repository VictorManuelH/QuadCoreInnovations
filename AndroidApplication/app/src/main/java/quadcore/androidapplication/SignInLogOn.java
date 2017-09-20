package quadcore.androidapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInLogOn extends AppCompatActivity {

    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_log_on);

        mButton = (Button) findViewById(R.id.logOn);


    }


    public void Log(View view) {
        Intent intent = new Intent(SignInLogOn.this, login.class);
        startActivity(intent);
    }
}
