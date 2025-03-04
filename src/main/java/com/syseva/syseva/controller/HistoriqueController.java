package com.syseva.syseva.controller;



import com.syseva.syseva.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoriqueController {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @GetMapping("/historique")
    public String showHistorique(Model model) {
        model.addAttribute("evaluations", evaluationRepository.findAll());
        return "historique"; // Ce nom correspond au fichier historique.html
    }
}
