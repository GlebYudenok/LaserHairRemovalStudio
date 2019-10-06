package by.gyudenok.entity;

import java.math.BigDecimal;

public class Service {
    private String mId;
    private String mZoneName;
    private BigDecimal mPrice;

    public Service() {
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getZoneName() {
        return mZoneName;
    }

    public void setZoneName(String zoneName) {
        mZoneName = zoneName;
    }

    public BigDecimal getPrice() {
        return mPrice;
    }

    public void setPrice(BigDecimal price) {
        mPrice = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        if (mId != null ? !mId.equals(service.mId) : service.mId != null) return false;
        if (mZoneName != null ? !mZoneName.equals(service.mZoneName) : service.mZoneName != null) return false;
        return mPrice != null ? mPrice.equals(service.mPrice) : service.mPrice == null;

    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mZoneName != null ? mZoneName.hashCode() : 0);
        result = 31 * result + (mPrice != null ? mPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Service{" +
                "mId='" + mId + '\'' +
                ", mZoneName='" + mZoneName + '\'' +
                ", mPrice=" + mPrice +
                '}';
    }
}
