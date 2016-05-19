package com.ejb;

import org.apache.log4j.Logger;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by piotrek on 18.05.16.
 */
@Singleton
@Startup
public class UserBox {
    private static final Logger logger = Logger.getLogger(MenuBox.class);
    @PersistenceContext(name="catering-company")
    private EntityManager em;

}
