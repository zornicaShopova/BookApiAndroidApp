package book.api.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends ArrayAdapter<BookDataModel> {
    private final Context context;
    private final List<BookDataModel> bookDataModel;
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
        //who display the row view in the parent  layout
        View rowView = inflater.inflate(R.layout.row_element, parent, false);

        //take from  row_element  layout the field  and
        TextView bookTitleTV = rowView.findViewById(R.id.book_name);
        TextView publishedDateTV = rowView.findViewById(R.id.book_date);


        //assign them them values
        bookTitleTV.setText(bookDataModel.get(position).getTitle());
        publishedDateTV.setText(bookDataModel.get(position).getPublishedDate());

//        bookTitleTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CheckBox checkBox = (CheckBox) v;
//                BookDataModel bookDataModel = (BookDataModel) checkBox.getTag();
//                Toast.makeText(context,"Clickeed on  chekbox"+ checkBox.getTag(),Toast.LENGTH_LONG).show();
//                //bookDataModel.setSelected(checkBox.isChecked());
//
//            }
//        });

//        BookDataModel bookDataModel  =  bookDataModel.get(position);
//        bookTitleTV.setText(bookDataModel.getTitle());
//        bookTitleTV.setChecked(bookDataModel.isSelected());


        //return the row
        return rowView;
    }
}
