# PO-FILE-TRANSLATOR

Little tool that helps you to translate your PO file in french

## Build or not build:
* Build your own jar from sources with maven
    `mvn clean package`
    
Or

* Use the already built jar, findable in the *built/* directory

## Usage
     java -jar built/po-file-translator-0.1-SNAPSHOT-jar-with-dependencies.jar <<mypofile.po>>
     
It will create a new file (with the same name as your source file, plus '-fr' suffix) in the same location.