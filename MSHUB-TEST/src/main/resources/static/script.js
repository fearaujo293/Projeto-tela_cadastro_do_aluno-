const formulario = document.getElementById("Aluno");
const Inome = document.getElementById("nome");
const Irm = document.getElementById("rm");
const Iresponsavel = document.getElementById("responsavel");
const Iemail = document.getElementById("email");
const Itelefone = document.getElementById("telefone");
const Ifoto = document.getElementById("foto");

formulario.addEventListener("submit", async (event) => {
    event.preventDefault(); // Impede o envio padrão do formulário

    try {
        // Primeiro Fetch: Enviando os dados em JSON
        const alunoData = {
            nome: Inome.value,
            matricula: Irm.value,
            responsavel: Iresponsavel.value,
            email: Iemail.value,
            telefone: Itelefone.value
        };

        const response1 = await fetch("http://localhost:8080/mshub/new", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(alunoData)
        });

        if (!response1.ok) {
            throw new Error("Erro ao enviar os dados do aluno.");
        }

        console.log("Dados do aluno enviados com sucesso.");
        
        // Timer de 4 segundos
        await new Promise((resolve) => setTimeout(resolve, 500));

        // Segundo Fetch: Enviando o arquivo
        const file = Ifoto.files[0];
        if (file) {
            const formData = new FormData();
            formData.append("file", file);

            const response2 = await fetch("http://localhost:8080/image/upload", {
                method: "POST",
                body: formData
            });

            if (!response2.ok) {
                throw new Error("Erro ao enviar a imagem.");
            }

            const result = await response2.json();
            console.log("Imagem enviada com sucesso:", result.url);
        } else {
            console.warn("Nenhuma imagem foi selecionada para upload.");
        }
    } catch (error) {
        console.error("Erro:", error.message);
    } finally {
        // Limpar os campos do formulário
        Inome.value = "";
        Irm.value = "";
        Iresponsavel.value = "";
        Iemail.value = "";
        Itelefone.value = "";
        Ifoto.value = "";
    }

});
