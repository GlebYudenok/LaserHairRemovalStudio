package by.gyudenok.entity;

import java.math.BigDecimal;
import java.util.List;

public class ComplexService {
    private String mId;
    private String mComplexName;
    private Gender mGender;
    private BigDecimal price;
    private List<String> mServiceIds;

    public ComplexService() {
    }

    public ComplexService(String id, String complexName, Gender gender, BigDecimal price) {
        mId = id;
        mComplexName = complexName;
        mGender = gender;
        this.price = price;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public List<String> getServiceIds() {
        return mServiceIds;
    }

    public void setServiceIds(List<String> serviceIds) {
        mServiceIds = serviceIds;
    }

    public String getComplexName() {
        return mComplexName;
    }

    public void setComplexName(String complexName) {
        mComplexName = complexName;
    }

    public Gender getGender() {
        return mGender;
    }

    public void setGender(Gender gender) {
        mGender = gender;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplexService service = (ComplexService) o;

        if (mId != null ? !mId.equals(service.mId) : service.mId != null) return false;
        if (mComplexName != null ? !mComplexName.equals(service.mComplexName) : service.mComplexName != null)
            return false;
        if (mGender != service.mGender) return false;
        if (price != null ? !price.equals(service.price) : service.price != null) return false;
        return mServiceIds != null ? mServiceIds.equals(service.mServiceIds) : service.mServiceIds == null;

    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mComplexName != null ? mComplexName.hashCode() : 0);
        result = 31 * result + (mGender != null ? mGender.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (mServiceIds != null ? mServiceIds.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ComplexService{" +
                "mId='" + mId + '\'' +
                ", mComplexName='" + mComplexName + '\'' +
                ", mGender=" + mGender +
                ", price=" + price +
                ", mServiceIds=" + mServiceIds +
                '}';
    }
}
