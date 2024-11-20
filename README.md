# Documentacion de una Api RESTfull de la Cultura Ayacuchana

Esta API REST proporciona acceso a un conjunto de datos relacionados con la rica y diversa cultura de Ayacucho, Perú. Ayacucho es conocida por su patrimonio histórico, tradiciones ancestrales, festividades y una vibrante escena artística. Esta documentación está diseñada para ayudar a desarrolladores e investigadores a interactuar con la API de manera efectiva, permitiendo la integración de datos culturales en aplicaciones y proyectos.

## Endpoinst
### Solicitudes Request
#### AuthLoginRequest
AuthLoginRequest es una clase de registro Java que representa la estructura de datos para una solicitud de inicio de sesión de autenticación. Contiene los campos necesarios para verificar las credenciales de inicio de sesión de un usuario.
```json 
{
    "username": "jimm005",
    "password": "mySecurePassword123"
}
```
#### NewCategoryRequest
NewCategoryRequest es una clase de registro Java que representa la estructura de datos para crear una nueva categoría. Contiene los campos necesarios para definir el nombre y la descripción de una categoría.
```json 
{
    "categoryName": "Iglesias",
    "description": "Category Iglesias"
}
```
#### NewCommentRequest
NewCommentRequest es una clase de registro Java que representa la estructura de datos para crear un nuevo comentario. Contiene el campo necesario para el texto del comentario.
```json 
{
    "textComment": "This is a great product!"
}
```
#### NewFavoriteRequest
NewFavoriteRequest es una clase de registro Java que representa la estructura de datos para crear un nuevo lugar favorito para un usuario. Contiene los campos necesarios para identificar al usuario y el lugar que se añade como favorito.
```json 
{
    "userId": 123,
    "placeId": 456
}
```
#### NewPhotoRequest
NewPhotoRequest es una clase de registro Java que representa la estructura de datos para crear una nueva foto. Contiene los campos necesarios para la descripción de la foto.
```json 
{
    "description": "Beautiful sunset over the lake"
}
```
#### NewPlaceRequest
NewPlaceRequest is a Java record class that represents the data structure for creating a new place or venue. It contains the necessary fields to define the basic information about the new place.
```json 
{
    "name": "Central Park Restaurant",
    "description": "A cozy restaurant in the heart of the city",
    "location": "123 Main Street, New York, NY 10001",
    "distance": "5 miles",
    "categoryIds": [1, 3, 7]
}
```
#### NewQualificationRequest
NewQualificationRequest es una clase de registro Java que representa la estructura de datos para crear una nueva calificación de usuario (rating) para un lugar. Contiene los campos necesarios para identificar al usuario, el lugar y el valor de la calificación.
```json 
{
    "userId": 123,
    "placeId": 456,
    "punctuation": 4
}
```
#### NewUserRequest
NewUserRequest es una clase de registro Java que representa la estructura de datos para crear una nueva cuenta de usuario. Contiene los campos necesarios para dar de alta un nuevo usuario en el sistema.
```json 
{
    "email": "user@example.com",
    "username": "johndoe",
    "password": "mySecurePassword123",
    "fullName": "John Doe"
}
```
### Solicitudes Response
#### AuthLoginResponse
Esta registro representa la respuesta de una solicitud de autenticación en un sistema. Este objeto está estructurado como un registro en Java con propiedades anotadas para definir el orden en el que se serializan a JSON.
```json 
{
  "username": "usuarioEjemplo",
  "message": "Inicio de sesión exitoso",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "success": true
}
```
#### CategoryResponse
Este documento describe el objeto CategoryResponse, el cual representa la respuesta de una solicitud relacionada con una categoría en un sistema. Este objeto está definido como un registro en Java y contiene información clave sobre una categoría.
```json 
{
  "categoryId": 1,
  "categoryName": "Iglesias",
  "descripcion": "todas las iglesias registradas"
}
```
#### CommentResponse
Este documento describe el objeto CommentResponse, el cual representa la respuesta de una solicitud relacionada con un comentario en un sistema. Este objeto está definido como un registro en Java y contiene información clave sobre un comentario hecho por un usuario en un lugar específico.
```json 
{
  "commentId": 101,
  "userId": 202,
  "placeId": 303,
  "textComment": "Gran lugar para visitar y excelente servicio.",
  "commentDate": "2024-11-13"
}
```
#### FavoriteResponse
Este documento describe el objeto FavoriteResponse, el cual representa la respuesta de una solicitud relacionada con un lugar marcado como favorito por un usuario en un sistema. Este objeto está definido como un registro en Java y contiene información clave sobre la relación entre un usuario y su lugar favorito.
```json 
{
  "favoriteId": 456,
  "userId": 789,
  "placeId": 123,
  "savedDate": "2024-11-13"
}
```
#### PhotoResponse
Este documento describe el objeto PhotoResponse, el cual representa la respuesta de una solicitud relacionada con una foto asociada a un lugar en un sistema. Este objeto está definido como un registro en Java y contiene información clave sobre una foto y su relación con un lugar específico.
```json 
{
  "photoId": 987,
  "placeId": 654,
  "pathPhoto": "/images/places/654/photo987.jpg",
  "description": "Foto de la entrada principal",
  "uploadDate": "2024-11-13"
}
```
#### PlaceDetailsResponse
Este registro representa la respuesta para los detalles de un lugar. Contiene información completa sobre el lugar, incluyendo su nombre, ubicación, descripción, calificación promedio y una lista de imágenes.

```json
{
    "name": "City Park",
    "location": "Downtown Area",
    "descripcion": "Public park in the heart of the city",
    "qualificationAverage": 4.0,
    "images": [
	    "https://example.com/images/hotel-exterior.jpg",
            "https://example.com/images/pool-view.jpg",
            "https://example.com/images/suite-interior.jpg"
	]
}
```
#### PlacePopularResponse
Este registro representa la respuesta para un lugar popular. Contiene información básica sobre el lugar, incluyendo su nombre, ubicación y una URL de imagen.
```json
{
    "name": "Pampa de Ayacucho",
    "location": "123 Downtown Avenue, New York, NY 10001",
    "urlImage": "https://example.com/images/skyline-restaurant.jpg"
}
```
#### PlaceResponse
Este documento describe el objeto PlaceResponse, el cual representa la respuesta de una solicitud relacionada con un lugar en el sistema. Este objeto está definido como un registro en Java y contiene información clave sobre un lugar, como su nombre, ubicación, y puntuación promedio.
```json 
{
  "placeId": 321,
  "name": "Museo Nacional",
  "description": "Un museo que alberga una vasta colección de arte e historia.",
  "location": "Av. Central, Ciudad",
  "distance": "2 km",
  "punctuationAverage": 4.7
}
```
#### QualificationResponse
Este documento describe el objeto QualificationResponse, el cual representa la respuesta de una solicitud relacionada con la calificación de un lugar por un usuario en el sistema. Este objeto está definido como un registro en Java y contiene información clave sobre una calificación, incluyendo la puntuación asignada a un lugar específico por un usuario.
```json 
{
  "qualificationId": 567,
  "userId": 890,
  "placeId": 123,
  "punctuation": 4
}
```
#### UserResponse
Este documento describe el objeto UserResponse, el cual representa la respuesta de una solicitud relacionada con la información de un usuario en el sistema. Este objeto está definido como un registro en Java y contiene detalles básicos sobre un usuario, como su identificación, correo electrónico y nombre completo.
```json 
{
  "userId": 456,
  "email": "usuario@ejemplo.com",
  "fullName": "Juan Pérez"
}
```
# Documentación de API

## Tabla de Contenidos
- [AuthController](#authcontroller)
- [CategoryController](#categorycontroller)
- [ImageController](#imagecontroller)
- [PlaceController](#placecontroller)

## AuthController

Base URL: `/api/v1/auth`

### Endpoints

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/register` | Registra un nuevo usuario |
| POST | `/login` | Autentica a un usuario existente |

## CategoryController

Base URL: `/api/v1/category`

### Endpoints

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/` | Crea una nueva categoría |
| PATCH | `/{id}` | Actualiza una categoría existente |
| GET | `/{id}` | Obtiene los detalles de una categoría específica |
| GET | `/all` | Obtiene la lista de todas las categorías disponibles |
| DELETE | `/{id}` | Elimina una categoría específica |

## ImageController

Base URL: `/api/v1/image`

### Endpoints

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/` | Carga una nueva foto asociada a un lugar |

**Notas:**
- Content-Type requerido: `multipart/form-data`
- Acepta datos del lugar y el archivo de imagen

## PlaceController

Base URL: `/api/v1/place`

### Endpoints

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/` | Crea un nuevo lugar |

## Convenciones de la API

- Todas las rutas comienzan con el prefijo `/api/v1`
- Las respuestas son en formato JSON
- Los IDs son parámetros de ruta usando la notación `/{id}`
- Los métodos HTTP siguen las convenciones REST:
    - POST: Crear recursos
    - GET: Obtener recursos
    - PATCH: Actualizar recursos parcialmente
    - DELETE: Eliminar recursos





### Fin