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
                System.out.println("Entrada inválida!");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Tipo (1 - Corrente / 2 - Poupança): ");
                    int tipo = Integer.parseInt(sc.nextLine());

                    System.out.print("Número: ");
                    String numero = sc.nextLine();

                    System.out.print("Titular: ");
                    String titular = sc.nextLine();

                    System.out.print("Saldo inicial: ");
                    double saldo = Double.parseDouble(sc.nextLine());

                    if (tipo == 1) {
                        System.out.print("Limite cheque especial: ");
                        double limite = Double.parseDouble(sc.nextLine());
                        banco.adicionarConta(new ContaCorrente(numero, titular, saldo, limite));
                    } else {
                        banco.adicionarConta(new ContaPoupanca(numero, titular, saldo));
                    }

                    System.out.println("Conta criada!");
                    break;

                case 2:
                    System.out.print("Número da conta: ");
                    Conta c1 = banco.buscarConta(sc.nextLine());

                    if (c1 == null) { System.out.println("Conta não encontrada."); break; }

                    System.out.print("Valor: ");
                    try {
                        c1.sacar(Double.parseDouble(sc.nextLine()));
                    } catch (SaldoInsuficienteException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Número da conta: ");
                    Conta c2 = banco.buscarConta(sc.nextLine());
                    if (c2 == null) { System.out.println("Conta não encontrada."); break; }

                    System.out.print("Valor: ");
                    c2.depositar(Double.parseDouble(sc.nextLine()));
                    break;

                case 4:
                    System.out.print("Conta origem: ");
                    String origem = sc.nextLine();

                    System.out.print("Conta destino: ");
                    String destino = sc.nextLine();

                    System.out.print("Valor: ");
                    double val = Double.parseDouble(sc.nextLine());

                    banco.realizarTransferencia(origem, destino, val);
                    break;

                case 5:
                    System.out.print("Número da conta: ");
                    Conta c3 = banco.buscarConta(sc.nextLine());
                    if (c3 != null) System.out.println("Saldo: R$ " + c3.getSaldo());
                    else System.out.println("Conta não encontrada.");
                    break;

                case 6:
                    System.out.print("Número da conta: ");
                    Conta c4 = banco.buscarConta(sc.nextLine());
                    if (c4 == null) { System.out.println("Conta não encontrada."); break; }

                    System.out.println("\n=========== EXTRATO ===========");
                    for (Transacao t : c4.getHistorico()) {
                        System.out.println(t);
                    }
                    System.out.println("================================");
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
