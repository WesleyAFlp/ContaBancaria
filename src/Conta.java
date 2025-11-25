import java.util.ArrayList;
import java.util.List;

// Criando a classe abstrata
public abstract class Conta {
    protected String numero;
    protected String titular;
    protected double saldo;
    protected List<Transacao> historicoTransacoes;

    // Construtor da class
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

    // Métodos getters and setters
    // Número da conta
    public String getNumero() {
        System.out.println("NÚMERO: " + this.numero);
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    // Titular da conta
    public String getTitular() {
        System.out.println("TITULAR: " + this.titular);
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }


    public double getSaldo() {
        System.out.println("SALDO: " + this.saldo);
        return this.saldo; // Adicionado o retorno
    }

    // Métodos

    public abstract void sacar(double valor);

    // Método depositar sobrescrito em ContaCorrente para incluir registro de Transacao
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        } else {
            System.out.println("Não é possível depositar um valor negativo ou igual a 0!");
        }
    }
}
