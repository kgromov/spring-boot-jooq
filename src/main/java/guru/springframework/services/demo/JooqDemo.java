package guru.springframework.services.demo;

import guru.springframework.domain.Difficulty;
import guru.springframework.jooq.tables.Notes;
import guru.springframework.jooq.tables.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
public class JooqDemo {
    @Autowired
    private DSLContext dsl;
    @Autowired
    private DifficultyConverter difficultyConverter;

    @PostConstruct
    public void init() {
        testSelectWithJoin();
    }

    public void testSelectWithJoin() {
        Recipe recipe = Recipe.RECIPE;
        Notes notes = Notes.NOTES;

        // AllArgsConstructor is not mandatory for #fetchInto method -seems the same approach as Hibernate - create via empty constructor + setters
        List<guru.springframework.domain.Notes> notesList = dsl.selectFrom(notes).fetchInto(guru.springframework.domain.Notes.class);
        SelectConditionStep<Record> select = dsl.selectFrom(recipe).where(recipe.ID.le(5L));
        List<Object> bindValues = select.getBindValues();
        String sql = select.getSQL();
        String selectSQL = select.getSQL(ParamType.INLINED);
        List<guru.springframework.domain.Recipe> recipeList = select.fetchInto(guru.springframework.domain.Recipe.class);

        List<Difficulty> difficulties = dsl.selectDistinct(recipe.DIFFICULTY)
                .from(recipe)
                .fetch(recipe.DIFFICULTY, difficultyConverter);

        Result<Record2<String, Integer>> result = dsl.selectDistinct(recipe.DIFFICULTY, recipe.PREP_TIME)
                .from(recipe)
                .fetch();

        SelectHavingStep<Record3<Long, String, Integer>> havingStep = dsl.select(recipe.ID, recipe.DESCRIPTION, DSL.count())
                .from(recipe)
                .join(notes)
                .on(recipe.NOTES_ID.equal(notes.ID))
                .groupBy(notes.ID);
        log.info("Sql query = {}", havingStep.toString());
        Result<Record3<Long, String, Integer>> fetch = havingStep.fetch();
    }
}
