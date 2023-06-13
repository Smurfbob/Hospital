package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
public class SqlService {
    private static Connection _connection;
    public SqlService(Connection connection) {
        _connection = connection;
    }

    public static void createTables() {
        String sqlCreationScript = readStringFrom("createTablesHospitalDb.sql");
        try (Statement statement = _connection.createStatement();
             ResultSet rs = statement.executeQuery(sqlCreationScript)) {

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void mergeTestData(String tablename, List<String> tableColumns, List<Object> dataList) {
        String sqlInsertionScript = readStringFrom("mergeTestDataIntoTable.sql");

        List<String> formattedDataForSql = new ArrayList<String>();

        for (int i = 0; i < dataList.size(); i++) {
            formattedDataForSql.add(dataList.get(i).toString());
        }

        System.out.println(formattedDataForSql);

        String sqlQuery = String.format(sqlInsertionScript,
                //ab hier folgen die Variablen für die SQL-Query.
                tablename);
    }

    private static String readStringFrom(final String name) {
        return new BufferedReader(new InputStreamReader(Objects.requireNonNull(Thread.currentThread()
                .getContextClassLoader().getResourceAsStream(name)))).lines().collect(Collectors.joining("\n"));
    }
}
