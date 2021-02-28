package com.example.wordcounter.service;

import com.example.wordcounter.exception.InvalidWordException;

public interface WordCountService {

    void addWords(String words) throws InvalidWordException;

    int getCountOfWord(String word);

    String getCountofAllWords();
}
