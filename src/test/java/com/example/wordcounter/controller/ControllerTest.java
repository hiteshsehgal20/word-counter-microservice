package com.example.wordcounter.controller;

import com.example.wordcounter.service.WordCountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @Mock
    WordCountService wordCountServiceImpl;

    @InjectMocks
    WordCountController controller;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addWordSuccessTest() {
        controller.addWord("test");
        verify(wordCountServiceImpl).addWords("test");
    }

    @Test
    public void getCountTest() {
        controller.getCount("test");
        verify(wordCountServiceImpl).getCountOfWord("test");
    }

    @Test
    public void getCountOfAllWordsTest() {
        controller.getCountOfAllWords();
        verify(wordCountServiceImpl).getCountofAllWords();
    }
}
