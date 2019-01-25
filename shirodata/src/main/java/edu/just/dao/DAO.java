package edu.just.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DAO {

    public String getPassword(String userName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "select password from user where name = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userName);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<String> listRoles(String userName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Set<String> set = new HashSet<>();

        try {
            connection = JDBCUtils.getConnection();
            String sql = "select r.name from user u " +
                    "left join user_role ur on u.id = ur.uid " +
                    "left join Role r on r.id = ur.rid " +
                    "where u.name = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userName);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                set.add(resultSet.getString(1));
            }
            return set;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<String> listPermissions(String userName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Set<String> set = new HashSet<>();

        try {
            connection = JDBCUtils.getConnection();
            String sql = "select p.name from user u " +
                    "left join user_role ru on u.id = ru.uid " +
                    "left join role r on r.id = ru.rid " +
                    "left join role_permission rp on r.id = rp.rid " +
                    "left join permission p on p.id = rp.pid" +
                    " where u.name = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userName);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String string = resultSet.getString(1);
                set.add(string);
            }
            return set;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
