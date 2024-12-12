package negocio;

import java.util.*;

public class Usuario {
    private UUID id;
    private String nome;
    private String email;
    private Date dataNascimento;
    private Sexo sexo;
    private float peso;
    private float altura;
    private float imc;
    private ArrayList<Meta> metas;
    private ArrayList<Treino> treinos;
    private ArrayList<Dieta> dietas;

    // Enum para representar o sexo do usuário
    public enum Sexo {
        MASCULINO,
        FEMININO,
    }

    // Construtor
    public Usuario() {
        this.id = UUID.randomUUID(); // Gera um ID único
        this.metas = new ArrayList<>();
        this.treinos = new ArrayList<>();
        this.dietas = new ArrayList<>();
    }

    // Construtor com parâmetros
    public Usuario(String nome, String email, Date dataNascimento, Sexo sexo, float peso, float altura) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        calcularIMC();
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public float getPeso() {
        return peso;
    }

    public float getAltura() {
        return altura;
    }

    public float getImc() {
        return imc;
    }

    public ArrayList<Meta> getMetas() {
        return metas;
    }

    public ArrayList<Treino> getTreinos() {
        return treinos;
    }

    public ArrayList<Dieta> getDietas() {
        return dietas;
    }

    // Setters
    public void setAltura(float altura) {
        this.altura = altura;
        calcularIMC(); // Recalcula o IMC após alterar a altura
    }

    public void setPeso(float peso) {
        this.peso = peso;
        calcularIMC(); // Recalcula o IMC após alterar o peso
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // OUTROS MÉTODOS
    // Método para atualizar os dados do usuário, ele testa com um if se os dados fornecidos mudaram
    public void atualizarDados(String nome, String email, Date dataNascimento,
                               Sexo sexo, float peso, float altura) {
        if (nome != null) {
            this.nome = nome;
        }

        if (email != null) {
            this.email = email;
        }

        if (dataNascimento != null) {
            this.dataNascimento = dataNascimento;
        }

        if (sexo != null) {
            this.sexo = sexo;
        }

        if (peso > 0) {
            this.peso = peso;
        }

        if (altura > 0) {
            this.altura = altura;
        }

        calcularIMC(); // Recalcula o IMC após alterar peso e altura
    }

    // Método para calcular o IMC do usuário
    public float calcularIMC() {
        if (altura > 0) {
            this.imc = peso / (altura * altura); // IMC = peso / altura²
            return this.imc;
        }
        throw new IllegalArgumentException("Altura e peso devem ser maiores que zero.");
    }

    // Método para cadastrar uma meta para o usuário
    public void cadastrarMeta(Meta meta) {
        // Adiciona a meta à lista se ela não for nula e não existir na lista
        if (meta != null && !metas.contains(meta)) {
            metas.add(meta);
        }
    }

    // Método para adicionar um treino para o usuário
    public void adicionarTreino(Treino treino) {
        // Adiciona o treino à lista se ele não for nulo e não existir na lista
        if (treino != null && !treinos.contains(treino)) {
            treinos.add(treino);
        }
    }

    // Método para adicionar uma dieta para o usuário
    public void adicionarDieta(Dieta dieta) {
        if (dieta != null && !dietas.contains(dieta)) {
            dietas.add(dieta);
        }
    }

    // Método para calcular o progresso geral do usuário
    public double getProgresso() {
        double progressoTotal = 0; // Essa variável vai acompanhar o progresso das atividades
        int contadorAtividades = 0; // Só contabiliza as atividades

        // Calcula o progresso das metas
        for (Meta meta : metas) {
            progressoTotal += meta.getProgresso();
            contadorAtividades++;
        }

        // Calcula o progresso dos treinos
        for (Treino treino : treinos) {
            progressoTotal += treino.getProgresso();
            contadorAtividades++;
        }

        // Calcula o progresso das dietas
        for (Dieta dieta : dietas) {
            progressoTotal += dieta.getProgresso();
            contadorAtividades++;
        }

        // Calcula a média do progresso e retorna
        return contadorAtividades > 0 ?
                progressoTotal / contadorAtividades : 0;
    }

    // Método para calcular a idade do usuário
    public int getIdade() {
        if (dataNascimento == null) {
            return 0; // Retorna 0 se a data não existe.
        }

        //Usamos a Calendar abaixo porque a 'Date' foi descontinuada
        //Calendar é uma classe abstrata
        Calendar nascimento = Calendar.getInstance(); // Cria um calendário para a data de nascimento
        nascimento.setTime(dataNascimento); // Define a data de nascimento no calendário

        Calendar hoje = Calendar.getInstance(); // Cria um calendário para a data atual

        // Calcula a idade baseada na data de nascimento:
        int idade = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);

        // Ajuste da idade caso o aniversário ainda não tenha ocorrido neste ano:
        if (hoje.get(Calendar.MONTH) < nascimento.get(Calendar.MONTH) ||
                (hoje.get(Calendar.MONTH) == nascimento.get(Calendar.MONTH) &&
                        hoje.get(Calendar.DAY_OF_MONTH) < nascimento.get(Calendar.DAY_OF_MONTH))) {
            idade--;
        }

        return idade;
    }

    // Método toString
    @Override
    public String toString() {
        return "negocio.Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", idade=" + getIdade() +
                ", sexo=" + sexo +
                ", peso=" + peso +
                ", altura=" + altura +
                ", imc=" + String.format("%.2f", imc) +
                '}';
    }
}