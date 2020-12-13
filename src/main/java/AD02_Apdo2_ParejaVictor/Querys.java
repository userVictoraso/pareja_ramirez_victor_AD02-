package AD02_Apdo2_ParejaVictor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Querys {
    static ResultSet rs = null;

    //Mostrar empleados que pertenecen a un departamento, indicada por su nombre
    public static void mostrarEmpleados(PreparedStatement stmtSelect, String depName) throws SQLException {
        try {
            stmtSelect.setString(1, depName);
            //stmtInsert.executeQuery();
            stmtSelect.getResultSet();
            rs = stmtSelect.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("APELLIDOS") + " - " + rs.getString("NOMBRE"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public static void actualizarSalario(PreparedStatement stmtUpdate, int percent, String depName) throws SQLException {
        try {
            stmtUpdate.setInt(1, percent);
            stmtUpdate.setString(2, depName);
            stmtUpdate.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public static void mostrarNuevoSalario(PreparedStatement stmtSelect, String depName) {
        try {
            stmtSelect.setString(1, depName);
            stmtSelect.getResultSet();
            rs = stmtSelect.executeQuery();
            System.out.println("Salario de los empleados del departamento " + depName + " actualizado.");
            while (rs.next()) {
                System.out.println(rs.getString("SALARIO") + " - " + rs.getString("APELLIDOS") + " - " + rs.getString("N.DEPART"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public static void mostrarDepartamentos(PreparedStatement stmtSelect) throws SQLException {
        try {
            rs = stmtSelect.executeQuery();
            System.out.println("N.DEP - N.EMP - DIN");
            while (rs.next()) {
                System.out.println(rs.getString("NOMBRE") + " - " + rs.getString("NºEMPLEADOS") + " - " + rs.getString("DINERO"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }
}
