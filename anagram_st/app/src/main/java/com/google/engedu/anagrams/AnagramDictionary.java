package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    HashSet wordSet = new HashSet();
    ArrayList wordList = new ArrayList();
    HashMap<String,ArrayList<String>> lettersToWord = new HashMap();

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;

        while ((line = in.readLine()) != null) {

            ArrayList<String> temp = new ArrayList<>();
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
            String sortedWord = sortWord(word);
            if( lettersToWord.containsKey(sortedWord))
            {
                temp =  lettersToWord.get(sortedWord); //get value of sorted word in temp
                temp.add(word); //add array list corresponding to key to temp
                lettersToWord.put(sortedWord,temp); //update temp in hashmap
            }
            else
            {
                ArrayList<String> newWord = new ArrayList<>();
                newWord.add(word);
                lettersToWord.put(sortedWord,newWord);
            }
        }

    }


    public boolean isGoodWord(String word, String base) {
       if (wordSet.contains(word))
       {
           if(word.contains(base))
           {
               return (false);
           }
           else
           {
               return (true);
           }
       }
        else
       {
           return (false);
       }
    }


    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> intermediateResult = new ArrayList<>();
        for(char i = 'a'; i<='z'; i++)
        {
            String anagram  = (word + i); //append one character
            String sortedAnagram = sortWord(anagram); //sort new word
            if(lettersToWord.containsKey(sortedAnagram))
            {
                intermediateResult = lettersToWord.get(sortedAnagram);
                for(String s:intermediateResult){
                    if(isGoodWord(s,word)){
                        result.add(s);
                    }
                }
            }

        }
        return result;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }


    public String sortWord(String word) {
        String temp=new String();

        char[] cArray = word.toCharArray();
        Arrays.sort(cArray);

        for (Character c:cArray){
            temp=temp+c;
        }
        return temp;
    }

}