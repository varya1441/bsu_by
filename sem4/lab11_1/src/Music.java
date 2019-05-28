import java.io.Serializable;

public class Music implements Serializable {
    String name;
    String author;
    private String extension;
    private int year;
    private int words;
    Music(){ }

    public String toXML() {
        return "\t<albom year=\""+year+"\" extension =\"" + extension + "\">\n" +
                "\t\t<name>"+name+"</name>\n" +
                "\t\t<author>"+author+"</author>\n" +
                "\t\t<words>"+words+"</words>\n" +
                "\t</albom>";
    }

    public String getName() {
        return name;
    }
    public String getExtension() {
        return extension;
    }

    public String getAuthor() {
        return author;
    }

    public int getWords() {
        return words;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setWords(int words) {
        this.words = words;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}