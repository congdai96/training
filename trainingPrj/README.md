# Servlet Training Project

This project is designed to provide a hands-on introduction to Java Servlets. It includes basic examples demonstrating core Servlet concepts and lifecycle.

## Getting Started

To run this project, you'll need a Java Development Kit (JDK) and a Servlet container (like Apache Tomcat).

### Prerequisites

* **Java Development Kit (JDK):** Make sure you have a compatible JDK installed on your system. You can download it from Oracle or OpenJDK distributions.
* **Apache Tomcat:** Download and install Apache Tomcat from the official website ([https://tomcat.apache.org/](https://tomcat.apache.org/)). Tomcat is a popular open-source Servlet container.

### Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/ServletTraining.git](https://github.com/your-username/ServletTraining.git)
    cd ServletTraining
    ```

2.  **Build the project (if necessary):** This project might be simple enough that you don't need a full build process. However, if you add more complex features later, you might use Maven or Gradle. For now, you'll likely just place the compiled `.class` files in the Tomcat web application directory.

3.  **Deploy to Tomcat:**
    * Create a new directory (e.g., `servlet-examples`) inside Tomcat's `webapps` directory (`apache-tomcat-[version]/webapps/`).
    * Place the compiled Servlet `.class` files (likely in a package structure like `com/example/servlets/`) inside the `WEB-INF/classes` directory within your `servlet-examples` web application directory (`apache-tomcat-[version]/webapps/servlet-examples/WEB-INF/classes/com/example/servlets/`). You might need to create these directories.
    * Create a `WEB-INF/web.xml` deployment descriptor file within your `servlet-examples` web application directory (`apache-tomcat-[version]/webapps/servlet-examples/WEB-INF/web.xml`).

4.  **Start Tomcat:** Navigate to your Tomcat installation directory (`apache-tomcat-[version]/bin/`) and run the appropriate startup script (e.g., `startup.sh` on Linux/macOS or `startup.bat` on Windows).

## Usage

Once Tomcat is running, you can access the Servlet examples through your web browser using the following URLs (assuming your Tomcat is running on the default port 8080):

* **Hello Servlet:** `http://localhost:8080/servlet-examples/hello`
* **Request Information Servlet:** `http://localhost:8080/servlet-examples/requestInfo`
* **Parameter Servlet:** `http://localhost:8080/servlet-examples/parameter?name=YourName&age=30` (Try changing the `name` and `age` parameters)

## Contributing

Contributions are welcome! Please follow the guidelines in [CONTRIBUTING.md](CONTRIBUTING.md).

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE](LICENSE) file for details.

## Examples Included

This project currently includes the following basic Servlet examples:

* **HelloServlet:** A simple Servlet that displays a "Hello, Servlet!" message.
* **RequestInfoServlet:** Demonstrates how to access basic request information like the client's IP address, user agent, and request method.
* **ParameterServlet:** Shows how to retrieve request parameters sent via the URL.

## Next Steps (Potential Future Examples)

* Handling form submissions.
* Working with Servlet lifecycle methods (`init()`, `service()`, `destroy()`).
* Using `HttpServletRequest` and `HttpServletResponse` objects in more detail.
* Managing session data.
* Using Servlet contexts.
