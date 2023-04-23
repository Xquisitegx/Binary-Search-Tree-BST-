//Jooyoung Song 101022942
//Danny Nguyen 100882851
package com.company;

public class WordInfo {
    public String word;
    public String meaning;
    public WordInfo left;
    public WordInfo right;

    public WordInfo(String word, String meaning){
        this.word = word;
        this.meaning = meaning;
        this.left = null;
        this.right = null;
    }
    public WordInfo(String word){
        this.word = word;
        this.left = null;
        this.right=null;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setRight(WordInfo pright){
        right = pright;
    }

    public WordInfo getRight(){
        return right;
    }

    public void setLeft(WordInfo pleft){
        left = pleft;
    }

    public WordInfo getLeft(){
        return left;
    }

}
