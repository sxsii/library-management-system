import javax.swing.*;
import java.util.ArrayList;
import  java.util.regex.*;

public class Transactions {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    public Transactions(){
        initialiseValues();
    }

    public void initialiseValues(){
        Book book1 = new Book(1, "Of Skeletons and Dragons", "Belle", "Fantasy");
        Book book2 = new Book(2, "The Chronicles of Adam", "Adam Santi", "Autobiography");
        Book book3 = new Book(3, "A Lost Wallet", "Samdu", "Romance");

        //look up CommandLineRunner

        User user1 = new User("Janah", 1, false);
        User user2 = new User("Rico", 2, false);
        User user3 = new User("Sesi", 3, false);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        books.add(book1);
        books.add(book2);
        books.add(book3);
    }

    //methods needed: .addBook(/), .removeBook(/), .returnBook(/), .borrowBook(/), .addUser(/), searchForBook(/), byISBN(/), byGenre(/), byName(/), byAuthor(/), displayBooks(/), displayUsers(/)

    public void addUser() {
        try {
            String testLibraryID = JOptionPane.showInputDialog("Please input your library ID: ");
            if (testLibraryID == null) return;
            int libraryID = Integer.parseInt(testLibraryID);

            for (User u : users) {
                if (u.getLibraryID() == libraryID) {
                    JOptionPane.showMessageDialog(null, "User ID already exists!");
                    return;
                }
            }

            String name = JOptionPane.showInputDialog("Input your name: ");
            if (name == null) return;
            boolean owesBooks = false;

            User newUser = new User(name, libraryID, owesBooks);
            users.add(newUser);

            JOptionPane.showMessageDialog(null, newUser.getName() + " has successfully been added!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter correct values.");
        }
    }



    public void addBook(){
        try {
            String testISBN = JOptionPane.showInputDialog("Please input the book's ISBN: ");
            if (testISBN == null) return;
            int ISBN = Integer.parseInt(testISBN);

            for (Book b : books) {
                if (b.getISBN() == ISBN) {
                    JOptionPane.showMessageDialog(null, "ISBN already exists!");
                    return;
                }
            }

            String name = JOptionPane.showInputDialog("Input the book's name: ");
            if (name == null) return;
            String author = JOptionPane.showInputDialog("Input the book's author: ");
            if (author == null) return;
            String genre = JOptionPane.showInputDialog("Input the book's genre: ");
            if (genre == null) return;

            Book newBook = new Book(ISBN, name, author, genre);
            books.add(newBook);
            JOptionPane.showMessageDialog(null, String.format("%s by %s has been added successfully!", newBook.getName(), newBook.getAuthor()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter correct values.");
        }
    }

    public void removeBook(){
        try {
            String testCheckID = JOptionPane.showInputDialog("Please input the book's ISBN: ");
            if (testCheckID == null) return;
            int checkID = Integer.parseInt(testCheckID);
            String delname = "";
            String delauthor = "";

            Book bookToRemove = null;

            for (Book b : books){
                if (checkID == b.getISBN()){
                    bookToRemove = b;
                    delname = b.getName();
                    delauthor = b.getAuthor();
                    break;
                }
            }

            if(bookToRemove != null){
                books.remove(bookToRemove);
                JOptionPane.showMessageDialog(null, String.format("%s by %s has successfully been deleted.", delname, delauthor));
            } else {
                JOptionPane.showMessageDialog(null, "This book does not exist!");
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter correct values.");
        }
    }

    public void returnBook(){
        System.out.println(users);
        try {
            String testCheckUserID = JOptionPane.showInputDialog("Please input your library ID: ");
            if (testCheckUserID == null) return;
            int checkUserID = Integer.parseInt(testCheckUserID);
            boolean check = false;
            int checkID;
            String testCheckID = "";

            for (User u : users){
                if (checkUserID == u.getLibraryID()){
                    check = true;
                    u.setOwesBooks(false);
                    break;
                }
            }

            if (check){
                try {
                    testCheckID = JOptionPane.showInputDialog("Please input the book's ISBN: ");
                } catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Please input correct values.");
                }
                if (testCheckID == null) return;
                checkID = Integer.parseInt(testCheckID);

                for (Book b : books){
                    if (checkID == b.getISBN()){
                        if (b.isAvailable()) {
                            JOptionPane.showMessageDialog(null, "This book is already returned.");
                            return;
                        }
                        JOptionPane.showMessageDialog(null, "You have successfully returned " + b.getName() + " by " + b.getAuthor() + "!");
                        b.setAvailable(true);
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "This user does not exist!");
            }

        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter correct values.");
        }
    }

    public void borrowBook(){
        try {
            String testCheckUserID = JOptionPane.showInputDialog("Please input your library ID: ");
            if (testCheckUserID == null) return;
            int checkUserID = Integer.parseInt(testCheckUserID);
            boolean checkUser = false;
            int checkID;
            String testCheckID = "";
            //boolean boolCheck = false;

            for (User u : users){
                if (checkUserID == u.getLibraryID()){
                    checkUser = true;
                    u.setOwesBooks(true);
                    if (u.isOwesBooks()) {
                        JOptionPane.showMessageDialog(null, "You already borrowed a book!");
                        return;
                    }
                    break;
                }
            }

            if (checkUser) {
                try {
                    testCheckID = JOptionPane.showInputDialog("Please input the book's ISBN: ");
                } catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "This book does not exist!");
                }
                if (testCheckID == null) return;
                checkID = Integer.parseInt(testCheckID);

                for (Book b : books){
                    if (checkID == b.getISBN()){
                        if (!b.isAvailable()) {
                            JOptionPane.showMessageDialog(null, "This book is currently unavailable.");
                            return;
                        }
                        JOptionPane.showMessageDialog(null, "You have successfully borrowed " + b.getName() + " by " + b.getAuthor() + "!");
                        b.setAvailable(false);
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "This user does not exist!");
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter correct values.");
        }
    }

        public void searchForBook(){
            boolean running = true;

            while (running) {
                String[] options = {"ISBN","Name", "Author", "Genre", "Exit"};
                int choice = JOptionPane.showOptionDialog(null, "How do you want to search?", "Book Search",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                switch (choice) {
                    case 0:
                        byISBN();
                        break;
                    case 1:
                        byName();
                        break;
                    case 2:
                        byAuthor();
                        break;
                    case 3:
                        byGenre();
                        break;
                    case 4:
                        running = false;
                }
            }
        }

        public void byISBN(){
            int checkISBN = Integer.parseInt(JOptionPane.showInputDialog("Please input the book's ISBN: "));
            StringBuilder sb = new StringBuilder("Results:\n");
            boolean found = false;

            for (Book b : books){
                if (b.getISBN() == checkISBN) {
                    sb.append(b.toString()).append("\n");
                    found = true;
                }
            }

            if (!found) {
                sb.append("No results.");
            }

            JOptionPane.showMessageDialog(null, sb.toString());
        }

        public void byGenre(){
            String checkGenre = JOptionPane.showInputDialog("Please input a genre: ");
            StringBuilder sb = new StringBuilder("Results:\n");
            boolean found = false;

            for (Book b : books){
                Pattern pattern = Pattern.compile(checkGenre, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(b.getGenre());
                boolean matchFound = matcher.find();

                if (matchFound){
                    found = true;
                    sb.append(b.toString()).append("\n");
                }
            }

            if (!found) {
                sb.append("No results.");
            }

            JOptionPane.showMessageDialog(null, sb.toString());
        }

        public void byAuthor(){
            String checkAuthor = JOptionPane.showInputDialog("Please input an author: ");
            StringBuilder sb = new StringBuilder("Results:\n");
            boolean found = false;

            for (Book b : books){
                Pattern pattern = Pattern.compile(checkAuthor, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(b.getAuthor());
                boolean matchFound = matcher.find();

                if (matchFound){
                    found = true;
                    sb.append(b.toString()).append("\n");
                }
            }

            if (!found) {
                sb.append("No results.");
            }

            JOptionPane.showMessageDialog(null, sb.toString());
        }

        public void byName(){
            String checkName = JOptionPane.showInputDialog("Please input a name: ");
            StringBuilder sb = new StringBuilder("Results:\n");
            boolean found = false;

            for (Book b : books){
                Pattern pattern = Pattern.compile(checkName, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(b.getName());
                boolean matchFound = matcher.find();

                if (matchFound){
                    found = true;
                    sb.append(b.toString()).append("\n");
                }
            }

            if (!found) {
                sb.append("No results.");
            }

            JOptionPane.showMessageDialog(null, sb.toString());
        }

    public void displayBooks(){

        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No books in the system.");
            return;
        }

        StringBuilder sb = new StringBuilder("Book List:\n");
        for (Book b : books) {
            sb.append(b.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }


    public void displayUsers(){

        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No users in the system.");
            return;
        }

        StringBuilder sb = new StringBuilder("User List:\n");
        for (User u : users) {
            sb.append(u.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
