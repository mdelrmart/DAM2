import java.util.Date;

public class Familiar {
    String NSSEmpleado;
    String nss;
    String nombre;
    String apellido1;
    String apellido2;
    Date fechaNacimiento;
    String parentesco;
    char sexo;

    public Familiar(String NSSEmpleado, String nss, String nombre, String apellido1, String apellido2, Date fechaNacimiento, String parentesco, char sexo) {
        this.NSSEmpleado = NSSEmpleado;
        this.nss = nss;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.parentesco = parentesco;
        this.sexo = sexo;
    }
}
