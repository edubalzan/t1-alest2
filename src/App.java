public class App {
    public static void main(String[] args) {
        Gerenciador g = Gerenciador.getInstance();

        g.calculaQtdLetras("caso01_julio.txt");
        g.calculaQtdLetras("caso02_julio.txt");
        g.calculaQtdLetras("caso03_julio.txt");
        g.calculaQtdLetras("caso04_julio.txt");
        g.calculaQtdLetras("caso05_julio.txt");
        g.calculaQtdLetras("caso06_julio.txt");
        g.calculaQtdLetras("caso07_julio.txt");
        g.calculaQtdLetras("caso08_julio.txt");
        g.calculaQtdLetras("caso09_julio.txt");
        g.calculaQtdLetras("caso10_julio.txt");
    }
}
