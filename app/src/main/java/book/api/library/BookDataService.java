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
        List<BookDataModel> volumeInfoList = new ArrayList<>();

        String url = QUERY_FOR_BOOKS_ITEMS + searchInput;
        //get the json object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // elements from  volumeInfo
                String title = "";
                String publishedDate = "";
                try {
                    JSONArray booksItems = response.getJSONArray("items");

                    for (int i = 0; i < booksItems.length(); i++) {

                        //get the first item --> kind
                        BookDataModel first_item = new BookDataModel(title, publishedDate);
                        JSONObject items = (JSONObject) booksItems.get(i);
                        //make volumeInfo into object from which we  take the strings data
                        JSONObject volumesElement = items.getJSONObject("volumeInfo");
                        //take the title  string
                        title = volumesElement.getString("title");
                        publishedDate = volumesElement.getString("publishedDate");

                        first_item.setVolumeInfo(title);
                        first_item.setVolumeInfo(publishedDate);
                        //add the elements from the volumes in the list
                        volumeInfoList.add(first_item);

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
