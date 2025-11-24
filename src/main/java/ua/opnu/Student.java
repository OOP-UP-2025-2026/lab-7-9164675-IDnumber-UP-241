package ua.opnu;

// Student.java
class Student {
    private String name;
    private String group;
    private int[] marks;

    // Конструктор
    public Student(String name, String group, int[] marks) {
        this.name = name;
        this.group = group;
        this.marks = marks;
    }

    // Гетери (getters)
    public String getName() { return name; }
    public String getGroup() { return group; }
    public int[] getMarks() { return marks; }

    @Override
    public String toString() {
        return name + " (" + group + ")";
    }
}