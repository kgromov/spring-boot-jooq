/*
 * This file is generated by jOOQ.
 */
package guru.springframework.jooq.tables;


import guru.springframework.jooq.SpringGuruRecipe;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Notes extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>spring_guru_recipe.notes</code>
     */
    public static final Notes NOTES = new Notes();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>spring_guru_recipe.notes.id</code>.
     */
    public final TableField<Record, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>spring_guru_recipe.notes.recipe_notes</code>.
     */
    public final TableField<Record, String> RECIPE_NOTES = createField(DSL.name("recipe_notes"), SQLDataType.CLOB, this, "");

    private Notes(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Notes(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>spring_guru_recipe.notes</code> table reference
     */
    public Notes(String alias) {
        this(DSL.name(alias), NOTES);
    }

    /**
     * Create an aliased <code>spring_guru_recipe.notes</code> table reference
     */
    public Notes(Name alias) {
        this(alias, NOTES);
    }

    /**
     * Create a <code>spring_guru_recipe.notes</code> table reference
     */
    public Notes() {
        this(DSL.name("notes"), null);
    }

    @Override
    public Schema getSchema() {
        return SpringGuruRecipe.SPRING_GURU_RECIPE;
    }

    @Override
    public Identity<Record, Long> getIdentity() {
        return (Identity<Record, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<Record> getPrimaryKey() {
        return Internal.createUniqueKey(Notes.NOTES, DSL.name("KEY_notes_PRIMARY"), new TableField[] { Notes.NOTES.ID }, true);
    }

    @Override
    public List<UniqueKey<Record>> getKeys() {
        return Arrays.<UniqueKey<Record>>asList(
              Internal.createUniqueKey(Notes.NOTES, DSL.name("KEY_notes_PRIMARY"), new TableField[] { Notes.NOTES.ID }, true)
        );
    }

    @Override
    public Notes as(String alias) {
        return new Notes(DSL.name(alias), this);
    }

    @Override
    public Notes as(Name alias) {
        return new Notes(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Notes rename(String name) {
        return new Notes(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Notes rename(Name name) {
        return new Notes(name, null);
    }
}
