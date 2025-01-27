package calculadora;

public class CalculadoraComplexa {

    public String somaComplexa(CalculadoraRequest request) {
        int resultado = request.getNumeroUm() + request.getNumeroDois();
        return "Soma do calculo: " + resultado;
    }
}
