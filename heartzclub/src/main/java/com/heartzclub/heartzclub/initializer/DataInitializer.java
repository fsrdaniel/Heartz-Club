package com.heartzclub.heartzclub.initializer;

import com.heartzclub.heartzclub.Model.Jogo;
import com.heartzclub.heartzclub.Repository.JogoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final JogoRepository jogoRepository;

    public DataInitializer(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    @Override
    public void run(String... args) {

        if (jogoRepository.count() > 0) {
            return;
        }

        salvar(
                "Red Dead Redemption 2",
                "Ação e Aventura",
                "Um épico de faroeste em mundo aberto da Rockstar Games, acompanhando Arthur Morgan em uma jornada marcada por assaltos, sobrevivência e conflitos dentro da gangue Van der Linde.",
                9.8,
                "https://upload.wikimedia.org/wikipedia/en/4/44/Red_Dead_Redemption_II.jpg"
        );

        salvar(
                "The Witcher 3: Wild Hunt",
                "RPG",
                "Geralt de Rívia embarca em uma jornada para encontrar Ciri em um vasto mundo repleto de monstros, escolhas morais e histórias memoráveis.",
                9.7,
                "https://upload.wikimedia.org/wikipedia/en/0/0c/Witcher_3_cover_art.jpg"
        );

        salvar(
                "Grand Theft Auto V",
                "Ação e Mundo Aberto",
                "Três protagonistas vivem histórias interligadas em Los Santos, em um dos jogos mais vendidos e jogados da última década.",
                9.6,
                "https://upload.wikimedia.org/wikipedia/en/a/a5/Grand_Theft_Auto_V.png"
        );

        salvar(
                "Minecraft",
                "Sandbox e Sobrevivência",
                "Um jogo de construção e exploração em blocos que se tornou um fenômeno global, permitindo criatividade ilimitada.",
                9.5,
                "https://upload.wikimedia.org/wikipedia/en/5/51/Minecraft_cover.png"
        );

        salvar(
                "The Legend of Zelda: Breath of the Wild",
                "Aventura",
                "Link desperta em Hyrule e deve derrotar Calamity Ganon em um mundo aberto revolucionário.",
                9.8,
                "https://upload.wikimedia.org/wikipedia/pt/0/0f/Legend_of_Zelda_Breath_of_the_Wild_capa.png"
        );

        salvar(
                "Elden Ring",
                "RPG de Ação",
                "Uma aventura desafiadora em um mundo criado pela FromSoftware em parceria com George R. R. Martin.",
                9.7,
                "https://upload.wikimedia.org/wikipedia/en/b/b9/Elden_Ring_Box_art.jpg"
        );

        salvar(
                "God of War",
                "Ação e Aventura",
                "Kratos e seu filho Atreus embarcam em uma jornada emocionante pela mitologia nórdica.",
                9.6,
                "https://upload.wikimedia.org/wikipedia/en/a/a7/God_of_War_4_cover.jpg"
        );

        salvar(
                "Cyberpunk 2077",
                "RPG e Ficção Científica",
                "Explore Night City em um RPG futurista repleto de escolhas, implantes cibernéticos e missões intensas.",
                8.9,
                "https://upload.wikimedia.org/wikipedia/en/9/9f/Cyberpunk_2077_box_art.jpg"
        );

        salvar(
                "Fortnite",
                "Battle Royale",
                "Um dos jogos online mais populares do mundo, conhecido por suas temporadas, eventos e colaborações.",
                8.8,
                "https://static.wikia.nocookie.net/fortnite_ptbr_gamepedia_ptbr/images/a/ae/Fortnite_%28Update_v28.00%29_-_Cover_Art_-_Fortnite.jpg/revision/latest?cb=20240503123646"
        );

        salvar(
                "Counter-Strike 2",
                "FPS Tático",
                "Evolução do clássico Counter-Strike, mantendo a jogabilidade competitiva que conquistou milhões de jogadores.",
                9.1,
                "https://upload.wikimedia.org/wikipedia/en/f/f2/CS2_Cover_Art.jpg"
        );

        salvar(
                "Valorant",
                "FPS Tático",
                "Shooter competitivo da Riot Games que combina precisão e habilidades únicas dos agentes.",
                8.9,
                "https://upload.wikimedia.org/wikipedia/en/b/ba/Valorant_cover.jpg"
        );
    }

    private void salvar(String nome,
                        String genero,
                        String descricao,
                        Double nota,
                        String imagemUrl) {

        Jogo jogo = new Jogo(nome, genero, descricao, nota, imagemUrl);
        jogo.setMediaNotas(nota);

        jogoRepository.save(jogo);
    }
}