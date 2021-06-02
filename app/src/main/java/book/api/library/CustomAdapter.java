package book.api.library;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class CustomAdapter extends ArrayAdapter<BookDataModel> {
    private final Context context;
    private final List<BookDataModel> bookDataModel;

    ArrayList<Integer> selectedBooks = new ArrayList<Integer>();
    // private final List<BookDataModel> allBooks;
    DatabaseHelper db ;

    //constructor
    public CustomAdapter(Context context, List<BookDataModel> bookDataModel) {
        super(context, R.layout.row_element, bookDataModel);
        this.context = context;
        this.bookDataModel = bookDataModel;
    }

//    public CustomAdapter(Context context, int textviewResourceid, ArrayList<BookDataModel> bookDataModel){
//        super(context,textviewResourceid,bookDataModel);
//        this.bookDataModel  =  new ArrayList<BookDataModel>();
//        this.bookDataModel.addAll(bookDataModel);
//
//    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //make inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //who display the row view in the parent layout
        View rowView = inflater.inflate(R.layout.row_element, parent, false);

        //take row_element
        TextView bookTitleTV = rowView.findViewById(R.id.book_name);
        TextView publishedDateTV = rowView.findViewById(R.id.book_date);
        TextView authorTV = rowView.findViewById(R.id.book_authors);
        CheckBox addCheckBox = rowView.findViewById(R.id.checkBox);
        //assign a value in them
        bookTitleTV.setText(bookDataModel.get(position).getTitle());
        publishedDateTV.setText(bookDataModel.get(position).getPublishedDate());
        authorTV.setText(String.valueOf(bookDataModel.get(position).getAuthor()));

        addCheckBox.setChecked(selectedBooks.contains(position));

        //check the boxes and save that status that is checked
        addCheckBox.setOnClickListener((View v) -> {
            String book = "";
            String data = "";
            if (addCheckBox.isChecked()) {
                selectedBooks.add(position);

            } else {
                if (selectedBooks.contains(position)) {
                    selectedBooks.remove(selectedBooks.indexOf(position));
                }
            }

            //if checkbox is check then take the data
            boolean checkbox = ((CheckBox) v).isChecked();

            if (checkbox) {

                book = bookTitleTV.getText().toString();
                data = publishedDateTV.getText().toString();



                Toast.makeText(v.getContext(), "Title: " + book + " " + data, Toast.LENGTH_SHORT).show();
                //save the field data to db
                db = new DatabaseHelper(context);
               Boolean cheakupdatedate =  db.saveDataToDB(book,data);
               if(cheakupdatedate==true){
                   Toast.makeText(v.getContext(),"The data is saved",Toast.LENGTH_LONG).show();
               }

        }else{
            Toast.makeText(v.getContext(), "Not saved", Toast.LENGTH_SHORT).show();
        }
    });


    //return the row
        return rowView;
 }
}
