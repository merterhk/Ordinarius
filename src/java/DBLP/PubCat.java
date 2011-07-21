package DBLP;

public class PubCat {

    String publication;
    int point = 0;

    public PubCat(String publication) {
        this.publication = publication;
    }

    public void reset() {
        point = 0;
    }

    public void increasePoint() {
        point++;
    }

    public void decreasePoint() {
        point--;
    }
}
