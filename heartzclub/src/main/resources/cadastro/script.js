async function cadastrar(event) {

    event.preventDefault();

    const url = 'http://localhost:8080/api/usuarios/cadastro';

    const nome = document.getElementById('Nome').value.trim();
    const email = document.getElementById('Email').value.trim();
    const idade = document.getElementById('Idade').value.trim();
    const cpf = document.getElementById('CPF').value.trim();
    const endereco = document.getElementById('Endereco').value.trim();
    const senha = document.getElementById('Senha').value.trim();
    const confirmar = document.getElementById('Confirmar').value.trim();

    // VERIFICA CAMPOS VAZIOS
    if (
        nome === '' ||
        email === '' ||
        idade === '' ||
        cpf === '' ||
        endereco === '' ||
        senha === '' ||
        confirmar === ''
    ) {

        alert('Preencha todos os campos!');
        return;
    }

    // VERIFICA SENHAS
    if (senha !== confirmar) {

        alert('As senhas não correspondem!');
        return;
    }

    // OBJETO ENVIADO PARA API
    const usuario = {

        nome,
        email,
        idade: parseInt(idade),
        cpf,
        endereco,
        senha,
        confirmaSenha: confirmar
    };

    console.log(usuario);

    try {

        const response = await fetch(url, {

            method: 'POST',

            headers: {
                'Content-Type': 'application/json'
            },

            body: JSON.stringify(usuario)
        });

        // SUCESSO
        if (response.ok) {

            alert('Cadastro realizado com sucesso!');

            window.location.href = '../login/login.html';

        } else {

            const erro = await response.text();

            console.error("ERRO BACKEND:");

            console.error(erro);

            const erroJson = JSON.parse(erro);

            alert(
                erroJson.errors[0].defaultMessage
            );
        }

    } catch (error) {

        console.error(error);

        alert('Erro ao conectar com o servidor!');
    }
}