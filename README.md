🧑‍💻 User Service
The User Service is a critical component of the e-commerce ecosystem that handles user registration, authentication, and profile management. It ensures secure access and seamless integration with other microservices via the API Gateway.

📦 Core Functionalities
1. User Registration & Authentication
User Registration: Allows new users to sign up with essential information such as name, email, and password.

Authentication: Validates user credentials for secure login, using JWT tokens for session management.

2. User Profile Management
Users can update their personal details such as:

Name

Email

Password

Contact information

3. Authorization
Implements Role-Based Access Control (RBAC) to manage access to different system functionalities based on user roles (e.g., admin, customer).

⚙️ Non-Functional Requirements
1. Scalability
Built to handle a large number of user registrations and authentication requests efficiently.

2. Security
JWT-based authentication ensures secure access.

Implements password hashing and secure user credential storage.

3. Performance
Optimized to respond to login and registration requests within 200ms.

4. Maintainability & Extensibility
Modular architecture for easy feature extensions and system updates.

📌 Technologies Used
Java / Spring Boot

JWT (JSON Web Token)

MySQL (for storing user data)

Spring Security

RESTful APIs

🌍 Integration with Other Services
API Gateway: All requests to the User Service are routed through the API Gateway for centralized routing and security management.

🧩 Contributors
[Ridhim Singh Raizada]

