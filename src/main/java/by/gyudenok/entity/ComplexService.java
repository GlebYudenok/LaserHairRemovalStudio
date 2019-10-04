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
