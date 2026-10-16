package principal;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {
    public static void main(String[] args) {
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
        System.out.println(ninput);
    }
}