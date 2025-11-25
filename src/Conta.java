import java.util.ArrayList;
import java.util.List;

public abstract class Conta {
    protected String numero;
    protected String titular;
    protected double saldo;
    protected List<Transacao> historicoTransacoes;

    public Conta(String numero, String titular, double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.historicoTransacoes = new ArrayList<>();

        if (saldo < 0.0) {
            System.out.println("Aviso: Saldo inicial negativo. Definindo para 0.0.");
            this.saldo = 0.0;
        } else {
            this.saldo = saldo;
        }
    }

    // --- ADICIONE ISTO AQUI ---
    public List<Transacao> getHistorico() {
        return this.historicoTransacoes;
    }
    // ---------------------------

    public String getNumero() { return numero; }
    public String getTitular() { return titular; }
    public double getSaldo() { return saldo; }

    public abstract void sacar(double valor);

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        } else {
            System.out.println("Não é possível depositar valor negativo ou zero.");
        }
    }
}
