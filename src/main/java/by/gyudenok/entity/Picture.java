package by.gyudenok.entity;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        if (mId != null ? !mId.equals(picture.mId) : picture.mId != null) return false;
        return mLink != null ? mLink.equals(picture.mLink) : picture.mLink == null;

    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mLink != null ? mLink.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "mId='" + mId + '\'' +
                ", mLink='" + mLink + '\'' +
                '}';
    }
}
