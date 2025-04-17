public class User {

    private String name;
    private int libraryID;
    private boolean owesBooks;

    public User (String name, int libraryID, boolean owesBooks) {
        this.name = name;
        this.libraryID = libraryID;
        this.owesBooks = owesBooks;
    }

    public String getName(){return name;}
    public int getLibraryID(){return libraryID;}
    public boolean isOwesBooks() {return owesBooks;}

    public void setName(String name) {this.name = name;}
    public void setLibraryID(int libraryID) {this.libraryID = libraryID;}
    public void setOwesBooks(boolean owesBooks) {this.owesBooks = owesBooks;}


    public String toString() {
        String owesBooksString;
        if (owesBooks) {
            owesBooksString = "Yes";
        } else {
            owesBooksString = "No";
        }
        return "Library ID: " + libraryID + ", Name: " + name + ", Owes Books? " +  owesBooksString;
    }
}
