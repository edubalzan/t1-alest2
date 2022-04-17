package managers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

public class Manager {
    // store data ".txt"
    private HashMap<String, String> data;
    private static Manager g = null;
    // store letter frequency
    private HashMap<String, Long> lengthLetters = new HashMap<>();

    // constructor
    private Manager() {
        data = new HashMap<>();
    }

    public static Manager getInstance() {
        if (g == null)
            g = new Manager();
        return g;
    }

    private void readFile(String filename) {
        // Nome do arquivo passado na linha de comando
        try (Stream<String> stream = Files.lines(
                Paths.get("src/cases/" + filename))) {
            stream.forEach(linha -> {
                // Quebrar a linha na letter e na string produzida
                var componentes = linha.split(" ");
                data.put(componentes[0], componentes.length > 1 ? componentes[1] : "");
            });
        } catch (Exception e) {
            System.out.println("Problemas no processamento do arquivo de entrada");
            e.printStackTrace();
        }
    }

    private String identifiesInitialLetter(String filename) {
        // read file
        readFile(filename);
        // auxiliary "HashMap"
        HashMap<String, String> auxData = new HashMap<>();
        for (String key : data.keySet()) {
            // adds every "key" that has value with length > 0
            if (data.get(key).length() > 0)
                auxData.put(key, data.get(key));
            for (String value : data.values()) {
                // remove all "key" that has value with some "key"
                if (value.contains(key))
                    auxData.remove(key);
            }
        }
        String letterInicial = "";
        for (String key : auxData.keySet())
            letterInicial += key;
        return letterInicial;
    }

    // FIRST WAY
    /*
     * private int wordSize(String initialLetter, String currentValue) {
     * // initialLetter's letters
     * String letters[] = data.get(initialLetter).split("");
     * // store letters with value != ""
     * HashSet<String> lettersWithValue = new HashSet<>();
     * // letter identifier with value != ""
     * boolean hasLetterWithValue = true;
     * while (hasLetterWithValue) {
     * for (String letter : letters) {
     * if (data.get(letter).length() == 0)
     * currentValue += letter;
     * else {
     * lettersWithValue.add(letter);
     * currentValue += data.get(letter).replaceAll(letter, data.get(letter));
     * }
     * if (!currentValue.contains(letter))
     * lettersWithValue.remove(letter);
     * }
     * letters = currentValue.split("");
     * currentValue = "";
     * if (lettersWithValue.isEmpty())
     * hasLetterWithValue = false;
     * }
     * return letters.length;
     * }
     */

    // SECOND WAY
    /*
     * private long wordSize(String initialLetter, String currentValue) {
     * // initialLetter's letters
     * String letters[] = data.get(initialLetter).split("");
     * // go through each letter
     * for (String letter : letters) {
     * // check if letter value has length == or != 0
     * if (data.get(letter).length() == 0)
     * count++;
     * else
     * lengthLetters.put(letter, wordSize(letter, data.get(letter)));
     * }
     * return count;
     * }
     */

    // THIRD WAY
    private long calculateNumberOfLetters(String initialLetter) {
        long count = 0;
        // initialLetter's letters
        String letters[] = data.get(initialLetter).split("");
        // go through each letter
        for (String letter : letters) {
            // check if letter value has length == or != 0
            if (data.get(letter).length() == 0)
                count++;
            else {
                if (lengthLetters.containsKey(letter))
                    count += lengthLetters.get(letter);
                else {
                    lengthLetters.put(letter, calculateNumberOfLetters(letter));
                    count += lengthLetters.get(letter);
                }
            }
        }
        return count;
    }

    public void wordSize(String filename) {
        System.out.println("Initial letter: " + identifiesInitialLetter(filename));
        System.out.println(calculateNumberOfLetters(identifiesInitialLetter(filename)));
    }
}