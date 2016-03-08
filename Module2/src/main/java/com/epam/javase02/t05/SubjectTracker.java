package com.epam.javase02.t05;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nick on 07.03.2016.
 *
 * Class provides simple functionality to manage student groups and subjects.
 */
public class SubjectTracker {

    public enum Subject {
        Mathematics(5),
        Physics(10.0);

        private Number maxRating;

        Subject(Number n) {
            maxRating = n;
        }

        public Number getMaxRating() {
            return maxRating;
        }
    }

    private Map<Subject, StudentGroup> subjectMap;

    SubjectTracker() {
        subjectMap = new HashMap<>();
    }

    public SubjectTracker registerGroup(StudentGroup group) {
        subjectMap.put(group.getSubject(), group);
        return this;
    }

    public Student get(String name) {
        StudentGroup studentGroup = subjectMap.values().stream().filter(group -> group.contains(name)).findAny().get();
        return studentGroup.get(name);
    }

    public List<StudentGroup> getGroups(String name) {
        return get(name).getGroups();
    }
}
