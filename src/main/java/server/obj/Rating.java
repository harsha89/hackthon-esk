package server.obj;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Rating")
public class Rating {
    private String documentId;
    private String comment;
    private double rating;
    private String user;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
