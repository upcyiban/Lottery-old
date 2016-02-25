package com.lihuanyu.yiban.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Created by skyADMIN on 16/2/4.
 */
public interface CreatorDao extends CrudRepository<Creator,Long> {
    public Collection<Creator> findByYibanid(long yibanid);
}
