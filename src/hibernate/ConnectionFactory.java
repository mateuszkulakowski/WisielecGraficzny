/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Mateusz
 */
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Mateusz
 */
public abstract class ConnectionFactory {
    
    private static SessionFactory sf;
    
    
    public static SessionFactory getSessionFactory()
    {
        if(sf==null)
        {
            Configuration c = new Configuration();
            c.configure("hibernate/hibernate.cfg.xml");
        
            sf = c.buildSessionFactory();
            
        }
        
        return sf;
    }
    
}
