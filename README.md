# Analizador Sintactico
Analizador sintáctico para un nuevo lenguaje de programación,
construido con el lenguaje de programacion java en su version 12.

* <a href="./src/main/java/Model/SyntacticAnalyzer.java">Analizador Sintactico</a>
* <a href="https://github.com/Daryl110/analizador__lexico">Analizador Lexico</a>
* <a href="./LICENSE">Licencia</a>
* <a href="./.circleci/config.yml">CircleCi</a>
* <a href="https://github.com/Daryl110/analizador__sintactico/wiki/BFN's">Definición de BNF's</a>

<h2>Gramaticas</h2>

- <h3><a href="./src/main/java/Model/Statement/CompilationUnit.java">Unidad de Compilación</a></h3>

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
    <li><a href="./src/main/java/Model/Statement/Functions/InvokeFunctionStatement.java">Sentencia de Invocación de Función</a></li>
    <li><a href="./src/main/java/Model/Statement/Assignment/SimpleAssignmentStatement.java">Sentencia de Asignación Simple</a></li>
    <li><a href="./src/main/java/Model/Statement/Assignment/OthersAssignmentsStatement.java">Sentencia de Otras Asignaciones</a></li>
    <li><a href="./src/main/java/Model/Statement/Switch/CaseStatement.java">Sentencia Case/Default</a></li>
    <li><a href="./src/main/java/Model/Statement/Switch/SwitchStatement.java">Sentencia Switch</a></li>
    <li><a href="./src/main/java/Model/Statement/Others/ReturnStatement.java">Sentencia de Retorno</a></li>
    <li><a href="./src/main/java/Model/Statement/IF/IfStatement.java">Sentencia If</a></li>
    <li><a href="./src/main/java/Model/Statement/IF/ElseStatement.java">Sentencia Else</a></li>
    <li><a href="./src/main/java/Model/Statement/Assignment/IncrementalDecrementalOperationStatement.java">Sentencia de Incremento o Decremento</a></li>
    <li><a href="./src/main/java/Model/Statement/Iterators/ForStatement.java">Sentencia de Iteración For</a></li>
    <li><a href="./src/main/java/Model/Statement/Iterators/WhileStatement.java">Sentencia de Iteración While</a></li>
    <li><a href="./src/main/java/Model/Statement/Iterators/UntilStatement.java">Sentencia de Iteración Until</a></li>
    <li><a href="./src/main/java/Model/Statement/Iterators/ForEachStatement.java">Sentencia de Iteración For Each</a></li>
    <li><a href="./src/main/java/Model/Statement/Functions/ArrowFunctionStatement.java">Sentencia de Funcion Flecha</a></li>
    <li><a href="./src/main/java/Model/Statement/Functions/FunctionStatement.java">Sentencia de Funcion</a></li>
    <li><a href="./src/main/java/Model/Statement/Functions/ParameterStatement.java">Sentencia de Parametro</a></li>
</ol>

<h2>Pruebas Unitarias</h2>

- <h3><a href="./src/test/java/Model/Statement/CompilationUnitTest.java">Unidad de Compilación</a></h3>

- <h3>Expresiones</h3>
<ol>
    <li><a href="./src/test/java/Model/Statement/Expression/ExpressionStatementTest.java">Expresión General</a></li>
    <li><a href="./src/test/java/Model/Statement/Expression/ArrayExpressionStatementTest.java">Expresión para Definición de Array</a></li>
    <li><a href="./src/test/java/Model/Statement/Expression/LogicalExpressionStatementTest.java">Expresión Numerica</a></li>
    <li><a href="./src/test/java/Model/Statement/Expression/NumericExpressionStatementTest.java">Expresión Relacional</a></li>
    <li><a href="./src/test/java/Model/Statement/Expression/RelationalExpressionStatementTest.java">Expresión Logica</a></li>
    <li><a href="./src/test/java/Model/Statement/Expression/StringExpressionStatementTest.java">Expresión de Cadena</a></li>
</ol>

- <h3>Sentencias</h3>
<ol>
    <li><a href="./src/test/java/Model/Statement/BlockStatementTest.java">Sentencia de Bloque</a></li>
    <li><a href="./src/test/java/Model/Statement/Functions/InvokeFunctionStatementTest.java">Sentencia de Invocación de Función</a></li>
    <li><a href="./src/test/java/Model/Statement/Assignment/SimpleAssignmentStatementTest.java">Sentencia de Asignación Simple</a></li>
    <li><a href="./src/test/java/Model/Statement/Assignment/OthersAssignmentsStatementTest.java">Sentencia de Otras Asignaciones</a></li>
    <li><a href="./src/test/java/Model/Statement/Switch/CaseStatementTest.java">Sentencia Case/Default</a></li>
    <li><a href="./src/test/java/Model/Statement/Switch/SwitchStatementTest.java">Sentencia Switch</a></li>
    <li><a href="./src/test/java/Model/Statement/Others/ReturnStatementTest.java">Sentencia de Retorno</a></li>
    <li><a href="./src/test/java/Model/Statement/IF/IfStatementTest.java">Sentencia If</a></li>
    <li><a href="./src/test/java/Model/Statement/IF/ElseStatementTest.java">Sentencia Else</a></li>
    <li><a href="./src/test/java/Model/Statement/Assignment/IncrementalDecrementalOperationStatementTest.java">Sentencia de Incremento o Decremento</a></li>
    <li><a href="./src/test/java/Model/Statement/Iterators/ForStatementTest.java">Sentencia de Iteración For</a></li>
    <li><a href="./src/test/java/Model/Statement/Iterators/WhileStatementTest.java">Sentencia de Iteración While</a></li>
    <li><a href="./src/test/java/Model/Statement/Iterators/UntilStatementTest.java">Sentencia de Iteración Until</a></li>
    <li><a href="./src/test/java/Model/Statement/Iterators/ForEachStatementTest.java">Sentencia de Iteración For Each</a></li>
    <li><a href="./src/test/java/Model/Statement/Functions/ArrowFunctionStatementTest.java">Sentencia de Funcion Flecha</a></li>
    <li><a href="./src/test/java/Model/Statement/Functions/FunctionStatementTest.java">Sentencia de Funcion</a></li>
    <li><a href="./src/test/java/Model/Statement/Functions/ParameterStatementTest.java">Sentencia de Parametro</a></li>
</ol>