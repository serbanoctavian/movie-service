package com.typesoft.repository;

import com.typesoft.entity.MovieAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieAuditRepository extends JpaRepository<MovieAudit, Long> {
}
