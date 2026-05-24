 const API_BASE      = 'http://localhost:8080/api';
    const JOGO_PAGE     = '../jogo/jogo.html';
    const PLACEHOLDER   = `data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg'
    width='200' height='267'
    viewBox='0 0 200 267'%3E%3Crect
    width='200' height='267' fill='%232a2a2a'/%3E%3Ctext x='100' y='140'
    text-anchor='middle' fill='%23666' font-family='sans-serif' font-size='11'%3ESem capa%3C/text%3E%3C/svg%3E`;

    // Lê o usuário logado do localStorage
    const usuarioLogado = JSON.parse(localStorage.getItem('usuarioLogado') || 'null');

    // Lê o ?id= da URL
    const params = new URLSearchParams(window.location.search);
    const jogoId = params.get('id');

    /* ── Carrega dados do jogo ── */
    async function carregarJogo() {
      if (!jogoId) {
        document.getElementById('game-title').textContent = 'Jogo não encontrado.';
        return;
      }

      try {
        const res  = await fetch(`${API_BASE}/jogos/${jogoId}`, {
         headers: {
          'Accept': 'application/json'
            }
          });

        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        const jogo = await res.json();

        document.title = `${jogo.nome} | Heartz Club`;

        const img = jogo.imagemUrl || PLACEHOLDER;
        document.getElementById('banner-img').src       = img;
        document.getElementById('banner-img').alt       = jogo.nome;
        document.getElementById('cover-img').src        = img;
        document.getElementById('cover-img').alt        = jogo.nome;
        document.getElementById('game-title').textContent       = jogo.nome;
        document.getElementById('game-description').textContent = jogo.descricao;
        document.getElementById('stat-nota').textContent        = `${jogo.nota}/10 ⭐`;
        document.getElementById('stat-genero').textContent      = jogo.genero;
        document.getElementById('stat-media').textContent       = `${jogo.mediaNotas}/10`;

      } catch (err) {
        console.error('[HeartzClub] Erro ao carregar jogo:', err);
        document.getElementById('game-title').textContent = 'Erro ao carregar o jogo.';
      }
    }

    /* ── Carrega comentários do jogo ── */
    async function carregarComentarios() {
      const lista = document.getElementById('comments-list');
      try {
        const res      = await fetch(`${API_BASE}/comentarios-jogo/jogo/${jogoId}`, {
         headers: {
          'Accept': 'application/json'
            }
          });

        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        const comentarios = await res.json();

        lista.innerHTML = '';

        if (comentarios.length === 0) {
          lista.innerHTML = '<p style="color:#aaa;">Nenhum comentário ainda. Seja o primeiro!</p>';
          return;
        }

        comentarios.forEach(c => lista.appendChild(criarCardComentario(c)));

      } catch (err) {
        console.error('[HeartzClub] Erro ao carregar comentários:', err);
        lista.innerHTML = '<p style="color:#e57373;">Erro ao carregar comentários.</p>';
      }
    }

    /* ── Cria card de comentário ── */
    function criarCardComentario(c) {
      const div = document.createElement('div');
      div.className = 'comment-card';

      const nome = c.usuario?.nome || 'Usuário';
      const data = c.dataComentario
        ? new Date(c.dataComentario).toLocaleDateString('pt-BR', { day:'2-digit', month:'short', year:'numeric' })
        : '';

      // Iniciais do nome para avatar
      const iniciais = nome.split(' ').map(p => p[0]).slice(0, 2).join('').toUpperCase();

      div.innerHTML = `
        <div class="comment-header">
          <div style="
            width:50px; height:50px; border-radius:50%;
            background:#c89b3c; color:#000;
            display:flex; align-items:center; justify-content:center;
            font-weight:bold; font-size:18px; flex-shrink:0;
          ">${iniciais}</div>
          <div>
            <h3 class="comment-user">${nome}</h3>
            <span class="comment-rating" style="color:#888; font-size:13px;">${data}</span>
          </div>
        </div>
        <p class="comment-text">${c.texto}</p>
      `;
      return div;
    }

    /* ── Envia comentário ── */
    async function enviarComentario() {
      const errorEl = document.getElementById('comment-error');
      const input   = document.getElementById('comment-input');
      const texto   = input.value.trim();

      errorEl.style.display = 'none';

      if (!texto) {
        errorEl.textContent    = 'Escreva algo antes de comentar.';
        errorEl.style.display  = 'block';
        return;
      }

      try {
        const res = await fetch(`${API_BASE}/comentarios-jogo`, {
          method: 'POST',
          headers: {
           'Content-Type': 'application/json'
           },
          body: JSON.stringify({
            texto,
            jogoId:     Number(jogoId),
            usuarioId:  usuarioLogado.id
          })
        });

        if (!res.ok) throw new Error(`HTTP ${res.status}`);

        input.value = '';
        // Recarrega a lista para mostrar o novo comentário
        await carregarComentarios();

      } catch (err) {
        console.error('[HeartzClub] Erro ao enviar comentário:', err);
        errorEl.textContent   = 'Erro ao enviar comentário. Tente novamente.';
        errorEl.style.display = 'block';
      }
    }

    document.addEventListener('DOMContentLoaded', async () => {
      // Exibe form de comentário só se logado
      if (usuarioLogado) {
        document.getElementById('comment-form-area').style.display    = 'flex';
        document.getElementById('comment-login-notice').style.display = 'none';
      } else {
        document.getElementById('comment-form-area').style.display    = 'none';
        document.getElementById('comment-login-notice').style.display = 'block';
      }

      await carregarJogo();
      await carregarComentarios();
    });