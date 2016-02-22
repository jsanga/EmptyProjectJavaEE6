/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.reportes;

import com.jscompany.ebsystem.managedbeans.session.ServletSession;
import com.jscompany.ebsystem.util.HiberUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;

/**
 *
 * @author origami-idea
 */
@WebServlet(name = "Documento", urlPatterns = {"/Documento"})
public class Documento extends HttpServlet {

    private Map parametros;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        ServletSession servletSession = (ServletSession) request.getSession().getAttribute("servletSession");
        JasperPrint jasperPrint;
        OutputStream outStream;
        Connection conn = null;
        if (servletSession == null || servletSession.estaVacio()) {
            
            return;
        }
        parametros = servletSession.getParametros();
        response.setContentType("application/pdf");

        if (servletSession.tieneParametro("ciRuc")) {
            response.addHeader("Content-disposition", "filename=" + servletSession.getNombreReporte() + servletSession.retornarValor("ciRuc") + ".pdf");
        } else {
            response.addHeader("Content-disposition", "filename=" + servletSession.getNombreReporte() + ".pdf");
        }

        try {
            request.setCharacterEncoding("UTF-8");
            String ruta;
            if (servletSession.getNombreSubCarpeta() == null) {
                ruta = getServletContext().getRealPath("//reportes//" + servletSession.getNombreReporte() + ".jasper");
            } else {
                ruta = getServletContext().getRealPath("//reportes//" + servletSession.getNombreSubCarpeta() + "//" + servletSession.getNombreReporte() + ".jasper");
            }

            if (servletSession.getTieneDatasource()) {
                Session sess = HiberUtil.getSession();
                SessionImplementor sessImpl = (SessionImplementor) sess;
                conn = sessImpl.getJdbcConnectionAccess().obtainConnection();
                jasperPrint = JasperFillManager.fillReport(ruta, parametros, conn);
                if(servletSession.getEncuadernacion()!=null && servletSession.getEncuadernacion()){
                    List pages = jasperPrint.getPages();
                    JRPrintPage page;
                    List<JRPrintElement> elements;
                    for (int i = 1; i < pages.size()+1; i++) {
                        page = (JRPrintPage) pages.get(i-1);
                        elements = page.getElements();
                        if(i%2 != 0){//IMPAR
                            for(JRPrintElement temp : elements){
                                temp.setX(temp.getX()+30);
                            }
                        }else{//PAR
                            for(JRPrintElement temp : elements){
                                temp.setX(temp.getX()-30);
                            }
                        }
                    }
                }
                outStream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
                outStream.flush();
                outStream.close();
            } else {
                JRDataSource dataSource = new JRBeanCollectionDataSource(new ArrayList());
                jasperPrint = JasperFillManager.fillReport(ruta, parametros, dataSource);
                
                outStream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
                outStream.flush();
                outStream.close();
            }

            servletSession.borrarDatos();

        } catch (SQLException | JRException | IOException e) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
