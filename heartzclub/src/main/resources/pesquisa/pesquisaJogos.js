// Pega os elementos da tela
const formBusca = document.getElementById('formBusca');
const inputPesquisa = document.getElementById('inputPesquisa');
const containerResultados = document.getElementById('containerResultados');

// Evento de clique no botão buscar
formBusca.addEventListener('submit', function(evento) {
    // Impede a página de recarregar 
    evento.preventDefault();

    // Pega o que o usuário digitou
    const termoDigitado = inputPesquisa.value;

    // Mostra mensagem de carregamento enquanto o Java pensa
    containerResultados.innerHTML = `<p style="color: white; text-align: center; width: 100%;">Buscando jogos...</p>`;

    
    // Aqui usamos um endereço de exemplo. Precisará trocar pelo endereço real da API do Java depois.
    const urlDaApiDoJava = `http://localhost:8080/api/jogos/buscar?nome=${termoDigitado}`;

    fetch(urlDaApiDoJava)
        .then(function(resposta) {
            if (!resposta.ok) {
                throw new Error("Erro de comunicação com o servidor.");
            }
            // Transforma a resposta do Java em um formato compreensível (JSON)
            return resposta.json(); 
        })
        .then(function(listaDeJogos) {
            // Limpa o "Buscando jogos..."
            containerResultados.innerHTML = "";

            // Se o Java retornar uma lista vazia
            if (listaDeJogos.length === 0) {
                containerResultados.innerHTML = `<p style="color: #ccc; text-align: center; width: 100%;">Nenhum jogo encontrado com esse nome.</p>`;
                return;
            }

            // Se achou jogos, cria os cartões HTML
            listaDeJogos.forEach(function(jogo) {
                const cartaoHTML = `
                    <div class="cartao-jogo">
                        <h3>${jogo.nome}</h3>
                        <p style="color: #ccc;"><strong>Gênero:</strong> ${jogo.genero}</p>
                        <p style="color: #ccc;"><strong>Nota:</strong> ${jogo.nota}</p>
                        <p style="color: #aaa; font-size: 14px; margin-top: 10px;">${jogo.resumo}</p>
                    </div>
                `;
                // Injeta no HTML
                containerResultados.innerHTML += cartaoHTML;
            });
        })
        .catch(function(erro) {
            console.error("Erro na busca:", erro);
            containerResultados.innerHTML = `<p style="color: red; text-align: center; width: 100%;">Ocorreu um erro ao buscar os jogos. O servidor Java está ligado?</p>`;
        });
});