# AuthGateway

## Overview
AuthGateway is a straightforward authentication system designed using the Spring Framework. This project aims to provide a basic yet effective way to manage user authentication.

## Prerequisites
Before you begin, ensure you have MySQL installed on your system as it is essential for the database operations of AuthGateway.

## Getting Started
To start using AuthGateway, follow these steps:

1. **Clone the Repository**
   First, clone the repository to your local machine:
```bash
git clone https://github.com/PeppinoTechTrends/AuthGateway.git
```
3. **Configuration**
   You need change the credentials of email-sender for the confirmation email, and the credentials of mysql in applications.properties.

2. **Database Setup**
   Set up your MySQL database according to the specifications provided in the project and create the table:
```bash

CREATE TABLE IF NOT EXISTS user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    fullname VARCHAR(100),
    city VARCHAR(50),
    state VARCHAR(50),
    zip VARCHAR(10),
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    email_confirmed BOOLEAN,
    confirmation_token VARCHAR(255),
    role varchar(100)
);

```

4. **Run the Application**
   Finally, run the application. If everything is set up correctly, AuthGateway will be operational and ready to handle authentication requests.

## Contributing
Contributions to AuthGateway are welcome! Please feel free to fork the repository, make your changes, and create a pull request to merge your enhancements or fixes.

