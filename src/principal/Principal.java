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

        //6
        System.out.println(Mes.ABRIL.getNumeroDeDias());
        System.out.println(Mes.JULHO.getNumeroDeDias());

        //7
        System.out.println(Moeda.DOLAR.converterPara(100)); // 19.60 (aproximado)
        System.out.println(Moeda.EURO.converterPara(100.00)); // 18.18 (aproximado)

        //8
        System.out.println(CodigoErro.NOT_FOUND.getCodigo()); // 404
        System.out.println(CodigoErro.BAD_REQUEST.getDescricao()); // Requisição inválida

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
        JANEIRO(31),
        FEVEREIRO(28),
        MARÇO(31),
        ABRIL(30),
        MAIO(31),
        JUNHO(30),
        JULHO(31),
        AGOSTO(31),
        SETEMBRO(30),
        OUTUBRO(31),
        NOVEMBRO(30),
        DEZEMBRO(31);

        private Integer dias;

        Mes(Integer dias){
            this.dias = dias;
        }

        public Integer getNumeroDeDias(){
            return dias;
        }
    }

    public static enum Moeda {
        DOLAR(5.06),
        EURO(5.69),
        REAL(1.00)
        ;

        private Double conversao;
        
        Moeda(Double conversao){
            this.conversao = conversao;
        }

        public double converterPara(Double quantia) {
            return Math.round((quantia / conversao) * 100.0) / 100.0;
        }
        public double converterPara(Integer quantia) {
            return Math.round((quantia / conversao) * 100.0) / 100.0;
        }
    }

    public static enum CodigoErro {
        NOT_FOUND(404,"The HTTP 404 Not Found response status code indicates that the server cannot find the requested resource. Links that lead to a 404 page are often called broken or dead links and can be subject to link rot"),
        BAD_REQUEST(400,"The HTTP 400 Bad Request response status code indicates that the server cannot or will not process the request due to something that is perceived to be a client error (for example, malformed request syntax, invalid request message framing, or deceptive request routing)."),
        INTERNAL_SERVER_ERROR(500,"The HTTP 500 Internal Server Error server error response code indicates that the server encountered an unexpected condition that prevented it from fulfilling the request.")
        ;

        Integer codigo;
        String descricao;

        CodigoErro(Integer codigo, String descricao){
            this.codigo = codigo;
            this.descricao = descricao;
        }
        
        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }
    }
}