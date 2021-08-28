package TcWeb.DataBase;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class DBUtilsDao {
    public Boolean insert(User user, String sql) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        int num = runner.update(sql, new Object[] {user.getUsername(), user.getPassword(), user.getEmail()});
        if (num > 0) {
            return true;
        }
        return false;
    }
    public Boolean insert_2(BookInformation user, String sql) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        int num = runner.update(sql, new Object[] {user.getAuthor(), user.getBookName(), user.getLocation()});
        if (num > 0) {
            return true;
        }
        return false;
    }
}
