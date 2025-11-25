public class ContaCorrente extends Conta {

    private double limiteChequeEspecial;

    public ContaCorrente(String numero, String titular, double saldoInicial, double limiteChequeEspecial) {
        super(numero, titular, saldoInicial);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0) {
            System.out.println("O valor deve ser positivo.");
            return;
        }

        double totalDisponivel = saldo + limiteChequeEspecial;

        if (valor > totalDisponivel) {
            throw new SaldoInsuficienteException(
                    "Saldo insuficiente. Disponível: R$ " + String.format("%.2f", totalDisponivel)
            );
        }

        saldo -= valor;

        historicoTransacoes.add(new Transacao("Saque", valor));

        System.out.println("Saque realizado.");
        System.out.println("Saldo atual: R$ " + String.format("%.2f", saldo));
    }
}
