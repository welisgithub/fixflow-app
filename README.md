# 🛠️ FixFlow - PROJETO EM ANDAMENTO

> SaaS de Gestão de Ordens de Serviço e Controle de Bancada de Manutenção.


O **FixFlow** é uma solução para gestão de assistências técnicas e bancadas de manutenção de eletrônicos. O sistema gerencia todo o ciclo de vida das Ordens de Serviço, integrações com consulta automática de IMEI, registro visual de avarias, anexos de fotos e emissão de comprovantes em PDF.

---

## 📌 Documentação do Projeto

Toda a arquitetura e especificação do sistema estão documentadas na pasta `docs/`:

* 📄 [Documento de Requisitos e Escopo (PRD)](./docs/PRD.md)
* 🗄️ [Modelo de Banco de Dados (ERD)](./docs/ERD.md)

---

## 🚀 Tecnologias Utilizadas

### **Backend**
* Java 21 / Spring Boot 3
* Spring Security + JWT (Autenticação e Autorização Stateless)
* Spring Data JPA / Hibernate
* PostgreSQL (via Docker)
* OpenAPI 3 / Swagger

### **Frontend**
* React + TypeScript
* Vite
* Tailwind CSS + Shadcn UI
* Axios

---

## 📁 Estrutura do Repositório

```text
fixflow-app/
├── backend/                  # API REST em Spring Boot
├── frontend/                 # Interface Web em React
├── docs/                     # Documentação Técnica
│   ├── PRD.md                # Requisitos Funcionais e Escopo
│   └── ERD.md                # Modelo Relacional do Banco de Dados
├── docker-compose.yml        # Infraestrutura (PostgreSQL + Serviços)
└── README.md                 # Apresentação do Projeto
```