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
    public String toString() {
        return "Service{" +
                "mId='" + mId + '\'' +
                ", mZoneName='" + mZoneName + '\'' +
                ", mPrice=" + mPrice +
                '}';
    }
}
