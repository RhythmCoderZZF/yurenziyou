package com.nbhysj.coupon.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.nbhysj.coupon.model.response.SearchBean;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.HomeSearchComprehensiveBean;

import com.nbhysj.coupon.greendao.SearchBeanDao;
import com.nbhysj.coupon.greendao.CountryBeanDao;
import com.nbhysj.coupon.greendao.HomeSearchComprehensiveBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig searchBeanDaoConfig;
    private final DaoConfig countryBeanDaoConfig;
    private final DaoConfig homeSearchComprehensiveBeanDaoConfig;

    private final SearchBeanDao searchBeanDao;
    private final CountryBeanDao countryBeanDao;
    private final HomeSearchComprehensiveBeanDao homeSearchComprehensiveBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        searchBeanDaoConfig = daoConfigMap.get(SearchBeanDao.class).clone();
        searchBeanDaoConfig.initIdentityScope(type);

        countryBeanDaoConfig = daoConfigMap.get(CountryBeanDao.class).clone();
        countryBeanDaoConfig.initIdentityScope(type);

        homeSearchComprehensiveBeanDaoConfig = daoConfigMap.get(HomeSearchComprehensiveBeanDao.class).clone();
        homeSearchComprehensiveBeanDaoConfig.initIdentityScope(type);

        searchBeanDao = new SearchBeanDao(searchBeanDaoConfig, this);
        countryBeanDao = new CountryBeanDao(countryBeanDaoConfig, this);
        homeSearchComprehensiveBeanDao = new HomeSearchComprehensiveBeanDao(homeSearchComprehensiveBeanDaoConfig, this);

        registerDao(SearchBean.class, searchBeanDao);
        registerDao(CountryBean.class, countryBeanDao);
        registerDao(HomeSearchComprehensiveBean.class, homeSearchComprehensiveBeanDao);
    }
    
    public void clear() {
        searchBeanDaoConfig.clearIdentityScope();
        countryBeanDaoConfig.clearIdentityScope();
        homeSearchComprehensiveBeanDaoConfig.clearIdentityScope();
    }

    public SearchBeanDao getSearchBeanDao() {
        return searchBeanDao;
    }

    public CountryBeanDao getCountryBeanDao() {
        return countryBeanDao;
    }

    public HomeSearchComprehensiveBeanDao getHomeSearchComprehensiveBeanDao() {
        return homeSearchComprehensiveBeanDao;
    }

}
