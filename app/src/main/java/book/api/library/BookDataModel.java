package book.api.library;

import org.json.JSONArray;

public class BookDataModel {

    private String volumeInfo;
    private String title;
    private String publishedDate;
    private int bookID;


//    public BookDataModel(String kind, String id, String selfLink, String volumeInfo) {
//        this.volumeInfo = volumeInfo;
//    }

    public BookDataModel() {
    }

    //constructor for  the database only
    public BookDataModel(int bookID, String title, String publishedDate) {
        this.bookID = bookID;
        this.title = title;
        this.title = publishedDate;
    }

    //constructor  fo the adapter only
    public BookDataModel(String title, String publishedDate) {
        this.title = title;
        this.publishedDate = publishedDate;
    }



    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

//    @Override
//    public String toString() {
//        return
//                "Title: " + title
//                + ", " + "Date: "+  publishedDate;
//    }

    public String getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(String volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}
