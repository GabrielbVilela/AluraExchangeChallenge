import Models.modelApi;

import java.util.Scanner;

public class Interface {
    private static String moedaBase = "";
    private static String moedaAlvo = "";
    private static boolean primeiroUso = true;
    private static int opcao = 0;


    public static void exibir(Scanner scanner) {
        menu(scanner);
    }

    private static void menu(Scanner scanner) {
        if (opcao != 8) {
            while (opcao != 8) {
                String informacao = primeiroUso ?
                        "" :
                        """
                                Você escolheu a moeda %s, agora precisará
                                escolher a moeda para qual irá converter
                                """.formatted(moedaBase);

                System.out.printf("""
                        ******************************************************
                        
                                   Conversor de moedas
                        
                        Para escolher um moeda, digite o número
                        entre parenteses que representa ela:
                        
                        (1) USD, Dolar - Estados Unidos,
                        (2) BRL, Real - Brasil
                        (3) ARS, Peso - Argentina
                        (4) EUR, Euro - União Europeia
                        (5) RUB, Rublo - Russia
                        (6) CNY, Renminbi - China
                        (7) JPY, Iene - Japão
                        (8) Para sair do programa
                        
                        %s******************************************************
                        """, informacao);
                opcao = scanner.nextInt();

                if (opcao < 1 || opcao > 8) {
                    System.out.println("******************* valor invalido *******************");
                    continue;
                } else if (opcao == 8) {
                    break;
                }

                String moeda = switch (opcao) {
                    case 1 -> "USD";
                    case 2 -> "BRL";
                    case 3 -> "ARS";
                    case 4 -> "EUR";
                    case 5 -> "RUB";
                    case 6 -> "CNY";
                    case 7 -> "JPY";
                    default -> "";
                };

                if (primeiroUso) {
                    moedaBase = moeda;
                    primeiroUso = false;
                    continue;
                } else {
                    moedaAlvo = moeda;
                    double valor = 0.0;
                    while (valor <= 0.0) {
                        System.out.printf("""
                                ******************************************************
                                %s para %s.
                                Você deseja:
                                1 - Escolher o valor da moeda base para converter.
                                2 - Somente ver o valor da conversão, ou seja
                                    assumir que a moeda base é igual a 1.
                                ******************************************************
                                """, moedaBase, moedaAlvo);
                        opcao = scanner.nextInt();
                        if (opcao == 1) {
                            System.out.printf("digite o valor da moeda %s \n",moedaBase);
                            valor = scanner.nextDouble();
                            if (valor <= 0.0) {
                                System.out.println("O valor precisa ser acima de 0");
                            }
                        } else if (opcao == 2) {
                            valor = 1;
                        } else {
                            System.out.println("******************* valor invalido *******************");
                            continue;
                        }
                    }
                    modelApi apiModulada = IntegrarAPI.setSite(moedaBase, moedaAlvo, valor);

                    if (apiModulada == null) {
                        break;
                    }

                    System.out.println("******************************************************");
                    System.out.println("O valor de 1 " + moedaBase + ", corresponde ao valor de " + apiModulada.conversion_rate() + " " + moedaAlvo +
                            "\nO valor dado " + valor + " " + moedaBase + " corresponde a " + apiModulada.conversion_result() + " " + moedaAlvo +
                            "\nA última verificação foi feita " + apiModulada.time_last_update_utc());
                    System.out.println("******************************************************");
                    primeiroUso = true;
                    System.out.println("\n" + opcao);
                    do {
                        System.out.println("""
                                Deseja fazer outra conversão?
                                1 - sim
                                2 - não
                                """);
                        opcao = scanner.nextInt();
                        if (opcao != 1 && opcao != 2) {
                            System.out.println("******************* valor invalido *******************");
                        }
                    } while (opcao != 1 && opcao != 2);

                    if (opcao == 1) {
                        continue;
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
