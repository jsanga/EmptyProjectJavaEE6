/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.util;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.chemistry.opencmis.client.api.CmisObject;
//import org.alfresco.cmis.client.AlfrescoDocument;
//import org.alfresco.cmis.client.impl.AlfrescoDocumentImpl;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.DocumentType;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.FolderType;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

/**
 *
 * @author Joao Israel
 */
public class CmisUtil implements Serializable{

    private static final long serialVersionUID = 1L;
    private Session sesionAlfresco;
    private String urlServidor;

    public CmisUtil() {
    }

    public CmisUtil(String usuario, String clave, String url) {
        SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
        Map<String, String> parameters = new HashMap<>();
        parameters.put(SessionParameter.USER, usuario);
        parameters.put(SessionParameter.PASSWORD, clave);
        parameters.put(SessionParameter.ATOMPUB_URL, url + "alfresco/service/cmis");
        parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
        Repository repository = sessionFactory.getRepositories(parameters).get(0);
        this.sesionAlfresco = repository.createSession();
        this.urlServidor = url;
    }

    public Folder getFolder(String folderName) {
        ObjectType type = this.sesionAlfresco.getTypeDefinition("cmis:folder");
        PropertyDefinition<?> objectIdPropDef = type.getPropertyDefinitions().get(PropertyIds.OBJECT_ID);
        String objectIdQueryName = objectIdPropDef.getQueryName();
        ItemIterable<QueryResult> results = this.sesionAlfresco.query("SELECT * FROM cmis:folder WHERE cmis:name='" + folderName + "'", false);
        for (QueryResult qResult : results) {
            String objectId = qResult.getPropertyValueByQueryName(objectIdQueryName);
            return (Folder) this.sesionAlfresco.getObject(this.sesionAlfresco.createObjectId(objectId));
        }
        return null;
    }

    public Folder createFolder(Folder parentFolder, String folderName) {
        try {
            Map<String, Object> folderProps = new HashMap<>();
            folderProps.put(PropertyIds.NAME, folderName);
            folderProps.put(PropertyIds.OBJECT_TYPE_ID, FolderType.FOLDER_BASETYPE_ID);

            ObjectId folderObjectId = this.sesionAlfresco.createFolder(folderProps, parentFolder, null, null, null);
            return (Folder) this.sesionAlfresco.getObject(folderObjectId);
        } catch (Exception e) {
            Logger.getLogger(CmisUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public Folder createSubFolder(Folder parent, String subfolderName) {
        Folder f = null;
        try {
            f = this.createFolder(parent, subfolderName);
        } catch (Exception e) {
            Logger.getLogger(CmisUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        return f;
    }

    /**
     * Busqueda de la carpeta por url en el repositorio, la consulta se hace por
     * objeto del Cmis y se hace un cast a Folder si se encuentra la carpeta
     *
     * @param path
     * @return
     */
    public Folder getFolderByPath(String path) {
        CmisObject object = this.sesionAlfresco.getObjectByPath(path);
        if (object != null) {
            if (object instanceof Folder) {
                return (Folder) object;
            }
        }
        return null;
    }

    public Document createDocument(Folder folder, String fileName, String mimetype, byte[] content) throws Exception {
        Map<String, Object> docProps = new HashMap<>();
        docProps.put(PropertyIds.NAME, fileName);
        docProps.put(PropertyIds.OBJECT_TYPE_ID, DocumentType.DOCUMENT_BASETYPE_ID);
        ByteArrayInputStream in = new ByteArrayInputStream(content);
        ContentStream contentStream = this.sesionAlfresco.getObjectFactory().createContentStream(fileName, content.length, mimetype, in);
        ObjectId documentId = this.sesionAlfresco.createDocument(docProps, this.sesionAlfresco.createObjectId((String) folder.getPropertyValue(PropertyIds.OBJECT_ID)), contentStream, null, null, null, null);
        Document document = (Document) this.sesionAlfresco.getObject(documentId);
        //System.out.println("/*** " + PropertyIds.OBJECT_TYPE_ID);
        //System.out.println("*****- " + DocumentType.DOCUMENT_BASETYPE_ID);
        //System.out.println("/////******" + document.getPropertyValue(PropertyIds.OBJECT_TYPE_ID));
        return document;
    }

    /**
     * Busqueda de Documento almacenado en Alfresco mediante el id.
     *
     * @param id
     * @return ContentStream
     */
    public ContentStream getDocument(String id) {
        Document dc = (Document) this.sesionAlfresco.getObject(id);
        return dc.getContentStream();
    }

    public String getUrlServidor() {
        return this.urlServidor;
    }

}
