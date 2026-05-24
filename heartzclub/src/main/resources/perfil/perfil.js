// perfil.js — carrega usuário do localStorage, permite editar nome e trocar foto de perfil

document.addEventListener('DOMContentLoaded', () => {
  const stored = localStorage.getItem('usuarioLogado');
  const profileAvatar = document.getElementById('profileAvatar');
  const profileName = document.getElementById('profileName');
  const profileDescription = document.getElementById('profileDescription');
  const editarBtn = document.getElementById('editarPerfilBtn');
  const editarForm = document.getElementById('editarPerfilForm');
  const inputNome = document.getElementById('inputNome');
  const inputFoto = document.getElementById('inputFoto');
  const cancelBtn = document.getElementById('cancelEdit');

  const defaultAvatar = 'https://upload.wikimedia.org/wikipedia/en/9/9d/Bonzi_Buddy.png';

  if (!stored) {
    profileAvatar.src = defaultAvatar;
    profileName.innerText = 'Usuário';
    profileDescription.innerText = ''; 
    profileDescription.style.display = 'none';
    return;
  }

  let usuario = JSON.parse(stored);
  let selectedPhoto = null;

  function render() {
    profileName.innerText = usuario.nome || 'Usuário';
    profileAvatar.src = usuario.fotoPerfil || defaultAvatar;
    if (usuario.descricao) {
      profileDescription.innerText = usuario.descricao;
      profileDescription.style.display = 'block';
    } else {
      profileDescription.innerText = '';
      profileDescription.style.display = 'none';
    }
  }

  render();

  editarBtn.addEventListener('click', () => {
    inputNome.value = usuario.nome || '';
    inputFoto.value = '';
    selectedPhoto = null;
    editarForm.style.display = 'block';
    editarBtn.style.display = 'none';
  });

  inputFoto.addEventListener('change', () => {
    const file = inputFoto.files && inputFoto.files[0];
    if (!file) {
      selectedPhoto = null;
      profileAvatar.src = usuario.fotoPerfil || defaultAvatar;
      return;
    }

    const reader = new FileReader();
    reader.onload = () => {
      selectedPhoto = reader.result;
      profileAvatar.src = selectedPhoto;
    };
    reader.readAsDataURL(file);
  });

  cancelBtn.addEventListener('click', () => {
    selectedPhoto = null;
    render();
    editarForm.style.display = 'none';
    editarBtn.style.display = 'inline-block';
  });

  editarForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const novoNome = inputNome.value.trim();

    if (!novoNome) {
      alert('Nome não pode ser vazio');
      return;
    }

    // Construir payload compatível com UsuarioRequestDTO
    const payload = {
      nome: novoNome,
      email: usuario.email,
      idade: usuario.idade,
      cpf: usuario.cpf,
      endereco: usuario.endereco || '',
      senha: usuario.senha || '',
      confirmaSenha: usuario.senha || ''
    };

    try {
      const response = await fetch(`http://localhost:8080/api/usuarios/${usuario.id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });

      if (!response.ok) {
        const txt = await response.text();
        throw new Error(txt || 'Erro ao atualizar usuário');
      }

      const updated = await response.json();
      // Mantém a foto local quando o backend não a envia
      usuario = {
        ...updated,
        fotoPerfil: selectedPhoto || usuario.fotoPerfil
      };
      localStorage.setItem('usuarioLogado', JSON.stringify(usuario));
      render();
      editarForm.style.display = 'none';
      editarBtn.style.display = 'inline-block';
      alert('Perfil atualizado com sucesso!');
    } catch (err) {
      console.error('Erro ao atualizar perfil:', err);
      alert('Erro ao atualizar perfil. Veja console.');
    }
  });
});
