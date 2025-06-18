# Course Management - Technical Assessment

This project is a technical assessment demonstrating a complete CI/CD pipeline for a Java Spring Boot application, deploying to Kubernetes.

---

## 1. Task Overview

The assessment involves the following tasks:
1.  Create a `Dockerfile` for a Java Spring Boot image.
2.  Design an ideal end-to-end CI/CD flow for both backend and mobile.
3.  Create a `Jenkinsfile` based on the designed flow.
4.  Create Kubernetes manifest files (`deployment.yaml`, `service.yaml`, `ingress.yaml`).

---

## 2. CI/CD Flow Design

This section details the ideal, end-to-end CI/CD flow as required by the assessment.

### Backend API Pipeline (Spring Boot App)

This is the automated workflow for the `coursemanagement` Java application.

*   **Trigger:** A developer pushes code to the `main` branch on GitHub.
*   **1. Code Scan (Quality Gate):**
    *   **Security Scan:** Use tools like Snyk or Trivy to find known vulnerabilities.
    *   **Code Quality Scan:** Use SonarQube to check for bugs and code smells.
    *   *Pipeline fails if critical issues are found.*
*   **2. Build & Unit Test:**
    *   Run `mvn clean package` to compile the code and execute all unit tests.
    *   *Pipeline fails if any test fails.*
*   **3. Build & Publish Docker Image:**
    *   Build a new Docker image using the `Dockerfile` in this repository.
    *   Push the new image to a container registry (e.g., Docker Hub).
*   **4. Deploy to Staging:**
    *   Deploy the new image to a staging Kubernetes cluster for final testing.
    *   Run automated integration and end-to-end tests against this environment.
*   **5. Manual Approval for Production:**
    *   The pipeline pauses and waits for a manual approval from a team lead. This is a crucial safety gate.
*   **6. Deploy to Production:**
    *   Upon approval, perform a zero-downtime rolling update on the production Kubernetes cluster.
*   **7. Notify:**
    *   Send a success notification to a team chat (e.g., Slack or Teams).

### Mobile App Pipeline (Conceptual Design)

This is the conceptual workflow for a hypothetical mobile application.

*   **Trigger:** A developer pushes code to the mobile app's repository.
*   **1. Build & Test:** The CI server compiles the Android/iOS app and runs its unit tests.
*   **2. Code Signing:** The app binary (`.apk` or `.ipa`) is signed with a secure, stored key.
*   **3. Distribute to QA:** The signed app is uploaded to a test distribution platform (e.g., Firebase App Distribution), and the QA team is notified.
*   **4. Manual Approval:** After QA testing is complete, a manager approves the build for public release.
*   **5. Publish to App Store:** The pipeline automatically uploads the final app to the Google Play Store or Apple App Store for review.

---