mkdir bin
pushd .
cd src
javac -deprecation -O -d ../bin prolog/terms/* prolog/fluents/*.java  prolog/io/*.java  prolog/builtins/*.java prolog/*.java
popd
run %1 %2 %3 %4