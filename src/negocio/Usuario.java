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

   public enum Sexo{
        MASCULINO,
        FEMININO,
        INDEFINIDO;
    } //Quando pesquisei a melhor forma para armazenar o Sexo do Usuário, apareceu opção do Enum. Mudar p outro arquivo.

    public Usuario(String nome, String email, Date dataNascimento, Sexo sexo, float peso, float altura) {
        this.id = UUID.randomUUID();
       this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;

        // Assim que o usuário incluir suas infos, a gente precisa inicilizar as listas.
        this.metas = new ArrayList<>();
        this.treinos = new ArrayList<>();
        this.dietas = new ArrayList<>();

        calcularIMC(); //Precisamos calcular o IMC assim que o usuário incluir suas infos.
    }
    // Getters e Setters
    public UUID getId(){
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

    public void setAltura(float altura) {
        this.altura = altura;
        calcularIMC(); // A gente precisa recalcular o IMC cada vez que a altura mudar
    }

    public void setPeso(float peso) {
        this.peso = peso;
        calcularIMC(); // A gente precisa recalcular o IMC cada vez que o peso mudar
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

    // Outros métodos
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

        // Sempre que a info sobre altura e peso mudar, a gente atualiza o IMC
        calcularIMC();
    }

    public float calcularIMC() {
        if (altura > 0) {
            // A fórmula do IMC é: peso / (altura em metros)²
            this.imc = peso / (altura * altura);
            return this.imc;
        }
            throw new IllegalArgumentException("Altura e peso devem ser maiores que zero.");
            //Esse erro é lançado se o usuário colocar números menores que zero.
    }

    public void cadastrarMeta(Meta meta) {
        if (meta != null && !metas.contains(meta)) {
            //Checagem se a meta não é nula ou se não já está na lista de metas do usuário.
            metas.add(meta);
        }
    }

    public void adicionarTreino(Treino treino) {
        if (treino != null && !treinos.contains(treino)) {
            //Também checa se o treino não é nulo ou se não já está na lista de treinos do usuário.
            treinos.add(treino);
        }
    }

    public void adicionarDieta(Dieta dieta) {
        if (dieta != null && !dietas.contains(dieta)) {
            dietas.add(dieta);
        }
    }

    public double getProgresso() {
        double progressoTotal = 0; //Métrica criada por nós para medir o progresso
        int contadorAtividades = 0; //Um contador de atividades par auxiliar

        // Progresso das metas
        for (Meta meta : metas) {
            progressoTotal += meta.getProgresso();
            contadorAtividades++;
        }

        // Progresso dos treinos
        for (Treino treino : treinos) {
            progressoTotal += treino.getProgresso();
            contadorAtividades++;
        }

        // Progresso das dietas
        for (Dieta dieta : dietas) {
            progressoTotal += dieta.getProgresso();
            contadorAtividades++;
        }

        // Aqui a gente calcula a média de progresso e retorna isso como uma nota para o usuário
        return contadorAtividades > 0 ?
                progressoTotal / contadorAtividades : 0;
    }

    // Esse método calcula a idade do usuário de acordo com a data de nascimento. Meio chatinho, mas tá aí.
    public int getIdade() {
        if (dataNascimento == null) {
            return 0;
        }

        Calendar nascimento = Calendar.getInstance();
        nascimento.setTime(dataNascimento);

        Calendar hoje = Calendar.getInstance();

        int idade = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);

        if (hoje.get(Calendar.MONTH) < nascimento.get(Calendar.MONTH) ||
                (hoje.get(Calendar.MONTH) == nascimento.get(Calendar.MONTH) &&
                        hoje.get(Calendar.DAY_OF_MONTH) < nascimento.get(Calendar.DAY_OF_MONTH))) {
            idade--;
        }

        return idade;
    }

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