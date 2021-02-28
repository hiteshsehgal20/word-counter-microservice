package com.example.wordcounter.service;

import com.example.wordcounter.exception.InvalidWordException;
import com.example.wordcounter.util.MutableInteger;
import com.example.wordcounter.util.TranslatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("wordCountServiceImpl")
public class WordCountServiceImpl implements WordCountService {

    private static final Map<String, MutableInteger> counterMap = new HashMap<>();

    @Autowired
    private TranslatorProvider translatorProvider;

    /** This method only expects a single word as input, if a string was to be passed,
     * you would split it through whitespaces or tokenize it into a list and,
     * then use the foreach to run through the if check.
     *
      * @param word
     * @throws InvalidWordException
     */
    @Override
    public void addWords(String word) throws InvalidWordException {
        if (isValidWord(word)) {
            counterMap.compute(translatorProvider.translate(word.toLowerCase()), (k, v) -> v == null ? new MutableInteger(0) : v)
                    .increment();
        } else {
            throw new InvalidWordException("Invalid Words");
        }

    }

    @Override
    public int getCountOfWord(String word) {
        if (isValidWord(word) && counterMap.containsKey(word.toLowerCase())) {
            return counterMap.get(translatorProvider.translate(word).toLowerCase()).getCount();
        }
        return 0;
    }

    @Override
    public String getCountofAllWords() {
        StringBuilder sb = new StringBuilder();
        counterMap.entrySet().forEach(entry -> {
            sb.append(entry.getKey() + " : " + entry.getValue().getCount() + "\n");
        });
        return sb.toString();
    }

    private boolean isValidWord(String str) {
        return ((str != null))
                && (!str.equals("")
                && (str.chars().allMatch(Character::isLetter)));
    }

}
