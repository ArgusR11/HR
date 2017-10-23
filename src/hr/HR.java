/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class HR {

    public int insertarRegion(Region region){
    
        return 0;
    
    }
    
    public int borrarRegion(int regionId){
    
        return 0;
    
    }
    
    public int modificarRegion(int regionId,Region region){
    
        return 0;
    
    }
    
    public Region leerRegion(int regionId){
    
        return null;
    
    }
    public ArrayList<Region> leerRegions(){
    return null;
    }
    
    public int insertarCountry(Country country){
    
        return 0;
    
    }
    
    public int borrarCountry(String countryId) throws ExcepcionHR{
      String llamada="";
      int registrosAfectados=0;
        try {
            System.out.println("----- Acciones Previas");
            System.out.println("Carga del driver de Oracle en memoria");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Creación de una conexión a una BD Oracle llamada HR");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "kk");
            System.out.println("Creación de un objeto sentencia llamable asociado a dicha conexión");
            llamada = "call Borrar_Country(?)";
            CallableStatement sentenciaLlamable = conexion.prepareCall(llamada);
            
            System.out.println("----- Lanzamiento de la sentencia llamable");
            sentenciaLlamable.setString(1,countryId);
            
            System.out.println("Llamada final: " + sentenciaLlamable.toString());
            registrosAfectados=sentenciaLlamable.executeUpdate();
            System.out.println("Procedimiento Almacenado realizado");
            
            System.out.println("----- Cerrar la Conexión a la BD");
            sentenciaLlamable.close();
            conexion.close();
            
      } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR= new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),null,llamada);
           switch (ex.getErrorCode()) {
                case 2292: excepcionHR.setMensajeErrorUsuario("No se puede eliminar el identificador de pais, porque tiene locaclidades asociadas.");
                    break;
                default:    excepcionHR.setMensajeErrorUsuario("Error general del sistema, consulte con el administrador.");
                    break;
            }
           throw excepcionHR;
        }
        return registrosAfectados;
    
    }
    
    public int modificarCountry(String countryId,Country country){
    
        return 0;
    
    }
    
    public Country leerCountry(String countryId){
    
        return null;
    
    }
    public ArrayList<Country> leerCountry(){
    return null;
    }
    
    public int insertarLocation(Location location){
    
        return 0;
    
    }
    
    public int borrarLocation(int locationId){
    
        return 0;
    
    }
    
    public int modificarLocation(int locationId,Location location){
    
        return 0;
    
    }
    
    public Location leerLocation(int locationId){
    
        return null;
    
    }
    public ArrayList<Location> leerLocation(){
    return null;
    }
    
    public int insertarDepartment(Department department){
    
        return 0;
    
    }
    
    public int borrarDepartment(int departmentId){
    
    return 0;
    }
    
    public int modificarDepartment(int departmentId,Department department) throws ExcepcionHR{
    int registrosAfectados=0;
    String dml="";
        try {
            System.out.println("----- Acciones Previas");
            System.out.println("Carga del driver de Oracle en memoria");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Creación de una conexión a una BD Oracle llamada HR");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "kk");
            System.out.println("Creación de un objeto sentencia asociado a dicha conexión");
            Statement sentencia = conexion.createStatement();

            System.out.println("----- Lanzamiento de una sentencia DML");
            dml = "update DEPARTMENTS set "+
                    "DEPARTMENT_ID = "+ department.getDepartmentId() + ","
                    +"DEPARTMENT_NAME = '"+ department.getDepartmentName()+ "',"
                    +"MANAGER_ID = "+ department.getManager().getEmployeeId()+ ","
                    +"LOCATION_ID = "+ department.getLocation().getLocationId() + " "
                    +"where DEPARTMENT_ID = "+ departmentId;
            
            registrosAfectados = sentencia.executeUpdate(dml);
            System.out.println("Registros Afectados: " + registrosAfectados);
            
            System.out.println("----- Cerrar la Conexión a la BD");
            sentencia.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR= new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),null,dml);
           switch (ex.getErrorCode()) {
                case 2291:  excepcionHR.setMensajeErrorUsuario("La localidad elegida no existe o eljefe de departamento no es un empleado de la empresa.");
                            break;
                case 1407:  excepcionHR.setMensajeErrorUsuario("El identificador y el nombre de departamento son obligatorios.");
                    break;
                case 2292: excepcionHR.setMensajeErrorUsuario("No se puede modificar el identificador de departamentos ya que tiene empleados asociados o datos historicos asociados.");
                    break;
                case 1:     excepcionHR.setMensajeErrorUsuario("El indentificador de departamento ya existe.");
                    break;
                default:    excepcionHR.setMensajeErrorUsuario("Error general del sistema, consulte con el administrador.");
                    break;
            }
           throw excepcionHR;
        }
        return registrosAfectados;
        
    
    }
    
    public Region leerDepartment(int departmentId){
    
        return null;
    
    }
    public ArrayList<Department> leerDepartment(){
    return null;
    }
    
    public int insertarEmployee(Employee employee){
            String dml = null;
            int registrosAfectados=0;
        try {
            System.out.println("----- Acciones Previas");
            System.out.println("Carga del driver de Oracle en memoria");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Creación de una conexión a una BD Oracle llamada HR");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "kk");
            System.out.println("Creación de un objeto sentencia asociado a dicha conexión");
            Statement sentencia = conexion.createStatement();

            System.out.println("----- Lanzamiento de una sentencia DML");
            dml = "insert into EMPLOYEES (EMPLOYEE_ID,  FIRST_NAME, LAST_NAME, EMAIL,    PHONE_NUMBER,   HIRE_DATE,JOB_ID,COMMISSION_PCT,SALARY, MANAGER_ID, DEPARTMENT_ID) "
                                      + "values (?,?,?,?,?,?,?,?,?,?,?)";
             PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            System.out.println("----- Lanzamiento de una sentencia DML preparada");
            sentenciaPreparada.setInt(1,employee.getEmployeeId());
            sentenciaPreparada.setString(2,employee.getFirstNme());
            sentenciaPreparada.setString(3,employee.getLastName());
            sentenciaPreparada.setString(4,employee.getEmail());
            sentenciaPreparada.setString(5,employee.getPhoneNumber());
            sentenciaPreparada.setDate(6,employee.getHireDate());
            sentenciaPreparada.setString(7,employee.getJob().getJobId());
            sentenciaPreparada.setDouble(8,employee.getCommissionPct());
            sentenciaPreparada.setDouble(9,employee.getSalary());
            sentenciaPreparada.setInt(10,employee.getManager().getEmployeeId());
            sentenciaPreparada.setInt(11,employee.getDepartment().getDepartmentId());
            System.out.println("Sentencia final: " + sentenciaPreparada.toString());
            registrosAfectados = sentenciaPreparada.executeUpdate();
            System.out.println("Registros Afectados: " + registrosAfectados);
            
            System.out.println("----- Cerrar la Conexión a la BD");
            sentenciaPreparada.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
//            PropertyConfigurator.configure("maniobra\\log4j.properties");
//            Logger loggerERROR = LogManager.getLogger("ERROR");
//            loggerERROR.error(dml + " \n " + ex.getErrorCode() + " - " + ex.getMessage()); 

            switch (ex.getErrorCode()) {
                case 2291:  System.out.printf("%s \n   %s \n   %s \n   %s \n",
                                              "Error: se ha producido uno de los siguientes errores:",
                                                "El departamento seleccionado no existe",
                                                "El trabajo seleccionado no existe",
                                                "El jefe seleccionado no existe");
                            break;
                case 1400:  System.out.printf("%s \n   %s \n   %s \n   %s \n   %s \n",
                                              "Error: los siguientes datos son obligatorios:",
                                                "Email",
                                                "Fecha de Contratación",
                                                "Apellido",
                                                "Trabajo");
                            break;
                case 2290:  System.out.println("Error: el salario no puede ser 0");
                            break;
                case 1:     System.out.println("Error: el email no puede repetirse");
                            break;
                default:    System.out.println("Error en el sistema. Consulta con el administrador");
                            break;
            }
        }
        return registrosAfectados;
    
    }
    
    public int borrarEmployee(int employeeId){
    
        return 0;
    
    }
    
    public int modificarEmployee(int employeeId,Employee employee){
    
        return 0;
    
    }
    
    public Region leerEmployee(int employeeId){
    
        return null;
    
    }
    public ArrayList<Employee> leerEmployee(){
    return null;
    }
    
    public int insertarJob(Job job){
    
        return 0;
    
    }
    
    public int borrarJob(String jobId){
    
        return 0;
    
    }
    
    public int modificarJob(String jobId,Job job){
    
        return 0;
    
    }
    
    public Region leerJob(String jobId){
    
        return null;
    
    }
    public ArrayList<Job> leerJob(){
    return null;
    }
    
    
    public int insertarJobHistory(JobHistory jobHistory){
    
        return 0;
    
    }
    
    public int borrarJobHistory(int employeeId, Date startDate){
    
        return 0;
    
    }
    
    public int modificarJobHistory(int emplotyeeId,Date startDate,JobHistory jobHistory){
    
        return 0;
    
    }
    
    public Region leerJobHistory(int employeeId,Date startDate){
    
        return null;
    
    }
    public ArrayList<JobHistory> leerJobHistory(){
    return null;
    }
}
