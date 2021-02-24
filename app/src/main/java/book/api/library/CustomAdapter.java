package book.api.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends ArrayAdapter<BookDataModel> {
    private final Context context;
    private final List<BookDataModel> bookDataModel;

//constructor
    public CustomAdapter(Context context, List<BookDataModel> bookDataModel) {
        super(context, R.layout.row_element, bookDataModel);
        this.context = context;
        this.bookDataModel = bookDataModel;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_element, parent, false);

        TextView bookTitleTV = rowView.findViewById(R.id.book_name);
        TextView publishedDateTV  = rowView.findViewById(R.id.book_date);

        bookTitleTV.setText(bookDataModel.get(position).getTitle());
        publishedDateTV.setText(bookDataModel.get(position).getPublishedDate());

        return rowView;
    }
}
