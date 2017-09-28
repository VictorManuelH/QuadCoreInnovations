package quadcore.androidapplication;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * Created by victor on 9/21/2017.
 */

public class HandleXML {
     private String name = "name";
    private String description = "name";
    private String location = "name";
    private String time = "name";
    private String urlString = null;
    XmlPullParserFactory mXmlPullParserFactory;
    public boolean finishParsing = true;

    public HandleXML(String Url){
        urlString = Url;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public void parseXMLAndStoreIt(XmlPullParser parser){
        int event;
        String text = null;

    }
}
