package principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entities.Produtos;

public class Program {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.print    ("Caminho do arquivo: ");
        String path = sc.nextLine();

        List<Produtos> listProds = new ArrayList<>(); 
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();
            while (line != null) {
                String[] prods = line.split(",");
                listProds.add(new Produtos(prods[0], Double.parseDouble(prods[1])));
                line = br.readLine();
            }

            Double media = listProds.stream()
                    .map(p -> p.getPrice())
                    .reduce(0.0, (x, y) -> x + y) / listProds.size();

                    
            Comparator<String> comp = ((o1, o2) -> o1.toUpperCase().compareTo(o2.toUpperCase()));
            List<String> name = listProds.stream()
                    .filter(p -> p.getPrice() < media)
                    .map(p -> p.getName()).sorted(comp.reversed())
                    .collect(Collectors.toList());
        
            System.out.println("Media: " + String.format("%.2f", media));
            name.forEach(System.out::println);
            

        } catch (IOException e) {
            e.getMessage();
        }

    }

}