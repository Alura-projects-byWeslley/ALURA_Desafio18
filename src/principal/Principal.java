package principal;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {

        //1
        List<String> input = Arrays.asList("10", "abc", "20", "30x");
        List<Integer> ninput = input.stream()
                .map(s -> {
                    try{
                        return Optional.of(Integer.parseInt(s));
                    }catch(NumberFormatException e){
                        return Optional.<Integer>empty();
                    }
                }).filter(n -> n.isPresent())
                .map(n -> n.get())
                .collect(Collectors.toList());

        //Prefiro muito mais essa
        List<Integer> ninput2 = input.stream()
                .filter(n -> n.matches("[^a-zA-Z]*"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
                
        System.out.println(ninput);
        System.out.println(ninput2);

        //2
        System.out.println(processaNumero(Optional.of(5))); // Saída: Optional[25]
        System.out.println(processaNumero(Optional.of(-3))); // Saída: Optional.empty
        System.out.println(processaNumero(Optional.empty()));

        //3
        System.out.println(obterPrimeiroEUltimoNome("  João Carlos Silva   ")); // Saída: "João Silva"
        System.out.println(obterPrimeiroEUltimoNome("Maria   ")); // Saída: "Maria"

        //4
        System.out.println(ehPalindromo("Java"));
        System.out.println(ehPalindromo("socorram me subi no onibus em marrocos")); // Saída: true

        //5
        List<String> emails = Arrays.asList("TESTE@EXEMPLO.COM", "exemplo@Java.com ", "Usuario@teste.Com");
        System.out.println(converterEmails(emails));
        
    }






    public static Optional<Integer> processaNumero(Optional<Integer> numero) {
        return numero.filter(n -> n > 0).map(n -> n*n);
    }

    public static String obterPrimeiroEUltimoNome(String nomeCompleto) {
        List<String> nome = Arrays.asList(nomeCompleto.trim().split(" "));
        if (nome.size() > 1) return nome.getFirst()+" "+nome.getLast();
        return nome.getFirst();
    }

    public static boolean ehPalindromo(String palavra) {
        List<String> subpalavra = List.of(palavra.toLowerCase()
                .replace(" ", "").split(""));
        return subpalavra.equals(subpalavra.reversed());
    }
    
    public static List<String> converterEmails(List<String> emails) {
        return emails.stream()
                .map(String::toLowerCase)
                .toList();
    }

    public static enum Mes{
        JANEIRO,
        FEVEREIRO,
        MARÇO,
        ABRIL,
        MAIO,
        JUNHO,
        JULHO,
        AGOSTO,
        SETEMBRO,
        OUTUBRO,
        NOVEMBRO,
        DEZEMBRO

        Mes(int dia){

        }
    }
}