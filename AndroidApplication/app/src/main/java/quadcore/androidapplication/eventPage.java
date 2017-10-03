package quadcore.androidapplication;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import quadcore.androidapplication.models.EventModel;

/**
 * Created by victor on 9/19/2017.
 */

public class eventPage extends AppCompatActivity {
    TextView mText;
    ListView eventList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventpage);
        mText = (TextView)findViewById(R.id.text);
        eventList = (ListView) findViewById(R.id.eventList);

        new JSONTask().execute("http://capstoneprototypeqci.azurewebsites.net/api/EventsAPI");
      //  new JSONTask().execute("https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesDemoList.txt");
    }

   public  class JSONTask extends AsyncTask<String, String, List<EventModel>>{

        @Override
        protected List<EventModel> doInBackground(String... params) {
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

                JSONArray jsonArray = new JSONArray(finalJSON);

                List<EventModel> eventModelList = new ArrayList<>();

                for (int i =0; i<jsonArray.length(); i++){
                    EventModel eventModel = new EventModel();
                    JSONObject finalObject = jsonArray.getJSONObject(i);
                    eventModel.setTime(finalObject.getString("Time"));
                    eventModel.setName(finalObject.getString("Name"));
                    eventModel.setEvent(finalObject.getString("Description"));
                    eventModel.setLocation(finalObject.getString("Location"));

                    eventModelList.add(eventModel);
                }
                return eventModelList;
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
        protected void onPostExecute(List<EventModel> result) {
            super.onPostExecute(result);

            EventAdapter adapter = new EventAdapter(getApplicationContext(), R.layout.row, result);
            eventList.setAdapter(adapter);
        }
    }

    public class EventAdapter extends ArrayAdapter{

         public List<EventModel> mEventModelList;
         public int resource;
        private LayoutInflater mInflater;

        public EventAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<EventModel> objects) {
            super(context, resource, objects);
            mEventModelList = objects;
            this.resource = resource;
            mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if (convertView == null){
                convertView = mInflater.inflate(resource, null);
            }

            ImageView imageView;
            TextView eventName, eventLocation, eventDescription, eventTime;

            imageView = (ImageView)convertView.findViewById(R.id.eventSponsor);
            eventName = (TextView)convertView.findViewById(R.id.eventName);
            eventTime = (TextView)convertView.findViewById(R.id.eventTime);
            eventLocation = (TextView)convertView.findViewById(R.id.eventLocation);
            eventDescription = (TextView)convertView.findViewById(R.id.eventDescription);

            eventName.setText(mEventModelList.get(position).getName());
            eventTime.setText("Date and Time: "+ mEventModelList.get(position).getTime());
            eventLocation.setText("Location: "+ mEventModelList.get(position).getLocation());
            eventDescription.setText("Description: "+ mEventModelList.get(position).getEvent());

            return convertView;
        }
    }
}
