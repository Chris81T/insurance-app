insurance-app
=============

Simple example, that is using mixed java / scala code; EJB 3.1 written in scala; using lift as web-framework; using AngularJS as client framework

What is needed to run the app:
==============================

- Please use a JEE 6 Container (web-profile is enough). I prefer the JBoss AS 7.1 web-server (for this one also the maven-plugin is configured)

1. call mvn package ( or install )
2. call mvn jboss-as:deploy ( or later on jboss-as:redeploy ) // this would only work with a JBoss AS server
3. have fun ; )
