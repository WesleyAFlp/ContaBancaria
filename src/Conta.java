import java.util.ArrayList;
import java.util.List;

public abstract class Conta {

    protected String numero;
    protected String titular;
    protected double saldo;
    protected List<Transacao> historicoTransacoes;

    public Conta(String numero, String titular, double saldoInicial) {
        this.numero = numero;
        this.titular = titular;
        this.historicoTransacoes = new ArrayList<>();

        if (saldoInicial < 0) {
            System.out.println("Aviso: saldo inicial negativo. Definido como 0.");
            this.saldo = 0.0;
        } else {
            this.saldo = saldoInicial;
        }
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Transacao> getHistorico() {
        return historicoTransacoes;
    }

    public abstract void sacar(double valor) throws SaldoInsuficienteException;

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;

            // registra transação
            historicoTransacoes.add(new Transacao("Depósito", valor));
        } else {
            System.out.println("Valor inválido para depósito!");
        }
    }
}
