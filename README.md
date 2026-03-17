👨‍💻 Autor

Elias Pinheiro Pereira
📧 Email: eliaspinheiropereiraa@gmail.com

# 🤖 Automação de Download de Contra-Cheques

Projeto desenvolvido em Java com Spring Boot e Selenium para automatizar o download de contra-cheques do Portal do Servidor (PA).

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Selenium WebDriver
- Maven
- Lombok
- MapStruct
- Jakarta Validation

---

## 📌 Funcionalidades

- 🔐 Login automático no portal
- 📅 Seleção de ano inicial e final
- 📆 Captura dinâmica dos meses disponíveis
- 📂 Criação automática de pastas por usuário e ano
- ⬇️ Download automático de contra-cheques
- 🔄 Movimentação dos arquivos:
  - `/Downloads/temp` → pasta do usuário organizada
- 🧠 Identificação automática do sistema operacional (Linux/Windows)
- ✅ Validação de dados via API

---

## 📁 Estrutura de Pastas
/Downloads/temp # Pasta temporária de download
/Desktop/BotPortalServidorPA/
└── pastas_servidores/
└── {nome + matrícula}/
└── {ano}/
└── {mes}.pdf

📡 Endpoint da API
POST /api/v1/automacao
📥 Exemplo de Requisição:
{
  "url": "https://sistemas.belem.pa.gov.br/portaldoservidor/#/login",
  "username": "usuario",
  "senha": "senha",
  "anoInicial": 2023,
  "anoFinal": 2022,
  "contrato": "SEMEC - 18"
}

✅ Validações

Campos obrigatórios com @NotBlank e @NotNull

Tratamento global de erros com @RestControllerAdvice

Retorno padrão:

{
  "status": 422,
  "mensagem": "erro de validação",
  "erros": [
    {
      "campo": "username",
      "mensagem": "Este campo é obrigatório"
    }
  ]
}

