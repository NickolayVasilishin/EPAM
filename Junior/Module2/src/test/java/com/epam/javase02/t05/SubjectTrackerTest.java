package com.epam.javase02.t05;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by Nick on 07.03.2016.
 */
public class SubjectTrackerTest {

    StudentGroup mathGroup1;
    StudentGroup mathGroup2;

    StudentGroup physicsGroup1;

    Student student1;
    Student student2;
    Student student3;
    Student student4;
    Student student5;

    SubjectTracker subjectTracker;

    @Before
    public void setup() throws Exception {
        subjectTracker = new SubjectTracker();

        student1 = new Student("1");
        student2 = new Student("2");
        student3 = new Student("3");
        student4 = new Student("4");
        student5 = new Student("5");

        mathGroup1 = new StudentGroup("mathGroup1", SubjectTracker.Subject.Mathematics)
                .add(student1)
                .add(student2);
        mathGroup2 = new StudentGroup("mathGroup2", SubjectTracker.Subject.Mathematics)
                .add(student3);

        physicsGroup1 = new StudentGroup("physicsGroup1", SubjectTracker.Subject.Physics)
                .add(student5)
                .add(student4)
                .add(student1);

        student1.addGrade(SubjectTracker.Subject.Mathematics, 5)
                .addGrade(SubjectTracker.Subject.Mathematics, 4)
                .addGrade(SubjectTracker.Subject.Mathematics, 5)
                .addGrade(SubjectTracker.Subject.Physics, 6.0)
                .addGrade(SubjectTracker.Subject.Physics, 3.0)
                .addGrade(SubjectTracker.Subject.Physics, 4.5);
        student2.addGrade(SubjectTracker.Subject.Mathematics, 3)
                .addGrade(SubjectTracker.Subject.Mathematics, 4)
                .addGrade(SubjectTracker.Subject.Mathematics, 4);
        student3.addGrade(SubjectTracker.Subject.Mathematics, 5)
                .addGrade(SubjectTracker.Subject.Mathematics, 4)
                .addGrade(SubjectTracker.Subject.Mathematics, 4);
        student4.addGrade(SubjectTracker.Subject.Physics, 4.8)
                .addGrade(SubjectTracker.Subject.Physics, 8.9)
                .addGrade(SubjectTracker.Subject.Physics, 5.5);
        student5.addGrade(SubjectTracker.Subject.Physics, 4.0)
                .addGrade(SubjectTracker.Subject.Physics, 4.2)
                .addGrade(SubjectTracker.Subject.Physics, 9.8);

        subjectTracker.registerGroup(mathGroup1)
                .registerGroup(mathGroup2)
                .registerGroup(physicsGroup1);
    }

    @Test
    public void searchStudent() {
        assertEquals(subjectTracker.get("1"), student1);
    }

    @Test
    public void searchGroups() {
        LinkedList<StudentGroup> groups = new LinkedList<>();
        groups.add(mathGroup1);
        groups.add(physicsGroup1);
        assertEquals(subjectTracker.getGroups("1").get(0), groups.get(0));
        assertEquals(subjectTracker.getGroups("1").get(1), groups.get(1));
    }

    @Test
    public void compareGrades() {
        SubjectTracker.Subject subject = subjectTracker.get("1").getStudiedSubjects().get(0);
        Number maxGrade = subjectTracker.get("1").getGrades(subject).stream().max(new Comparator<Number>() {
            @Override
            public int compare(Number o1, Number o2) {
                double d1;
                double d2;
                d1 = o1.doubleValue();
                d2 = o2.doubleValue();

                return (int) (d1 - d2);
            }
        }).get();
        assertEquals(maxGrade.doubleValue(), 6.0, 0.1);

    }

}