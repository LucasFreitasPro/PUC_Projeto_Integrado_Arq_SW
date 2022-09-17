# ADR-3: Persistência com banco NoSQL

## Status

Aceita

## Context

A solução está inserida em um contexto de ao alto volume de dados que serão coletados pelos dispositivos IoT no campo e transmitidos ao sistema, e devido a isso, faz-se necessário a utilização de tecnologias de bancos de dados que possam suportar trabalhar com esse contexto, além de também conseguirem convergir adequadamente com os microserviços nesse contexto.

## Decision

Devido ao contexto do ecossistema em que a solução estará inserida, decidiu-se, além de utilizar um banco relacional, utilizar também o recurso de bancos não relacionais, em específico, o mongodb, que por ser um banco de dados de documentos, suporta cargas extremamente altas de dados com uma performance alta e adequada à necessidade que a persistência exige nesse modelo, inclusive apresentando lidar melhor com o problema que os bancos de dados relacionais. 

## Consequences

As consequências envolvem a garantia de alta performance das aplicações, com volumes exorbitantes de dados. Também é mais simples alterar o modelo dos dados, inclusive com exclusividade de modelos e atributos por documentos, então a flexibilidade agrega muito valor.
Em detrimento a isso, existe uma alta complexidade em construir e manter bancos de dados não relacionais, além de ser bem mais complexo relacionar dados entre modelos.