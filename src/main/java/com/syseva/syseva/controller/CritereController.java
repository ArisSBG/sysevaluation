package com.syseva.syseva.controller;

import com.syseva.syseva.model.Critere;
import com.syseva.syseva.model.Evaluation;
import com.syseva.syseva.repository.CritereRepository;
import com.syseva.syseva.repository.EvaluationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/critere")
@Tag(name = "Critères", description = "API pour gérer les critères")
public class CritereController {
    private final CritereRepository critereRepository;
    private final EvaluationRepository evaluationRepository;

    public CritereController(CritereRepository critereRepository, EvaluationRepository evaluationRepository) {
        this.critereRepository = critereRepository;
        this.evaluationRepository = evaluationRepository;
    }

    // Afficher la liste des critères
    @GetMapping
    @Operation(summary = "Lister tous les critères",description = "Récupère la liste de tous les critères")
    public String showCriterePage(Model model) {
        List<Critere> criteres = critereRepository.findAll();
        model.addAttribute("criteres", criteres);
        model.addAttribute("critere", new Critere());
        return "critere";
    }

    // Ajouter un critère
    @PostMapping("/add")
    @Operation(summary = "Ajouter un critère", description = "ajoute un critère")
    public String addCritere(@ModelAttribute Critere critere) {
        critereRepository.save(critere);
        return "redirect:/critere";
    }

    // Afficher les détails d'un critère avec ses évaluations
    @GetMapping("/{id}")
    @Operation(summary = "Afficher les détails", description = "Afficher les détails d'un critères a travers son id")
    public String viewCritere(@PathVariable int id, Model model) {
        Optional<Critere> critere = critereRepository.findById(id);
        if (critere.isPresent()) {
            model.addAttribute("critere", critere.get());
            model.addAttribute("evaluations", evaluationRepository.findByCritereId(id));
            model.addAttribute("evaluation", new Evaluation());
            return "critere_detail";
        }
        return "redirect:/critere";
    }

    // Ajouter une évaluation à un critère
    @PostMapping("/{id}/addEvaluation")
    @Operation(summary = "Ajouter un critère", description = "Ajouter un critère ")
    public String addEvaluation(@PathVariable int id, @ModelAttribute Evaluation evaluation) {
        Optional<Critere> critere = critereRepository.findById(id);
        critere.ifPresent(evaluation::setCritere);
        evaluationRepository.save(evaluation);
        return "redirect:/critere/" + id;
    }

    //  Afficher le formulaire de modification d'un critère
    @GetMapping("/edit/{id}")
    @Operation(summary = "Modifier un critère", description = "Affiche le formulaire pour modifier un critère")
    public String editCritere(@PathVariable int id, Model model) {
        Optional<Critere> critere = critereRepository.findById(id);
        if (critere.isPresent()) {
            model.addAttribute("critere", critere.get());
            return "critere_form"; // Assure-toi d’avoir un fichier "critere_form.html"
        }
        return "redirect:/critere";
    }

    //  Modifier un critère (Enregistrer la modification)
    @PostMapping("/update")
    @Operation(summary = "Mettre à jour un critère", description = "Modifie un critère existant")
    public String updateCritere(@ModelAttribute Critere critere) {
        critereRepository.save(critere); // Si l’ID existe, JPA met à jour l'entrée
        return "redirect:/critere";
    }


    // Supprimer un critère
    @GetMapping("/delete/{id}")
    @Operation(summary = "Supprimer un critère", description = "supprimer un critère a travers son ID ")
    public String deleteCritere(@PathVariable int id) {
        critereRepository.deleteById(id);
        return "redirect:/critere";
    }
}
