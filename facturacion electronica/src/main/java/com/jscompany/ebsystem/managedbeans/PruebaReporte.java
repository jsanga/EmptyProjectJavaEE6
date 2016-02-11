/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans;

import com.jscompany.ebsystem.util.HiberUtil;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
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
 * @author Joao Sanga
 */
@ManagedBean
@ViewScoped
public class PruebaReporte implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    private Map parametros;
    private JRBeanCollectionDataSource dataSource;

    public void generarReporte() throws SQLException, JRException, IOException{
        
        JasperPrint jasperPrint = null;
        JasperPrint jasperPrintTemp;
        JRPrintElement element = null;
        List<JRPrintElement> elements;
        HttpServletResponse response;
        OutputStream outputStream = null;
        byte[] byteStream = null;
        int i = 1;           
        
        
        String ruta = JsfUti.getRealPath("//reportes//" + "pruebaMargen.jasper");
        try{
            Session sess = HiberUtil.getSession();
            SessionImplementor sessImpl = (SessionImplementor) sess;
            Connection conn = sessImpl.getJdbcConnectionAccess().obtainConnection();
            jasperPrint = JasperFillManager.fillReport(ruta, parametros, conn);
            jasperPrintTemp = new JasperPrint();
            List pages = jasperPrint.getPages();
            for (int j = 0; j < pages.size(); j++) {
                JRPrintPage object = (JRPrintPage) pages.get(j);
                elements = object.getElements();
                if(i%2 == 1){
                    for(JRPrintElement temp : elements){
                        temp.setX(temp.getX()+10);
                    }
                }else{
                    for(JRPrintElement temp : elements){
                        temp.setX(temp.getX()+70);
                    }
                }
                //object.setElements(elements);
                i++;
            }
    
            //jasperPrint.setPageWidth(noOfColumns * 10);
            response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.reset();
            response.setContentType("application/pdf");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=reporte.pdf");
            outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            //byteStream = JasperRunManager.runReportToPdf(ruta, parametros, conn);
            
            conn.close();  
            //response.setContentLength(byteStream.length);
            
            
            //outputStream.write(byteStream,0,byteStream.length);
        }catch (JRException e) {
            Logger.getLogger(PruebaReporte.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            if(outputStream!=null){
                outputStream.flush();
                outputStream.close();
            }
            FacesContext.getCurrentInstance().responseComplete();
        }
        
    }
    
}
