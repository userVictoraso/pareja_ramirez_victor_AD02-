package AD02_Apdo2_ParejaVictor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Utils {

    private static Scanner s = new Scanner(System.in);
    static Connection oConn = null;

    public final static String selectDep = "SELECT e.APELLIDOS, d.NOMBRE FROM EMPLEADOS e JOIN DEPARTAMENTOS d ON e.ID_DEPARTAMENTO = d.ID WHERE d.NOMBRE = ?";
    public final static String percentIncrement = "UPDATE EMPLEADOS e JOIN DEPARTAMENTOS d ON e.ID_DEPARTAMENTO = d.ID SET SALARIO = SALARIO+(SALARIO*?/100) WHERE d.NOMBRE = ?";
    public final static String showPercentIncrement = "SELECT e.SALARIO, e.APELLIDOS, d.NOMBRE AS 'N.DEPART' FROM DEPARTAMENTOS d INNER JOIN EMPLEADOS e ON e.ID_DEPARTAMENTO=d.ID WHERE d.NOMBRE=? GROUP BY e.ID;";
    public final static String getEmployers = "SELECT d.NOMBRE, COUNT(e.ID) as NºEMPLEADOS, SUM(COMISION) as DINERO FROM DEPARTAMENTOS d JOIN EMPLEADOS e ON d.ID = e.ID_DEPARTAMENTO GROUP BY d.NOMBRE";

    public static String depName;
    //MOSTRAR MENÚ
    public static void showMenu() throws SQLException, ClassNotFoundException {
        boolean salir = true;
        try{
            oConn = AD02_Apdo1_ParejaVictor.Utils.Utils.conectar();
        } catch (SQLException | ClassNotFoundException e){
            e.getMessage();
        }finally {
            try{
                while(salir){
                    menuOptions();
                    switch (getOption()) {
                        case 1:
                            Querys.mostrarEmpleados(oConn.prepareStatement(selectDep), getName());
                            break;
                        case 2:
                            Querys.actualizarSalario(oConn.prepareStatement(percentIncrement), getPercent(), getName());
                            Querys.mostrarNuevoSalario(oConn.prepareStatement(showPercentIncrement), depName);
                            break;
                        case 3:
                            Querys.mostrarDepartamentos(oConn.prepareStatement(getEmployers));
                            break;
                        case 4:
                            salir = false;
                            System.out.println("Adiós.");
                            System.exit(0);
                            break;
                    }
                }
            } catch (SQLException ex){
                ex.getMessage();
            }
        }
    }

    public static int getOption(){
        int option = 0;
        try{
            String optionString = s.nextLine();
            option = Integer.valueOf(optionString);
            if(option < 1 || option > 4){
                System.out.println("Seleccione una opción válida.");
            }
        }catch (NumberFormatException e){
            System.out.println("Seleccione una opción válida.");
        }
        return option;
    }

    //MÉTODO PARA MOSTRAR LAS OPCIONES DEL MENÚ
    public static void menuOptions() {
        System.out.println("***********************************************************************************");
        System.out.println("¿Qué quiere hacer?");
        System.out.println("1.- Empleados que pertenecen a un departamento (Ingresar nombre de departamento)");
        System.out.println("2.- Incrementar el salario en porcentaje de los empleados de un departamento");
        System.out.println("3.- Obtener número de empleados de cada departamento y cantidad de dinero");
        System.out.println("4.- Salir");
        System.out.println("***********************************************************************************");
    }


    public static String getName(){
        System.out.println("Ingresa el nombre del departamento: ");
        depName = s.nextLine();
        return depName;
    }

    public static int getPercent(){
        System.out.println("Ingresa el porcentaje a incrementar: ");
        String percentString = s.nextLine();
        int percent = Integer.valueOf(percentString);
        return percent;
    }

}
