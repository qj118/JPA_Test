package org.demon.dao;

import org.demon.domain.Linkman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LinkmanDao extends JpaRepository<Linkman, Long>, JpaSpecificationExecutor<Linkman> {
}
