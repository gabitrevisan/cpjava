import java.util.Scanner;

public class Criptografia {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Inicialização do objeto Scanner para entrada do usuário

        // Mensagem de boas-vindas e opções do menu
        System.out.println("Bem-vindo ao sistema de criptografia!");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Decriptar uma frase");
        System.out.println("2. Encriptar uma frase");

        int opcaoPrincipal = scanner.nextInt(); // Captura da opção escolhida pelo usuário

        switch (opcaoPrincipal) {
            case 1:
                System.out.println("Escolha o método de criptografia:");
                System.out.println("1. Cifra de César");
                System.out.println("2. Código Morse");

                int metodoDecriptacao = scanner.nextInt(); // Captura do método de descriptografia escolhido
                scanner.nextLine(); // Limpar o buffer do scanner

                System.out.println("Digite a frase a ser decriptada:");
                String fraseDecriptada = scanner.nextLine(); // Captura da frase a ser descriptografada

                String resultadoDecriptado = "";

                switch (metodoDecriptacao) {
                    case 1:
                        resultadoDecriptado = decifraCesar(fraseDecriptada); // Descriptografa usando Cifra de César
                        break;
                    case 2:
                        resultadoDecriptado = decifraMorse(fraseDecriptada); // Descriptografa usando Código Morse
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        return;
                }

                System.out.println("Resultado da decriptação: " + resultadoDecriptado); // Exibe o resultado da descriptografia
                break;

            case 2:
                System.out.println("Escolha o método de criptografia:");
                System.out.println("1. Cifra de César");
                System.out.println("2. Código Morse");

                int metodoEncriptacao = scanner.nextInt(); // Captura do método de criptografia escolhido
                scanner.nextLine(); // Limpar o buffer do scanner

                System.out.println("Digite a frase a ser encriptada:");
                String fraseEncriptada = scanner.nextLine(); // Captura da frase a ser criptografada

                String resultadoEncriptado = "";

                switch (metodoEncriptacao) {
                    case 1:
                        resultadoEncriptado = cifraCesar(fraseEncriptada); // Criptografa usando Cifra de César
                        break;
                    case 2:
                        resultadoEncriptado = codificaMorse(fraseEncriptada); // Criptografa usando Código Morse
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        return;
                }

                System.out.println("Resultado da encriptação: " + resultadoEncriptado); // Exibe o resultado da criptografia
                break;

            default:
                System.out.println("Opção inválida.");
        }
    }

    // Implementação da cifra de César para encriptação
    public static String cifraCesar(String texto) {
        int deslocamento = 3; // deslocamento fixo de 3 posições
        StringBuilder resultado = new StringBuilder();

        for (char caractere : texto.toCharArray()) {
            if (Character.isLetter(caractere)) {
                char inicial = Character.isUpperCase(caractere) ? 'A' : 'a'; // Verifica se é maiúscula ou minúscula
                resultado.append((char) (((caractere - inicial + deslocamento) % 26) + inicial)); // Aplica a cifra de César
            } else {
                resultado.append(caractere); // Mantém caracteres que não são letras
            }
        }

        return resultado.toString(); // Retorna o texto criptografado
    }

    // Implementação da cifra de César para decriptação
    public static String decifraCesar(String texto) {
        int deslocamento = 3; // deslocamento fixo de 3 posições
        StringBuilder resultado = new StringBuilder();

        for (char caractere : texto.toCharArray()) {
            if (Character.isLetter(caractere)) {
                char inicial = Character.isUpperCase(caractere) ? 'A' : 'a'; // Verifica se é maiúscula ou minúscula
                resultado.append((char) (((caractere - inicial - deslocamento + 26) % 26) + inicial)); // Desfaz a cifra de César
            } else {
                resultado.append(caractere); // Mantém caracteres que não são letras
            }
        }

        return resultado.toString(); // Retorna o texto descriptografado
    }

    // Implementação do código Morse para encriptação
    public static String codificaMorse(String texto) {
        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
                "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."}; // Array com códigos Morse

        StringBuilder resultado = new StringBuilder();

        for (char caractere : texto.toUpperCase().toCharArray()) { // Converte a frase para maiúsculas e itera sobre cada caractere
            if (Character.isLetter(caractere)) {
                resultado.append(morse[caractere - 'A']).append(" "); // Concatena o código Morse do caractere
            } else {
                resultado.append(caractere); // Mantém caracteres que não são letras
            }
        }

        return resultado.toString(); // Retorna o texto criptografado em código Morse
    }

    // Implementação do código Morse para decriptação
    public static String decifraMorse(String texto) {
        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
                "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."}; // Array com códigos Morse

        StringBuilder resultado = new StringBuilder();
        StringBuilder buffer = new StringBuilder();

        for (char caractere : texto.toCharArray()) {
            if (caractere == ' ' && buffer.length() > 0) { // Verifica se é um espaço e se o buffer não está vazio
                int indice = -1;
                for (int i = 0; i < morse.length; i++) {
                    if (morse[i].equals(buffer.toString())) { // Compara o buffer com os códigos Morse
                        indice = i;
                        break;
                    }
                }
                if (indice != -1) {
                    resultado.append((char) ('A' + indice)); // Adiciona a letra correspondente ao código Morse
                } else {
                    resultado.append(buffer.toString()); // Mantém o código Morse original se não for reconhecido
                }
                buffer.setLength(0); // Limpa o buffer
            } else if (caractere != ' ') {
                buffer.append(caractere); // Adiciona os pontos e traços ao buffer
            }
        }

        if (buffer.length() > 0) { // Lida com o último caractere se o buffer não estiver vazio
            int indice = -1;
            for (int i = 0; i < morse.length; i++) {
                if (morse[i].equals(buffer.toString())) { // Compara o buffer com os códigos Morse
                    indice = i;
                    break;
                }
            }
            if (indice != -1) {
                resultado.append((char) ('A' + indice)); // Adiciona a letra correspondente ao código Morse
            } else {
                resultado.append(buffer.toString()); // Mantém o código Morse original se não for reconhecido
            }
        }

        return resultado.toString(); // Retorna o texto descriptografado
    }
}