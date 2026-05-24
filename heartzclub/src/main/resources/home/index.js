const API_BASE = 'http://localhost:8080/api/jogos';
const JOGO_PAGE = '../jogo/jogo.html';


    const PLACEHOLDER_IMG = 'https://via.placeholder.com/200x267?text=Sem+capa';
    function criarItemJogo(jogo) {
    const li = document.createElement('li');
    li.className = 'game';

  const imgSrc  = jogo.imagemUrl || jogo.capa || jogo.imagem || jogo.foto || PLACEHOLDER_IMG;
  const titulo  = jogo.titulo || jogo.nome || jogo.name || 'Jogo';
  const jogoUrl = `${JOGO_PAGE}?id=${jogo.id}`;

  li.innerHTML = `
    <a href="${jogoUrl}">
      <img
        class="game__img"
        src="${imgSrc}"
        alt="${titulo}"
        loading="lazy"
        onerror="this.src='${PLACEHOLDER_IMG}'"
      >
    </a>
  `;
  return li;
}

function renderizarJogos(ulElement, jogos) {
  ulElement.innerHTML = '';

  if (!jogos || jogos.length === 0) {
    ulElement.innerHTML = '<li class="games--empty">Nenhum jogo encontrado.</li>';
    return;
  }

  jogos.forEach(jogo => ulElement.appendChild(criarItemJogo(jogo)));
}

function exibirErro(ulElement, mensagem) {
  ulElement.innerHTML = `<li class="games--error">${mensagem}</li>`;
}

async function carregarJogos() {
  const ulPopular     = document.getElementById('games-popular');
  const ulLancamentos = document.getElementById('games-lancamentos');

  try {
    const response = await fetch(API_BASE, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' }
    });

    if (!response.ok) {
      throw new Error(`Erro HTTP ${response.status}`);
    }

    const jogos = await response.json();
    const hoje = new Date();

    const populares = jogos.filter(j => {
      if (j.popular !== undefined) return j.popular;
      if (j.avaliacao !== undefined) return j.avaliacao >= 4;
      return true;
    }).slice(0, 6);

    const lancamentos = jogos.filter(j => {
      if (j.lancamento !== undefined) return j.lancamento;
      if (j.dataLancamento) return new Date(j.dataLancamento) > hoje;
      return false;
    }).slice(0, 4);

    renderizarJogos(ulPopular, populares);
    if (lancamentos.length === 0) {
      exibirErro(ulLancamentos, 'Nenhum lançamento disponível no momento.');
    } else {
      renderizarJogos(ulLancamentos, lancamentos);
    }

  } catch (error) {
    console.error('Erro ao buscar jogos:', error);
    exibirErro(ulPopular,     'Não foi possível carregar os jogos.');
    exibirErro(ulLancamentos, 'Não foi possível carregar os jogos.');
  }
}

document.addEventListener('DOMContentLoaded', carregarJogos);