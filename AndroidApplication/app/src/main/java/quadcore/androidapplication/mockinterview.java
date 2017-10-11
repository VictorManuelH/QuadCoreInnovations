package quadcore.androidapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 10/4/2017.
 * shup noobs
 */

public class mockinterview extends AppCompatActivity {
    private static final int TAKE_AVATAR_CAMERA_REQUEST = 1;
    Spinner mQuestions;
    VideoView mVideoView;
    Button record, delete;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mQuestions.findViewById(R.id.spinner);
        addItemsOnSpinner2();
        initVideo();
    }

    private void initVideo() {
        record = (Button) findViewById(R.id.record);


        record.setOnClickListener(new ChooseCameraListener());
    }


    private class ChooseCameraListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent pictureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(Intent.createChooser(pictureIntent, "Take your Video"), TAKE_AVATAR_CAMERA_REQUEST);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_AVATAR_CAMERA_REQUEST:
                if (resultCode == Activity.RESULT_CANCELED) {

                } else if (resultCode == Activity.RESULT_OK) {
                    Bitmap cameraPic = (Bitmap) data.getExtras().get("data");
                    if (cameraPic != null) {
                        try {
                            saveAvatar(cameraPic);
                        } catch (Exception e) {
                        }
                    }
                }
        }
    }

    private void saveAvatar(Bitmap cameraPic) {
    }

    public void addItemsOnSpinner2() {

        mQuestions = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mQuestions.setAdapter(dataAdapter);
    }
}

