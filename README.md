<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Exchange Rate API</title>
  </head>
  <body>
    <h1>Exchange Rate API</h1>
    <p>This project implements an API that retrieves exchange rate data from various providers and stores it in a local database. It provides two methods for retrieving exchange rate data:</p>
<ol>
  <li><code>/currency/average</code> - Retrieves a list of exchange rates for all sources, including the average market rate.</li>
  <li><code>/currency/average_by_date</code> - Retrieves a list of average exchange rates for all sources for a specified period.</li>
</ol>

<h2>API Providers</h2>

<p>This project supports the following API providers:</p>

<ul>
  <li><a href="https://api.monobank.ua/docs/">MonoBank API</a></li>
  <li><a href="https://minfin.com.ua/ua/developers/api/">Minfin API</a></li>
  <li><a href="https://api.privatbank.ua/#p24/exchange">PrivatBank API</a></li>
</ul>

<p>For each of these providers, a common interface has been implemented, with separate implementations for each provider.</p>

<h2>Data Synchronization</h2>

<p>The API periodically synchronizes data with the API providers using a cron job. This ensures that the local database always has the most up-to-date exchange rate data.</p>

<h2>Technologies Used</h2>

<p>This project was developed using the following technologies:</p>

<ul>
  <li>Java/Kotlin</li>
  <li>Tomcat</li>
  <li>Spring Boot</li>
  <li>Hibernate</li>
  <li>PostgreSQL</li>
  <li>Git</li>
  <li>Swagger</li>
  <li>Gradle</li>
</ul>

<h2>Running the Project</h2>

<p>To run the project, you will need to perform the following steps:</p>

<ol>
  <li>Clone the project repository: <code>git clone https://github.com/Dastya/currency_management_API.git</code></li>
  <li>Set up a PostgreSQL database and create the necessary tables
  <li>Update the database configuration in <code>src/main/resources/application.properties</code> to point to your database.</li>
  <li>Build the project using Gradle: <code>./gradlew build</code></li>
  <li>Start the API using the command <code>java -jar build/libs/currency_exchange_daemon.jar</code></li>
  <li>Navigate to <code>http://localhost:8080/swagger-ui.html</code> to access the API documentation using Swagger.</li>
</ol>

<h2>API Documentation</h2>

<p>The API is documented using Swagger. To view the documentation, navigate to <code>http://localhost:8080/swagger-ui.html</code> after starting the API.</p>

</body>
</html>
