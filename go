mkdir bin
pushd .
cd src
javac -deprecation -O -d ../bin prolog/j*/*.java *.java
popd
run $1 $2 $3 $4