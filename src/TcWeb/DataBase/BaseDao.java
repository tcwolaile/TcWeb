package TcWeb.DataBase;

import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
    public static Object query(String sql, ResultSetHandler<?> rsh, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; params != null && i < params.length; i++)
            {
                pstmt.setObject(i + 1, params[i]);
            }
            rs = pstmt.executeQuery();
            Object obj = rsh.handle(rs);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs, pstmt, conn);
        }
        return rs;
    }
}