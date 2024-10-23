package com.devminrat.tennis;

import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.model.MatchDao;
import com.devminrat.tennis.model.MatchDaoImpl;
import com.devminrat.tennis.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GetMatchesTest {

    @Test
    public void getAllMatches() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            MatchDao matchDao = new MatchDaoImpl();
            List<Match> matches = matchDao.getAllMatches(session, 5, 0);
            System.out.println(matches);
            for (Match match : matches) {
                System.out.println(match);
            }
        }
    }
}
