package com.foch.springserver.model.welfare;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WelfareRepository extends CrudRepository<Welfare, String>  {
    
}
