package Clases;

import java.util.List;

public class Actualizaciones {
    public List<Empleado> EmpleadosReasignados;
    public List<Proyecto> ProyectosReasignados;
    public Departamento DepartamentoEliminado;

    public Actualizaciones(List<Empleado> EmpleadosReasignados, List<Proyecto> ProyectosReasignados, Departamento DepartamentoEliminado) {
        this.EmpleadosReasignados = EmpleadosReasignados;
        this.ProyectosReasignados = ProyectosReasignados;
        this.DepartamentoEliminado = DepartamentoEliminado;
    }
}
