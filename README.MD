# Projektowanie Efektywnych Algorytmów - Projekt

## Instrukcja uruchomienia

- Aby uruchomić problem należy skorzystać z java >= 17.0.2
- Uruchamiamy przez komende java -jar PeaProjekt.jar

```bash
javac -d build/PeaProjekt -sourcepath src/main/java src/main/java/TSP/Main.java
jar mcfv MANIFEST.MF PeaProjekt.jar -C build/PeaProjekt . src/ README.MD
java -jar PeaProjekt.jar
```