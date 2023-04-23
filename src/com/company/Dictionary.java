//Jooyoung Song 101022942
//Danny Nguyen 100882851
package com.company;

public class Dictionary {

    private WordInfo root;
    private int count;

    public boolean add(String pWord, String pMeaning){
        WordInfo newWord = new WordInfo(pWord,pMeaning);

        if (root == null) {
            root = newWord;
            count++;
            return true;
        }
        WordInfo parent,current;
        current = parent = root;
        while(current != null){
            parent = current;
            if (pWord.compareToIgnoreCase(current.getWord()) > 0){
                current = current.getRight();
            }else if (pWord.compareToIgnoreCase(current.getWord()) < 0){
                current = current.getLeft();
            }else if(pWord.compareToIgnoreCase(current.getWord()) == 0){
                break;
            }
        }
        if (pWord.compareToIgnoreCase(parent.getWord())>0){
            parent.right = newWord;
            count++;
            return true;
        }else if(pWord.compareToIgnoreCase(parent.getWord())<0){
            parent.left = newWord;
            count++;
            return true;
        }else{
            return false;
        }
    }

    public void printWords(WordInfo current){
        if (current == null) return;
        printWords(current.getLeft());
        System.out.println(current.getWord());
        printWords(current.getRight());
    }
    public void printWordList(){
        printWords(root);
        System.out.println("");
    }

    public void printWordAndMeaning(WordInfo current){
        if (current == null) return;
        printWordAndMeaning(current.getLeft());
        System.out.println(current.getWord()+": "+current.getMeaning());
        printWordAndMeaning(current.getRight());
    }

    public void printDictionary(){
        printWordAndMeaning(root);
        System.out.println("");
    }

    public int getCount(){
        return count;
    }

    public boolean exists(String word){
        WordInfo curr = root;
        if (root == null) return false;
        while(curr != null && !curr.word.equals(word)){
            if (word.compareTo(curr.word)> 0){
                curr = curr.right;
            }else{
                curr = curr.left;
            }
        }
        return curr != null;
    }


    public WordInfo getWordMeaning(WordInfo pRoot, String pWord){

        WordInfo found = null;

        if(pRoot == null){
            found = null;
            System.out.println("That word doesn't exist");
        }else{
            if(pWord.equals(pRoot.getWord())) {
                found = pRoot;
                System.out.println("The meaning for " + pWord + " is: " + pRoot.getMeaning());
            }else if(pWord.compareTo(pRoot.getWord()) < 0){
                found = getWordMeaning(pRoot.getLeft(), pWord);
            }else{
                found = getWordMeaning(pRoot.getRight(), pWord);
            }
        }return found;
    }

    public String getMeaning(String pWord){
        getWordMeaning(root, pWord);
        return pWord;
    }

    public boolean delete(String pWord){
        root = deleteRec(root, pWord);
        count--;
        return true;
    }

    public WordInfo deleteRec(WordInfo root, String pWord){
        if (root == null) return root;
        if (pWord.compareToIgnoreCase(root.getWord()) < 0){
            root.left = deleteRec(root.left, pWord);
        }else{
            if (pWord.compareToIgnoreCase(root.getWord()) > 0){
                root.right = deleteRec(root.right, pWord);
            }else{
                if (root.left == null) return root.right;
                if (root.right == null) return root.left;
                WordInfo successor = root.right;
                while (successor.left != null){
                    successor = successor.left;
                }
                root.word = successor.word;
                root.right = deleteRec(root.right, successor.word);
            }
        }
        return root;
    }
}

