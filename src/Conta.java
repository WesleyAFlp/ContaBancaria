import java.util.ArrayList;

// Criando a classe abstrata
public abstract class Conta{
    protected String numero;
    protected String titular;
    protected double saldo;
    protected ArrayList<Transacao> historicoTransacoes;
    //= new ArrayList();

    // Construtor da class
    public Conta(String numero, String titular, double saldo){
        this.numero = numero;
        this.titular = titular;
        if (saldo < 0.0){
            System.out.println();
        }else{
            this.saldo = saldo;
        }
    }

    // Métodos getters and setters
    // Número da conta
    public String getNumero(){
        System.out.println("NÚMERO: " + this.numero);
        return numero;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }
    // Titular da conta
    public String getTitular(){
        System.out.println("TITULAR: " + this.titular);
        return titular;
    }

    public void setTitular(String titular){
        this.titular = titular;
    }

    // Saldo
    public double getSaldo(){
        System.out.println("SALDO: " + this.saldo);
    }

    // Métodos

    public abstract void sacar(double valor);

    public void depositar(double valor){
        if (valor > 0){
            this.saldo += valor;
        }else{
            System.out.println("Não é possível depositar um valor negativo ou igual a 0!");
        }
    }


}
