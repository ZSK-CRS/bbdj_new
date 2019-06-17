package com.mt.bbdj.baseconfig.db.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.mt.bbdj.baseconfig.db.BluetoothMessage;
import com.mt.bbdj.baseconfig.db.City;
import com.mt.bbdj.baseconfig.db.County;
import com.mt.bbdj.baseconfig.db.ExpressLogo;
import com.mt.bbdj.baseconfig.db.MingleArea;
import com.mt.bbdj.baseconfig.db.Province;
import com.mt.bbdj.baseconfig.db.UserBaseMessage;
import com.mt.bbdj.baseconfig.db.WaillMessage;

import com.mt.bbdj.baseconfig.db.gen.BluetoothMessageDao;
import com.mt.bbdj.baseconfig.db.gen.CityDao;
import com.mt.bbdj.baseconfig.db.gen.CountyDao;
import com.mt.bbdj.baseconfig.db.gen.ExpressLogoDao;
import com.mt.bbdj.baseconfig.db.gen.MingleAreaDao;
import com.mt.bbdj.baseconfig.db.gen.ProvinceDao;
import com.mt.bbdj.baseconfig.db.gen.UserBaseMessageDao;
import com.mt.bbdj.baseconfig.db.gen.WaillMessageDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig bluetoothMessageDaoConfig;
    private final DaoConfig cityDaoConfig;
    private final DaoConfig countyDaoConfig;
    private final DaoConfig expressLogoDaoConfig;
    private final DaoConfig mingleAreaDaoConfig;
    private final DaoConfig provinceDaoConfig;
    private final DaoConfig userBaseMessageDaoConfig;
    private final DaoConfig waillMessageDaoConfig;

    private final BluetoothMessageDao bluetoothMessageDao;
    private final CityDao cityDao;
    private final CountyDao countyDao;
    private final ExpressLogoDao expressLogoDao;
    private final MingleAreaDao mingleAreaDao;
    private final ProvinceDao provinceDao;
    private final UserBaseMessageDao userBaseMessageDao;
    private final WaillMessageDao waillMessageDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        bluetoothMessageDaoConfig = daoConfigMap.get(BluetoothMessageDao.class).clone();
        bluetoothMessageDaoConfig.initIdentityScope(type);

        cityDaoConfig = daoConfigMap.get(CityDao.class).clone();
        cityDaoConfig.initIdentityScope(type);

        countyDaoConfig = daoConfigMap.get(CountyDao.class).clone();
        countyDaoConfig.initIdentityScope(type);

        expressLogoDaoConfig = daoConfigMap.get(ExpressLogoDao.class).clone();
        expressLogoDaoConfig.initIdentityScope(type);

        mingleAreaDaoConfig = daoConfigMap.get(MingleAreaDao.class).clone();
        mingleAreaDaoConfig.initIdentityScope(type);

        provinceDaoConfig = daoConfigMap.get(ProvinceDao.class).clone();
        provinceDaoConfig.initIdentityScope(type);

        userBaseMessageDaoConfig = daoConfigMap.get(UserBaseMessageDao.class).clone();
        userBaseMessageDaoConfig.initIdentityScope(type);

        waillMessageDaoConfig = daoConfigMap.get(WaillMessageDao.class).clone();
        waillMessageDaoConfig.initIdentityScope(type);

        bluetoothMessageDao = new BluetoothMessageDao(bluetoothMessageDaoConfig, this);
        cityDao = new CityDao(cityDaoConfig, this);
        countyDao = new CountyDao(countyDaoConfig, this);
        expressLogoDao = new ExpressLogoDao(expressLogoDaoConfig, this);
        mingleAreaDao = new MingleAreaDao(mingleAreaDaoConfig, this);
        provinceDao = new ProvinceDao(provinceDaoConfig, this);
        userBaseMessageDao = new UserBaseMessageDao(userBaseMessageDaoConfig, this);
        waillMessageDao = new WaillMessageDao(waillMessageDaoConfig, this);

        registerDao(BluetoothMessage.class, bluetoothMessageDao);
        registerDao(City.class, cityDao);
        registerDao(County.class, countyDao);
        registerDao(ExpressLogo.class, expressLogoDao);
        registerDao(MingleArea.class, mingleAreaDao);
        registerDao(Province.class, provinceDao);
        registerDao(UserBaseMessage.class, userBaseMessageDao);
        registerDao(WaillMessage.class, waillMessageDao);
    }
    
    public void clear() {
        bluetoothMessageDaoConfig.clearIdentityScope();
        cityDaoConfig.clearIdentityScope();
        countyDaoConfig.clearIdentityScope();
        expressLogoDaoConfig.clearIdentityScope();
        mingleAreaDaoConfig.clearIdentityScope();
        provinceDaoConfig.clearIdentityScope();
        userBaseMessageDaoConfig.clearIdentityScope();
        waillMessageDaoConfig.clearIdentityScope();
    }

    public BluetoothMessageDao getBluetoothMessageDao() {
        return bluetoothMessageDao;
    }

    public CityDao getCityDao() {
        return cityDao;
    }

    public CountyDao getCountyDao() {
        return countyDao;
    }

    public ExpressLogoDao getExpressLogoDao() {
        return expressLogoDao;
    }

    public MingleAreaDao getMingleAreaDao() {
        return mingleAreaDao;
    }

    public ProvinceDao getProvinceDao() {
        return provinceDao;
    }

    public UserBaseMessageDao getUserBaseMessageDao() {
        return userBaseMessageDao;
    }

    public WaillMessageDao getWaillMessageDao() {
        return waillMessageDao;
    }

}
