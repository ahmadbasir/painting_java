package painting;

import java.sql.*;

public class simpanDB {
    private static final String DB_USER = "root";
    private static final String DB_PASS = "cipo";
    private static final String DB_URL = "jdbc:mysql://localhost/pbo";
    private Connection conn;
    private Statement stmnt;

    public simpanDB() throws SQLException{
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        stmnt = conn.createStatement();
    }
    
    public void tambah(String nama_user, String nama_file, String objek_file,
            String path_file) throws SQLException {
        String sql1 = "INSERT INTO pengguna(user,file,path) VALUES("
                + "'"+ nama_user + "','" + nama_file + "','" + path_file + "')";
        String sql2 = "INSERT INTO file(nama,objek) VALUES("
                + "'" + nama_file + "','" + objek_file + "')";
        
        stmnt.executeUpdate(sql1);
        stmnt.executeUpdate(sql2);
    }
    
    public void buatTabel() throws SQLException{
        String sql1 = "CREATE TABLE pengguna(id INTEGER(4) NOT NULL AUTO_INCREMENT,"
                + "user VARCHAR(40), file VARCHAR(20), path VARCHAR(50), "
                + "PRIMARY KEY(id) )";
        String sql2 = "CREATE TABLE file(id INTEGER(4) NOT NULL AUTO_INCREMENT, "
                + "nama VARCHAR(20), objek VARCHAR(20), "
                + "PRIMARY KEY(id) )";

        stmnt.executeUpdate(sql1);
        stmnt.executeUpdate(sql2);
    }
    
    public void closeAll() throws SQLException {
        conn.close();
        stmnt.close();
    }
    
}
