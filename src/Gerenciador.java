import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class Gerenciador {
    private HashMap<String, String> map;
    private static Gerenciador g = null;

    private Gerenciador() {
        map = new HashMap<>();
    }

    public static Gerenciador getInstance() {
        if (g == null)
            g = new Gerenciador();
        return g;
    }

    public void readFile(String filename) {
        // Nome do arquivo passado na linha de comando
        try (Stream<String> stream = Files.lines(
                Paths.get("/home/eduardo/Documentos/PUCRS/T1 - ALGORITMO/src/casos/" + filename))) {
            stream.forEach(linha -> {
                // Quebrar a linha na letra e na string produzida
                var componentes = linha.split(" ");
                map.put(componentes[0], componentes.length > 1 ? componentes[1] : "");
            });
        } catch (Exception e) {
            System.out.println("Problemas no processamento do arquivo de entrada");
            e.printStackTrace();
        }
    }

    public String identificaLetraInicial(String filename) {
        // lê o arquivo
        readFile(filename);
        // HashMap auxiliar
        HashMap<String, String> auxMap = new HashMap<>();
        for (String key : map.keySet()) {
            // adiciona toda "key" que possui valor com lenght > 0
            if (map.get(key).length() > 0)
                auxMap.put(key, map.get(key));
            for (String value : map.values()) {
                // remove toda "key" que possui valor com alguma "key"
                if (value.contains(key))
                    auxMap.remove(key);
            }
        }
        String letraInicial = "";
        for (String key : auxMap.keySet()) {
            letraInicial += key;
        }
        return letraInicial;
    }

    public void calculaQtdLetras(String filename) {
        // lê o arquivo
        readFile(filename);
        // variável que armazena a primeira letra
        String letraInicial = identificaLetraInicial(filename);
        int qtdLetras = 0;
        ArrayList<String> letras = new ArrayList<>();
        String[] letrasArray = map.get(letraInicial).split("");
        // for (String key : map.keySet()) {
        for (String letra : letrasArray) {
            letras.add(letra);
        }
        // }
        System.out.println(letras);
        System.out.println(qtdLetras);
    }
}