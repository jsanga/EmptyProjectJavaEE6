/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.ejb;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angel Navarro
 */
public class ReflexionEntity implements Serializable {

    /**
     *
     * @param <T>
     * @param entity Nombre de La entity
     * @return Si la entity se encuentra en el paquete
     * "com.origami.sgm.entities" retorna la clase requerida.
     */
    public static <T> Class entityClass(String entity) {
        try {
            return Class.forName("com.origami.sgm.entities." + entity);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReflexionEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String getIdEntity(Object entity) {
        if (entity != null) {
            Field fiel[] = entity.getClass().getDeclaredFields();
            for (Field f : fiel) {
                if (isAnnotationType(f, "@javax.persistence.Id()")) {
                    if (!f.isAccessible()) {
                        try {
                            f.setAccessible(true);
                            Object value = f.get(entity);
                            if (value != null) {
//                                System.out.println("TTTT " + f.get(entity).toString());
                                return f.get(entity).toString();
                            } else {
                                return null;
                            }
                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(ReflexionEntity.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        return null;
    }

    public static Boolean isAnnotationType(Field field, String annotation) {
        Annotation anotacion[] = field.getAnnotations();
        for (Annotation anot : anotacion) {
            if (anot.toString().equals(annotation)) {
                return true;
            }
        }
        return false;
    }

    public static Object emptyInstance(String type) {
        Object obj = null;
        try {
            Class clazz = Class.forName(type);

            for (java.lang.reflect.Constructor con : clazz.getConstructors()) {
                if (con.getParameterTypes().length == 0) {
                    obj = con.newInstance();
                    break;
                }
            }
        } catch (ClassNotFoundException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            return null;
        }
        return obj;
    }

    public Boolean equalsFieldType(Field field, Class typeClass) {
        return field.getType().equals(typeClass);
    }

   

}
