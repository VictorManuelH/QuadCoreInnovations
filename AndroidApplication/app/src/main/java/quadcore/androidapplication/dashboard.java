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
       Intent eventIntent = new Intent(dashboard.this, eventPage.class);
        startActivity(eventIntent);
    }

    public void mockInterview(View view) {
        Intent interviewIntent = new Intent(dashboard.this, mockInterview.class);
        startActivity(interviewIntent);
    }
}
