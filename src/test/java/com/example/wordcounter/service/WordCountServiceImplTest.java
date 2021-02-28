package com.example.wordcounter.service;

import com.example.wordcounter.exception.InvalidWordException;
import com.example.wordcounter.util.TranslatorProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WordCountServiceImplTest {

    @Mock
    TranslatorProvider translatorProvider;

    @InjectMocks
    WordCountServiceImpl wordCountServiceImpl;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addWordsSuccessCaseInsensitive() throws InvalidWordException {
        when(translatorProvider.translate(anyString())).thenReturn("test");
        wordCountServiceImpl.addWords("Test");
        wordCountServiceImpl.addWords("teSt");
        Mockito.verify(translatorProvider, Mockito.times(2)).translate("test");
        assertEquals(2, wordCountServiceImpl.getCountOfWord("test"));
    }

    @Test(expected = InvalidWordException.class)
    public void addWordsValidateStringsWithChars() throws InvalidWordException {
        wordCountServiceImpl.addWords("Test1");
        assertEquals(0, wordCountServiceImpl.getCountOfWord("Test1"));
    }

    @Test(expected = InvalidWordException.class)
    public void addWordsNulls() throws InvalidWordException {
        wordCountServiceImpl.addWords(null);
    }

    @Test
    public void getCountOfWord() {
        when(translatorProvider.translate(anyString())).thenReturn("test");
        assertEquals(2, wordCountServiceImpl.getCountOfWord("test"));
    }

    @Test
    public void getCountofAllWordsTest() {
        String value ="word : 2\n";
        when(translatorProvider.translate(anyString())).thenReturn("word");
        wordCountServiceImpl.addWords("word");
        wordCountServiceImpl.addWords("word");
        assertEquals(value,wordCountServiceImpl.getCountofAllWords());
    }

    @Test
    public void getCountofAllWordsEmpty() {
        String value ="";
        assertEquals(value,wordCountServiceImpl.getCountofAllWords());

    }

}