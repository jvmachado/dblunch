# language: pt
# encoding: ISO-8859-1
Funcionalidade: US001 - Votar Restaurante
  Eu como profissional faminto
  Quero votar no meu restaurante favorito
  Para que eu consiga democraticamente levar meus colegas a comer onde eu gosto

  Cenario: RN 01 - Deve votar em um restaurante
    Dado que o profissional "Robinho" selecionou o restaurante "Palatus"
    Quando votar no restaurante 
    Então o voto deve ser salvo
    
    Cenário: RN01 - Deve permitir votar em dias diferentes
    Dado que no dia anterior o profissional "Robinho" votou no restaurante "Palatus"
    E que o profissional "Robinho" selecionou o restaurante "Palatus"
    Quando votar no restaurante 
    Então o voto deve ser salvo
    
    Cenário: RN 02 - Deve validar se o profissional já votou em um restaurate
    Dado que o profissional "Rambo" votou no restaurante "Palatus"
    E que o profissional "Rambo" selecionou o restaurante "Palatus"
    Quando votar no restaurante 
    Então deve receber uma mensagem de aviso "Usuário já votou hoje."
    E o voto não deve ser salvo
    
    Cenário: RN01 - Deve permitir votar em restaurante em dias diferentes