import managers.Manager;

public class App {
    public static void main(String[] args) {
        // cria o objeto "Gerenciador"
        Manager g = Manager.getInstance();

        double start = System.currentTimeMillis();
        // g.wordSize("caso01_julio.txt");
        // g.wordSize("caso02_julio.txt");
        // g.wordSize("caso03_julio.txt");
        // g.wordSize("caso04_julio.txt");
        // g.wordSize("caso05_julio.txt");
        // g.wordSize("caso06_julio.txt");
        // g.wordSize("caso07_julio.txt");
        // g.wordSize("caso08_julio.txt");
        // g.wordSize("caso09_julio.txt");
        g.wordSize("caso10_julio.txt");

        // variável que calcula o tempo de execução
        double executionTime = System.currentTimeMillis() - start;
        System.out.println("Execution time: " + executionTime + " ms");
    }
}
