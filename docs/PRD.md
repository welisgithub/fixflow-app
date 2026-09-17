# Product Requirements Document (PRD) - SaaS de Gestão de Ordens de Serviço

## 1. Visão Geral e Atores do Sistema
* **ADMIN:** Acesso total ao sistema (gestão de usuários, relatório financeiro, peças e configurações).
* **TECNICO:** Gestão operacional das Ordens de Serviço (alteração de status, laudo técnico, consumo de peças e fotos).
* **CLIENTE:** Acesso exclusivamente passivo de visualização do status do reparo via token/código da O.S. e CPF, sem necessidade de login.

---

## 2. Mapeamento de Estados da O.S.
```text
[ABERTA] -> [EM_ANALISE] -> [ORCAMENTO_GERADO]
                |                   |
                | (Se Aprovado)     +---> [ORCAMENTO_APROVADO] -> [PECA_EM_TRANSITO] -> [EM_ANDAMENTO] -> [AGUARDANDO_RETIRADA] -> [FINALIZADA]
                |                   |
                + (Se Recusado)     +-------------------------------------------------------------------> [AGUARDANDO_RETIRADA]
```

---

## 3. Requisitos Funcionais (RF)
* **RF-01 (Integração IMEI):** Ao informar o IMEI no cadastro, o sistema consome uma API REST externa para autopreencher Marca, Modelo Comercial e Modelo Técnico.
* **RF-02 (Registro Visual e Avarias):** Campo dedicado a anotações de danos pré-existentes e suporte a upload de fotos do aparelho no ato da entrada.
* **RF-03 (Geração de PDF):** Módulo para exportar a Ordem de Serviço formatada em PDF contendo dados da empresa, cliente, termos de serviço e estado do aparelho para impressão ou envio.
* **RF-04 (Consulta Externa):** Endpoint público para o cliente acompanhar o status informando apenas o código da O.S. e CPF.

---

## 4. Requisitos Não-Funcionais (RNF)
* **RNF-01 (Segurança):** Autenticação Stateless com Spring Security + JWT e controle de acesso baseado em papéis (RBAC).
* **RNF-02 (Persistência):** PostgreSQL gerenciado via Docker com migrações de banco de dados.
* **RNF-03 (Documentação de API):** Swagger/OpenAPI integrado ao Spring Boot para testes e documentação interativa dos endpoints.