package io.reflectoring.buckpal.adapter.persistence;

public interface AccountRepositoryCustom <T, ID>{

    void lockAccount(ID id);

    void releaseAccount(ID id);
}
