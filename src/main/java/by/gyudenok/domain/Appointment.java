package by.gyudenok.domain;

import java.util.Calendar;
import java.util.List;

public class Appointment {
    private Calendar sDateOfMeet = Calendar.getInstance();
    private String mUserId;
    private List<String> mServiceId;
    private String mComplexId;

    public Appointment() {

    }

    public Calendar getsDateOfMeet() {
        return sDateOfMeet;
    }

    public void setsDateOfMeet(Calendar sDateOfMeet) {
        this.sDateOfMeet = sDateOfMeet;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public List<String> getServiceId() {
        return mServiceId;
    }

    public void setServiceId(List<String> serviceId) {
        mServiceId = serviceId;
    }

    public String getComplexId() {
        return mComplexId;
    }

    public void setComplexId(String complexId) {
        mComplexId = complexId;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "sDateOfMeet=" + sDateOfMeet +
                ", mUserId='" + mUserId + '\'' +
                ", mServiceId='" + mServiceId + '\'' +
                ", mComplexId='" + mComplexId + '\'' +
                '}';
    }
}
