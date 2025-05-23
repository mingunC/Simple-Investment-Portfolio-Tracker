# Simple Investment Portfolio Tracker

This is a full-stack application for tracking a simplified investment portfolio. It's built with **Spring Boot** for the backend and **Angular** for the frontend, demonstrating core full-stack development capabilities, API integration, and fundamental financial data handling.

This project is designed to showcase skills relevant to a Software Developer role in the financial/investment industry, as outlined in job descriptions from companies like Fidelity.

---

## Features

### Core MVP Features
* **Asset Management:** Users can add, view, edit, and delete various "virtual" investment assets (e.g., Stocks, Bonds, Mutual Funds) in their portfolio.
* **Portfolio Summary:** Displays the overall value of the user's portfolio.
* **Performance Overview:** Calculates and displays the total profit or loss for the portfolio based on purchase vs. current prices.
* **Asset Allocation:** Shows the percentage allocation of different asset types (e.g., Stock, Bond, Mutual Fund) within the portfolio.
* **Dummy Asset Data:** The application uses an in-memory database with pre-populated dummy data for demonstration purposes.

---

## Technologies Used

### Backend (Spring Boot)
* **Java 17+:** Programming Language
* **Spring Boot 3.3.12 (or higher):** Framework for building robust, stand-alone RESTful APIs.
* **Gradle:** Build automation tool.
* **Spring Data JPA:** For simplified database interaction and Object-Relational Mapping.
* **H2 Database:** An in-memory, lightweight SQL database used for development and testing. Data is reset on application restart.
* **RESTful APIs:** For communication with the frontend, providing CRUD operations and portfolio metrics.

### Frontend (Angular)
* **Angular 17+:** JavaScript framework for building dynamic user interfaces.
* **TypeScript:** Superset of JavaScript that compiles to plain JavaScript, providing type safety.
* **HTML/CSS:** For structuring and styling the web pages.
* **Angular CLI:** Command Line Interface for Angular projects.
* **Node.js 20+ & npm:** JavaScript runtime and package manager.
* **HttpClientModule:** For making API calls to the backend.
* **FormsModule:** For two-way data binding in forms.

---

## Setup and Running the Project

### Prerequisites
Before you start, ensure you have the following installed on your macOS system:
* **Java Development Kit (JDK) 17 or higher**
* **Gradle**
* **Node.js 20 or higher** (managed via `nvm` is recommended)
* **npm** (comes with Node.js)
* **Angular CLI:** Install globally using `npm install -g @angular/cli`.
* **A suitable IDE:** IntelliJ IDEA (for backend) and VS Code (for frontend) are recommended.

### Getting Started

1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/mingunC/Simple-Investment-Portfolio-Tracker
    cd Simple-Investment-Portfolio-Tracker
    ```

2.  **Backend Setup & Run:**
    Open your terminal and navigate to the `backend` directory:
    ```bash
    cd backend
    ```
    Run the Spring Boot application:
    ```bash
    ./gradlew bootRun
    ```
    The backend server will start on `http://localhost:8080`.
    You can access the H2 database console at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:portfolio`, User Name: `sa`, Password: `<empty>`).

3.  **Frontend Setup & Run:**
    Open a **new terminal window** (keep the backend terminal running).
    Navigate to the `frontend` directory:
    ```bash
    cd ../frontend
    ```
    Install the Node.js dependencies (first time only, or if `node_modules` is deleted):
    ```bash
    npm install
    ```
    Run the Angular development server:
    ```bash
    ng serve --open
    ```
    The frontend application will typically open in your browser at `http://localhost:4200`.

---

## Project Structure
Simple-Investment-Portfolio-Tracker/
├── backend/                  # Spring Boot Backend Project
│   ├── build.gradle          # Gradle build file
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/cmgg919/tracker/
│   │   │   │   ├── controller/ # REST API endpoints
│   │   │   │   ├── model/      # JPA Entities (e.g., Asset.java)
│   │   │   │   ├── repository/ # Spring Data JPA Repositories
│   │   │   │   └── service/    # Business logic
│   │   │   └── resources/      # application.properties, data.sql
│   └── ...
├── frontend/                 # Angular Frontend Project
│   ├── angular.json          # Angular CLI configuration
│   ├── package.json          # Node.js dependencies
│   ├── src/
│   │   ├── app/
│   │   │   ├── app.component.ts      # Root component
│   │   │   ├── app.module.ts         # Main NgModule
│   │   │   ├── app-routing.module.ts # Routing configuration
│   │   │   ├── components/           # UI components (e.g., portfolio-list, asset-form)
│   │   │   ├── models/               # TypeScript interfaces for data (e.g., asset.model.ts)
│   │   │   └── services/             # API communication services (e.g., asset.service.ts)
│   │   └── ...
│   └── ...
└── README.md 

## Troubleshooting Tips

* **`zsh: command not found: <command>`:**
    * For `gradlew`, ensure you are in the `backend` directory and `gradlew` script exists (`ls -l`). If not, run `gradle wrapper`.
    * For `node`, `npm`, `ng`, ensure your `nvm` setup is correct in `~/.zshrc` (`source ~/.zshrc` or restart terminal) and that `nvm use <version>` has activated a Node.js version. Reinstall global tools if needed (`npm install -g @angular/cli`).
* **Compilation Errors in Spring Boot (`./gradlew bootRun`):**
    * **`package ... does not exist`:** Check `backend/build.gradle` for missing dependencies (e.g., `spring-boot-starter-data-jpa`, `h2`).
    * **`Table "ASSET" not found`:** Ensure `backend/src/main/resources/data.sql` starts with the `CREATE TABLE` statement for `asset`.
    * **`Unrecognized 'hibernate.hbm2ddl.auto' setting`:** Remove comments from the same line as properties in `backend/src/main/resources/application.properties`.
* **Compilation Errors in Angular (`ng serve --open`):**
    * **`Component is standalone, and cannot be declared in an NgModule`:** This is the most common issue. Ensure you ran `ng new frontend --no-standalone --skip-ssr` and meticulously replaced *all* generated files inside `frontend/src/app/` with the code provided in the project. Double-check that `standalone: true` is *not* present in any of your component's `@Component` decorators.
    * **`Cannot find module ...` (TS2307):** Check the exact relative import paths. Common mistakes: `.././` instead of `../` or `../.././` instead of `../../`.
    * **`No suitable injection token for parameter '...'`:** Often a consequence of `Cannot find module` for a service or a component not being correctly declared in `app.module.ts`.
    * **`Could not find stylesheet file ...`:** Ensure the `.css` file exists at the specified path (`styleUrls: ['./my-component.css']`) or remove/comment out the `styleUrls` property if no CSS file is intended.
* **Whitelabel Error Page (404) at `http://localhost:8080/`:** Normal. Your backend is running. The API endpoints are at `/api/assets`.
* **Empty Angular Page (only nav links) at `http://localhost:4200/`:**
    * **Check browser console (F12 > Console tab) for JavaScript errors.**
    * **Check browser network tab (F12 > Network tab) for API calls to `http://localhost:8080/api/assets`.** Look for `Status` (e.g., `200`, `404`, `500`, `pending`) and especially **CORS errors**. Ensure backend is running.

---
