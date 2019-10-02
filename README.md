# Analizador Sintactico
Analizador sintáctico para un nuevo lenguaje de programación,
construido con el lenguaje de programacion java en su version 12.

* <a href="./src/main/java/Model/SyntacticAnalyzer.java">Analizador Sintactico</a>
* <a href="https://github.com/Daryl110/analizador__lexico">Analizador Lexico</a>
* <a href="./LICENSE">Licencia</a>
* <a href="./.circleci/config.yml">CircleCi</a>
* <a href="https://github.com/Daryl110/analizador__sintactico/wiki/BFN's">Definición de BNF's</a>

<h2>Gramaticas</h2>

- <h3>Expresiones</h3>
<ol>
    <li><a href="./src/main/java/Model/Statement/Expression/ExpressionStatement.java">Expresión General</a></li>
    <li><a href="./src/main/java/Model/Statement/Expression/ArrayExpressionStatement.java">Expresión para Definición de Array</a></li>
    <li><a href="./src/main/java/Model/Statement/Expression/LogicalExpressionStatement.java">Expresión Numerica</a></li>
    <li><a href="./src/main/java/Model/Statement/Expression/NumericExpressionStatement.java">Expresión Relacional</a></li>
    <li><a href="./src/main/java/Model/Statement/Expression/RelationalExpressionStatement.java">Expresión Logica</a></li>
    <li><a href="./src/main/java/Model/Statement/Expression/StringExpressionStatement.java">Expresión de Cadena</a></li>
</ol>

- <h3>Sentencias</h3>
<ol>
    <li><a href="./src/main/java/Model/Statement/BlockStatement.java">Sentencia de Bloque</a></li>
    <li><a href="./src/main/java/Model/Statement/InvokeFunctionStatement.java">Sentencia de Invocación de Función</a></li>
    <li><a href="./src/main/java/Model/Statement/Assignment/SimpleAssignmentStatement.java">Sentencia de Asignación Simple</a></li>
    <li><a href="./src/main/java/Model/Statement/Assignment/OthersAssignmentsStatement.java">Sentencia de Otras Asignaciones</a></li>
    <li><a href="./src/main/java/Model/Statement/Switch/CaseStatement.java">Sentencia Case/Default</a></li>
    <li><a href="./src/main/java/Model/Statement/Switch/SwitchStatement.java">Sentencia Switch</a></li>
</ol>