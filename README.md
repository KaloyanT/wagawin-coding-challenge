Wagawin Coding Task
== 

# How to run the application: 
1. Create a MySQL database with the name "wagawin_db" and a user "wagawin_db_user" with ALL PRIVILEGES granted. 
2. Specify the address of the database and the port in the src/resource/application.properties file. 
3. Open a terminal window, navigate to the root folder of the application and run "mvn spring-boot:run". Alternatively run "mvn clean package", navigate to the "target" directory and run "java -jar wagawin-coding-task-0.0.1.jar"

# Available Endpoints: 
/house?houseId=

/child/info?childId=

/color?childId=

/persons/children