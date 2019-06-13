package com.mt.bbdj.baseconfig.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author : ZSK
 * Date : 2019/1/4
 * Description :  省
 */
@Entity()
public class Province {

    public String id;       //省id
    public String region_name;   //省名称
    public String parent_id;     //上级id
    public String region_code;   //编号
    @Generated(hash = 824843892)
    public Province(String id, String region_name, String parent_id,
            String region_code) {
        this.id = id;
        this.region_name = region_name;
        this.parent_id = parent_id;
        this.region_code = region_code;
    }
    @Generated(hash = 1309009906)
    public Province() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRegion_name() {
        return this.region_name;
    }
    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
    public String getParent_id() {
        return this.parent_id;
    }
    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
    public String getRegion_code() {
        return this.region_code;
    }
    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

}
