package org.example.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public final class DatabaseUtils {

    private static final String SQL_URL = String.format("jdbc:postgresql://127.0.0.1:%s/hospital", System.getenv("port"));
    private static final String USER_NAME = Objects.nonNull(System.getenv("name")) ? System.getenv("name") : "postgres";
    private static final String PASSWORD = Objects.nonNull(System.getenv("password")) ? System.getenv("password") : "postgres";

    private DatabaseUtils() {
    }

    public static Connection requestDatabaseConnection() {
        try {
            return Objects.requireNonNull(DriverManager.getConnection(SQL_URL, USER_NAME, PASSWORD));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> fetchAllElements(final String tableName, final Connection connection, Function<ResultSet, T> method) {
        final String sqlStatement = String.format("SELECT * FROM %s;", tableName);
        final List<T> elements = new ArrayList<>();
        try {
            final Statement statement = connection.createStatement();
            statement.execute(sqlStatement);
            final ResultSet resultSet = statement.getResultSet();
            while (resultSet.next())
                elements.add(method.apply(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elements;
    }
}
