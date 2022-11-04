package com.lottery.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lottery.project.entity.History;

@Repository
public interface HistoryRepositoryJPA extends JpaRepository<History, Long>   {

}
