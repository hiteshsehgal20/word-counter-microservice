package com.example.wordcounter.controller;

import com.example.wordcounter.service.WordCountService;
import com.example.wordcounter.exception.InvalidWordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class WordCountController {

    @Autowired
    WordCountService wordCountServiceImpl;

    @PostMapping("/addword")
    public void addWord(@RequestBody String word) {
        try {
            wordCountServiceImpl.addWords(word);
        } catch (InvalidWordException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/getword")
    public int getCount(@RequestBody String word) {
        return wordCountServiceImpl.getCountOfWord(word);
    }

    @GetMapping("/getallwords")
    public String getCountOfAllWords() {
        return wordCountServiceImpl.getCountofAllWords();
    }
}
