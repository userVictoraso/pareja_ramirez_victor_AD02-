package AD02_Apdo1_ParejaVictor.Querys;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import AD02_Apdo1_ParejaVictor.Utils.Utils;


public class Procedure {
    static Connection oConn = null;
    public static void showProcedure(int minComision, int maxComision) throws SQLException{
        try{
            oConn= Utils.conectar();
        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        } finally {
            CallableStatement cs = oConn.prepareCall(Utils.procedure);
            cs.setLong(1, minComision);
            cs.setLong(2, maxComision);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.executeUpdate();
            int empleados = cs.getInt(3);
            System.out.println("Número de empleados que cobren entre " + minComision + " y " + maxComision + ": " + empleados);
            cs.close();
        }
    }

}
