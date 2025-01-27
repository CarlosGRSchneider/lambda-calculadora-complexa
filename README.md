# lambda-calculadora-complexa

Esta função Lambda faz parte de um **circuit breaker serverless** implementado na AWS. Ela simula um cálculo que pode ser altamente complexo, gerando alta latência de forma controlada. Em caso de falhas ou latência excessiva, o circuito redireciona as requisições para uma função fallback. O fallback desta função está disponível no repositório: [lambda-fallback-calculadora-complexa](https://github.com/CarlosGRSchneider/lambda-fallback-calculadora-complexa).

## Descrição

A função realiza operações de soma simples com base nos valores fornecidos, mas simula alta latência para representar cálculos complexos. A latência simulada é configurável por meio da variável de ambiente `TAXA_DE_LATENCIA`.

## Configuração

- **Variáveis de ambiente**:
  - `TAXA_DE_LATENCIA`: (opcional) Define a latência simulada em milissegundos. Valor padrão: `0` (sem latência).

## Endpoint

A função é acessada via API Gateway como um **endpoint POST**. O payload deve conter os números para cálculo no seguinte formato:

```json
{
  "numeroUm": 5,
  "numeroDois": 3
}
```

## Exemplo de Retorno

Em uma execução bem-sucedida, a função retorna uma string no formato:

```
"Soma do calculo: 8"
```

Se a latência for configurada, a função pode demorar antes de retornar a resposta.

## Estrutura do Projeto

### Classes Principais

1. **`CalculadoraComplexa`**
   - Contém a lógica de soma dos números fornecidos.

2. **`CalculadoraComplexaLambda`**
   - Classe principal da função Lambda.
   - Configura a latência com base na variável de ambiente.
   - Implementa a lógica de soma e simulação de latência.

3. **`CalculadoraRequest`**
   - Classe que define a estrutura do payload de entrada.

## Relação com o Fallback

O fallback desta função retorna uma mensagem simples indicando que o serviço está indisponível no momento. Consulte o repositório do fallback para mais informações: [lambda-fallback-calculadora-complexa](https://github.com/CarlosGRSchneider/lambda-fallback-calculadora-complexa).
