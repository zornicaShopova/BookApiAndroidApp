package book.api.library;

public class BookDataModel {


    private String volumeInfo;
    private String title;
    private String publishedDate;


    public BookDataModel(String kind, String id, String selfLink, String volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public BookDataModel() {
    }

    public BookDataModel(String title, String publishedDate) {
        this.title = title;
        this.publishedDate = publishedDate;
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
