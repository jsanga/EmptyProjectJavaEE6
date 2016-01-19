/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans;

import com.jscompany.ebsystem.entidades.usuarios.Persona;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import static javassist.CtMethod.ConstParameter.string;
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
    
    private UploadedFile file;
    private InputStream archivo;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private String version = FacesContext.class.getPackage().getImplementationVersion();
    private Persona temp;
    private List<Persona> personasList;
    
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
    
    public void mostrarMensaje() throws IOException{
        try{
            if(file != null) {
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
                    if(cont>0){
                        
                        temp = new Persona();
                        cell = row.getCell(0);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        temp.setCedula(cell.getStringCellValue());
                        cell = row.getCell(1);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        temp.setNombres(cell.getStringCellValue());
                        cell = row.getCell(2);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        temp.setApellidos(cell.getStringCellValue());
                        cell = row.getCell(3);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        temp.setDireccion(cell.getStringCellValue());
                        
                        personasList.add(temp);
                    }
                    cont++;
                }
                archivo.close();
                
                for(Persona t : personasList){
                    System.out.println(t.getCedula());
                    System.out.println(t.getNombres());
                    System.out.println(t.getApellidos());
                    System.out.println(t.getDireccion());
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

    public Persona getTemp() {
        return temp;
    }

    public void setTemp(Persona temp) {
        this.temp = temp;
    }

    public List<Persona> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Persona> personasList) {
        this.personasList = personasList;
    }
    
}
