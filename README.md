# API RESTful Cultura Ayacuchana 🏺🌄

API que permite acceder y gestionar información sobre el patrimonio cultural, histórico y artístico de Ayacucho, Perú. Diseñada para facilitar el desarrollo de aplicaciones que promuevan y preserven las tradiciones, festividades y manifestaciones culturales ayacuchanas.

## Propósito 🎯
- Proporcionar acceso estructurado a datos culturales de Ayacucho
- Facilitar la integración de contenido cultural en aplicaciones
- Servir como recurso para desarrolladores e investigadores

## Convenciones de la API 🔧
- Todas las rutas comienzan con el prefijo `/api/v1`
- Las respuestas son en formato JSON
- Los IDs son parámetros de ruta usando la notación `/{id}`

## Controladores de API

### 1. AuthController
**Base URL:** `/api/v1/auth`

#### Registro de Usuario
**Endpoint:** `POST /register`

**Request Body:**
```json
{
    "email": "user@example.com",
    "username": "johndoe",
    "password": "mySecurePassword123",
    "fullName": "John Doe"
}
```

#### Inicio de Sesión
**Endpoint:** `POST /login`

**Request Body:**
```json
{
    "username": "jimm005",
    "password": "mySecurePassword123"
}
```

**Response Exitoso:**
```json
{
    "username": "usuarioEjemplo",
    "message": "Inicio de sesión exitoso",
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "success": true
}
```

### 2. PlaceController
**Base URL:** `/api/v1/place`

#### Obtener Lugares Populares
**Endpoint:** `GET /popular`

**Response Exitoso:**
```json
[
    {
        "placeId": 1,
        "name": "Nombre del lugar",
        "location": "Ubicación del lugar", 
        "urlImage": "https://example.com/image.jpg",
        "punctuationAverage": 4.5
    }
]
```

#### Detalles de un Lugar
**Endpoint:** `GET /{id}`

**Response Exitoso:**
```json
{
    "placeId": 1,
    "name": "Nombre del lugar",
    "location": "Ubicación del lugar",
    "descripcion": "Descripción detallada del lugar",
    "qualificationAverage": 4.5,
    "images": [
        "https://example.com/image1.jpg",
        "https://example.com/image2.jpg"
    ]
}
```

#### Búsqueda de Lugares
**Endpoint:** `GET /search`

**Parámetros:**
- `keyword`: Palabra clave de búsqueda

**Response Exitoso:**
```json
[
    {
        "placeId": 1,
        "name": "Nombre del lugar",
        "location": "Ubicación del lugar", 
        "urlImage": "https://example.com/image.jpg",
        "punctuationAverage": 4.5
    }
]
```

#### Lugares por Categoría
**Endpoint:** `GET filter/{category}`

**Response Exitoso:**
```json
[
    {
        "placeId": 1,
        "name": "Nombre del lugar",
        "location": "Ubicación del lugar", 
        "urlImage": "https://example.com/image.jpg",
        "punctuationAverage": 4.5,
        "category": "Categoría del lugar"
    }
]
```

#### Lugares por Categoría y Provincia
**Endpoint:** `GET filter/{category}/province/{province}`

**Parámetros:**
- `category`: Categoría del lugar
- `province`: Provincia donde se encuentra el lugar

**Response Exitoso:**
```json
[
    {
        "placeId": 1,
        "name": "Plaza Mayor",
        "location": "Centro histórico, Ayacucho",
        "province": "Ayacucho Centro",
        "urlImage": "http://example.com/image1.jpg",
        "punctuationAverage": 4.5
    },
    {
        "placeId": 2,
        "name": "Museo de Arte de Ayacucho",
        "location": "Avenida Los Andes, Ayacucho",
        "province": "Ayacucho Norte",
        "urlImage": "http://example.com/image2.jpg",
        "punctuationAverage": 4.7
    }
]
```

### 3. FavoriteController
**Base URL:** `/api/v1/favority`

#### Agregar Favorito
**Endpoint:** `POST /`

**Parámetros:**
- `username`: Nombre de usuario
- `placeId`: ID del lugar a agregar a favoritos

**Response Exitoso:**
```json
{
    "favoriteId": 1,
    "username": "johndoe",
    "placeId": 123,
    "message": "Lugar agregado a favoritos"
}
```

#### Obtener Favoritos de Usuario
**Endpoint:** `GET /user`

**Parámetros:**
- `username`: Nombre de usuario del cual se quieren obtener los favoritos

**Response Exitoso:**
```json
[
    {
        "favoriteId": 1,
        "placeId": 123,
        "placeName": "Nombre del lugar",
        "urlImage": "https://example.com/image.jpg"
    },
    {
        "favoriteId": 2,
        "placeId": 124,
        "placeName": "Otro lugar",
        "urlImage": "https://example.com/image2.jpg"
    }
]
```

## Consideraciones Importantes 🛡️

### Autenticación
- Hasta este momento de desarrollo ningún endpoint requiere autenticación
- En futuras versiones se implementará autenticación JWT
- El token JWT deberá incluirse en el header `Authorization: Bearer {token}`

### Manejo de Errores
- Códigos de estado HTTP estándar
- Mensajes de error descriptivos
- Validación de entrada de datos

### Errores Comunes 🚨
- **400 Bad Request:** Parámetros inválidos
- **401 Unauthorized:** Credenciales incorrectas
- **404 Not Found:** Recurso no encontrado
- **500 Internal Server Error:** Errores del servidor

### Buenas Prácticas
- Implementar caché para consultas frecuentes
- Usar paginación para grandes conjuntos de datos
- Mantener consistencia en formatos de respuesta

*Última actualización: Diciembre 2024*