package com.example.wordcounter.util;

import org.springframework.stereotype.Component;

@Component("translatorProvider")
public class TranslatorProvider {
    public String translate(String str) {
        return str;
    }
}
