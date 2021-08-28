package TcWeb.DataBase;

public class BookInformation {
    private String author;
    private String bookName;
    private String location;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getAuthor() {
        return author;
    }

    public String getBookName() {
        return bookName;
    }

    public String getLocation() {
        return location;
    }

}
