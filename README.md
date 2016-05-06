# _Database basics independent project: Hair Salon_

#### _A program that lets salon owner to add a list of the stylists, and for each stylist, add clients who see that stylist.  05/06/2016_

#### By _**Nadiya Yeroshkina**_

## Description

This program that lets salon owner add a list of the stylists, and for each stylist, add clients who see that stylist.. The app list out all of the stylists, and clients.Let you click on them to view all of them.

## Setup/Installation Requirements

* Be sure to have all technologies used installed.
* Clone this repository
* Run a server in a terminal, using
`postgres`
* In a separate terminal, run:
`psql`
* In PSQL, run these commands:

```
CREATE DATABASE hair_salon;
CREATE TABLE stylists (id serial PRIMARY KEY, name varchar);
CREATE TABLE clients (id serial PRIMARY KEY, name varchar, stylist_id int);
CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;
```

* Download this repository
* In a separate terminal, navigate to your project folder then run:

`psql hair_salon < hair_salon.sql`

* And now, in the same terminal in which you are viewing your project folder, run the command:

`gradle run`



## Support and contact details

_Questions or comments can be directed to the developer_

_HTML, CSS, Bootstrap, Java, Gradle, Velocity, JUnit Testing, Spark, FluentLenium,  Postgres and Sql2o._

### License

**This project is licensed under the [MIT license](https://opensource.org/licenses/MIT).**

Copyright (c) 2016 Yeroshkina N.
