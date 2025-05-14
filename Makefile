.PHONY: help up down build test clean logs

help:
	@echo "Available commands:"
	@echo "  make up      - Start the application and DB using Docker Compose"
	@echo "  make down    - Stop and remove Docker containers"
	@echo "  make build   - Build the Spring Boot project using Maven"
	@echo "  make test    - Run unit tests"
	@echo "  make clean   - Clean the Maven project"
	@echo "  make logs    - Show Docker container logs"

up:
	docker-compose up -d

down:
	docker-compose down

test:
	mvn test

clean:
	mvn clean

