public class ContaCorrente extends Conta {

    public ContaCorrente(String numero, String titular, double saldoInicial) {
        super(numero, titular, saldoInicial);
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0) {
            System.out.println("O saque deve ser positivo.");
            return;
        }

        if (valor > getSaldo()) {
            throw new SaldoInsuficienteException("Saldo insuficiente para saque.");
        }

        registrarSaque(valor, "Saque");
        System.out.println("Saque realizado. Saldo atual: R$ " + String.format("%.2f", getSaldo()));
    }
}
