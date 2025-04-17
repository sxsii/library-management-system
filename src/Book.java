public class Book {

    private int ISBN;
    private String name;
    private String author;
    private String genre;

    public Book(int ISBN, String name, String author, String genre) {
        this.ISBN = ISBN;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public int getISBN() {return ISBN;}
    public String getName() {return name;}
    public String getAuthor() {return author;}
    public String getGenre() {return genre;}

    public void setISBN(int ISBN) {this.ISBN = ISBN;}
    public void setName(String name) {this.name = name;}
    public void setAuthor(String author) {this.author = author;}
    public void setGenre(String genre) {this.genre = genre;}

    public String toString() {
        return "ISBN: " + ISBN + ", Name: " + name + ", Author: " + author + ", Genre: " + genre;
    }
}
