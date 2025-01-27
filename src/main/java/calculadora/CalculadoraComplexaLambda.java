package calculadora;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CalculadoraComplexaLambda implements RequestHandler<CalculadoraRequest, String> {

    private final CalculadoraComplexa calculadora = new CalculadoraComplexa();
    private final int taxaDeLatencia;

    public CalculadoraComplexaLambda() {
        String taxaDeLatenciaEnv = System.getenv("TAXA_DE_LATENCIA");
        this.taxaDeLatencia = taxaDeLatenciaEnv != null ? Integer.parseInt(taxaDeLatenciaEnv) : 0;
    }

    @Override
    public String handleRequest(CalculadoraRequest request, Context context) {
        context.getLogger().log("TAXA_DE_LATENCIA: " + taxaDeLatencia);

        aplicaLatencia(context);

        String result = calculadora.somaComplexa(request);
        context.getLogger().log("Resultado do calculo: " + result);
        return result;
    }

    private void aplicaLatencia(Context context) {
        if (taxaDeLatencia > 0) {
            try {
                context.getLogger().log("Gerando latencia de " + taxaDeLatencia + " ms");
                Thread.sleep(taxaDeLatencia);
            } catch (InterruptedException e) {
                context.getLogger().log("Erro para a simulação de latencia: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
