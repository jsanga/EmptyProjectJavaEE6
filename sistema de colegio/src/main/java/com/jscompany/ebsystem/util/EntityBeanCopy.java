/*
 *  Origami Solutions
 */

package com.jscompany.ebsystem.util;

import java.io.IOException;
import org.jboss.marshalling.cloner.ClonerConfiguration;
import org.jboss.marshalling.cloner.ObjectCloner;
import org.jboss.marshalling.cloner.ObjectClonerFactory;
import org.jboss.marshalling.cloner.ObjectCloners;

/**
 * 
 * @author fernando
 */
public class EntityBeanCopy {
    
    public static Object clone(Object source){
        
        final ObjectClonerFactory clonerFactory = ObjectCloners.getSerializingObjectClonerFactory();
        final ClonerConfiguration configuration = new ClonerConfiguration();
        configuration.setObjectResolver(new EntityResolver());
        final ObjectCloner cloner = clonerFactory.createCloner(configuration);
        
        Object result = null;
        
        try{
            result = cloner.clone(source);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
}
