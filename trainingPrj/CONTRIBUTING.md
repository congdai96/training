# Contributing to the Servlet Training Project

We welcome contributions that help improve the learning experience for those new to Java Servlets.

## How to Contribute

1.  **Fork the repository** on GitHub.
2.  **Clone the forked repository** to your local machine.
    ```bash
    git clone [https://github.com/Your-GitHub-Username/ServletTraining.git](https://github.com/Your-GitHub-Username/ServletTraining.git)
    cd ServletTraining
    ```
3.  **Create a new branch** for your contribution. Choose a descriptive name:
    * For new examples: `feature/new-servlet-example`
    * For bug fixes: `bugfix/fix-request-info`
    * For documentation improvements: `docs/improve-readme`

    ```bash
    git checkout -b feature/your-contribution-name
    ```

4.  **Make your changes.** This might involve:
    * Creating new Servlet classes (under the appropriate package, e.g., `com.example.servlets`).
    * Updating the `web.xml` file to map the new Servlets to URLs.
    * Adding or modifying the `README.md` to document the new examples.

5.  **Follow coding conventions:** Please try to adhere to standard Java coding conventions.

6.  **Write clear commit messages:** Each commit should have a concise and informative message explaining the changes.

7.  **Test your changes:** Ensure that your new Servlets or modifications function correctly within a Servlet container like Tomcat.

8.  **Push your changes** to your forked repository on GitHub.
    ```bash
    git push origin feature/your-contribution-name
    ```

9.  **Create a Pull Request** through the GitHub website. Navigate to your forked repository and click the "Compare & pull request" button. Provide a clear title and description of your contribution.

## Types of Contributions We Welcome

* **New Servlet Examples:** Demonstrating different Servlet features and concepts (e.g., form handling, session management, cookies).
* **Bug Fixes:** Identifying and fixing errors in the existing examples or documentation.
* **Documentation Improvements:** Making the `README.md` or other documentation clearer, more comprehensive, or easier to follow.
* **Enhancements:** Suggesting and implementing improvements to the project structure or existing examples.

## License

By contributing to the Servlet Training Project, you agree that your contributions will be licensed under the Apache 2.0 License as specified in the [LICENSE](LICENSE) file.

## Questions?

If you have any questions about contributing, feel free to open an issue on GitHub.
