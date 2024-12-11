import negocio.*;
import dados.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        // Criando um usuário usando o Singleton
        Usuario usuario = Usuario.getInstance();
        usuario.inicializar("João Silva", "joao.silva@email.com", new Date(1990, 5, 15), Usuario.Sexo.MASCULINO, 80f, 1.75f);
        System.out.println("Usuário criado: " + usuario);

        // Criando um treino usando o Singleton
        Treino treinoMusculacao = Treino.getInstance();
        treinoMusculacao.inicializar("Musculação", "Força", 60, 3, usuario);

        // Criando um exercício usando o Singleton
        Exercicio exercicioAgachamento = Exercicio.getInstance();
        exercicioAgachamento.inicializar("Agachamento", "Trabalha pernas e glúteos", Exercicio.TipoExercicio.FORCA, 60, 5.5);
        exercicioAgachamento.adicionarMusculoTrabalhado("Quadríceps");
        exercicioAgachamento.adicionarMusculoTrabalhado("Glúteos");
        exercicioAgachamento.concluir();

        treinoMusculacao.adicionarExercicio(exercicioAgachamento);

        System.out.println("Exercício adicionado: " + exercicioAgachamento);
        System.out.println("Calorias queimadas no exercício: " + exercicioAgachamento.getCaloriasQueimadas());
        System.out.println("Treino: " + treinoMusculacao);

        //Adicionando mais um exercício (dessa vez não concluído)
        Exercicio exercicioCorrida = Exercicio.getInstance();
        exercicioCorrida.inicializar("Corrida", "Exercício cardiovascular", Exercicio.TipoExercicio.CARDIO, 300, 10);
        exercicioCorrida.adicionarMusculoTrabalhado("Perna");
        treinoMusculacao.adicionarExercicio(exercicioCorrida);
        System.out.println("Treino atualizado: " + treinoMusculacao);

        //Atualizando o progresso do treino
        treinoMusculacao.atualizarProgresso();
        System.out.println("Progresso do treino: " + treinoMusculacao.getProgresso() + "%");

        // Criando um repositório de usuários
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
        repositorioUsuarios.adicionar(usuario);

        //Exemplo de criação de refeição usando o Singleton
        Map<String, Double> macronutrientes = new HashMap<>();
        macronutrientes.put("Proteínas", 20.0);
        macronutrientes.put("Carboidratos", 30.0);
        macronutrientes.put("Gorduras", 10.0);

        Refeicao refeicao = Refeicao.getInstance();
        refeicao.inicializar("Almoço", "Frango com arroz e salada", macronutrientes);
        System.out.println("Refeição criada: " + refeicao.getNome() + ", Calorias: " + refeicao.getCalorias());
    }
}