// USUÁRIO FAKE PARA TESTES
const usuarioTeste = {

    id: 1,

    nome: "Pablo Eduardo",

    email: "pablo@gmail.com",

    idade: 20,

    cpf: "52998224725",

    endereco: "São Paulo",

    senha: "123456",

};

// SALVA NO LOCALSTORAGE
localStorage.setItem(

    "usuarioLogado",

    JSON.stringify(usuarioTeste)
);

async function logar() {

    const url = 'http://localhost:8080/api/usuarios/login';

    // CAMPOS
    const email = document.getElementById('email').value;

    const senha = document.getElementById('senha').value;

    try {

        console.log(
            "Enviando dados:",
            { email, senha }
        );

        const response = await fetch(url, {

            method: 'POST',

            headers: {
                'Content-Type': 'application/json'
            },

            body: JSON.stringify({
                email,
                senha
            })
        });

        // ERRO LOGIN
        if (!response.ok) {

            const errorMessage =
                await response.text();

            throw new Error(
                `Erro no login: ${response.status} - ${errorMessage}`
            );
        }

        // DADOS DO USUÁRIO
        const data = await response.json();

        console.log('Sucesso:', data);

        // SALVA USUÁRIO
        localStorage.setItem(
            'usuarioLogado',
            JSON.stringify(data)
        );

        alert('Login realizado com sucesso!');

        // REDIRECIONA
        window.location.href =
            "/heartzclub/src/main/resources/home/home.html";

    } catch (error) {

        console.error(
            'Erro ao fazer login:',
            error
        );

        alert(
            'Erro ao fazer login. Verifique suas credenciais.'
        );
    }
}