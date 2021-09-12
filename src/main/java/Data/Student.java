package Data;

public class Student {
    private String Name;
    private String SurName;
    private int Id;

    public Student(String name, String surName, int id) {
        Name = name;
        SurName = surName;
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
