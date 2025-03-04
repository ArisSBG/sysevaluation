package com.syseva.syseva.service;


import com.syseva.syseva.model.Critere;
import com.syseva.syseva.model.Evaluation;
import com.syseva.syseva.repository.CritereRepository;
import com.syseva.syseva.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CritereService {
    @Autowired
    private CritereRepository critereRepository;

    // Injection du repository pour accéder aux évaluations associées aux critères.
    @Autowired
    private EvaluationRepository evaluationRepository;

    // Récupérer tous les critères
    public List<Critere> getAllCriteres() {
        return critereRepository.findAll();
    }

    // Récupérer un critère par son ID
    public Critere getCritereById(int id) {
        return critereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Critère non trouvé avec l'ID: " + id));
    }

    // Créer un nouveau critère
    public Critere createCritere(Critere critere) {
        return critereRepository.save(critere);
    }

    // Mettre à jour un critère existant
    public Critere updateCritere(int id, Critere critereDetails) {
        Critere existingCritere = getCritereById(id);
        existingCritere.setNom(critereDetails.getNom());
        existingCritere.setDescription(critereDetails.getDescription());
        return critereRepository.save(existingCritere);
    }

    // Supprimer un critère
    public void deleteCritere(int id) {
        critereRepository.deleteById(id);
    }

    // Calculer et renvoyer la moyenne des notes pour un critère donné
    public double getAverageNoteForCritere(int id) {
        Critere critere = getCritereById(id);
        List<Evaluation> evaluations = critere.getEvaluations();
        if (evaluations == null || evaluations.isEmpty()) {
            return 0.0;
        }
        double sum = evaluations.stream().mapToInt(Evaluation::getNote).sum();
        return sum / evaluations.size();
    }
}
