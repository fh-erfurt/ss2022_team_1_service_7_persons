import java.util.ArrayList;
import java.util.Date;

class Entry {
    private String data;
    private SearchType type;
    private Date date;

    public Entry(String data, SearchType type) {
        this.data = data;
        this.type = type;
        this.date = new Date();
    }

    public String getData() { return data; }
    public void setData(String name) { this.data = name; }

    public SearchType getType() { return type; }
    public void setType(SearchType type) { this.type = type; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}

public class SearchHistory {
    private final ArrayList<Entry> entries;

    public SearchHistory() {
        this.entries = new ArrayList<>();
    }

    public void addEntry(String data, SearchType type) {
        this.entries.add(new Entry(data, type));
    }

    public Entry[] getEntries() { return entries.toArray(new Entry[0]); }
}
