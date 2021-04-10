[![Contributors][contributors-shield]][contributors-url]
[![Commit-activity][commit-activity-shield]][commit-activity-url]
[![Issues][issues-shield]][issues-url]
[![Repo-size][repo-size-shield]][repo-size-url]
[![Forks][forks-shield]][forks-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

# TravelAgencyService
Example of a service side of an application for a travel agency.

<!-- ABOUT THE PROJECT -->
## About The Project

The application allows the user to search the database of travel arrangements of the travel agency. Search data includes travel type (winter, summer, trip, etc.), country, city, and search keyword.
Maven Java Web application was build for working with TravelAgencyDB, as well as expose the data through the REST API so that it can be accessed from Android client application.
Access to databases is performed via the JDBC API.

The operation of the service has been confirmed by the Postman client. 

### Built With

This are the major frameworks that are used to built this project.
* [MySQL Workbench](https://dev.mysql.com/downloads/workbench/apa)
* [Apache NetBeans](https://netbeans.apache.org/)
* [Wamp Server](https://www.wampserver.com/en/)

<!-- DESIGN -->
## Design

### Database Scheme

![Database scheme][database-screenshot]

<!-- GETTING STARTED -->
## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

#### 1. Download and install Apache NetBeans and Wamp Server

#### 2. Run Wamp server on your local machine

#### 3. In Apache NetBeans IDE add a GlassFish server instance on localhost on HTTP port 8080

### Running the app

#### 1. Clone the repository

```
git clone https://github.com/ConusDaktis/TravelAgencyService.git
```

#### 2. Launch the server and import the test database from 
```
src\main\resources\database
```
#### 3. Open Apache NetBeans IDE and import the project as a Maven Java Web Application

#### 4. Go to com.etf.zadatak2.dao\ResourcesManager.java and edit the JDBC_CONNECTION_STRING
```
JDBC_CONNECTION_STRING = "jdbc:mysql://localhost:<PORT_USED_BY_MYSQL>/<NAME_OF_DB>?user=root&password=";
<PORT_USED_BY_MYSQL>: should be checked on the server (usually 3306 or 3308)
<NAME_OF_DB>: should be travelagencydb1 if database imported as is. 
```
#### 5. Launch
1. Start the GlassFish server
2. Build the project with dependencies
3. Run project

#### 6. Verify

Go to http://localhost:8080/TravelAgencyService to check that the app is up and running.

In the postman directory are collections of tests for all functionalities provided by the service.

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be, learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- MARKDOWN LINKS & IMAGES -->
[contributors-shield]: https://img.shields.io/github/contributors/ConusDaktis/TravelAgencyService
[contributors-url]: https://github.com/ConusDaktis/TravelAgencyService/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/ConusDaktis/TravelAgencyService?style=social
[forks-url]: https://github.com/ConusDaktis/TravelAgencyService/network/members
[issues-shield]: https://img.shields.io/github/issues/ConusDaktis/TravelAgencyService
[issues-url]: https://github.com/ConusDaktis/TravelAgencyService/issues
[commit-activity-shield]: https://img.shields.io/github/last-commit/ConusDaktis/TravelAgencyService
[commit-activity-url]: https://github.com/ConusDaktis/TravelAgencyService/graphs/commit-activity
[repo-size-shield]: https://img.shields.io/github/repo-size/ConusDaktis/TravelAgencyService
[repo-size-url]: https://img.shields.io/github/repo-size/ConusDaktis/TravelAgencyService
[linkedin-shield]: https://img.shields.io/badge/LinkedIn-0077B5?style=plastice&logo=linkedin&logoColor=white
[linkedin-url]: https://linkedin.com/in/stokicdusan
[database-screenshot]: src/main/resources/database/database.png
[localhost-url]: http://localhost:8080/TravelAgencyService

