document.addEventListener("DOMContentLoaded", function () {
    const cadastroContainer = document.getElementById('cadastro');
    const listagemContainer = document.getElementById('listagem');
    const cadastrarButton = document.getElementById('cadastrarButton');
    const novoProduto = document.getElementById('novoProduto');
    const produtoTabela = document.getElementById('produto');

    let produtos = [];

    function mostrarCadastro() {
        cadastroContainer.classList.add('active');
        listagemContainer.classList.remove('active');
    }

    function mostrarListagem() {
        cadastroContainer.classList.remove('active');
        listagemContainer.classList.add('active');
    }

    function adicionar(produto) {
        produtos.push(produto);
        produtos.sort((a, b) => a.valor - b.valor);
        atualizar();
    }

    function atualizar() {
        produtoTabela.innerHTML = '';
        produtos.forEach(produto => {
            const linha = document.createElement('tr');
            linha.innerHTML = `<td>${produto.nome}</td><td>${produto.valor}</td>`;
            produtoTabela.appendChild(linha);
        });
    }

    cadastrarButton.addEventListener('click', () => {
        try {
            const nome = document.getElementById('nome').value;
            const descricao = document.getElementById('descricao').value;
            const valor = parseFloat(document.getElementById('valor').value);
            const disponivel = document.querySelector('input[name="disponivel"]:checked').value === 'sim';

            if (nome && descricao && !isNaN(valor) && valor >= 0) {
                const produto = { nome, descricao, valor, disponivel };
                adicionar(produto);

                document.getElementById('nome').value = '';
                document.getElementById('descricao').value = '';
                document.getElementById('valor').value = '';
                document.querySelector('input[name="disponivel"]:checked').checked = false;
                mostrarListagem();
            }
        } catch (error) {
            alert('Preencha os campos corretamente');
            console.log(error);

        }

    });

    novoProduto.addEventListener('click', () => {
        mostrarCadastro()
    });
    mostrarListagem();
});