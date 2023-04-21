/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet_sis;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;
import modelos.Alumno;
import modelos.controladorAlumno;

/**
 *
 * @author Reaper Borja
 */
@WebServlet(name = "servlet_alumno", urlPatterns = {"/servlet_alumno"})
public class servlet_alumno extends HttpServlet {
    
controladorAlumno calumno;
Alumno alumno=new Alumno();

    public servlet_alumno() throws NamingException, SystemException {
        this.calumno = new controladorAlumno();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servlet_alumno</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servlet_alumno at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        int id = 0;
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
        }

        List<Alumno> consultaGeneral = this.calumno.traerListaAlumnos();
        switch (accion) {
            case "con":
                request.setAttribute("alumnos", consultaGeneral);
                request.getRequestDispatcher("vistas/view_alumno.jsp").forward(
                        request, response);
                break;

            case "mod":
                Alumno alumno = this.calumno.traerAlumno(id);
                request.setAttribute("alumno", alumno);
                request.getRequestDispatcher("vistas/upd_alumno.jsp").forward(request, response);
                break;

            case "del":
                this.calumno.eliminarAlumno(id);
                consultaGeneral = this.calumno.traerListaAlumnos();
                request.setAttribute("alumnos", consultaGeneral);
                request.getRequestDispatcher("vistas/view_alumno.jsp").forward(
                        request, response);
                break;

            case "add":
                List<Alumno> consultaUltimos = this.calumno.consultaUltimosAlumnos(5, 1);
                request.setAttribute("alumnos", consultaUltimos);
                request.getRequestDispatcher("vistas/add_alumno.jsp").forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("txtnombre");
        String apellido = request.getParameter("txtapellido");
        String fechaStr = request.getParameter("txtfecha").trim().toString();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fechaStr, formato);
        Date date = java.sql.Date.valueOf(fecha);

        alumno.setNombre(nombre);
        alumno.setApellido(apellido);
        alumno.setFechanac(date);

        String btnAgregar = request.getParameter("btnAgregar");
        String btnUpdate = request.getParameter("btnUpdate");

        if (btnAgregar != null && !btnAgregar.isEmpty()) {
            //viene el dato de agregar
            this.calumno.crearAlumno(alumno);
        } else if (btnUpdate != null && !btnUpdate.isEmpty()) {
            int id = Integer.parseInt(request.getParameter("txtid"));
            alumno.setId(id);
            this.calumno.editarAlumno(alumno);
        }

        //processRequest(request, response);
        List<Alumno> consultaUltimos = this.calumno.consultaUltimosAlumnos(5, 1);
        request.setAttribute("alumnos", consultaUltimos);
        request.getRequestDispatcher("vistas/add_alumno.jsp").forward(request,
                response);

    }
}
