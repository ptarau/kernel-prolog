mkdir bin
pushd .
cd src
javac -deprecation -O -d ../bin prolog/*/*.java prolog/*.java
popd
run $1 $2 $3 $4