package by.gyudenok.entity;

public enum Gender {
    MALE("male"),
    FEMALE("female"),
    OTHER("other");

    private String mGender;

    Gender(String gender) {
        mGender = gender;
    }

    public Gender getGenderById(int id) {
        return Gender.values()[id];
    }

    public int getId() {
        return ordinal();
    }

    @Override
    public String toString() {
        return "Gender{" +
                "mGender='" + mGender + '\'' +
                '}';
    }
}
