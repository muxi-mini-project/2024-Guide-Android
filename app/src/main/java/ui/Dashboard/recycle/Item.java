package ui.Dashboard.recycle;

public class Item {

    private String name;
    private String time;
    private String style;
    private String level;

    public void setTextlevel(String textlevel) {
        this.level = textlevel;
    }

    public String getTextlevel() {
        return level;
    }

    public void setTextname(String textname) {
        this.name = textname;
    }

    public String getTextname() {
        return name;
    }

    public void setTextstyle(String textstyle) {
        this.style = textstyle;
    }

    public String getTextstyle() {
        return style;
    }

    public void setTexttime(String texttime) {
        this.time = texttime;
    }

    public String getTexttime() {
        return time;
    }
}
