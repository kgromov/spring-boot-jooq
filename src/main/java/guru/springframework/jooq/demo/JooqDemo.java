package guru.springframework.jooq.demo;

import guru.springframework.jooq.tables.Notes;
import guru.springframework.jooq.tables.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JooqDemo {
    @Autowired
    private DSLContext dsl;

    public void testSelectWithJoin() {
        Recipe recipe = Recipe.RECIPE;
        Notes notes = Notes.NOTES;
        SelectWhereStep<Record> selectAll = dsl.selectFrom(recipe);
        log.info("Sql query = {}", selectAll.toString());
        int columns = selectAll.fetch().fields().length;
        SelectHavingStep<Record3<Long, String, Integer>> sql = dsl.select(recipe.ID, recipe.DESCRIPTION, DSL.count())
                .from(recipe)
                .join(notes)
                .on(recipe.NOTES_ID.equal(notes.ID))
                .groupBy(notes.ID);
        log.info("Sql query = {}", sql.toString());
        Result<Record3<Long, String, Integer>> fetch = sql.fetch();
    }
}
