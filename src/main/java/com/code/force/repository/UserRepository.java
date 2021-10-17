package com.code.force.repository;

import com.code.force.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Query("select e from Users e WHERE e.firstName = :name")
    List<Users> findByFirstNameQuery(@Param("name") String name);

    @Query("select e from Users e WHERE e.role = :role")
    List<Users> findByRoleQuery(@Param("role") String role);

    @Query("select e from Users e WHERE e.userID = :userID")
    Users findByIDQuery(@Param("userID") Long userID);

    @Transactional
    @Modifying
    @Query(value = "update users set first_name=:firstName where userid=:userID", nativeQuery = true)
    void updateFirstNameNative(@Param("firstName") String firstName, @Param("userID") Long userID);

    @Query("select e from Users e WHERE e.userName = :userName")
    Users userExists(@Param("userName") String userName);

    @Transactional
    @Modifying
    @Query(value = "update users set last_name=:lastName where userid=:userID", nativeQuery = true)
    void updateLastNameNative(@Param("userID") Long userID, @Param("lastName") String lastName);

    @Transactional
    @Modifying
    @Query(value = "update users set user_name=:userName where userid=:userID", nativeQuery = true)
    void updateUserNameNative(@Param("userID") Long userID, @Param("userName") String userName);

    @Transactional
    @Modifying
    @Query(value = "update users set password=:password where userid=:userID", nativeQuery = true)
    void updatePasswordNative(@Param("userID") Long userID, @Param("password") String password);

    @Transactional
    @Modifying
    @Query(value = "update users set role=:role where userid=:userID", nativeQuery = true)
    void updateRoleNative(@Param("userID") Long userID, @Param("role") String role);

    @Transactional
    @Modifying
    @Query(value = "update users set blocked=:blocked where userid=:userID", nativeQuery = true)
    void updateBlockedNative(@Param("userID") Long userID, @Param("blocked") Integer blocked);

    @Query(value = "update Users e set e.firstName='Margot' WHERE e.userID=2")
    List<Users> updateName(@Param("userID") Long userID, @Param("firstName") String firstName);
}
