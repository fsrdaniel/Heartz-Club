const formSolicitar = document.getElementById('formSolicitar');
const formRedefinir = document.getElementById('formRedefinir');
let emailSalvo = ""; // Guarda o e-mail para usar na etapa 2

// ETAPA 1: Enviar o e-mail para receber o código
formSolicitar.addEventListener('submit', async function(evento) {
    evento.preventDefault();
    emailSalvo = document.getElementById('email').value;
    
    // Mostra um aviso de carregamento (opcional, mas recomendado)
    const btn = formSolicitar.querySelector('button');
    btn.innerText = "Enviando...";

    try {
        // Chama a API do Java para gerar e enviar o código
        const resposta = await fetch('http://localhost:8080/api/senha/solicitar', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email: emailSalvo })
        });

        if (resposta.ok) {
            // Sucesso! Esconde o form 1 e mostra o form 2
            formSolicitar.style.display = 'none';
            formRedefinir.style.display = 'block';
        } else {
            alert("E-mail não encontrado ou erro no servidor.");
            btn.innerText = "Enviar Código";
        }
    } catch (erro) {
        console.error("Erro:", erro);
        alert("Erro ao conectar com o servidor.");
        btn.innerText = "Enviar Código";
    }
});

// ETAPA 2: Enviar o código e a nova senha
formRedefinir.addEventListener('submit', async function(evento) {
    evento.preventDefault();
    const codigoDigitado = document.getElementById('codigo').value;
    const novaSenhaDigitada = document.getElementById('novaSenha').value;

    try {
        // Envia o pacote completo para o Java validar e alterar a senha
        const resposta = await fetch('http://localhost:8080/api/senha/redefinir', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ 
                email: emailSalvo, 
                codigo: codigoDigitado, 
                novaSenha: novaSenhaDigitada 
            })
        });

        if (resposta.ok) {
            alert("Senha alterada com sucesso! Faça login.");
            window.location.href = "login.html"; // Redireciona para o login
        } else {
            alert("Código inválido ou expirado. Tente novamente.");
        }
    } catch (erro) {
        console.error("Erro:", erro);
        alert("Erro ao conectar com o servidor.");
    }
});