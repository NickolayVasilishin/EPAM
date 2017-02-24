package com.epam.javase02.t05;

import javax.swing.plaf.synth.SynthFormattedTextFieldUI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Nick on 07.03.2016.
 *
 * Model of a student group.
 */
public class StudentGroup {
    private String name;
    private Map<String, Student> students;
    SubjectTracker.Subject subject;

    public StudentGroup(String name, SubjectTracker.Subject subject) {
        this.name = name;
        this.subject = subject;
        students = new HashMap<>();
    }

    public StudentGroup add(Student student) {
        student.addSubject(subject);
        student.addGroup(this);
        students.put(student.getName(), student);
        return this;
    }

    public SubjectTracker.Subject getSubject() {
        return subject;
    }

    public Student remove(String name) {
        return students.remove(name);
    }

    public List<Student> getAll() {
        return new LinkedList<>(students.values());
    }

    public Student get(String name) {
        return students.get(name);
    }

    public boolean contains(String name) {
        return students.containsKey(name);
    }
}
