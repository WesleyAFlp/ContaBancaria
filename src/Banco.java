import java.util.HashMap;
import java.util.Map;

public class Banco {

    private Map<String, Conta> contas = new HashMap<>();

    public void adicionarConta(Conta c) {
        contas.put(c.getNumero(), c);
    }

    public Conta buscarConta(String numero) {
        return contas.get(numero);
    }

    public void realizarTransferencia(String origemNum, String destinoNum, double valor) {

        Conta origem = buscarConta(origemNum);
        Conta destino = buscarConta(destinoNum);

        if (origem == null || destino == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        try {
            origem.sacar(valor);

            destino.depositar(valor);

            origem.getHistorico().add(new Transacao("Transferência Enviada", valor));
            destino.getHistorico().add(new Transacao("Transferência Recebida", valor));

            System.out.println("Transferência realizada com sucesso!");

        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro na transferência: " + e.getMessage());
        }
    }
}
