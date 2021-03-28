package com.luxoft.springdb.lab3.service;

import com.luxoft.springdb.lab3.dao.CountryDao;
import com.luxoft.springdb.lab3.dao.CountryNotFoundException;
import com.luxoft.springdb.lab3.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Repository
public class ProgrammaticTransactionCountryServiceImpl implements ProgrammaticTransactionCountryService {
    private TransactionTemplate transactionTemplate;
    private CountryDao dao;

    @Autowired
    public ProgrammaticTransactionCountryServiceImpl(PlatformTransactionManager transactionManager, CountryDao dao) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.dao = dao;
    }

    public Country getCountryByName(final String name) {
        transactionTemplate.setIsolationLevel(Isolation.REPEATABLE_READ.value());
        return (Country) transactionTemplate.execute((TransactionCallback<Object>) status -> {
            try {
                return dao.getCountryByName(name);
            } catch (CountryNotFoundException e) {
                return null;
            }
        });
    }

    public Country getCountryByCodeName(final String codeName, final Propagation propagation) {
        return (Country) transactionTemplate.execute((TransactionCallback<Object>) status ->
                getCountryByCodeNameInTransaction(codeName, propagation));
    }

    private Country getCountryByCodeNameInTransaction(final String codeName, final Propagation propagation) {

        return (Country) transactionTemplate.execute((TransactionCallback<Object>) status -> {
            TransactionLog.append("Method inside transaction, propagation = " + propagation.toString() + "\n");
            return dao.getCountryByCodeName(codeName);
        });
    }

    public List<Country> getAllCountries() {
        return dao.getCountryList();
    }
}
