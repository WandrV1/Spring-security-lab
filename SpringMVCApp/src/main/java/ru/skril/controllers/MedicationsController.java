package ru.skril.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.skril.utils.InputChecker;
import ru.skril.repository.MedicationRepository;
import ru.skril.entity.Medication;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/medications")
public class MedicationsController {

    private MedicationRepository rep;

    @Autowired
    public MedicationsController(MedicationRepository repository) {
        this.rep = repository;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("medications", rep.findAll());
        return "medications/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Optional<Medication> medication = rep.findById(id);
        if (medication.isPresent()) {
            model.addAttribute("medication", medication.get());
            return "medications/show";
        }
        return "redirect:/medications";
    }

    @GetMapping("/new")
    public String newMedication(@ModelAttribute("medication") Medication medication) {
        return "medications/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("medication") @Valid Medication medication,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "medications/new";
        }

        rep.save(medication);
        return "redirect:/medications";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        Optional<Medication> medication = rep.findById(id);
        if (medication.isPresent()) {
            model.addAttribute("medication", medication.get());
            return "medications/edit";
        }
        return "redirect:/medications";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("medication") @Valid Medication medication, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "medications/edit";
        }

        rep.save(medication);
        return "redirect:/medications";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        if (rep.existsById(id)) {
            rep.deleteById(id);
        }
        return "redirect:/medications";
    }

    @GetMapping("/search")
    public String search() {
        return "medications/search";
    }

    @PostMapping("/results")
    public String searchResults(@ModelAttribute("param") String param,
                                @ModelAttribute("type") String type,
                                Model model) {
        if (!InputChecker.checkSearchParam(param, type)) {
            return "redirect:/welcome";
        }
        switch (type) {
            case "name":
                model.addAttribute("medications", rep.findAllByNameStartingWith(param));
                break;
            case "substance":
                model.addAttribute("medications", rep.findAllBySubstanceStartingWith(param));
                break;
            case "country":
                model.addAttribute("medications", rep.findAllByCountryStartingWith(param));
                break;
            case "priceLess":
                model.addAttribute("medications",
                        rep.findAllByPriceIsLessThanEqual(Integer.parseInt(param)));
                break;
            case "priceBigger":
                model.addAttribute("medications",
                        rep.findAllByPriceIsGreaterThanEqual(Integer.parseInt(param)));
                break;
            case "priceEqual":
                model.addAttribute("medications", rep.findAllByPriceIs(Integer.parseInt(param)));
                break;
            case "ratingBigger":
                model.addAttribute("medications",
                        rep.findAllByRatingIsGreaterThanEqual(Double.parseDouble(param)));
                break;
            case "ratingLess":
                model.addAttribute("medications",
                        rep.findAllByRatingIsLessThanEqual(Double.parseDouble(param)));
                break;
            case "ratingEqual":
                model.addAttribute("medications",
                        rep.findAllByRatingIs(Double.parseDouble(param)));
                break;
            default:
                return "redirect:/welcome";
        }
        return "/medications/results";
    }
}
