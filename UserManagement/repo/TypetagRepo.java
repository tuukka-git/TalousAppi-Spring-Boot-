package com.tuukkal.talousappi.UserManagement.repo;

import com.tuukkal.talousappi.UserManagement.domain.Type;
import com.tuukkal.talousappi.UserManagement.domain.Typetag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TypetagRepo extends JpaRepository<Typetag, Long> {

    public String TAG_EXISTS = "SELECT CASE WHEN COUNT(tag) > 0 THEN true" +
            " ELSE false END FROM typetags WHERE tag = :tag AND user_id = :userId";

    @Query(value = TAG_EXISTS, nativeQuery = true)
    public boolean TagExists(@Param("userId") Long userId, @Param("tag") String tag);


}
