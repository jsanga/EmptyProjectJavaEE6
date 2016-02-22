/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.reportes;

import com.jscompany.ebsystem.util.HiberUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;

/**
 *
 * @author supergold
 */
public class PdfReporte implements Serializable {

    public static final Long serialVersionUID = 1L;

    JasperPrint reporte_view;
    public byte[] generarPdf(String nombre, Map paramt) throws SQLException {
        byte[] pdfByte = null;
        Connection conn = null;
        try {
            Session sess = HiberUtil.getSession();
            SessionImplementor implementor = (SessionImplementor) sess;
            conn = implementor.getJdbcConnectionAccess().obtainConnection();
            String in = FacesContext.getCurrentInstance().getExternalContext().getRealPath(nombre);
//            reporte_view = JasperFillManager.fillReport(in, paramt, conn);
            pdfByte = JasperExportManager.exportReportToPdf(JasperFillManager.fillReport(in, paramt, conn));
            
        } catch (JRException | SQLException e) {
            Logger.getLogger(PdfReporte.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            if(conn!=null)
                conn.close();
        }
        return pdfByte;
    }

    public JasperPrint getReporte_view() {
        return reporte_view;
    }

    public void setReporte_view(JasperPrint reporte_view) {
        this.reporte_view = reporte_view;
    }

}
