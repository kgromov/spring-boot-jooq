package com.recipe.application.repositories.custom;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public abstract class JooqSqlResultMapperRepository {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    protected DSLContext dslContext;

    @SuppressWarnings("unchecked")
    protected <T> List<T> getResultList(org.jooq.Query jooqQuery, String sqlResultMapperName) {
        Query hibernateQuery = entityManager.createNativeQuery(jooqQuery.getSQL(), sqlResultMapperName);
        setBindParameterValues(hibernateQuery, jooqQuery);
        return (List<T>) hibernateQuery.getResultList();
    }

    private static void setBindParameterValues(Query hibernateQuery, org.jooq.Query jooqQuery) {
        List<Object> values = jooqQuery.getBindValues();
        for (int i = 0; i < values.size(); i++) {
            hibernateQuery.setParameter(i + 1, values.get(i));
        }
    }
}
