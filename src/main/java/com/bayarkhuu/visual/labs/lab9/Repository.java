package com.bayarkhuu.visual.labs.lab9;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Repository<T> {
    private final Class<T> clazz;

    public Repository(Class<T> clazz) {
        this.clazz = clazz;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/person_data", "root", "system0705@!");
    }

    public Integer save(T entity) {
        String q = "?,".repeat(entity.getClass().getDeclaredFields().length);
        q = q.substring(0, q.length() - 1);
        String query = "insert into " + entity.getClass().getSimpleName().toLowerCase() + " values (" + q + ")";

        try {
            PreparedStatement statement = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            for (Field field : entity.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(entity);
                statement.setObject(i, value);
                i++;
            }
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T findById(Integer id) {
        try {
            String query = "select * from " + clazz.getSimpleName() + " where id = " + id;
            ResultSet rs = getConnection().createStatement().executeQuery(query);

            if (rs.next()) {
                T t = clazz.getDeclaredConstructor().newInstance();
                for (Field field : t.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value = rs.getObject(field.getName());
                    if (value instanceof Date) {
                        field.set(t, ((Date) value).toLocalDate());
                    } else field.set(t, value);
                }
                return t;
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
