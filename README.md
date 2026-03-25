# Enterprise Employee Management System (EMS)

![Language](https://img.shields.io/badge/language-Java-blue)
![Architecture](https://img.shields.io/badge/arquitetura-Layered%20Architecture-success)
![Interface](https://img.shields.io/badge/interface-Java%20Swing-orange)

---

Sistema de gestão de funcionários desenvolvido em **Java**, com foco em **boas práticas de arquitetura de software**, **persistência em arquivos JSON** e **interface gráfica desktop com Swing**.

Este projeto foi construído para demonstrar habilidades técnicas relevantes para ambiente profissional, incluindo modelagem orientada a objetos, separação de responsabilidades e organização em camadas.

## Preview

![App Gif](assets/gifs/app.gif)

## Principais funcionalidades

- Autenticação de usuários (login e cadastro)
- Gerenciamento de sessão de usuário
- Cadastro e manutenção de funcionários
- Diferentes tipos de funcionário (Developer, Manager, Intern)
- Persistência de dados em arquivos JSON
- Interface gráfica com múltiplas telas e componentes reutilizáveis
- Tema visual centralizado para padronização da UI

## Tecnologias utilizadas

- **Java** (POO)
- **Java Swing** (GUI desktop)
- **JSON** (armazenamento de dados local)
- **DAO Pattern** (acesso a dados)
- **Repository Pattern** (abstração de persistência)
- **Service Layer** (regras de negócio)
- **Hash de senha** (segurança de credenciais)

## Conhecimentos aplicados

- Programação Orientada a Objetos
- Arquitetura em camadas
- Design e implementação de CRUD com regras de negócio
- Organização de projeto e separação de responsabilidades
- Desenvolvimento de aplicações desktop
- Manipulação de arquivos e serialização de dados
- Tratamento de autenticação e segurança básica de usuários
- Estruturação de código para manutenção e escalabilidade

## Estrutura do projeto

```text
EMS/
├── src/
│   ├── dao/          # Acesso aos dados (JSON)
│   ├── model/        # Entidades de domínio
│   ├── repository/   # Camada de abstração de persistência
│   ├── service/      # Regras de negócio e autenticação
│   ├── session/      # Controle de sessão
│   └── view/         # Interface gráfica (Swing)
├── data/             # Base local em JSON (employees/users)
├── assets/           # Fontes, imagens e gifs
├── lib/              # Dependências locais
└── run.sh            # Script de execução
```

## Como executar

```bash
git clone https://github.com/seu-usuario/Enterprise_Employee_Management_System_-EMS-.git
cd Enterprise_Employee_Management_System_-EMS-
chmod +x run.sh
./run.sh
```

## Autoria

**Desenvolvimento e Arquitetura**

- Alejandro David Nava Nava — [GitHub](https://github.com/Alejandro-bcc)

---

_Este projeto foi desenvolvido com fins educacionais e de portfólio profissional._
