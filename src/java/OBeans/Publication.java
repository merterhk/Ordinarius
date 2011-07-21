package OBeans;

public class Publication {

    private String title;
    private String link;
    private String year;
    private String coauthors;
    private String journal;
    private String booktitle;

    public Publication() {
    }

    public Publication(String title, String year, String coauthors, String journal, String booktitle) {
        this.title = title;
        this.year = year;
        this.coauthors = coauthors;
        this.journal = journal;
        this.booktitle = booktitle;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the coauthors
     */
    public String getCoauthors() {
        return coauthors;
    }

    public String getCoauthorsAsLink() {
        String link = "";
        for (String s : coauthors.split(",")) {
            link += "<a class=\"link\" onclick=\"searchThis(\\'" + s.trim() + "\\')\">" + s.trim() + "</a>&nbsp;&nbsp;";
        }
        return link;
    }

    /**
     * @param coauthors the coauthors to set
     */
    public void setCoauthors(String coauthors) {
        this.coauthors = coauthors;
    }

    /**
     * @return the journal
     */
    public String getJournal() {
        return journal==null?"":journal;
    }

    /**
     * @param journal the journal to set
     */
    public void setJournal(String journal) {
        this.journal = journal;
    }

    /**
     * @return the booktitle
     */
    public String getBooktitle() {
        return booktitle==null?"":booktitle;
    }

    /**
     * @param booktitle the booktitle to set
     */
    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    
}
