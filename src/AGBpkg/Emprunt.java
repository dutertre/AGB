package AGBpkg;
public class Emprunt {
    private Book book;
    private String user;
    private String date_emprunt;
    //définit si l'exemplaire est actuellement emprunté ou non
    //si la booléen est sur false, alors c'est une réservation
    private boolean emprunt;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate_emprunt() {
        return date_emprunt;
    }

    public void setDate_emprunt(String date_emprunt) {
        this.date_emprunt = date_emprunt;
    }

    public boolean isEmprunt() {
        return emprunt;
    }

    public void setEmprunt(boolean emprunt) {
        this.emprunt = emprunt;
    }

    public Emprunt (Book book, String user, String date, boolean emprunt)
    {
        this.book = book;
        this.user = user;
        this.date_emprunt = date;
        this.emprunt = emprunt;
    }
}