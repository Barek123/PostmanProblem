    pl.marcb.postman.algorithm.Cpp
tworzy CppAlgorithm z parametrów znajdujących sie w application.properties
weryfikuje czy wynik jest poprawny, jezeli nie puszcza ponownie
sprawdza czy nie zostala przekroczona maksymalna ilosc powtórzeń

    pl.marcb.postman.algorithm.CppAlgorithm
zwyczajna implementacja klasy GAStringsSeq
podczas wywołania getFitness(int index) wywołuje metode calculate w CppUtils

    pl.marcb.postman.algorithm.CppUtils
cała logika aplikacji (ta którą zaimplementowałem)
* calculateFitness(ChromStrings chromosome) metoda używana przez CppAlgorithm
* boolean allPointsExist(List<String> genes) zwraca czy wszystkie krawędzie i wierzchołki są zawarte w odpowiedzi
* private double searchInGraphElement(String pointA, String pointB) wyszukuje wartości polączenia punktu A do B (lub odwrotnie) i zwraca znalezioną wartość, w przeciwnym wypadku 0.0
* initData() służy do pobrania danych wejściowych z pliku testowego (np. lines.txt) służących do obliczeń

    pl.marcb.postman.config.ConfigProperties
dzięki tej klasie możliwe jest pobieranie wartości z pliku appliaction.properties
(PostmanProblem\src\main\resources\application.properties)

    pl.marcb.postman.domain.GraphElement
klasa służąca do przechowywania danych o grafie (pobranych z pliku) tzw. DTO (data transfer object)

    pl.marcb.postman.exceptions.ParserException
nowy wyjątek który występujący podczas niekompletnych danych podczas parsowania pliku txt
np. 1 2 34 zamiast 1 2 3 4

    pl.marcb.postman.parser.Parser
parser dzięki któremu za pomocą wywołąnia metody mapowany jest plik z danymi testowymi
dodatkowo przy parsowaniu wyszukiwane są unikatlne punkty i zapiywane jako List<String> oraz String[] (w zależności od potrzeb)
zapiwyana jest też wartość EDGE

    pl.marcb.postman.Main
wykorzystywana do startu :) bez znaczenia (startuje klase Cpp)

    plik: PostmanProblem\src\main\resources\application.properties
prechowuje wartości startowe tj.
* lokalizacje pliku z wartościami testowymi
* wartości wykorzystywane w algorytmie np. rozmiar populacji, maksymalna generacja
* maksymalną liczbę powtórzeń przy wyszukiwaniu wartości (żeby wykluczyć zapętlenie w nieskończoność)


klasy takie jak Parser, ConfigProperties, CppUtils wykorzystuja wzorzec projektowy Singletone
dzięki temu klasy te mogą mięc wyłącznie jedną instance
aby korzystać z takiej klasy wpisuje się np. Parser parser = Parser.getInstance(); zamiast Parser parser = new Parser;



#############################################################
########################### UWAGA ###########################
#############################################################

ABY POPRAWNIE URUCHOMIĆ PROGRAM W INTELLIJ POTRZEBNA JEST WARTOŚĆ SYSTEMOWA
w górnym prawym rogu gdzie jest konfiguracja uruchomieniowa (np. Main)
naciskamy -> wybieramy edit configuration
w polu VM Options wpisujemy
    -DPROP_FILE="C:/Users/..../PostmanProblem/src/main/resources/application.properties"
(oczywiście z prawdziwą ścieżką do pliku :))

#############################################################
########################## UWAGA v2##########################
#############################################################

aby uruchomić program w intellij należy otworzyć go jako maven project
czyli open -> wejście do folderu projektu -> dwukrotne naciśnięcie na pom.xml -> Open as maven project
