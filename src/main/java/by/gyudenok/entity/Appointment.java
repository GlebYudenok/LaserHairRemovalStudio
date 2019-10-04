package by.gyudenok.entity;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

public class Appointment {
    private String mId;
    private Calendar sDateOfMeet;
    private String mUserId;
    private String mServiceId;
    private String mComplexId;

    public Appointment() {

    }

    public Appointment(String id, Calendar dateOfMeet, String userId, String serviceId, String complexId) {
        mId = id;
        sDateOfMeet = dateOfMeet;
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);
        sDateOfMeet.setTimeZone(timeZone);
        mUserId = userId;
        mServiceId = serviceId;
        mComplexId = complexId;
    }


    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
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

    public String getServiceId() {
        return mServiceId;
    }

    public void setServiceId(String serviceId) {
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
                "mId='" + mId + '\'' +
                ", sDateOfMeet=" + sDateOfMeet +
                ", mUserId='" + mUserId + '\'' +
                ", mServiceId=" + mServiceId +
                ", mComplexId='" + mComplexId + '\'' +
                '}';
    }
}
