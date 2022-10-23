package com.example.diplom_project.data_base;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws SQLException, ClassNotFoundException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public String name1, name2, name3;
    public double rank1, rank2, rank3;

    public void inputInfo(String name, String mark5, String mark4, String mark3, String mark2, String passes, String fails,
                          String resp, String dign, String comm, String diploma) throws SQLException, ClassNotFoundException {
        String input = "INSERT INTO students_info" + "(" +
                Constants.NAME + "," +
                Constants.MARK5 + "," +
                Constants.MARK4 + "," +
                Constants.MARK3 + "," +
                Constants.MARK2 + "," +
                Constants.PASSES + "," +
                Constants.FAILS + "," +
                Constants.RESP + "," +
                Constants.DIGN + "," +
                Constants.COMM + "," +
                Constants.DIPLOMA + ")" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?);";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(input);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, mark5);
        preparedStatement.setString(3, mark4);
        preparedStatement.setString(4, mark3);
        preparedStatement.setString(5, mark2);
        preparedStatement.setString(6, passes);
        preparedStatement.setString(7, fails);
        preparedStatement.setString(8, resp);
        preparedStatement.setString(9, dign);
        preparedStatement.setString(10, comm);
        preparedStatement.setString(11, diploma);
        preparedStatement.executeUpdate();
    }

    public void deleteLine() throws SQLException, ClassNotFoundException {
        String delete_line = "DELETE FROM students_info ORDER BY id DESC LIMIT 1;";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete_line);
        preparedStatement.executeUpdate();
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        String delete_all = "TRUNCATE students_info;";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete_all);
        preparedStatement.executeUpdate();
    }

    public int countRows() throws SQLException, ClassNotFoundException {
        int counter = 0;
        Statement statement = getDbConnection().createStatement();
        String count = "SELECT count(*) FROM students_info;";
        ResultSet resultSet = statement.executeQuery(count);
        while (resultSet.next()) {
            counter = resultSet.getInt(1);
        }
        return counter;
    }

    double[] resultRank() throws SQLException, ClassNotFoundException {
        int size = countRows();
        DataBaseHandler obj = new DataBaseHandler();
        Double[] r1 = obj.getRanks1("mark5");
        Double[] r2 = obj.getRanks2("mark4");
        Double[] r3 = obj.getRanks2("mark3");
        Double[] r4 = obj.getRanks2("mark2");
        Double[] r5 = obj.getRanks1("passes");
        Double[] r6 = obj.getRanks2("fails");
        Double[] r7 = obj.convertStringsToInteger("resp");
        Double[] r8 = obj.convertStringsToInteger("dign");
        Double[] r9 = obj.convertStringsToInteger("comm");
        Double[] r10 = obj.getRanks1("diploma");
        Double[][] matrix = new Double[10][size];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < size; j++) {
                matrix[0][j] = r1[j];
                matrix[1][j] = r2[j];
                matrix[2][j] = r3[j];
                matrix[3][j] = r4[j];
                matrix[4][j] = r5[j];
                matrix[5][j] = r6[j];
                matrix[6][j] = r7[j];
                matrix[7][j] = r8[j];
                matrix[8][j] = r9[j];
                matrix[9][j] = r10[j];
            }
        }

        double[] sum = new double[size];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < size; j++) {
                sum[j] += matrix[i][j];
            }
        }
        return sum;
    }

    public void finalRanks() throws SQLException, ClassNotFoundException {
        int size = countRows();
        Statement statement = getDbConnection().createStatement();
        String sql = "SELECT * FROM students_info;";
        ResultSet resultSet = statement.executeQuery(sql);
        List<String> source = new ArrayList<>();
        double[] ranks = resultRank();
        while (resultSet.next()) {
            String data = resultSet.getString("name");
            source.add(data);
        }
        String[] names = source.toArray(new String[0]);
        double tempNum;
        String tempName;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (ranks[i] > ranks[j]) {
                    tempNum = ranks[i];
                    tempName = names[i];
                    ranks[i] = ranks[j];
                    names[i] = names[j];
                    ranks[j] = tempNum;
                    names[j] = tempName;
                }
            }
        }
        name1 = names[0];
        name2 = names[1];
        name3 = names[2];
        rank1 = ranks[0];
        rank2 = ranks[1];
        rank3 = ranks[2];
    }

    public Double[] getRanks1(String column_name) throws SQLException, ClassNotFoundException {
        Statement statement = getDbConnection().createStatement();
        String sql = "SELECT * FROM students_info;";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Integer> source = new ArrayList<>();
        List<Double> ranks = new ArrayList<>();
        int size = countRows();
        while (resultSet.next()) {
            int data = resultSet.getInt(column_name);
            source.add(data);
            ranks.add((double) 0);
            assignRanks1(source, ranks, size);
        }
        return ranks.toArray(new Double[0]);
    }

    public Double[] getRanks2(String column_name) throws SQLException, ClassNotFoundException {
        Statement statement = getDbConnection().createStatement();
        String sql = "SELECT * FROM students_info;";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Integer> source = new ArrayList<>();
        List<Double> ranks = new ArrayList<>();
        int size = countRows();
        while (resultSet.next()) {
            int data = resultSet.getInt(column_name);
            source.add(data);
            ranks.add((double) 0);
            assignRanks2(source, ranks, size);
        }
        return ranks.toArray(new Double[0]);
    }

    public Double[] convertStringsToInteger(String column_name) throws SQLException, ClassNotFoundException {
        Statement statement = getDbConnection().createStatement();
        String sql = "SELECT * FROM students_info;";
        ResultSet resultSet = statement.executeQuery(sql);
        List<String> S_source = new ArrayList<>();
        int size = countRows();
        while (resultSet.next()) {
            String data = resultSet.getString(column_name);
            S_source.add(data);
        }
        List<Integer> I_source = new ArrayList<>();
        List<Double> ranks = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            switch (S_source.get(i)) {
                case "определенно, да", "Определенно, да" -> {
                    int a = 1;
                    I_source.add(a);
                }
                case "скорее да, чем нет", "Скорее да, чем нет" -> {
                    int b = 2;
                    I_source.add(b);
                }
                case "скорее нет, чем да", "Скорее нет, чем да" -> {
                    int c = 3;
                    I_source.add(c);
                }
                case "определенно, нет", "Определенно, нет" -> {
                    int d = 4;
                    I_source.add(d);
                }
            }
            ranks.add((double) 0);
        }
        assignRanks2(I_source, ranks, size);
        return ranks.toArray(new Double[0]);
    }

    public void assignRanks1(List<Integer> src, List<Double> rnk, int size) {
        ArrayList<Integer> sorted = new ArrayList<>(src);
        sorted.sort(null);
        for (int i = 0; i < src.size(); i++) {
            Integer next = src.get(i);
            int minIndex = sorted.indexOf(next);
            int maxIndex = sorted.lastIndexOf(next);
            double rank = -(-size + minIndex + (maxIndex - minIndex) / 2.);
            rnk.set(i, rank);
        }
    }

    public void assignRanks2(List<Integer> src, List<Double> rnk, int size) {
        ArrayList<Integer> sorted = new ArrayList<>(src);
        sorted.sort(null);
        for (int i = 0; i < src.size(); i++) {
            Integer next = src.get(i);
            int minIndex = sorted.indexOf(next);
            int maxIndex = sorted.lastIndexOf(next);
            double rank = 1 + minIndex + (maxIndex - minIndex) / 2.;
            rnk.set(i, rank);
        }
    }
}