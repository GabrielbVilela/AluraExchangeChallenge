# Conversor de Moedas ☕️

Este é um projeto em Java que permite aos usuários converterem valores entre diferentes moedas, utilizando a API ExchangeRate para obter taxas de câmbio atualizadas. O programa é interativo e guiado por menus, fornecendo uma experiência amigável para o usuário.

## Funcionalidades 🚀

- Conversão entre diversas moedas, incluindo USD, BRL, ARS, EUR, RUB, CNY, e JPY.
- Integração com a API ExchangeRate para buscar taxas de câmbio em tempo real.
- Opção de converter valores personalizados ou verificar a taxa de câmbio de uma unidade.
- Tratamento de erros e exceções para entrada de dados inválidos e problemas na API.

## Estrutura do Projeto 👤

```
/src
├── Main.java               # Ponto de entrada do programa
├── Interface.java          # Gerencia a interação com o usuário
├── IntegrarAPI.java        # Conecta e processa dados da API ExchangeRate
├── Exceptions
│   └── ApiException.java   # Trata erros específicos da API
└── Models
    └── modelApi.java       # Modelo para dados da API
```

## Pré-requisitos 🛠️

- **Java 11** ou superior.
- **Bibliotecas**: `gson` (Google Gson para manipulação de JSON).

## Uso

- Escolha a moeda base e a moeda alvo a partir de uma lista de opções.
- Insira o valor que deseja converter ou utilize o valor padrão de 1 unidade.
- O programa exibirá a taxa de câmbio atual e o valor convertido.

## Exemplo de Uso

```text
******************************************************
                        
           Conversor de moedas
                        
Para escolher um moeda, digite o número
entre parenteses que representa ela:
                        
(1) USD, Dolar - Estados Unidos,
(2) BRL, Real - Brasil
(3) ARS, Peso - Argentina
(4) EUR, Euro - União Europeia
(5) RUB, Rublo - Russia
(6) CNY, Renminbi - China
(7) JPY, Iene - Japão
(8) Para sair do programa
                        
******************************************************
```

## Tratamento de Erros

- **ApiException**: Trata erros retornados pela API ExchangeRate, como `unsupported-code`, `quota-reached` e `malformed-request`.
- **Entrada Inválida**: O programa verifica e solicita novamente se entradas inválidas forem detectadas.

## Licença

Este projeto é licenciado sob a [MIT License](LICENSE).

## Desenvolvimento

Desenvolvido com 💻 e ☕️ por Gabriel Vilela.
