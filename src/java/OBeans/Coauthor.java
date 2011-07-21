package OBeans;

public class Coauthor {

    private String urlpt;
    private String name;
    private int count;

    public Coauthor(String urlpt, String name, int count) {
        this.urlpt = urlpt;
        this.name = name;
        this.count = count;
    }

    /**
     * @return the urlpt
     */
    public String getUrlpt() {
        return urlpt;
    }

    /**
     * @param urlpt the urlpt to set
     */
    public void setUrlpt(String urlpt) {
        this.urlpt = urlpt;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    
}
