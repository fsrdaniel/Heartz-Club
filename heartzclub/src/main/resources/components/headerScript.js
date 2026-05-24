const BASE = '/heartzclub/src/main/resources';

function carregarHeader() {

    const nav = document.getElementById("nav");

    // ESPERA O HEADER EXISTIR
    if (!nav) {
        setTimeout(carregarHeader, 100);
        return;
    }

    // PEGA USUÁRIO
    const usuario = JSON.parse(localStorage.getItem("usuarioLogado"));

    console.log(usuario);

    // LOGADO
    if (usuario) {

        nav.innerHTML = `
               <a href="${BASE}/paginaJogos/paginaJogos.html" class="nav__link"></a>
                Jogos
            </a>

            <div class="perfil-header">

                <a href="${BASE}/perfil/perfil.html">
                    <img src="https://upload.wikimedia.org/wikipedia/en/9/9d/Bonzi_Buddy.png" class="foto-perfil">
                </a>

                <a href="${BASE}/perfil/perfil.html">
                    <span class="nome-usuario">${usuario.nome}</span>
                </a>

                <button class="btn-sair" onclick="logout()">
                    Sair
                </button>

            </div>
        `;

    } else {

        // NÃO LOGADO
        nav.innerHTML = `
            <a href="/heartzclub/src/main/resources/paginaJogos/paginaJogos.html" class="nav__link">
                Jogos
            </a>

            <a href="${BASE}/login/login.html" class="nav__link">
                Login
            </a>

            <a href="${BASE}/cadastro/cadastro.html" class="nav__link">
                Cadastro
            </a>
        `;
    }
}

// LOGOUT
function logout() {
    localStorage.removeItem("usuarioLogado");
    window.location.href = '/heartzclub/src/main/resources/home/home.html';
}

// INICIA
carregarHeader();

function pesquisar() {
    window.location.href = '/heartzclub/src/main/resources/pesquisa/pesquisa.html';
}