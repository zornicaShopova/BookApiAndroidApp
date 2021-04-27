package book.api.library;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends ArrayAdapter<BookDataModel> {
    private final Context context;
    private final List<BookDataModel> bookDataModel;
    ArrayList<Integer> selectedBooks = new ArrayList<Integer>();
    // private final List<BookDataModel> allBooks;

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
        //add book in selectedBook array
        addCheckBox.setOnClickListener(v -> {
            if (addCheckBox.isChecked()) {
                selectedBooks.add(position);
            } else {
                if (selectedBooks.contains(position)) {
                    selectedBooks.remove(selectedBooks.indexOf(position));
                }
            }
        });


        //return the row
        return rowView;
    }
}
