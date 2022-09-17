# ADR-2: Arquitetura com monolito e microserviços

## Status

Aceita

## Context

O contexto em que a solução está inserida exige que a arquitetura seja o mais desacoplada possível. Isso por que a solução é voltada para a gestão do campo, e precisa de alta escalabilidade, devido a ter que lidar com várias frentes de processamento.

## Decision

Decidimos implementar microserviços e um monolito para a aplicação web.
A aplicação que o usuário acessa pode ser uma aplicação única de plataforma Web, com padrão MVC, porém, os serviços que rodam permeando a aplicação, alimentando os dados coletados do campo e fornecendo insumos para as features do sistema precisam ser serviços autônomos, para garantir alta disponibilidade e alta performance.

## Consequences

As consequências envolvem a necessidade de um time de tecnologia com elevado grau de maturidade para trabalhar com uma arquitetura híbrida, pois a complexidade aumenta bastante. Em contra-partida, consegue-se um alto desempenho, alta disponibilidade e alta escalabilidade das partes do sistema, individualmente.