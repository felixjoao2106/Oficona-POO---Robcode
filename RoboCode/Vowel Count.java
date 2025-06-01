/*
 
Autor: João Vitor Felix Aizza da Silva
Data: 13/05/2025
Atividade: 03
Kata: Vowel Count
 
*/


//do-while

public class ContadorVogaisDoWhile {
    public static void main(String[] args) {
        String texto = "exemplo de string com algumas vogais";
        int count = 0;
        int i = 0;

        if (!texto.isEmpty()) {
            do {
                char c = texto.charAt(i);
                if (ehVogal(c)) {
                    count++;
                }
                i++;
            } while (i < texto.length());
        }

        System.out.println("Número de vogais (do-while): " + count);
    }

    public static boolean ehVogal(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}


//while

public class ContadorVogaisWhile {
    public static void main(String[] args) {
        String texto = "exemplo de string com algumas vogais";
        int count = 0;
        int i = 0;

        while (i < texto.length()) {
            char c = texto.charAt(i);
            if (ehVogal(c)) {
                count++;
            }
            i++;
        }

        System.out.println("Número de vogais (while): " + count);
    }

    public static boolean ehVogal(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}



//for-each

public class ContadorVogaisForEach {
    public static void main(String[] args) {
        String texto = "exemplo de string com algumas vogais";
        int count = 0;

        for (char c : texto.toCharArray()) {
            if (ehVogal(c)) {
                count++;
            }
        }

        System.out.println("Número de vogais (for-each): " + count);
    }

    public static boolean ehVogal(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}

