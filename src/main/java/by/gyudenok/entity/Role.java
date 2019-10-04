package by.gyudenok.entity;

public enum Role {
    ADMIN("admin"),
    MASTER("master"),
    CLIENT("client");

    private String mName;

    Role(String name){
        mName = name;
    }

    public static Role getRoleById(int id) {
        return Role.values()[id];
    }

    public int getId() {
        return ordinal();
    }

    @Override
    public String toString() {
        return "Role{" +
                "mName='" + mName + '\'' +
                '}';
    }
}
