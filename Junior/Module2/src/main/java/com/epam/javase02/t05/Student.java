package com.epam.javase02.t05;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Nick on 07.03.2016.
 */
public class Student {
    private String name;
    private Map<SubjectTracker.Subject, List<Number>> grades;
    private List<StudentGroup> groups;

    public Student(String name) {
        this.name = name;
        grades = new HashMap<>();
        groups = new LinkedList<>();
    }

    /**
     * Adds new grade to specified subject.
     * @param subject
     * @param grade
     * @return this instance
     * @throws IllegalArgumentException if the grade is out of bounds
     */
    public Student addGrade(SubjectTracker.Subject subject, Number grade) {
        //double type is rather large to store any grade.
        if(subject.getMaxRating().doubleValue() >= grade.doubleValue() && grade.doubleValue() >= 0) {
            grades.get(subject).add(grade);
        } else {
            throw new IllegalArgumentException("Grade is greater then maximum available grade.");
        }
        return this;
    }

    /**
     * Add subject to student studied subjects.
     * @param subject
     * @return this instance
     */
    public Student addSubject(SubjectTracker.Subject subject) {
        if(!grades.containsKey(subject))
            grades.put(subject, new LinkedList<>());
        return this;
    }

    /**
     * Add link to group student belongs.
     * @param group
     * @return this instance
     */
    public Student addGroup(StudentGroup group) {
        groups.add(group);
        return this;
    }

    public List<StudentGroup> getGroups() {
        return groups;
    }

    public List<Number> getGrades(SubjectTracker.Subject subject) {
        return grades.get(subject);
    }

    public List<SubjectTracker.Subject> getStudiedSubjects() {
        return new LinkedList<>(grades.keySet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return name != null ? name.equals(student.name) : student.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public String getName() {
        return name;
    }
}
