# ch.bfh.bti7081.s2013.red

## Overview: Wo ist was? Welche Dokumente sind wo?

GITHUB Branches
- master => stabile Version
- branches => entwicklung

Dokumente:
- Project [Diary RED (Google Docs)](https://docs.google.com/document/d/1rRi7C8XRM7_PWkS1ulC-wMDsGuzwwAkunF85WuAIzIA/edit?usp=sharing)
- SCRUM (Google Docs):
  - [Product Backlog](https://docs.google.com/spreadsheet/ccc?key=0AnCAnfX0pVZcdDBvdWhCQm5WSmhHejd2RlNrR2VpbWc&usp=sharing)
  - [Sprint Backlog]  (https://docs.google.com/spreadsheet/ccc?key=0AnCAnfX0pVZcdGdqN2s5eUFNaXpsSlgzREw3MF9Ga0E&usp=sharing)
- CS* Tasks: 
  - [CS1 TASKS] (https://github.com/reubd1/ch.bfh.bti7081.s2013.red/tree/master/ch.bfh.red.parent-doc/doc/cs1_tasks)
  - [CS2 TASKS] (https://github.com/reubd1/ch.bfh.bti7081.s2013.red/tree/master/ch.bfh.red.parent-doc/doc/cs2_tasks)

## VAADIN ein Buch mit 7ben Siegeln:

- Vaadin Book: https://vaadin.com/book
- JPAContainer erklärt: http://vaadin.com/download/jpacontainer-tutorial/

# @Team RED

## Setup vom Projekt:

- GIT installieren
- JDK (1.7) installieren
- Maven installieren (als Kommandozeile! Oder einfach maven/bin in PATH hinzufügen)
- Eclipse mit folgenden Plugins via Marketplace 
  - egit
  - m2e (Maven Integration for Eclipse)
  - Vaadin
  - run-jetty-run
- [See teamPolicy](https://github.com/reubd1/ch.bfh.bti7081.s2013.red/tree/master/ch.bfh.red.parent-doc/teamPolicy)


## Database

REDapp braucht HSQLDB mit JPAContainer und EclipseLink, welche in [persistence.xml](https://github.com/reubd1/ch.bfh.bti7081.s2013.red/tree/master/ch.bfh.red.appl/src/main/resources/META-INF/persistence.xml) definiert werden.

### DB Konfiguration
siehe und konfiguriere: persistence.xml

#### REDapp verwendet HSQLDB in zwei Varianten:
- Embedded (fast, easy)
- Server (REQURIES DB start in advance, Enables JDBC Access)

#### DB DDL Generation
- Falls Wert auf "drop-and-create-tables" wird bei jedem START die DB neu erstellt. 

### DB Design and Debugging

Use [ANT Script build.xml](https://github.com/reubd1/ch.bfh.bti7081.s2013.red/tree/master/ch.bfh.red.appl/runDBServer/build.xml)



## Importing to Eclipse


You should be able to import the demo project in Eclipse as a Maven project.
You will need the m2e plugin for Eclipse. (get it from Marketplace "m2e Maven Integration for Eclipse"

Window --> Open Perspective --> Gti Repo Exploring
--> Navigate to project --> right click "import projects"



## git command line

git branch --set-upstream diary01 origin/diary01
