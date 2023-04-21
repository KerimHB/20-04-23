/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Reaper Borja
 */
import controladores.ControladoraPersistencia;
import java.util.List;
import javax.naming.NamingException;
import javax.transaction.SystemException;

public class controladorAlumno {

    ControladoraPersistencia cont_persis;

    public controladorAlumno()
            throws NamingException, SystemException {
        this.cont_persis = new ControladoraPersistencia();
    }

    public void crearAlumno(Alumno alumno) {
        this.cont_persis.crearAlumno(alumno);
    }

    public List<Alumno> traerListaAlumnos() {
        return this.cont_persis.traerListaAlumnos();
    }

    public Alumno traerAlumno(int id) {
        return this.cont_persis.traerAlumno(id);
    }

    public List<Alumno> consultaUltimosAlumnos(int maxResults, int firstResult) {
        return this.cont_persis.consultaUltimos(5, 1);
    }

    public void editarAlumno(Alumno alumno) {
        this.cont_persis.editarAlumno(alumno);
    }

    public void eliminarAlumno(int id) {
        this.cont_persis.eliminarAlumno(id);
    }
}
