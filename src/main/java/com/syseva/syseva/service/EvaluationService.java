package com.syseva.syseva.service;


import com.syseva.syseva.model.Evaluation;
import com.syseva.syseva.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {
    @Autowired
    private EvaluationRepository evaluationRepository;

    // Récupérer toutes les évaluations
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    // Récupérer une évaluation par son ID
    public Evaluation getEvaluationById(int id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation non trouvée avec l'ID: " + id));
    }

    // Créer une nouvelle évaluation
    public Evaluation createEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    // Récupérer toutes les évaluations pour un critère spécifique
    public List<Evaluation> getEvaluationsByCritereId(int critereId) {
        return evaluationRepository.findByCritereId(critereId);
    }


}
