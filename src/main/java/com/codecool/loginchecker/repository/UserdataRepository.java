package com.codecool.loginchecker.repository;

import com.codecool.loginchecker.model.Userdata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserdataRepository extends JpaRepository<Userdata, Long> {
}
