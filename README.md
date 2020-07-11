# spring-security-jdbc
Spring Security Application with JDBC authentication 
(see here)[http://spring-security-jdbc.herokuapp.com]
!!! this is just a demonstration of spring security, do not use your personal credentials !!!

Role based authentication with 2 roles
  * ADMIN
  * USER

Pre-available credentials to login :-
username: root, password: root, role: ADMIN
username: admin, password: root, role: ADMIN
username: user, password: root, role: USER

Custom Password validation with 6 ways
 * Length between 8 and 20
 * At least a number
 * At least a uppercase
 * At least a lowercase
 * At least a special character
 * Should not have a space

>Setup.sql file under src/resources
>execution of provided sql file is required to make security work
