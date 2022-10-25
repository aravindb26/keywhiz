/*
 * This file is generated by jOOQ.
 */
package keywhiz.jooq.tables;


import java.util.Arrays;
import java.util.List;

import keywhiz.jooq.Indexes;
import keywhiz.jooq.Keys;
import keywhiz.jooq.KeywhizdbTest;
import keywhiz.jooq.tables.records.GroupsRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row9;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Groups extends TableImpl<GroupsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>keywhizdb_test.groups</code>
     */
    public static final Groups GROUPS = new Groups();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<GroupsRecord> getRecordType() {
        return GroupsRecord.class;
    }

    /**
     * The column <code>keywhizdb_test.groups.id</code>.
     */
    public final TableField<GroupsRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>keywhizdb_test.groups.name</code>.
     */
    public final TableField<GroupsRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>keywhizdb_test.groups.updatedat</code>.
     */
    public final TableField<GroupsRecord, Long> UPDATEDAT = createField(DSL.name("updatedat"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>keywhizdb_test.groups.createdat</code>.
     */
    public final TableField<GroupsRecord, Long> CREATEDAT = createField(DSL.name("createdat"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>keywhizdb_test.groups.description</code>.
     */
    public final TableField<GroupsRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>keywhizdb_test.groups.createdby</code>.
     */
    public final TableField<GroupsRecord, String> CREATEDBY = createField(DSL.name("createdby"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>keywhizdb_test.groups.updatedby</code>.
     */
    public final TableField<GroupsRecord, String> UPDATEDBY = createField(DSL.name("updatedby"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>keywhizdb_test.groups.metadata</code>.
     */
    public final TableField<GroupsRecord, String> METADATA = createField(DSL.name("metadata"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>keywhizdb_test.groups.owner</code>.
     */
    public final TableField<GroupsRecord, Long> OWNER = createField(DSL.name("owner"), SQLDataType.BIGINT, this, "");

    private Groups(Name alias, Table<GroupsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Groups(Name alias, Table<GroupsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>keywhizdb_test.groups</code> table reference
     */
    public Groups(String alias) {
        this(DSL.name(alias), GROUPS);
    }

    /**
     * Create an aliased <code>keywhizdb_test.groups</code> table reference
     */
    public Groups(Name alias) {
        this(alias, GROUPS);
    }

    /**
     * Create a <code>keywhizdb_test.groups</code> table reference
     */
    public Groups() {
        this(DSL.name("groups"), null);
    }

    public <O extends Record> Groups(Table<O> child, ForeignKey<O, GroupsRecord> key) {
        super(child, key, GROUPS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : KeywhizdbTest.KEYWHIZDB_TEST;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.GROUPS_OWNER_IDX);
    }

    @Override
    public Identity<GroupsRecord, Long> getIdentity() {
        return (Identity<GroupsRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<GroupsRecord> getPrimaryKey() {
        return Keys.KEY_GROUPS_PRIMARY;
    }

    @Override
    public List<UniqueKey<GroupsRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_GROUPS_NAME);
    }

    @Override
    public Groups as(String alias) {
        return new Groups(DSL.name(alias), this);
    }

    @Override
    public Groups as(Name alias) {
        return new Groups(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Groups rename(String name) {
        return new Groups(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Groups rename(Name name) {
        return new Groups(name, null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<Long, String, Long, Long, String, String, String, String, Long> fieldsRow() {
        return (Row9) super.fieldsRow();
    }
}
