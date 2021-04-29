###### Demo-banking-app

###### Required java version
```bash
java version 11
```

###### Create path for log files in logback.xml

```bash
create '/var/log/tomcat' directory in working Drive
```
###### Correct the DB path in application.properties

```bash
sample DB path C:/Users/91894/Downloads/accountsdb.accdb
ex:
spring.datasource.url=jdbc:ucanaccess://C:/Users/91894/Downloads/accountsdb.accdb;openExclusinve=false;ignoreCase=true
```

##### Run springboot application

```bash
mvn spring-boot:run
```

##### Steps to check the result
```bash
After start springboot application
1.Enter 'http://localhost:8080/' in browser

2.login page will display enter user name and password
User1: Username: admin & Password: admin
User2: Username: user & Password: user

3. Successful login redirect to sample form

Fill the form and submit the check the response based on user

OR

Enter the below url in browser to check the response
Ex:
User1: Username: admin
http://localhost:8080/statements?accountId=1&fromDate=2020-01-01&toDate=2020-01-02&fromAmount=1&toAmount=2

User2: Username: user
http://localhost:8080/statements?accountId=1
http://localhost:8080/statements?accountId=1&fromDate=2020-01-01&toDate=2020-01-02&fromAmount=1&toAmount=2

```