import java.util.ArrayList;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(String numero, String titular, double saldoInicial) {
        super(numero, titular, saldoInicial);
        this.historicoTransacoes = new ArrayList<>();
    }

    // sobrescrevendo o metodo sacar
    @Override
    public void sacar(double valor) {
        // caso o valor for 0 ou negativo, printa uma mensagem e retorna
        if (valor <= 0) {
            System.out.println("O valor do saque deve ser maior que zero.");
            return;
        }

        // se o valor for maior do que o saldo, lança a SaldoInsuficienteException
        if (valor > this.saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar o saque.");
        }

        this.saldo -= valor;

        // registra a transação
        historicoTransacoes.add(new Transacao("Saque", valor));
    }
}
