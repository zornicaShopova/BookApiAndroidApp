package book.api.library;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookDataService {

    public static final String QUERY_FOR_BOOKS_ITEMS = "https://www.googleapis.com/books/v1/volumes?q=";

    Context context;
    String totalItems;

    public BookDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String totalItems);
    }

    public void getTotalItems(String searchInput, VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_BOOKS_ITEMS + searchInput;

        //set up the request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                totalItems = "";

                try {
                    totalItems = response.getString("totalItems");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(context, "Total books : " + totalItems, Toast.LENGTH_SHORT).show();
                volleyResponseListener.onResponse(totalItems);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Something  went wrong!", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something wrong!");
            }
        });

        //instantiate the RequestQueue
        MySingleton.getInstance(context).addToRequestQueue(request);

    }

    public interface VolumeInfoResponseListener {
        void onError(String message);

        void onResponse(List<BookDataModel> bookDataModel);
    }

    public void getVolumeInfo(String searchInput, VolumeInfoResponseListener volumeInfoResponseListener) {
        //make a list where to store the data
        List<BookDataModel> volumeInfoList = new ArrayList<>();
        //url from which we fetch the json data
        String url = QUERY_FOR_BOOKS_ITEMS + searchInput;
        //make a json object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // variables for saving the attrs values
                String title = "";
                String publishedDate = "";
                try {
                    // get the response like JSONArray
                    JSONArray booksItems = response.getJSONArray("items");

                    // iterate through the array to take his attribute
                    // we need just attr - volumeInfo
                    for (int i = 0; i < booksItems.length(); i++) {

                        // make object to store  the attr you get from the volumeInfo
                        BookDataModel attrFromVolumeInfo = new BookDataModel(title,publishedDate);
                        // save "items" in jsonObject
                        JSONObject items = (JSONObject) booksItems.get(i);
                        // make volumeInfo into object from which we get his attrs
                        JSONObject volumesElement = items.getJSONObject("volumeInfo");
                        // store the values of attrs in variables
                        title = volumesElement.getString("title");
                        publishedDate = volumesElement.getString("publishedDate");

//                        // save the json data in the database
//                        DatabaseHelper databaseHelper = new DatabaseHelper(context);
//                        // take the "items" and save the whole information in the db
//                        databaseHelper.saveDataToDB(booksItems);

                        attrFromVolumeInfo.setVolumeInfo(title);
                        attrFromVolumeInfo.setVolumeInfo(publishedDate);

                        //add the elements from the volumes in the list
                        volumeInfoList.add(attrFromVolumeInfo);

                    }

                    volumeInfoResponseListener.onResponse(volumeInfoList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Something  went  wrong!", Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
