import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Banco banco = new Banco();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== CAIXA ELETRÔNICO =====");
            System.out.println("1. Criar Conta");
            System.out.println("2. Sacar");
            System.out.println("3. Depositar");
            System.out.println("4. Transferir");
            System.out.println("5. Consultar Saldo");
            System.out.println("6. Extrato");
            System.out.println("7. Sair");
            System.out.print("Escolha: ");

            int opcao;

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Valor inválido!");
                continue;
            }

            switch (opcao) {

                case 1:
                    System.out.print("Tipo (1-Corrente / 2-Poupança): ");
                    int tipo = Integer.parseInt(sc.nextLine());

                    System.out.print("Número da conta: ");
                    String numero = sc.nextLine();

                    System.out.print("Titular: ");
                    String titular = sc.nextLine();

                    System.out.print("Saldo inicial: ");
                    double saldoInicial = Double.parseDouble(sc.nextLine());

                    if (tipo == 1) {
                        System.out.print("Limite do cheque especial: ");
                        double limite = Double.parseDouble(sc.nextLine());

                        banco.adicionarConta(new ContaCorrente(numero, titular, saldoInicial, limite));

                    } else {
                        banco.adicionarConta(new ContaPoupanca(numero, titular, saldoInicial));
                    }

                    System.out.println("Conta criada!");
                    break;

                case 2:
                    System.out.print("Número da conta: ");
                    Conta c1 = banco.buscarConta(sc.nextLine());

                    if (c1 == null) {
                        System.out.println("Conta não encontrada.");
                        break;
                    }

                    System.out.print("Valor do saque: ");
                    double valorSaque = Double.parseDouble(sc.nextLine());

                    try {
                        c1.sacar(valorSaque);
                    } catch (SaldoInsuficienteException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Número da conta: ");
                    Conta c2 = banco.buscarConta(sc.nextLine());

                    if (c2 == null) {
                        System.out.println("Conta não encontrada.");
                        break;
                    }

                    System.out.print("Valor do depósito: ");
                    double valorDep = Double.parseDouble(sc.nextLine());

                    c2.depositar(valorDep);
                    break;

                case 4:
                    System.out.print("Conta de origem: ");
                    String origem = sc.nextLine();

                    System.out.print("Conta de destino: ");
                    String destino = sc.nextLine();

                    System.out.print("Valor: ");
                    double valorTransf = Double.parseDouble(sc.nextLine());

                    banco.realizarTransferencia(origem, destino, valorTransf);
                    break;

                case 5:
                    System.out.print("Número da conta: ");
                    Conta c3 = banco.buscarConta(sc.nextLine());

                    if (c3 == null) {
                        System.out.println("Conta não encontrada.");
                        break;
                    }

                    System.out.println("Saldo: R$ " + String.format("%.2f", c3.getSaldo()));
                    break;

                case 6:
                    System.out.print("Número da conta: ");
                    Conta c4 = banco.buscarConta(sc.nextLine());

                    if (c4 == null) {
                        System.out.println("Conta não encontrada.");
                        break;
                    }

                    System.out.println("\n===== EXTRATO =====");

                    if (c4.getHistorico().isEmpty()) {
                        System.out.println("Nenhuma transação encontrada.");
                    } else {
                        for (Transacao t : c4.getHistorico()) {
                            System.out.println(t);
                        }
                    }

                    System.out.println("=====================");
                    break;

                case 7:
                    System.out.println("Encerrando...");
                    System.exit(0);

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
