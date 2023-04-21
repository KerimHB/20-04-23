/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

/**
 *
 * @author Reaper Borja
 */
import controladores.exceptions.RollbackFailureException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.transaction.SystemException;
import modelos.Alumno;

public class ControladoraPersistencia {
    //objeto de la clase AlumnoJpaController

    AlumnoJpaController alumnoJpa;

    //constructor
    public ControladoraPersistencia() throws NamingException, SystemException {
        this.alumnoJpa = new AlumnoJpaController();
    }

    //Sirve para agregar nuevos alumnos
    public void crearAlumno(Alumno alumno) {
        try {
            this.alumnoJpa.create(alumno);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex
        
    

    );
 }
 }

 //consulta general de alumnos
 public List<Alumno> traerListaAlumnos() {
        return this.alumnoJpa.findAlumnoEntities();
    }

    //consulta un alumno en especifico
    public Alumno traerAlumno(int id) {
        return this.alumnoJpa.findAlumno(id);
    }

    //actualiza la información de un alumno
    public void editarAlumno(Alumno alumno) {
        try {
            this.alumnoJpa.edit(alumno);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex
         );
 } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex
        
    

    );
 }
 }

 //elimina un alumno en especifico
 public void eliminarAlumno(int id) {
        try {
            this.alumnoJpa.destroy(id);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex


    );
 }
 }

 //consulta los ultimos 4 alumnos agregados
 //i=cantidad de alumnos, j=desde donde comienza a contar
 public List<Alumno> consultaUltimos(int i, int j) {
        int total = this.alumnoJpa.getAlumnoCount() - 4;
        return this.alumnoJpa.findAlumnoEntities(i, total);
    }
}
