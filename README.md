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
