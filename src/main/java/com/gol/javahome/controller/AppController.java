package com.gol.javahome.controller;

import java.sql.Date;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AppController {

    // prodotti divisi per ambiente casa
    @GetMapping("/prodotti")
    public String prodotti() {
        return "prodotti.html";
    }

    // redirect Utente per aggiungere prodotti/clienti+ lista clienti
    @GetMapping("/nuovo")
    public String nuovo() {
        return "nuovo.html";
    }

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/findAllClienti")
    public String clienti(Model model) {

        List<Cliente> cli = cliDao.findAll();
        model.addAttribute("listaClienti", cli);

        return "nuovo.html";
    }

    @GetMapping("/new_cliente")
    public String newCliente(Model model, @RequestParam HashMap<String, String> params) {
        // import org.springframework.ui.Model;
        cliDao.save(
                new Cliente(
                        0,
                        params.get("nome"),
                        params.get("cognome"),
                        Date.valueOf(params.get("nascita")),
                        params.containsKey("tesserato")));

        return "redirect:/";

    }

    /* restituisce una stringa(desing pattern) cerca una pagina con quel nome */
    @GetMapping("/findAllProdotti")
    /* Model di Spring */
    public String prodotti(Model model) {
        List<Prodotto> prods = proDao.findAll();
        model.addAttribute("listaProdotti", prods);
        return "prodotti.html";
    }

    @GetMapping("/new_prodotto")
    public String newProdotto(Model model, @RequestParam HashMap<String, String> params) {
        // import org.springframework.ui.Model;
        proDao.save(
                new Prodotto(
                        0,
                        params.get("nome"),
                        params.get("tipo"),
                        Double.parseDouble(params.get("prezzo")),
                        Integer.parseInt(params.get("quantita"))));

        return "redirect:/findAllProdotti";

    }
}
