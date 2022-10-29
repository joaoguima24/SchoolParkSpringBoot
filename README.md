# SchoolParkSpringBoot
Experiments with MySQL , Redis 

This is a project using designed with MVC architecture, using Java SpringBoot with security, data (JPA connecting with MySql), AOP (to create a logger).



Create account and login (using JWT security).



Use CRUD operators to create, delete and update Teachers and this is process will always pass through the command to validate the request params before getting into controllers(using DTO), then will pass through the service and only then will make it to the repository.



Create events and relate this events with the teachers.
