//Jooyoung Song 101022942
//Danny Nguyen 100882851
package com.company;
import java.io.*;
import java.util.*;


public class Main {
    static Scanner sc = new Scanner(System.in);
    static Dictionary newWord = new Dictionary();
    public static void main(String[] args){

        start(newWord);
    }
    public static int getRangeInt(int min, int max) { // function for setting range of integer for user to enter
        int num = -1;
        do {
            try {
                num = Integer.parseInt(sc.nextLine());
                if (num < min || num > max) {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException ex) {
                num = -1;
                System.out.println("Invalid Entry! Please enter between " + min + " to " + max + "!");
            }
        } while (num == -1);
        return num;
    }
    public static int menu() {
        System.out.println("--------------------------------------------------------");
        System.out.println("                           Menu                         ");
        System.out.println("--------------------------------------------------------");
        System.out.println("                1. Add New Word                         ");
        System.out.println("                2. Delete Word                          ");
        System.out.println("                3. Get Meaning                          ");
        System.out.println("                4. Dictionary List                      ");
        System.out.println("                5. Spell check a text file              ");
        System.out.println("                6. Exit                                 ");
        System.out.println("--------------------------------------------------------");
        System.out.println("        Please choose what you would like to do:        ");
        return getRangeInt(1, 6);
    }
    public static void start(Dictionary dict){ //main program ran by this function

        try{
            File wordFile = new File("wordList.txt");
            FileReader wordTxt = new FileReader(wordFile);
            BufferedReader words = new BufferedReader(wordTxt);
            //words.readLine();
            String line = words.readLine();
            while (line != null){
                String definition = "Undefined Word";
                dict.add(line,definition);
                line = words.readLine();
            }words.close();
        }catch(FileNotFoundException fileNotFoundException){
            System.out.println("File not found.");
        }catch(IOException e){
            System.out.println("File input error");
        }

        boolean isStart = true;

        while (isStart) {
            int choice = menu();
            switch (choice) {
                case 1:
                    System.out.println("Please enter a new word: ");
                    String word = sc.nextLine();
                    System.out.println("Please enter the meaning of the word: ");
                    String meaning = sc.nextLine();
                    if (dict.add(word,meaning)){
                        System.out.println("Added " + word + " successfully.");
                    }else{
                        System.out.println("Failed to add.");
                    }
                    break;
                case 2:
                    System.out.println("Please enter a word to delete: ");
                    String deleteWord = sc.next().toLowerCase();
                    sc.nextLine();
                    if (!dict.delete(deleteWord)){
                        System.out.println("Word was not found");
                    }else{
                        System.out.println("Word was deleted");
                    }
                    break;
                case 3:
                    System.out.println("Please enter the word to get the meaning: ");
                    String input = sc.nextLine();
                    dict.getMeaning(input);
                    break;
                case 4:
                    dict.printWordList();
                    System.out.println(dict.getCount());
                    break;
                case 5:
                    System.out.println("Please enter the name of the text file: ");
                    String fileName = sc.nextLine();
                    try{
                        File wordFile = new File(fileName);
                        Scanner newWords = new Scanner(wordFile);
                        while (newWords.hasNext()){
                            String words = newWords.next().replace(",", "").replace(".", "").toLowerCase();
                            if(!dict.exists(words)){
                                System.out.println(words);
                            }
                        }
                    }catch(FileNotFoundException fileNotFoundException){
                        System.out.println("File not found.");
                    }
                    break;
                default:
                    System.out.println("Okay, See you again! Bye!");
                    return;
            }
            System.out.println("\nWould you like to go back to the Menu? (Y/N)");
            isStart = yesNo();
            sc.nextLine();
        }

    }
    public static boolean yesNo() {
        String answer;
        boolean yn;

        while (true) {
            answer = sc.next();
            if (answer.equals("y") || answer.equals("Y")) {
                return yn = true;
            } else if (answer.equals("n") || answer.equals("N")) {
                return yn = false;
            } else {
                System.out.println("Invalid Entry. Please answer Y/N");
            }
        }
    }

}