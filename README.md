# full_test

A test project</br>
web server</br></br>

<h1>Postgres Setup Instructions</h1>
Install Postgres 9.3</br>
From Postgres:</br>
CREATE DATABASE full_test_db;</br>
CREATE DATABASE full_test_db_units;</br>
CREATE USER fulltestuser WITH PASSWORD 'fulltestpassword';
GRANT ALL PRIVILEGES ON DATABASE full_test_db TO fulltestuser;
GRANT ALL PRIVILEGES ON DATABASE full_test_db_units_ TO fulltestuser;
