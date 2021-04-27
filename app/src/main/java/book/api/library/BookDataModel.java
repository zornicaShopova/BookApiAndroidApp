package book.api.library;

import org.json.JSONArray;

public class BookDataModel {

    private String volumeInfo;
    private String title;
    private String publishedDate;
    private String author;
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

    //constructor  for the adapter only
    public BookDataModel(String title, String publishedDate, String author) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
