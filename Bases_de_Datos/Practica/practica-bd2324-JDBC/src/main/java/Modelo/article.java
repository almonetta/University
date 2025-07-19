package Modelo;

import java.util.Date;

public class article {
    private String DOI;
    private String title;
    private Date publication_date;
    private String url;

    private int num_citations;
    private long journal_id;

    public article(String DOI, String title, Date publication_date, String url, int num_citations, long journal_id) {
        this.DOI = DOI;
        this.title = title;
        this.publication_date = publication_date;
        this.url = url;
        this.num_citations = num_citations;
        this.journal_id = journal_id;
    }

    public article (String title, Date publication_date){
        this.title= title;
        this.publication_date = publication_date;
    }

    public String getDOI() {
        return DOI;
    }

    public void setDOI(String DOI) {
        this.DOI = DOI;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Date publication_date) {
        this.publication_date = publication_date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getJournal_id() {
        return journal_id;
    }

    public void setJournal_id(long journal_id) {
        this.journal_id = journal_id;
    }
}
