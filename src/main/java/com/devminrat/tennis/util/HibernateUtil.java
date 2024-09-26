package com.devminrat.tennis.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {
    public static SessionFactory buildSessionFactory() {
        Configuration config = new Configuration().configure();
        return config.buildSessionFactory();
    }
}
