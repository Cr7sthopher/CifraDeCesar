import java.util.ArrayList;
import java.util.Scanner;

public class cifradecesar {

    public static void main(String[] args) { 
        Scanner entrada = new Scanner(System.in);

        // Listas
        ArrayList<String> originais = new ArrayList<>();
        ArrayList<String> cifradas = new ArrayList<>();
        ArrayList<Integer> deslocamentos = new ArrayList<>();

        while (true) {
            System.out.println("\n=== Cifra de César ===");
            System.out.println("1 - Criptografar mensagem");
            System.out.println("2 - Descriptografar mensagem");
            System.out.println("3 - Ver as mensagens criptografadas");
            System.out.println("4 - Limpar histórico");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = entrada.nextLine().trim();

            if (opcao.equals("0")) {
                System.out.println("ADEUS!!!!");
                break;
            }

            switch (opcao) {
                case "1":
                    System.out.print("Digite a mensagem: ");
                    String texto = entrada.nextLine();
                    System.out.print("Digite o deslocamento das letras(só números inteiros): ");
                    int deslocamento = Integer.parseInt(entrada.nextLine());
                    String cifrada = criptografar(texto, deslocamento);
                    System.out.println("Mensagem criptografada: " + cifrada);

                    // salvar
                    originais.add(texto);
                    cifradas.add(cifrada);
                    deslocamentos.add(deslocamento);
                    break;

                case "2":
                    System.out.print("Digite a mensagem cifrada: ");
                    String textoCifrado = entrada.nextLine();
                    System.out.print("Digite o deslocamento usado na cifra: ");
                    int deslocamentoDesc = Integer.parseInt(entrada.nextLine());
                    String decifrada = descriptografar(textoCifrado, deslocamentoDesc);
                    System.out.println("Mensagem descriptografada: " + decifrada);
                    break;

                case "3":
                    if (cifradas.isEmpty()) {
                        System.out.println("Nenhuma mensagem criptografada ainda.");
                    } else {
                        System.out.println("\n=== Histórico de Mensagens Criptografadas ===");
                        for (int i = 0; i < cifradas.size(); i++) {
                            System.out.println("\nMensagem #" + (i + 1));
                            System.out.println(" - Original     : " + originais.get(i));
                            System.out.println(" - Cifrada      : " + cifradas.get(i));
                            System.out.println(" - Deslocamento : " + deslocamentos.get(i));
                        }
                    }
                    break;

                case "4":
                    originais.clear();
                    cifradas.clear();
                    deslocamentos.clear();
                    System.out.println("Histórico limpo com sucesso!");
                    break;

                default:
                    System.out.println(" Opção inválida. Tente novamente.");
            }
        }

        entrada.close();
    }

    // Criptografia
    public static String criptografar(String texto, int deslocamento) {
        StringBuilder resultado = new StringBuilder();
        deslocamento = ((deslocamento % 26) + 26) % 26; // normaliza para 0–25

        for (char c : texto.toCharArray()) {
            if (Character.isUpperCase(c)) {
                char letra = (char) ((c - 'A' + deslocamento) % 26 + 'A');
                resultado.append(letra);
            } else if (Character.isLowerCase(c)) {
                char letra = (char) ((c - 'a' + deslocamento) % 26 + 'a');
                resultado.append(letra);
            } else {
                resultado.append(c); // mantém espaços, números, etc.
            }
        }
        return resultado.toString();
    }

    // Descriptografia
    public static String descriptografar(String texto, int deslocamento) {
        return criptografar(texto, -deslocamento);
    }
}