ch.bfh.bti7081.s2013.red
========================

=================================
VAADIN ein Buch mit 7ben Siegeln:
- Vaadin Book: https://vaadin.com/book
- JPAContainer erklärt: http://vaadin.com/download/jpacontainer-tutorial/

=================================
@Team RED

-----------------------
Setup vom Projekt:
- GIT installieren
- JDK (1.7) installieren
- Maven installieren (als Kommandozeile! Oder einfach maven/bin in PATH hinzufügen)
- Eclipse mit folgenden Plugins via Marketplace 
  - egit
  - m2e (Maven Integration for Eclipse)
  - Vaadin

-----------------------
So kann das Demo Projekt gestartet werden:
- git pull 
- Kommandozeile starten
  - cd jpacontainer-addressbook-demo
  - mvn clean package
  - mvn jetty:run
- Browser http://localhost:8080
