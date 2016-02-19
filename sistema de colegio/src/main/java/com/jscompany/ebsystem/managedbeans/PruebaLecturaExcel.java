/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.ejb.interfaces.UsuariosServices;
import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.colegios.Curso;
import com.jscompany.ebsystem.entidades.colegios.Paralelo;
import com.jscompany.ebsystem.entidades.usuarios.Estudiante;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.Rol;
import com.jscompany.ebsystem.services.AclService;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Joao Sanga
 */
@ManagedBean
@ViewScoped
public class PruebaLecturaExcel implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @EJB(beanName = "usuariosServices")
    private UsuariosServices usuariosServices;
    
    private UploadedFile file;
    private InputStream archivo;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Colegio colegio;
    private Rol rol;
    private List<Colegio> colegiosList;
    private String version = FacesContext.class.getPackage().getImplementationVersion();
    
    @PostConstruct
    public void init(){
        colegiosList = services.getListEntitiesByParameters(Querys.getColegiosList, new String[]{}, new Object[]{});
        rol = (Rol) services.getEntityByParameters(Querys.getRolById, new String[]{"rolId"}, new Object[]{new Long(3)});
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        try{
            if(event != null) {
                file = event.getFile();
                FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void leerEstudiante() throws IOException{
        try{
            if(file != null) {
                Persona temp;
                Estudiante est;
                List<Persona> personasList;
                int cont = 0;
                Cell cell;
                Row row;
                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                archivo = file.getInputstream();
                workbook = new XSSFWorkbook(archivo);
                sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();
                personasList = new ArrayList<>();
                while (rowIterator.hasNext())
                {
                    row = rowIterator.next();
                    if(cont>3){
                        
                        temp = new Persona();
                        cell = row.getCell(13);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        temp.setCedula(cell.getStringCellValue());
                        cell = row.getCell(6);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        temp.setNombres(cell.getStringCellValue());
                        cell = row.getCell(7);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        temp.setApellidos(cell.getStringCellValue());
                        cell = row.getCell(3);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        temp.setDireccion(cell.getStringCellValue());
                        temp.setEstado(Boolean.TRUE);
                        cell = row.getCell(15);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        /*if(cell.getStringCellValue().equals("1"))
                            temp.setSexo("Masculino");
                        else
                            temp.setSexo("Femenino");*/
                        
                        personasList.add(temp);
                    }
                    cont++;
                }
                archivo.close();          
                int n = usuariosServices.guardarPersonasEstudiantesExcel(personasList, rol, colegio);
                System.out.println("NUMERO DE PERSONAS INGRESADAS: "+n+"\nColegio: "+colegio.getNombre()+"\nRol: "+rol.getRolName().toUpperCase());
                //System.out.println("El tamaño de la lista es: "+personasList.size());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void leerColegio() throws IOException{
        try{
            if(file != null) {
                Colegio temp;
                List<Colegio> colegiosList;
                int cont = 0;
                Cell cell;
                Row row;
                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                archivo = file.getInputstream();
                workbook = new XSSFWorkbook(archivo);
                sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();
                colegiosList = new ArrayList<>();
                while (rowIterator.hasNext())
                {
                    row = rowIterator.next();
                    if(cont>0){
                        
                        temp = new Colegio();
                        cell = row.getCell(0);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        temp.setNombre(cell.getStringCellValue());
                        cell = row.getCell(1);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        temp.setDireccion(cell.getStringCellValue());
                        temp.setEstado(Boolean.TRUE);
                        
                        colegiosList.add(temp);
                    }
                    cont++;
                }
                archivo.close();
                
                for(Colegio t : colegiosList){
                    System.out.println(t.getNombre());
                    System.out.println(t.getDireccion());
                }
                
                //System.out.println("El tamaño de la lista es: "+personasList.size());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void leerCursos() throws IOException{
        try{
            if(file != null) {
                Curso temp;
                List<Curso> cursoList;
                int cont = 0;
                Cell cell;
                Row row;
                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                archivo = file.getInputstream();
                workbook = new XSSFWorkbook(archivo);
                sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();
                cursoList = new ArrayList<>();
                while (rowIterator.hasNext())
                {
                    row = rowIterator.next();
                    if(cont>0){
                        
                        temp = new Curso();
                        cell = row.getCell(0);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        temp.setNombre(cell.getStringCellValue());
                        temp.setEstado(Boolean.TRUE);
                        cursoList.add(temp);
                    }
                    cont++;
                }
                archivo.close();
                
                for(Curso t : cursoList){
                    System.out.println(t.getNombre());
                }
                
                //System.out.println("El tamaño de la lista es: "+personasList.size());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void leerParalelos() throws IOException{
        try{
            if(file != null) {
                Paralelo temp;
                List<Paralelo> paralelosList;
                int cont = 0;
                Cell cell;
                Row row;
                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                archivo = file.getInputstream();
                workbook = new XSSFWorkbook(archivo);
                sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();
                paralelosList = new ArrayList<>();
                while (rowIterator.hasNext())
                {
                    row = rowIterator.next();
                    if(cont>0){
                        
                        temp = new Paralelo();
                        cell = row.getCell(0);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        temp.setNombre(cell.getStringCellValue());
                        temp.setEstado(Boolean.TRUE);
                        paralelosList.add(temp);
                    }
                    cont++;
                }
                archivo.close();
                
                for(Paralelo t : paralelosList){
                    System.out.println(t.getNombre());
                }
                
                //System.out.println("El tamaño de la lista es: "+personasList.size());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Colegio getColegio() {
        return colegio;
    }

    public void setColegio(Colegio colegio) {
        this.colegio = colegio;
    }

    public List<Colegio> getColegiosList() {
        return colegiosList;
    }

    public void setColegiosList(List<Colegio> colegiosList) {
        this.colegiosList = colegiosList;
    }
    
}
