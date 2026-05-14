# RESTI API CRUD with Angular & Spring Boot

This is a full-stack CRUD (Create, Read, Update, Delete) system project developed to manage records in a simple and efficient way. The application uses Angular for the user interface and Spring Boot for the REST API backend.

## 💻 Tecnologies

### Backend (`crud-spring`)
* **Java 21** (version 17 or higher)
* **Spring Boot 4** ( Spring 7, Spring Web, Spring Data JPA)
* **Database:** H2 Database (in memory for development and testing.) / MSSQL
* **Maven** (dependency manager)
* **JUnit 5 + Mockito** (back-end tests)

### Frontend (`crud-angular`)
* **Angular** (version 21)
* **TypeScript**
* **Angular Material** (for modern UI components)
* **SCSS** (advanced styling)
* **Karma + Jasmine** (front-end tests)

## ⌨️ Editor / IDE

* Visual Studio Code
* Java Extensions [link](https://marketplace.visualstudio.com/items?itemName=loiane.java-spring-extension-pack)
* Angular Extensions [link](https://marketplace.visualstudio.com/items?itemName=loiane.angular-extension-pack)

## Some functionalities available in the API

* ✅ Java model class with validation
* ✅ JPA repository
* ✅ JPA Pagination
* ✅ MSSQL database (you can use any database of your preference)
* ✅ Controller, Service, and Repository layers
* ✅ Has-Many relationships (Partners-Contacts)
* ✅ Java 21 Records as DTO (Data Transfer Object)
* ✅ Hibernate / Jakarta Validation
* ✅ Unit tests for all layers (repository, service, controller)
* ✅ Test coverage for tests
* ✅ Spring Docs - Swagger

## Some functionalities available in the front end
* ✅ Angular Standalone components (Angular v16+)
* ✅ Angular Material components
* ✅ List of all records with pagination
* ✅ Form to update/create records with contacts (has-many - FormArray)
* ✅ View only screen
* ✅ TypedForms (Angular v14+)
* ✅ Presentational x Smart Components
* 🚧 Unit and Integration tests for components, services, pipes, guards

## 🛠️ How to Run the Project Locally

### Prerequisites 
Before you begin, you need to have the following installed on your machine:
* **Java JDK 17** (or higher)
* **Maven**
* **Node.js** (version LTS)
* **Angular CLI** (`npm install -g @angular/cli`)
* **Git**

---

### 1. Setting up the Backend (Spring Boot)

1. Navigate to the backend folder:
   ```bash
   cd crud-spring
   ```
2. Install the dependencies and compile the project:
   ```bash
   ./mvnw clean install
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. The API will be available at: `http://localhost:8080`

---

### 2. Setting up the Frontend (Angular)

1. Navigate to the frontend folder:
   ```bash
   cd crud-angular
   ```
2. Install the Node dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   ng serve
   ```
   *Technical Note: Although Angular natively uses the `ng serve` command, the use of `npm start` is highly recommended in the ongoing development flow of this project for the following reasons:*
    * **Execution of custom scripts:** The `npm start` command retrieves the instruction defined within the `"scripts"` object in the `package.json` file.
    * **Automatic Parameter Injection:** If the project has additional configurations required to run (such as custom ports, proxy settings `proxy.config.json` to connect to the Spring backend, or environment variables), these will be automatically injected and interpreted by npm. This saves the developer from having to manually type extra parameters on the command line every time the system starts.
4. Open your browser and go to: `http://localhost:4200`

---

## 🗺️ Primary API Endpoints

The API exposes the following standard REST endpoints:

* `GET /api/parceiros` - List all records
* `GET /api/parceiros/{id}` - Search for records by ID
* `POST /api/parceiros` - Create a new record
* `PUT /api/parceiros/{id}` - Update an existing record
* `DELETE /api/parceiros/{id}` - Delete a record

*(Note: If your main entity is not "partners", replace the name above in the final file.)*

---

## ✒️ Author

* **Guilherme Neto** - :octocat: [GitHub](https://github.com/guilhermeNetogit)