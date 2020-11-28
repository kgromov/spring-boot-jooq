package guru.springframework.jooq.demo;

import guru.springframework.jooq.tables.Notes;
import guru.springframework.jooq.tables.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class JooqDemo {
    @Autowired
    private DSLContext dsl;

    @PostConstruct
    public void init() {
        testSelectWithJoin();
    }

    public void testSelectWithJoin() {
        Recipe recipe = Recipe.RECIPE;
        Notes notes = Notes.NOTES;

        SelectHavingStep<Record3<Long, String, Integer>> sql = dsl.select(recipe.ID, recipe.DESCRIPTION, DSL.count())
                .from(recipe)
                .join(notes)
                .on(recipe.NOTES_ID.equal(notes.ID))
                .groupBy(notes.ID);
        log.info("Sql query = {}", sql.toString());
        Result<Record3<Long, String, Integer>> fetch = sql.fetch();
    }
}
