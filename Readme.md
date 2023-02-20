# Movies Spring Boot

## Crear JAR del pryecto

Cada vez que exista un cambio en el proyecto hay que crear un nuevo jar del proyecto:

```bash
./mvnw install
```

## Crear imagen

Cada vez que exista un cambio en el proyecto hay que crear un nueva imagen con:

```bash
sudo docker build -t unir/movies-back .
```

## Crear contenedor

```bash
sudo docker run -d --name movies-back__container_1 -p 8181:8181 unir/movies-back 
```