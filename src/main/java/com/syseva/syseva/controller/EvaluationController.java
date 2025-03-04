package com.syseva.syseva.controller;

import com.syseva.syseva.model.Evaluation;
import com.syseva.syseva.model.Critere;
import com.syseva.syseva.repository.EvaluationRepository;
import com.syseva.syseva.repository.CritereRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/evaluation")
@Tag(name = "Évaluations", description = "API pour gérer les évaluations")
public class EvaluationController {

    private final EvaluationRepository evaluationRepository;
    private final CritereRepository critereRepository;

    public EvaluationController(EvaluationRepository evaluationRepository, CritereRepository critereRepository) {
        this.evaluationRepository = evaluationRepository;
        this.critereRepository = critereRepository;
    }

    @GetMapping
    @Operation(summary = "Lister toutes les évaluations", description = "Récupère la liste de toutes les évaluations.")
    public String listEvaluations(Model model) {
        List<Evaluation> evaluations = evaluationRepository.findAll();
        model.addAttribute("evaluations", evaluations);
        return "evaluation";
    }

    @GetMapping("/add")
    @Operation(summary = "Ajouter une évaluation", description = "Ajoute une évaluation avec un critère donné.")
    public String showEvaluationForm(Model model) {
        model.addAttribute("evaluation", new Evaluation());
        model.addAttribute("criteres", critereRepository.findAll());
        return "evaluation_form";
    }

    @PostMapping("/add")
    @Operation(summary = "Ajouter une évaluation", description = "Ajoute une évaluation avec un critère donné.")
    public String saveEvaluation(@ModelAttribute Evaluation evaluation, @RequestParam int critereId) {
        Optional<Critere> critere = critereRepository.findById(critereId);
        critere.ifPresent(evaluation::setCritere);
        evaluation.setDateEvaluation(LocalDate.now());
        evaluationRepository.save(evaluation);
        return "redirect:/evaluation";
    }

    @PostMapping("/save")
    @Operation(summary = "Ajouter une évaluation", description = "Enregistrer une évaluation avec un critère donné.")
    public String saveEvaluation(@ModelAttribute Evaluation evaluation) {
        System.out.println("Enregistrement de l'évaluation : " + evaluation);
        evaluationRepository.save(evaluation);
        return "redirect:/evaluation";
    }


    @GetMapping("/edit/{id}")
    @Operation(summary = "Modifier une évaluation", description = "Modifie une évaluation existante.")
    public String editEvaluation(@PathVariable int id, Model model) {
        Optional<Evaluation> evaluation = evaluationRepository.findById(id);
        if (evaluation.isPresent()) {
            model.addAttribute("evaluation", evaluation.get());
            model.addAttribute("criteres", critereRepository.findAll());
            return "evaluation_form";
        }
        return "redirect:/evaluation";
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "Supprimer une évaluation", description = "Supprime une évaluation par son ID.")
    public String deleteEvaluation(@PathVariable int id) {
        evaluationRepository.deleteById(id);
        return "redirect:/evaluation";
    }

    @GetMapping("/historique")
    public String showHistorique(Model model) {
        model.addAttribute("evaluations", evaluationRepository.findAll());
        return "historique"; // Assure-toi que le fichier existe dans src/main/resources/templates/
    }
}
