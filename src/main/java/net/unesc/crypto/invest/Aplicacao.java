package net.unesc.crypto.invest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;

import java.io.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Aplicacao implements Runnable {

    private List<CarteiraInvestimento> carteiras;
    private List<CryptoMoeda> moedas;

    public Aplicacao() throws IOException {
        carteiras = new ArrayList<>();
        carregaMoedas();
    }

    private void carregaMoedas() throws IOException {
        try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/moedas.json"))) {
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();
            Type listType = new TypeToken<ArrayList<CryptoMoeda>>() {
            }.getType();
            moedas = gson.fromJson(reader, listType);
        }
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            mostraMenu();

            while (true) {
                String line = reader.readLine();

                switch (line.trim().toLowerCase()) {
                    case "1":
                        addCarteira(reader);
                        break;
                    case "2":
                        listaCarteiras();
                        break;
                    case "3":
                        addInvestimento(reader);
                        break;
                    case "x":
                        System.out.println("Tchau!");
                        return;
                    default:
                        System.out.println("Opção inválida");
                        mostraMenu();
                }
            }
        } catch (IOException e) {
            System.out.println("Falha ao ler a entrada do usuário");
        }
    }

    private void listaCarteiras() {
        for (CarteiraInvestimento carteiraInvestimento : carteiras) {
            System.out.println(carteiraInvestimento.toString());
            for (Investimento investimento: carteiraInvestimento.getInvestimentos()) {
                System.out.println("\t" + investimento.toString());
            }
        }
    }

    private void addCarteira(BufferedReader reader) throws IOException {
        System.out.println("Informe o nome da carteira: ");
        String nomeCarteira = reader.readLine();

        carteiras.add(new CarteiraInvestimento(nomeCarteira));
        System.out.println("Carteira adicionada");
    }

    private void addInvestimento(BufferedReader reader) throws IOException {
        System.out.println("Selecione a carteira: ");
        for (int i = 0; i < carteiras.size(); i++) {
            System.out.println(i + " - " + carteiras.get(i).toString());
        }

        Integer carteiraSelecionada = Integer.parseInt(reader.readLine());
        if (carteiraSelecionada < 0 || carteiraSelecionada >= carteiras.size()) {
            System.out.println("Carteira inválida");
            return;
        }

        System.out.println("Selecione a moeda: ");
        for (int i = 0; i < moedas.size(); i++) {
            System.out.println(i + " - " + moedas.get(i).toString());
        }

        Integer moedaSelecionada = Integer.parseInt(reader.readLine());
        if (moedaSelecionada < 0 || moedaSelecionada >= moedas.size()) {
            System.out.println("Moeda inválida");
            return;
        }

        System.out.println("Informe o investimento inicial em dolares: ");
        BigDecimal valorInicial = new BigDecimal(reader.readLine());

        carteiras.get(carteiraSelecionada).addInvestimento(new Investimento(moedas.get(moedaSelecionada), valorInicial));
        System.out.println("Investimento adicionado");
    }

    private void mostraMenu() {
        System.out.println("Opções:");
        System.out.println("\t1 - Adicionar carteira");
        System.out.println("\t2 - Consultar carteiras");
        System.out.println("\t3 - Novo investimento");
        System.out.println("\tX - Para sair");
    }

    public static void main(String[] args) throws IOException {
        new Aplicacao().run();
    }
}
