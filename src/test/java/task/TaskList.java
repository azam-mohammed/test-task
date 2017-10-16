package task;

public class TaskList {

    /**
     * {
     "kind": "tasks#taskList",
     "id": string,
     "etag": string,
     "title": string,
     "updated": datetime,
     "selfLink": string
     }
     */
    private String kind;
    private String id;
    private String etag;
    private String title;
    private String updated;
    private String selfLink;

    @Override
    public String toString() {
        return "TaskList{" +
                "kind='" + kind + '\'' +
                ", id='" + id + '\'' +
                ", etag='" + etag + '\'' +
                ", title='" + title + '\'' +
                ", updated='" + updated + '\'' +
                ", selfLink='" + selfLink + '\'' +
                '}';
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }
}
