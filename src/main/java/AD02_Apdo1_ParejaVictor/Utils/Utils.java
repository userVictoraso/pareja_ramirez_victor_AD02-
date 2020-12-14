package AD02_Apdo1_ParejaVictor.Utils;

import AD02_Apdo1_ParejaVictor.Querys.Function;
import AD02_Apdo1_ParejaVictor.Querys.Procedure;
import AD02_Apdo1_ParejaVictor.Querys.Select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Utils {

    //CONSTANTES PARA CONECTAR A LA DB
    public static final String URL = "jdbc:mysql://10.242.27.110:3306/tareaonline02?serverTimezone=UTC";
    public static final String user = "uservictor";
    public static final String pass = "00572887aA$";
    //VARIABLE PARA ESTABLECER CONEXIÓN
    private static Connection oConn;
    //CONSTANTES DE CONSULTAS
    public static final String function = "SELECT cantidad_trabajadores_rango_salario(?,?) AS 'rango_salarios'";
    public static final String procedure = "CALL rango_comision(?,?,?)";
    public static final String select = "SELECT e.APELLIDOS, e.OFICIO, e.FECHA_ALTA, e.SALARIO, e.COMISION, e.ID_DEPARTAMENTO, d.NOMBRE, d.LOCALIZACION, e.ID from " +
            "DEPARTAMENTOS d inner join EMPLEADOS e on d.ID=e.ID_DEPARTAMENTO order by d.NOMBRE";
    //VARIABLES PARA OBTENER EL RANGO
    public static int[] interval = new int[2];
    private static Scanner s = new Scanner(System.in);

    //MÉTODO PARA ESTABLECER CONEXIÓN
    public static Connection conectar() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        oConn = DriverManager.getConnection(Utils.URL, Utils.user, Utils.pass);
        System.out.println("Conexión establecida. Cargando datos...");
        return oConn;
    }

    //MOSTRAR MENÚ
    public static void showMenu() throws SQLException, ClassNotFoundException {
        boolean salir = true;
        while (salir) {
            menuOptions();
            int option = s.nextInt();
            switch (option) {
                case 1:
                    Select.showSelect();
                    break;
                case 2:
                    Utils.setInterval();
                    Function.showFunction(Utils.interval[0], Utils.interval[1]);
                    break;
                case 3:
                    Utils.setInterval();
                    Procedure.showProcedure(Utils.interval[0], Utils.interval[1]);
                    break;
                case 4:
                    System.out.println("Adiós.");
                    System.exit(0);
                    break;
            }
        }

    }

    //MÉTODO PARA MOSTRAR LAS OPCIONES DEL MENÚ
    public static void menuOptions() {
        System.out.println("***********************************************************************************");
        System.out.println("¿Qué quiere hacer?");
        System.out.println("1.- Mostrar empleados (SELECT)");
        System.out.println("2.- Número de empleados con sueldo específico (FUNCTION)");
        System.out.println("3.- Número de empleados con comisión específica (PROCEDURE)");
        System.out.println("4.- Salir");
        System.out.println("***********************************************************************************");
    }

    //MÉTODO PARA ESTABLECER INTERVALO DE DINERO
    public static int[] setInterval() {
        System.out.println("Ingresa la cantidad mínima");
        int min = s.nextInt();
        interval[0] = min;
        System.out.println("Ingresa la cantidad máxima");
        int max = s.nextInt();
        interval[1] = max;
        return interval;
    }


}
