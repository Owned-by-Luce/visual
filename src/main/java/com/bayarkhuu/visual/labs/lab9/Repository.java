package com.bayarkhuu.visual.labs.lab9;

import com.bayarkhuu.visual.home.home8.annotation.ForeignKey;
import com.bayarkhuu.visual.home.home8.annotation.Json;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Repository<T> {
    private static final String schemaName = "auto_part";
    private final Class<T> clazz;
    private final Gson gson = new Gson();

    public Repository(Class<T> clazz) {
        this.clazz = clazz;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + schemaName, "root", "system0705@!");
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

                Object value;
                if (field.isAnnotationPresent(ForeignKey.class)) {
                    Field id = Arrays.stream(field.get(entity).getClass().getDeclaredFields()).filter(f -> f.getName().equals("id")).findFirst().orElse(null);
                    if (id == null) throw new IllegalAccessException("id field байхгүй байна");
                    id.setAccessible(true);
                    value = id.get(field.get(entity));
                } else if (field.isAnnotationPresent(Json.class)) {
                    value = gson.toJson(field.get(entity));
                } else value = field.get(entity);
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
            String query = "select * from " + clazz.getSimpleName().toLowerCase() + " where id = " + id;
            ResultSet rs = getConnection().createStatement().executeQuery(query);

            if (rs.next()) {
                return createObject(rs, clazz);
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <Q> Q findById(Integer id, Class<Q> clazzQ) {
        try {
            String query = "select * from " + clazzQ.getSimpleName().toLowerCase() + " where id = " + id;
            ResultSet rs = getConnection().createStatement().executeQuery(query);

            if (rs.next()) {
                return createObject(rs, clazzQ);
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> findAllByCriteria(Predicate<T> criteria) {
        try {
            String query = "select * from " + clazz.getSimpleName().toLowerCase();
            ResultSet rs = getConnection().createStatement().executeQuery(query);

            List<T> list = new ArrayList<>();

            while (rs.next()) {
                list.add(createObject(rs, clazz));
            }

            if (criteria == null) return list;
            return list.stream().filter(criteria).collect(Collectors.toList());
        } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();

    }

    private <Q> Q createObject(ResultSet rs, Class<Q> classType) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        Q t = classType.getDeclaredConstructor().newInstance();
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            Object value = rs.getObject(field.getName());
            if (field.isAnnotationPresent(ForeignKey.class)) {
                value = findById(Integer.valueOf(String.valueOf(value)), field.getType());
            } else if (field.isAnnotationPresent(Json.class)) {
                value = gson.fromJson(String.valueOf(value), field.getType());
            }

            if (value instanceof Date) {
                field.set(t, ((Date) value).toLocalDate());
            } else field.set(t, value);
        }
        return t;
    }
}
