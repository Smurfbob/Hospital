package org.example.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class DatabaseUtils {
    public static <T> List<T> fetchAllElements(final String tableName, final Connection connection, Function<ResultSet,T> method) {
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
