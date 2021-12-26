package com.tuukkal.talousappi.UserManagement.repo;

import com.tuukkal.talousappi.UserManagement.domain.Type;
import com.tuukkal.talousappi.UserManagement.domain.Typetag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TypeRepo extends JpaRepository<Type, Long> {

    public String GET_TYPETAG = "SELECT * FROM typetags " +
            "INNER JOIN types ON type_id = type_type_id " +
            "WHERE user_id = :userId AND tag = :tag";

    @Query(value = GET_TYPETAG, nativeQuery = true)
    public Type getTypeWithTag(@Param("userId") Long userId, @Param("tag") String tag);


}
