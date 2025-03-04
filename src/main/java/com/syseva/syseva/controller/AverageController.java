package com.syseva.syseva.controller;

import com.syseva.syseva.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AverageController {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @GetMapping("/average")
    public String showAverages(Model model) {
        // Récupère la moyenne des évaluations groupées par critère
        List<Object[]> averages = evaluationRepository.findAverageByCritere();
        model.addAttribute("averages", averages);
        return "average"; // correspond au fichier average.html dans src/main/resources/templates/
    }
}
