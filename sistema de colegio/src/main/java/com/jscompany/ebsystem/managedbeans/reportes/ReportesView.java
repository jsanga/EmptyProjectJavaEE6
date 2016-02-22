/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.reportes;

import com.jscompany.ebsystem.managedbeans.session.ServletSession;
import com.jscompany.ebsystem.util.ApplicationContextUtils;
import com.jscompany.ebsystem.util.CmisUtil;
import com.jscompany.ebsystem.util.HiberUtil;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;

/**
 *
 * @author Joao Sanga
 */
@ManagedBean
@ViewScoped
public class ReportesView implements Serializable{
    
    private static final Long serialVersionUID = 1L;
    
    @ManagedProperty("#{servletSession}")
    private ServletSession servletSession;
    
    private JRBeanCollectionDataSource dataSource;
    private Map parametros;
    private CmisUtil cmis;
    private int read = 0;
    private HashMap<String, Object> params = new HashMap<>();
    
    @PostConstruct
    public void init(){
        
    }
    
    public void descargarImagenArregloBytes(byte[] bytes) throws IOException{
        HttpServletResponse response;
        OutputStream outputStream = null;
        try{
            if(bytes==null)
                return;

            response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.reset();

            response.setContentType("image/JPEG");
            response.setCharacterEncoding("UTF-8");
            //response.setHeader("Content-disposition", "attachment; filename="+servletSession.getNombreReporte()+".pdf");
            response.setHeader("Content-disposition", "attachment; filename=inspeccion"+servletSession.getNombreReporte()+".jpeg");
            response.setContentLength(bytes.length);

            outputStream = response.getOutputStream();

            outputStream.write(bytes, 0, bytes.length); 
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(outputStream!=null){
                outputStream.flush();
                outputStream.close();
            }
            FacesContext.getCurrentInstance().responseComplete();
        }
        
    }
    
    public void descargarPDFarregloBytes(byte[] bytes) throws IOException{
        HttpServletResponse response;
        OutputStream outputStream = null;
        
        try{
            byte[] byteStream = bytes;

            if(byteStream==null)
                return;

            response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.reset();

            response.setContentType("application/pdf");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment; filename="+servletSession.getNombreReporte()+".pdf");
            response.setContentLength(byteStream.length);

            outputStream = response.getOutputStream();

            outputStream.write(byteStream, 0, byteStream.length);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(outputStream!=null){
                outputStream.flush();
                outputStream.close();
            }
            FacesContext.getCurrentInstance().responseComplete();
        }
    }
    
    public InputStream descargarByteArrayDesdeAlfrescoPorURL(String url){
        
        ContentStream fileStream = null;
        
        try {
            cmis = (CmisUtil) ApplicationContextUtils.getBean("cmisUtil");
            if (cmis != null)
                fileStream = cmis.getDocument(url);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            cmis = null;
        }
        if(fileStream!=null)
            return fileStream.getStream();
        else
            return null;
    }
    
    public void generarReporte() throws SQLException, JRException, IOException{
        
        parametros = servletSession.getParametros();
        JasperPrint jasperPrint = null;
        HttpServletResponse response;
        OutputStream outputStream = null;
        byte[] byteStream = null;
        
        String ruta = JsfUti.getRealPath("//reportes//" + servletSession.getNombreReporte() + ".jasper");
        try{
            if (servletSession.getTieneDatasource()) {
                Session sess = HiberUtil.getSession();
                SessionImplementor sessImpl = (SessionImplementor) sess;
                Connection conn = sessImpl.getJdbcConnectionAccess().obtainConnection();
                byteStream = JasperRunManager.runReportToPdf(ruta, parametros, dataSource);
                conn.close();
            }
            else{
                dataSource = new JRBeanCollectionDataSource(new ArrayList());
                //jasperPrint = JasperFillManager.fillReport(ruta, parametros, dataSource);
                byteStream = JasperRunManager.runReportToPdf(ruta, parametros, dataSource);
                dataSource = null;
            }
            
            params.put("archivoByteArray", byteStream);
            params.put("tipoArchivoByteArray", "application/pdf");
            params.put("nombreArchivoByteArray", servletSession.getNombreReporte());
            
            servletSession.borrarDatos();
            
            
            response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.reset();
            
            response.setContentType("application/pdf");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=reporte.pdf");
            response.setContentLength(byteStream.length);
            outputStream = response.getOutputStream();
            
            outputStream.write(byteStream,0,byteStream.length); 
            //JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);  
            //servletOutputStream.flush();
            
            
            
        }catch (JRException e) {
            e.printStackTrace();
        }finally{
            if(outputStream!=null){
                outputStream.flush();
                outputStream.close();
            }
            FacesContext.getCurrentInstance().responseComplete();
        }
        
    }

    public ServletSession getServletSession() {
        return servletSession;
    }

    public void setServletSession(ServletSession servletSession) {
        this.servletSession = servletSession;
    }

    public JRBeanCollectionDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(JRBeanCollectionDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map getParametros() {
        return parametros;
    }

    public void setParametros(Map parametros) {
        this.parametros = parametros;
    }

    public HashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }
    
}
