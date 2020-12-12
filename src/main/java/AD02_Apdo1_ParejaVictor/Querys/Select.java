package AD02_Apdo1_ParejaVictor.Querys;

import java.sql.*;
import AD02_Apdo1_ParejaVictor.Utils.Utils;

public class Select {
    final static String T = " - ";

    public static void showSelect() {
        Connection oConn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            oConn = Utils.conectar();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                stmt = oConn.createStatement();
                rs = stmt.executeQuery(Utils.select);
                while (rs.next()) { // Hacer algo con los resultados...
                    System.out.println(rs.getString("APELLIDOS") + T +
                            rs.getString("OFICIO") + "  " + T +
                            rs.getString("FECHA_ALTA")+ "  " + T +
                            rs.getString("SALARIO")+ "  " + T +
                            rs.getString("COMISION")+ "  " + T +
                            rs.getString("ID_DEPARTAMENTO")+ "  " + T +
                            rs.getString("NOMBRE")+ "  " + T +
                            rs.getString("LOCALIZACION"));
                }
            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) {
                    } // ignore
                    rs = null;
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException sqlEx) {
                    } // ignore
                    stmt = null;
                }
            }
        }

    }


}
