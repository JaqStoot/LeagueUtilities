package com.jaqstoot.leagueutilities.controller;

import com.jaqstoot.leagueutilities.service.RuneService;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/runes")
public class RuneController {
    private final RuneService runeService;

    public RuneController(RuneService runeService) {
        this.runeService = runeService;
    }

    @GetMapping("/all")
    public String getAllRunes() {
        try {
            JSONArray runePages = runeService.getRunePages();
            return runePages.toString();
        } catch (IOException e) {
            return "Error fetching rune pages: " + e.getMessage();
        }
    }

    @PostMapping("/create")
    public String createRunePage() {
        try {
            return runeService.createRunePage();
        } catch (IOException e) {
            return "Error creating rune page: " + e.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRunePage(@PathVariable int id) {
        try {
            return runeService.deleteRunePage(id);
        } catch (IOException e) {
            return "Error deleting rune page: " + e.getMessage();
        }
    }
}
