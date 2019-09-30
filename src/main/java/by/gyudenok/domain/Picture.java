package by.gyudenok.domain;

public class Picture {
    private String mId;
    private String mLink;

    public Picture() {
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "mId='" + mId + '\'' +
                ", mLink='" + mLink + '\'' +
                '}';
    }
}
