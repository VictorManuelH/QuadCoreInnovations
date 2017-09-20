package quadcore.androidapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by victor on 9/19/2017.
 */

public class eventPage extends AppCompatActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventpage);

        mTextView = (TextView) findViewById(R.id.event);

        new JSONTask().execute("http://capstoneprototypeqci.azurewebsites.net/api/EventsAPI/2");
    }

    public  class JSONTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                String line = "";
                StringBuffer stringBuffer = new StringBuffer();


                while ((line = reader.readLine()) != null) {
                    stringBuffer.append(line);
                }

                String finalJSON = stringBuffer.toString();

                JSONObject jsonObject = new JSONObject(finalJSON);

                //JSONArray jsonArray = jsonObject.getJSONArray("");

               // JSONObject finalObject = jsonArray.getJSONObject(0);

                String name = jsonObject.getString("Name");
                String description = jsonObject.getString("Description");
                return name + " " + description;
               } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mTextView.setText(result);

        }
    }
}
