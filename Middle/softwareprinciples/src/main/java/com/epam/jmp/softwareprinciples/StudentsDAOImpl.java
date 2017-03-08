package com.epam.jmp.softwareprinciples;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAOImpl implements StudentsDAO {
    public static final String SELECT_ALL_FROM_STUDENTS_WHERE_ID = "SELECT * FROM STUDENTS WHERE ID = ?";
    public static final String UPDATE_STUDENTS_NAME_AGE_WHERE_ID = "UPDATE STUDENTS SET NAME = ?, AGE = ? WHERE ID = ?";
    public static final String SELECT_ALL_FROM_STUDENTS_WHERE_AGE_BETWEEN = "SELECT * FROM STUDENTS WHERE AGE BETWEEN ? AND ?";
    public static final String SELECT_ALL_FROM_STUDENTS = "SELECT * FROM STUDENTS";
    public static final String ID_COLUMN_LABEL = "ID";
    public static final String NAME_COLUMN_LABEL = "NAME";
    public static final String AGE_COLUMN_LABEL = "AGE";


    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (QueryManager manager = new QueryManager()) {
            ResultSet resultSet = manager.setQuery(SELECT_ALL_FROM_STUDENTS).getResultSet();
            while (resultSet.next()) {
                Student student = getStudent(resultSet);
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public Student getStudent(long id) {
        Student student = null;
        try (QueryManager manager = new QueryManager()) {
            PreparedStatement preparedStatement = manager.setQuery(SELECT_ALL_FROM_STUDENTS_WHERE_ID).getPreparedStatement();
            preparedStatement.setLong(1, id);
            ResultSet resultSet = manager.getResultSet();
            while (resultSet.next()) {
                //Assuming that ID is unique
                student = getStudent(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }


    @Override
    public void saveStudent(Student student) {
        try (QueryManager manager = new QueryManager()) {
            PreparedStatement preparedStatement = manager
                    .setQuery(UPDATE_STUDENTS_NAME_AGE_WHERE_ID)
                    .getPreparedStatement();
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setLong(3, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> getStudentsByAge(int from, int to) {
        List<Student> students = new ArrayList<>();
        try (QueryManager manager = new QueryManager()) {
            PreparedStatement preparedStatement = manager
                    .setQuery(SELECT_ALL_FROM_STUDENTS_WHERE_AGE_BETWEEN)
                    .getPreparedStatement();
            preparedStatement.setInt(1, from);
            preparedStatement.setInt(2, to);
            ResultSet resultSet = manager.getResultSet();
            while (resultSet.next()) {
                Student student = getStudent(resultSet);
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    private Student getStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong(ID_COLUMN_LABEL));
        student.setName(resultSet.getString(NAME_COLUMN_LABEL));
        student.setAge(resultSet.getInt(AGE_COLUMN_LABEL));
        return student;
    }
}
