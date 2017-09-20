package quadcore.androidapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by victor on 9/19/2017.
 */

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);



    }

    public void Events(View view) {
        Intent intent = new Intent(dashboard.this, eventPage.class);
        startActivity(intent);
    }
}
