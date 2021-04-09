[![Contributors][contributors-shield]][contributors-url]
[![Commit Activity][commit-activity]][commit-activity-url]
[![Issues][issues-shield]][issues-url]
[![Forks][forks-shield]][forks-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

# TravelAgencyService
Example of a service side of an application for a travel agency.

The application allows the user to search the database of travel arrangements of the travel agency. Search data includes travel type (winter, summer, trip, etc.), country, city, and search keyword.
Maven Java Web application was build for working with TravelAgencyDB, as well as expose the data through the REST API so that it can be accessed from Android client application.
Access to databases is performed via the JDBC API.
The operation of the service has been confirmed by the Postman client. 

<!-- DESIGN -->
## Design

### Database Scheme

![Database scheme][database-screenshot]

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

### Running the app

#### 1. Clone the repository

```
git clone https://github.com/ConusDaktis/TravelAgencyService.git
```

#### 1. Verify

Go to http://localhost:8080/TravelAgencyService to check that the app is up and running.


<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

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
[commit-activity]: https://img.shields.io/github/commit-activity/m/ConusDaktis/TravelAgencyService
[commit-activity-url]: https://github.com/ConusDaktis/TravelAgencyService/commits/main
[linkedin-shield]: https://img.shields.io/badge/LinkedIn-0077B5?style=plastice&logo=linkedin&logoColor=white
[linkedin-url]: https://linkedin.com/in/stokicdusan
[database-screenshot]: src/main/resources/database/database.PNG?raw=true
[localhost-url]: http://localhost:8080/TravelAgencyService
