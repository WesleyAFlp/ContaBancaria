public class ContaPoupanca extends Conta {

    public ContaPoupanca(String numero, String titular, double saldoInicial) {
        super(numero, titular, saldoInicial);
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {

        if (valor <= 0) {
            System.out.println("O valor deve ser maior que zero.");
            return;
        }

        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para saque.");
        }

        this.saldo -= valor;

        historicoTransacoes.add(new Transacao("Saque", valor));
    }
}
