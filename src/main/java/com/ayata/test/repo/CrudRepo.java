package com.ayata.test.repo;

import com.ayata.test.model.Dept;
import com.ayata.test.model.Employee;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CrudRepo implements DatabaseRepo {

    private Connection connection;
    private Statement statement, inStatement;
    public CrudRepo() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void openConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restapi", "admin", "Javadev123!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee findById(int id){
        openConnection();
        Employee employee;
        try {
            statement = connection.createStatement();
            inStatement = connection.createStatement();
             ResultSet set = statement.executeQuery("SELECT * FROM employee WHERE id = "+id+";");
             set.next();
             ResultSet foreignSet = inStatement.executeQuery("SELECT * FROM dept WHERE deptid = "+set.getInt(3)+";");
             foreignSet.next();
             employee = new Employee(set.getInt(1), set.getString(2), new Dept(foreignSet.getInt(1), foreignSet.getString(2), foreignSet.getString(3)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
                inStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        closeConnection();
        return employee;
    };



    @Override
    public List<Employee> findAll(){
        openConnection();
        List<Employee> list = new ArrayList<Employee>();
        try {
            statement = connection.createStatement();
            inStatement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM employee;");
            while(set.next()){
                Employee employee = new Employee();
                employee.setId(set.getInt(1));
                employee.setName(set.getString(2));
                ResultSet foreignSet = inStatement.executeQuery("SELECT * FROM dept WHERE deptid = "+set.getInt(3)+";");
                foreignSet.next();
                employee.setDept(new Dept(foreignSet.getInt(1), foreignSet.getString(2), foreignSet.getString(3)));
                list.add(employee);
                foreignSet.close();
            }
            set.close();
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
                inStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        closeConnection();
        return list;
    };

    public Dept getDept(int id){
        openConnection();
        Dept dept = new Dept();
        try {
            inStatement = connection.createStatement();
            ResultSet set = inStatement.executeQuery("SELECT * FROM dept WHERE deptid="+id+";");
            if(set!=null) {
                set.next();
                dept.setDeptid(set.getInt(1));
                dept.setRole(set.getString(2));
                dept.setPosition(set.getString(3));
                set.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        closeConnection();
        return dept;
    }

    @Override
    public List<Employee> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Employee> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Employee entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Employee> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Employee> S save(S entity) {
        openConnection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO employee(id, name, deptid) VALUES("+entity.getId()+",'"+entity.getName()+"',"+entity.getDept().getDeptid()+")");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        closeConnection();
        return null;
    }

    @Override
    public <S extends Employee> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Employee> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Employee> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Employee> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Employee> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Employee getOne(Integer integer) {
        return null;
    }

    @Override
    public Employee getById(Integer integer) {
        return null;
    }

    @Override
    public Employee getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Employee> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Employee> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Employee> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Employee> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Employee> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Employee> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Employee, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
