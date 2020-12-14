package AD02_Apdo1_ParejaVictor.Querys;

import java.sql.*;
import AD02_Apdo1_ParejaVictor.Utils.Utils;

public class Function {

    static Connection oConn = null;
    private static ResultSet rs = null;

    public static void showFunction(int minSalary, int maxSalary) throws SQLException, ClassNotFoundException {
        try{
            oConn = Utils.conectar();
        } catch (SQLException e){

        }finally {
            PreparedStatement ps = oConn.prepareStatement(Utils.function);
            ps.setLong(1, minSalary);
            ps.setLong(2, maxSalary);
            System.out.printf("Número de empleados cuya comisión está entre " + minSalary + " y " + maxSalary + ": ");
            ps.getResultSet();
            rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("rango_salarios"));
            }
        }
    }
}
