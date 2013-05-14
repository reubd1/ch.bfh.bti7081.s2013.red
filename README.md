ch.bfh.bti7081.s2013.red
========================

VAADIN ein Buch mit 7ben Siegeln:
=================================
- Vaadin Book: https://vaadin.com/book
- JPAContainer erklärt: http://vaadin.com/download/jpacontainer-tutorial/

@Team RED
=================================

Setup vom Projekt:
-----------------------
- GIT installieren
- JDK (1.7) installieren
- Maven installieren (als Kommandozeile! Oder einfach maven/bin in PATH hinzufügen)
- Eclipse mit folgenden Plugins via Marketplace 
  - egit
  - m2e (Maven Integration for Eclipse)
  - Vaadin

So kann das Demo Projekt gestartet werden:
-----------------------
- git pull 
- Kommandozeile starten
  - cd jpacontainer-addressbook-demo
  - mvn clean package
  - mvn jetty:run
- Browser http://localhost:8080

"Demo Persitent machen"
--------------------------
Demo braucht JPAContainer und Hibernate, welche in persistence.xml definiert werden. 
- Falls Wert auf "drop-and-create-tables" wird bei jedem START die DB neu erstellt. 
- Wenn man dies nicht möchte einfach folgende Zeile auskommentieren:
  - https://github.com/reubd1/ch.bfh.bti7081.s2013.red/blob/master/jpacontainer-addressbook-demo/src/main/resources/META-INF/persistence.xml#L25


Importing to Eclipse
--------------------------

You should be able to import the demo project in Eclipse as a Maven project.
You will need the m2e plugin for Eclipse. (get it from Marketplace "m2e Maven Integration for Eclipse"

Window --> Open Perspective --> Gti Repo Exploring
--> Navigate to project --> right click "import projects"



git command line
--------------------
git branch --set-upstream diary01 origin/diary01
