package com.tuukkal.talousappi.UserManagement.repo;

import com.tuukkal.talousappi.UserManagement.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    public String INCOME = ""+
            "SELECT t.type_name, ROUND(CAST(COALESCE(SUM(tra.sum),0) as numeric),2) " +
            "FROM transactions AS tra " +
            "INNER JOIN types AS t ON tra.type_type_id = t.type_id " +
            "WHERE tra.date >= :fro AND  tra.date < :to AND tra.sum > 0 AND tra.user_id = :userId " +
            "GROUP BY t.type_name";

    @Query(value = INCOME, nativeQuery = true)
    List<Collection> getIncome(@Param("userId") Long userId, @Param("fro") Date from, @Param("to") Date to);


    public String EXPENSES = ""+
            "SELECT t.type_name, ABS(ROUND(CAST(COALESCE(SUM(tra.sum),0) as numeric),2)) " +
            "FROM transactions AS tra " +
            "INNER JOIN types AS t ON tra.type_type_id = t.type_id " +
            "WHERE tra.date >= :fro AND  tra.date < :to AND tra.sum < 0 AND tra.user_id = :userId " +
            "GROUP BY t.type_name";
    @Query(value = EXPENSES, nativeQuery = true)
    List<Collection> getExpenses(@Param("userId") Long userId,@Param("fro") Date from, @Param("to") Date to);

    public String GET_YEARS = ""+
            "SELECT Distinct EXTRACT(YEAR FROM date) " +
            "FROM transactions " +
            "WHERE user_id = :userId";
    @Query(value = GET_YEARS, nativeQuery = true)
    Collection getYears(@Param("userId") Long userId);

    public String GET_MONTHS = ""+
            "SELECT Distinct EXTRACT(MONTH FROM date) " +
            "FROM transactions " +
            "WHERE user_id = :userId AND " +
            "EXTRACT(YEAR FROM date) = :year";
    @Query(value = GET_MONTHS, nativeQuery = true)
    Collection getMoths(@Param("userId") Long userId, @Param("year") Integer year);


}
