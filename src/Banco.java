import java.util.HashMap;
import java.util.Map;

public class Banco {

    // Mapa que armazena todas as contas do banco
    // Chave = número da conta, Valor = objeto Conta
    private Map<String, Conta> contas = new HashMap<>();

    // Adiciona uma nova conta ao banco
    public void adicionarConta(Conta c) {
        contas.put(c.getNumero(), c);
    }

    // Busca uma conta pelo número
    // Retorna a conta encontrada ou null se não existir
    public Conta buscarConta(String numero) {
        return contas.get(numero);
    }

     //* Metodo para realizar transferência entre duas contas.
     //* Ele:
     //* 1. Busca a conta de origem e de destino
     //* 2. Verifica se ambas existem
     //* 3. Usa try/catch para tentar sacar da origem
     //* 4. Caso o saque funcione, deposita na conta destino
     //* 5. Registra as transações nas duas contas

    public void realizarTransferencia(String origemNum, String destinoNum, double valor) {
        Conta origem = buscarConta(origemNum);
        Conta destino = buscarConta(destinoNum);

        // Verifica se as contas existem
        if (origem == null || destino == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        try {
            // Tenta sacar da conta de origem
            origem.sacar(valor);

            // Depósito sempre funciona (valor positivo)
            destino.depositar(valor);

            // Registra na conta de origem
            origem.getNumero() .add(new Transacao("Transferência Enviada", valor));

            // Registra na conta destino
            destino.getNumero().add(new Transacao("Transferência Recebida", valor));

            System.out.println("Transferência realizada com sucesso.");

        } catch (SaldoInsuficienteException e) {
            // Caso o saldo seja insuficiente, o saque falha aqui
            System.out.println("Erro na transferência: " + e.getMessage());
        }
    }
}
