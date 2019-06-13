package com.mt.bbdj.baseconfig.db.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.mt.bbdj.baseconfig.db.Province;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PROVINCE".
*/
public class ProvinceDao extends AbstractDao<Province, Void> {

    public static final String TABLENAME = "PROVINCE";

    /**
     * Properties of entity Province.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", false, "ID");
        public final static Property Region_name = new Property(1, String.class, "region_name", false, "REGION_NAME");
        public final static Property Parent_id = new Property(2, String.class, "parent_id", false, "PARENT_ID");
        public final static Property Region_code = new Property(3, String.class, "region_code", false, "REGION_CODE");
    }


    public ProvinceDao(DaoConfig config) {
        super(config);
    }
    
    public ProvinceDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PROVINCE\" (" + //
                "\"ID\" TEXT," + // 0: id
                "\"REGION_NAME\" TEXT," + // 1: region_name
                "\"PARENT_ID\" TEXT," + // 2: parent_id
                "\"REGION_CODE\" TEXT);"); // 3: region_code
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PROVINCE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Province entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String region_name = entity.getRegion_name();
        if (region_name != null) {
            stmt.bindString(2, region_name);
        }
 
        String parent_id = entity.getParent_id();
        if (parent_id != null) {
            stmt.bindString(3, parent_id);
        }
 
        String region_code = entity.getRegion_code();
        if (region_code != null) {
            stmt.bindString(4, region_code);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Province entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String region_name = entity.getRegion_name();
        if (region_name != null) {
            stmt.bindString(2, region_name);
        }
 
        String parent_id = entity.getParent_id();
        if (parent_id != null) {
            stmt.bindString(3, parent_id);
        }
 
        String region_code = entity.getRegion_code();
        if (region_code != null) {
            stmt.bindString(4, region_code);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Province readEntity(Cursor cursor, int offset) {
        Province entity = new Province( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // region_name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // parent_id
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // region_code
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Province entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setRegion_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setParent_id(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setRegion_code(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Province entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Province entity) {
        return null;
    }

    @Override
    public boolean hasKey(Province entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}