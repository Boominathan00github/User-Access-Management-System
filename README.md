# User-Access-Management-System
Project Assignment : This system allows users(role) to signup, request access to software application, and enables managers(role) to approve or reject these users requests.
https://github.com/Boominathan00github/User-Access-Management-System.git

User Access Management System

This project provide a user access management system where users can signup, login, request access to software, and manage access requests based on roles(Employee, Manager, Admin). It uses java Servlets for handling the server-side logic and PostgreSQL for database management.

FEATURES:

User Sign Up: Allows users to sign up with a username and password.
User Login: Users can log in with their credentials, and based on their role, they are redirected to different pages(e.g., Employee-> RequestAccess, Manager-> PendingRequest, Admin-> CreateSoftware).
Request Access: Employees can request access to software by specifying the software, accesstype, and reason.
Manage Request: Managers can approve or reject requests made by employees.
Create Software: Admins can create new software entries with different access levels.


TECHNOLOGIES USED:

Java Servlet
JSP for Frontend
PostgreSQL


 
PROJECT STRUCTURE:

Servlets: Handle the main logic for user registration, login, access request, and request approval.
JSP Pages: Forms for user interaction (sign up, login, create software, request access).


Setup Instructions:

Clone the repository: git clone URl--> https://github.com/Boominathan00github/User-Access-Management-System.git
Setup the PostgreSQL database and configure your database connection details in the servlets.  
Deploy the project to your Preferred Container(e.g., Apache Tomcat 9.0).


Database Tables:  

User Table: CREATE TABLE users (id SERIAL PRIMARY KEY, username TEXT UNIQUE, password TEXT, role TEXT);
Software Table: CREATE TABLE softwares (id SERIAL PRIMARY KEY, name TEXT, description TEXT, access_levels TEXT);
Request Table: CREATE TABLE requests (id SERIAL PRIMARY KEY, user_id INTEGER REFERENCES users(id), software_id INTEGER REFERENCES softwares(id) , access_type TEXT, reason TEXT, status TEXT);


Operation(How to Use):

1.Right click the User-Access-Management-System & select run option and Deploy the Project into Tomcat, At the time Tomcat is runnig in localhost port8080.
2.Context Path:http://localhost:8080/User-Access-Management-System/ -> Index.jsp 
3./signUp.jsp -> At the moment view the signup.jsp,user signup the application, need two things Username & password(NOTE:newUser Default Role "Employee").
4.singnUpServlet get from the user data signup.jsp and store userinfo to the database(PostgreSQL:user table),once sign is successful then user Redirect to Login.jsp.
5./login.jsp -> user enter a login userinfo the Request -> goto LoginServlet and check userdata match found, The user Redirect to corresponding Servlet(Emp->requestAccess, Manager->pendingRequest, Admin->createSoftware).
6.TEST: Goto LoginServlet class and Change the user Role to Employee or Manager or Admin. And test the Application. 


NOTE: This application help to utilize your j2EE knowledge.
