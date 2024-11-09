## 📚 ARMAZENAMENTO DE IMAGENS E METADADOS 👨🏻‍💻
Este sistema foi desenvolvido com o objetivo de estudar o armazenamento de imagens e seus metadados em um banco de dados relacional. 
As imagens são convertidas em bytes e armazenadas no PostgreSQL.

## 📖 DESCRIÇÃO 
Utilizando a biblioteca Metadata-extractor, o sistema extrai automaticamente metadados da imagem fornecida, como: modelo, data, latitude, longitude, tamanho, resolução, altura e largura. Além disso, permite a inserção manual de um título e uma tag descritiva para cada imagem.

O sistema oferece funcionalidade de busca por palavra-chave, seja em contexto geral ou por categoria específica. As consultas ao banco de dados são realizadas por meio de queries personalizadas que utilizam agrupamento de dados para otimizar o desempenho. Os resultados são mapeados para uma estrutura List<Map<String, Object>> e posteriormente convertidos para um DTO, facilitando a manipulação e exibição dos registros.

<br>

## 🖥️ FUNCIONALIDADES PRINCIPAIS
* Busca por palavra-chave
* Armazenamento de Imagens
* Extração de Metadados
* Consultas Otimizadas e Agrupamento de Dados
<br>

## TECNOLOGIAS
Para o desenvolvimento, foram utilizados: <code style="color : darkorange">Lombok</code>, <code style="color : darkorange">Maven</code>, <code style="color : darkorange">Metadata-extractor</code>, <code style="color : darkorange">Spring Boot</code>, <code style="color : darkorange">PostgreSQL</code>, <code style="color : darkorange">SQL</code>. 

