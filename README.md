
<p align="center">
    <img src="https://raw.githubusercontent.com/mcosta21/alarme-contra-incendio/master/docs/logo.png" alt="Logo" width="600">=
</p>

<p align="center">
    Aplicação JSF que realiza comunicação serial com Arduino UNO para monitorar ambiente a fim de prevenir contra incêndios.
    <br />
  </p>

## Sumário

* [Sobre o projeto](#sobre-o-projeto)
  * [Componentes Físicos](#componentes-físicos)
  * [Componentes do Tinkercad](#componentes-do-tinkercad)
* [Estrutura](#prerequisites)
  * [Arduino e Tinkercad](#arduino-e-tinkercad)
  * [Web](#web)
* [Simulação](#simulação)
* [Iniciar projeto](#iniciar-projeto)
* [Como contribuir](#Como-contribuir)
* [Contato](#contato)
* [Licença](#licença)


## Sobre o projeto

 <img src="https://raw.githubusercontent.com/mcosta21/alarme-contra-incendio/master/docs/banner.png" alt="Banner">

O projeto em questão visa o monitoramento de um determinado ambiente, de modo a prevenir riscos contra incêndios, assim foi estabelecido uma comunicação serial entre um Arduino UNO, o qual irá gerenciar os sensores, e uma aplicação JSF (Java Server Faces) para exibir as leituras e alertas conforme níveis condicionais.
Logo, definiu-se a utilização dos seguintes sensores e condições a serem tratadas:
* **Sensor de gás**: Detectar uma concentração de gás no ambiente igual ou superior a 30%;
* **Sensor de luz**: Averigar uma luminosidade excessiva igual ou superior a 60%, que indique possíveis causas de incêncio;
* **Sensor de temperatura**: Medir a temperatura ambiente do local, de modo a associar uma temperatura normal de 15°C a 35°C, sendo passível de alarmar em variações abruptas a 20% ou superiores a 35°C;
* **Sensor de presença**: Detectar a presença de pessoas no ambiente, a fim de indicar níveis de incidêntes emergências;

Diante dessas definições, atribuiu-se níves de alarme de acordo com as ocorrências, resultando na seguinte tabela:

| Nível | Gás | Luz | Temperatura | Presença |
| --- | --- | --- | --- | --- |
| `Normal` | False | False | False | True or False |
| `Em risco` | False | False | True | True or False |
| `Risco eminente` | True | False | True | True or False |
| `Incêndio Nível 1` | False | False | True | False |
| `Incêndio Nível 2` | True | True | True | False |
| `Alerta Vermelho` | True or False | True | True | True |

Então, a partir dos valores condicionais, foi possível atribuir um conjunto para cada leitura provida pelo Arduino, a fim de "printar" via comunicação serial, e tratar tais valores pela aplicação.

```
[gas,7.45%,54,0];[light,56.86%,145,0];[temp,45.00%,24.00,1];[presence,Movimento local,1,1];
```

#### Componentes físicos

* Arduino UNO;
* Jumper;
* Protoboard;
* Sensor Fotoresistor LDR de 5mm;
* Sensor de presença (PIR);
* Sensor de temperatura TMP36;
* Sensor de Gás MQ-5 GLP (Gás de Cozinha e Gás Natural);
* Resistor de 10kΩ;

#### Componentes do Tinkercad

* Arduino UNO;
* Jumper;
* Protoboard;
* Sensor de luz ambiente;
* Sensor de presença (PIR);
* Sensor de temperatura TMP36;
* Sensor de gás;
* Piezo;
* Display lcd 16x2;
* Resistores de 220Ω, 1kΩ, e 10kΩ;

## Estrutura

Além da criação da aplicação, foi construido um circuito similar na plataforma Tinkercad, de modo que possamos realizar testes virtualmente. Então, note que a estrutura do projeto se divide em três pastas, onde tem-se: 

* **arduino**: Pasta contendo o arquivo com código fonte para implementação do projeto no Arduino UNO;
* **tinkercad**: Pasta contendo o arquivo com código fonte usado em simulação do circuito no Tinkercad;
* **web**: Pasta referente a aplicação JSF;

#### Arduino e Tinkercad
A implementação do código em ambos ambientes seguiram a mesma base, diferenciado-se apenas no modelo dos sensores, que por sua vez, influencia nos valores de medição de cada um deles. E além disso, a simulação do projeto no tinkercad conta com um Display 16x2 para subir a ausência da aplicação web.

#### Web
Quanta a aplicação JSF, foi criado um projeto Maven com uso da IDE Eclipse e um servidor Tomcat 9 para rodar a aplicação. No arquivo **pom.xml** é encontrado as dependências do projeto, onde tem-se a dependência referida ao framework **Primefaces** para criar da interface, e a biblioteca **JSerialComm**, reponsável pelos métodos de comunicação serial com o arduino. Vale ressaltar que a aplicação e o arduino irão estabelecer a comunicação serial via porta USB.

## Tecnologias
Basicamente, este projeto foi desenvolvimento com as seguintes tecnologias:

<p align="center">
 <a href="https://pt.wikipedia.org/wiki/JavaServer_Faces" target="_blank"><img src="https://raw.githubusercontent.com/mcosta21/alarme-contra-incendio/master/docs/icon-jsf.png" alt="JSF" width="150"></a>
  &nbsp&nbsp
 <a href="https://www.primefaces.org/showcase/getstarted.xhtml" target="_blank"><img src="https://raw.githubusercontent.com/mcosta21/alarme-contra-incendio/master/docs/icon-primefaces.png" alt="Primfaces" width="150"></a>
  &nbsp&nbsp
 <a href="https://fazecast.github.io/jSerialComm/" target="_blank"><img src="https://raw.githubusercontent.com/mcosta21/alarme-contra-incendio/master/docs/icon-jserialcomm.png" alt="jSerialComm" width="150"></a>
 &nbsp&nbsp
 <a href="https://www.eclipse.org/downloads/" target="_blank"><img src="https://raw.githubusercontent.com/mcosta21/alarme-contra-incendio/master/docs/icon-eclipse.png" alt="Eclipse IDE" width="150"></a>
 &nbsp&nbsp
  <a href="http://tomcat.apache.org/" target="_blank"><img src="https://raw.githubusercontent.com/mcosta21/alarme-contra-incendio/master/docs/icon-tomcat.png" alt="Tomcat" width="150"></a>
 &nbsp&nbsp
</p>

## Simulação

<img src="https://raw.githubusercontent.com/mcosta21/alarme-contra-incendio/master/docs/banner-tinkercad.png" alt="Banner">

Conforme mencionado anteriormente, foi criado um circuito no Tinkercad para fins de testes, logo, você pode acessa-lo através do link abaixo.

<p align="center">
 <a href="https://www.tinkercad.com/things/a4jds0HMe1j-alarme-de-incendio" target="_blank"><img src="https://raw.githubusercontent.com/mcosta21/alarme-contra-incendio/master/docs/button.png" alt="Visualizar Tinkercad" width="200"></a>
 </p>

## Iniciar projeto

Com a Eclipse IDE instalado, e em posse do terminal, siga as seguintes instruções:

```bash
# Clonar o repositório
$ git clone https://github.com/mcosta21/alarme-contra-incendio

# Abra o projeto no Eclipse
Arquivo > Abrir projeto

# Adicione servidor Tomcat
Na janela `Servidores`, busque adicionar um novo servidor e localize o tipo 'Tomcar v9.0 Server'
e avance para as configurações, em seguida adicione o projeto 'alarme-contra-incendio'
clicando em `Adicionar`

# Iniciar a aplicação
Note que o servidor estará em estado de parado, clique no botão `Iniciar`

# Acesse a aplicação
No navegador, acesse 'http://localhost:8080/alarme/'
```

## Como contribuir

As contribuições são o que torna a comunidade de código aberto um lugar incrível para aprender, inspirar e criar. Quaisquer contribuições que você fizer serão **muito apreciadas**.

1. Faça um fork do projeto.
2. Crie uma nova branch com as suas alterações: (`git checkout -b my-feature`)
3. Salve as alterações e crie uma mensagem de commit contando o que você fez: (`git commit -m 'Add a new amazing feature'`)
4. Envie as suas alterações: (`git push origin my-feature`)

## Contato

Marcio Costa - [marcioc424@gmail.com](mailto:marcioc424@gmail.com)

| <a href="https://github.com/mcosta21"><img src="https://cdn.iconscout.com/icon/free/png-512/github-153-675523.png" width="30"></a> | <a href="https://www.linkedin.com/in/marcio-costa-03131a149/"><img src="https://www.pontovixmarketingdigital.com.br/images/linkedin.png" width="30"></a> | <a href="mailto:marcioc424@gmail.com"><img src="https://image.flaticon.com/icons/png/512/281/281769.png" width="30"></a> | 
| --- | --- | --- |

## Licença

Este projeto está sob a licença MIT.

> Desenvolvido por [Marcio Costa](https://www.linkedin.com/in/marcio-costa-03131a149/).
<br/>
