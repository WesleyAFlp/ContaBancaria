import java.util.ArrayList;
import java.util.List;

public abstract class Conta {

    private String numero;
    private String titular;
    private double saldo;
    private List<Transacao> historicoTransacoes;

    public Conta(String numero, String titular, double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial não pode ser negativo.");
        }

        this.numero = numero;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.historicoTransacoes = new ArrayList<>();
    }

    public String getNumero() { return numero; }
    public String getTitular() { return titular; }
    public double getSaldo() { return saldo; }
    public List<Transacao> getHistorico() { return historicoTransacoes; }

    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Depósito deve ser positivo.");
            return;
        }

        saldo += valor;
        historicoTransacoes.add(new Transacao("Depósito", valor));
    }

    public abstract void sacar(double valor) throws SaldoInsuficienteException;

    protected void registrarSaque(double valor, String tipo) {
        saldo -= valor;
        historicoTransacoes.add(new Transacao(tipo, valor));
    }
}
