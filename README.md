
<p align="center">
  <a href="#">
    <img src="https://raw.githubusercontent.com/mcosta21/alarme-contra-incendio/master/docs/logo.png" alt="Logo" width="600">
  </a>
</p>

<p align="center">
    An awesome README template to jumpstart your projects!
    <br />
    <a href="https://github.com/othneildrew/Best-README-Template"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/othneildrew/Best-README-Template">View Demo</a>
    ·
    <a href="https://github.com/othneildrew/Best-README-Template/issues">Report Bug</a>
    ·
    <a href="https://github.com/othneildrew/Best-README-Template/issues">Request Feature</a>
  </p>

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [Sobre o projeto](#sobre-o-projeto)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Usage](#usage)
* [Roadmap](#roadmap)
* [Contributing](#contributing)
* [License](#license)
* [Contact](#contact)
* [Acknowledgements](#acknowledgements)


## Sobre o projeto

 <img src="https://raw.githubusercontent.com/mcosta21/alarme-contra-incendio/master/docs/banner.png" alt="Banner">

O projeto em questão visa o monitoramento de um deteminado ambiente, de modo a prevenir riscos contra incêndios, assim foi estabelecido uma comunicação serial entre um Arduino UNO, o qual irá gerenciar os sensores, e uma aplicação JSF (Java Server Faces) para exibir as leituras e alertas conforme níveis condicionais.
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

Vale ressaltar que além da criação da aplicação, foi construido um circuito similar na plataforma Tinkercad, de modo que possamos realizar testes virtualmente. Então, note que a estrutura do projeto se divide em três pastas, onde tem-se: 

* **arduino**: Pasta contendo o arquivo com código fonte para implementação do projeto no Arduino UNO;
* **tinkercad**: Pasta contendo o arquivo com código fonte usado em simulação do circuito no Tinkercad;
* **web**: Pasta referente a aplicação JSF;

#### Arduino e Tinkercad
A implementação do código em ambos ambientes seguiram a mesma base, diferenciado-se apenas no modelo dos sensores, que por sua vez, influenciou nos valores de medição de cada um deles. E além disso, a simulação do projeto no tinkercad conta com um Display 16x2 para subir a ausência da aplicação web.

#### Web
Quanta a aplicação JSF, foi criado um projeto Maven com uso da IDE Eclipse e um servidor Tomcat 9 para rodar a aplicação. No arquivo **pom.xml** é encontrado as dependências do projeto, onde tem-se a dependência referida ao framework **Primefaces** para criar da interface, e a biblioteca **JSerialComm**, reponsável pela métodos de comunicação serial com o arduino.


<p align="center">
 <a href="https://fazecast.github.io/jSerialComm/" target="_blank"><img src="https://raw.githubusercontent.com/mcosta21/alarme-contra-incendio/master/docs/icon-jserialcomm.png" alt="jSerialComm" width="200"></a>
 &nbsp&nbsp&nbsp&nbsp
 <a href="https://www.primefaces.org/showcase/getstarted.xhtml" target="_blank"><img src="https://raw.githubusercontent.com/mcosta21/alarme-contra-incendio/master/docs/icon-primefaces.png" alt="Primfaces" width="200"></a>
</p>

Vale ressaltar que a aplicação e o arduino irão estabelecer a comunicação serial via porta USB.

Of course, no one template will serve all projects since your needs may be different. So I'll be adding more in the near future. You may also suggest changes by forking this repo and creating a pull request or opening an issue.

A list of commonly used resources that I find helpful are listed in the acknowledgements.

### Built With
This section should list any major frameworks that you built your project using. Leave any add-ons/plugins for the acknowledgements section. Here are a few examples.
* [Bootstrap](https://getbootstrap.com)
* [JQuery](https://jquery.com)
* [Laravel](https://laravel.com)



<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.
* npm
```sh
npm install npm@latest -g
```

### Installation

1. Get a free API Key at [https://example.com](https://example.com)
2. Clone the repo
```sh
git clone https://github.com/your_username_/Project-Name.git
```
3. Install NPM packages
```sh
npm install
```
4. Enter your API in `config.js`
```JS
const API_KEY = 'ENTER YOUR API';
```



<!-- USAGE EXAMPLES -->
## Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_



<!-- ROADMAP -->
## Roadmap

See the [open issues](https://github.com/othneildrew/Best-README-Template/issues) for a list of proposed features (and known issues).



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Your Name - [@your_twitter](https://twitter.com/your_username) - email@example.com

Project Link: [https://github.com/your_username/repo_name](https://github.com/your_username/repo_name)



<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* [GitHub Emoji Cheat Sheet](https://www.webpagefx.com/tools/emoji-cheat-sheet)
* [Img Shields](https://shields.io)
* [Choose an Open Source License](https://choosealicense.com)
* [GitHub Pages](https://pages.github.com)
* [Animate.css](https://daneden.github.io/animate.css)
* [Loaders.css](https://connoratherton.com/loaders)
* [Slick Carousel](https://kenwheeler.github.io/slick)
* [Smooth Scroll](https://github.com/cferdinandi/smooth-scroll)
* [Sticky Kit](http://leafo.net/sticky-kit)
* [JVectorMap](http://jvectormap.com)
* [Font Awesome](https://fontawesome.com)





<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=flat-square
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=flat-square
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=flat-square
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=flat-square
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=flat-square
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/screenshot.png
