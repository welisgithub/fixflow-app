package com.fixflow.backend.domain.entity;

public enum OrderStatus {
    OPEN,               // Aberta
    IN_DIAGNOSIS,       // Em Análise
    WAITING_APPROVAL,   // Aguardando Aprovação do Cliente
    APPROVED,           // Aprovada
    IN_REPAIR,          // Em Manutenção
    WAITING_PARTS,      // Aguardando Peça / Peça em Trânsito
    COMPLETED,          // Concluída / Pronta para Retirada
    DELIVERED,          // Entregue ao Cliente
    CANCELLED           // Cancelada
}