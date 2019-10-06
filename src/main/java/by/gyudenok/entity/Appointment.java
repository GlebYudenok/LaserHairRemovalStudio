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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appointment that = (Appointment) o;

        if (mId != null ? !mId.equals(that.mId) : that.mId != null) return false;
        if (sDateOfMeet.getTime().getYear() != ((Appointment) o).getsDateOfMeet().getTime().getYear()) return false;
        if (sDateOfMeet.getTime().getMonth() != ((Appointment) o).getsDateOfMeet().getTime().getMonth()) return false;
        if (sDateOfMeet.getTime().getDay() != ((Appointment) o).getsDateOfMeet().getTime().getDay()) return false;
        if (sDateOfMeet.getTime().getHours() != ((Appointment) o).getsDateOfMeet().getTime().getHours()) return false;
        if (sDateOfMeet.getTime().getMinutes() != ((Appointment) o).getsDateOfMeet().getTime().getMinutes()) return false;
        if (mUserId != null ? !mUserId.equals(that.mUserId) : that.mUserId != null) return false;
        if (mServiceId != null ? !mServiceId.equals(that.mServiceId) : that.mServiceId != null) return false;
        return mComplexId != null ? mComplexId.equals(that.mComplexId) : that.mComplexId == null;

    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (sDateOfMeet != null ? sDateOfMeet.hashCode() : 0);
        result = 31 * result + (mUserId != null ? mUserId.hashCode() : 0);
        result = 31 * result + (mServiceId != null ? mServiceId.hashCode() : 0);
        result = 31 * result + (mComplexId != null ? mComplexId.hashCode() : 0);
        return result;
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
