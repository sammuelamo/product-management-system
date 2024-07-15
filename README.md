# Spring Boot MongoDB Product Manager

## Overview

This project is a Product Manager application built using Spring Boot and MongoDB. It manages products and categories with CRUD operations.

## Table of Contents

- [Controllers](#controllers)
    - [CategoryController](#categorycontroller)
    - [ProductController](#productcontroller)
- [Entities](#entities)
    - [Category](#category)
    - [Product](#product)
- [Services](#services)
    - [CategoryService](#categoryservice)
    - [ProductService](#productservice)
- [Repositories](#repositories)
- [Exception Handling](#exception-handling)
- [Dependencies](#dependencies)

## Controllers

### CategoryController

- **Endpoints:**
    - `POST /category/add`: Add a new category.
    - `GET /category/all`: Get all categories.
    - `GET /category/{id}`: Get a category by ID.
    - `PUT /category/update/{id}`: Update a category by ID.
    - `DELETE /category/delete/{id}`: Delete a category by ID.

### ProductController

- **Endpoints:**
    - `GET /products`: Get paginated list of products with sorting and searching.
    - `GET /products/add`: Add a new product form.
    - `POST /products/add`: Add a new product.
    - `GET /products/{id}`: Get a product by ID.
    - `GET /products/update/{id}`: Update a product form.
    - `POST /products/update/{id}`: Update a product by ID.
    - `GET /products/delete/{id}`: Delete confirmation for a product.
    - `DELETE /products/delete/{id}`: Delete a product by ID.

## Entities

### Category

- Represents a product category.
- Fields: `id`, `name`.

### Product

- Represents a product.
- Fields: `id`, `name`, `price`, `quantity`, `category`.

## Services

### CategoryService

- Provides CRUD operations for categories.
- Handles exceptions for category operations.

### ProductService

- Provides CRUD operations for products.
- Handles exceptions for product operations.
- Manages associations with categories.

## Repositories

- `CategoryRepository`: Interface for category data access.
- `ProductRepository`: Interface for product data access.

## Exception Handling

- Custom exceptions (`CategoryAlreadyExistsException`, `CategoryNotFoundException`, `ProductAlreadyExistsException`, `ProductNotFoundException`) handle specific error cases.

## Dependencies

- **Spring Boot**: Framework for building web applications.
- **Spring Data MongoDB**: Integration for MongoDB database.
- **Thymeleaf**: Template engine for server-side Java applications.

## Usage

1. Ensure MongoDB is installed and running.
2. Clone the repository.
3. Configure MongoDB connection properties in `application.properties`.
4. Build and run the application using Maven or your IDE.

