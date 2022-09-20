# ADR-1: Mensageria com RabbitMQ

## Status

Aceita

## Context

A utilização de mensageria é motivada pela necessidade de alto volume de transmissão e processamento de dados vindos de várias origens do campo, e esse processamento não pode causar impactos negativos no sistema, ocasionando inclusive perda de dados.

## Decision

Decidimos implementar o RabbitMQ para promover esse controle de transmissão, e evitar que haja perda de dados e informação no meio de tantos procedimentos realizados durante o processamento.

## Consequences

As consequencias envolvem obter melhor desempenho no processamento dos dados coletados, sem que haja gargalos e/ou perda de dados, que são utilizados para após processamento, fornecerem insumos estatísticos e gerarem inteligência para uma melhor tomada de decisão.
Além disso, é auto-escalável, podendo inclusive trazer economia com a tecnologia implementada.

Um ponto a se destacar é a complexidade envolvida nessa inplementação, que aumenta e exige um time mais experiente.