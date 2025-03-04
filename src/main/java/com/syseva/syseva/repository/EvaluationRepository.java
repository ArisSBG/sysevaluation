package com.syseva.syseva.repository;

import com.syseva.syseva.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
    List<Evaluation> findByCritereId(int critereId);
    @Query("SELECT e.critere, AVG(e.note) FROM Evaluation e GROUP BY e.critere ORDER BY e.critere.nom")
    List<Object[]> findAverageByCritere();
}
