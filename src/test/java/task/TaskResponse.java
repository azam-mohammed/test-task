package task;

public class TaskResponse {

    private String kind;
    private String id;
    private String etag;
    private String title;
    private String updated;
    private String selfLink;
    private String position;
    private String status;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskResponse{" +
                "kind='" + kind + '\'' +
                ", id='" + id + '\'' +
                ", etag='" + etag + '\'' +
                ", title='" + title + '\'' +
                ", updated=" + updated +
                ", selfLink='" + selfLink + '\'' +
                ", position='" + position + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
