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
- Los métodos HTTP siguen las convenciones REST:
  - POST: Crear recursos
  - GET: Obtener recursos
  - PATCH: Actualizar recursos parcialmente
  - DELETE: Eliminar recursos

## Controladores de API

### 1. AuthController
**Base URL:** `/api/v1/auth`

#### Registro de Usuario
**Endpoint:** `POST /register`

**Descripción:** Registra un nuevo usuario en el sistema.

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

**Descripción:** Autentica a un usuario existente.

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

**Descripción:** Recupera los lugares más populares, ordenados por puntuación.

**Respuesta Exitosa:**
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

**Descripción:** Obtiene los detalles completos de un lugar específico.

**Respuesta Exitosa:**
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

**Descripción:** Busca lugares por palabra clave.

**Parámetros:**
- `keyword`: Palabra clave de búsqueda

**Respuesta Exitosa:**
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
**Endpoint:** `GET /{category}`

**Descripción:** Recupera lugares filtrados por categoría.

**Respuesta Exitosa:**
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

## Consideraciones Importantes 🛡️

### Autenticación
- Hasta este momento de desarrollo ningun endpoint requiere autenticacion(mas adelante si)
- Usar token JWT proporcionado en el login
- Incluir token en header `Authorization: Bearer {token}`

### Manejo de Errores
- Códigos de estado HTTP estándar
- Mensajes de error descriptivos
- Validación de entrada de datos

### Buenas Prácticas
- Implementar caché para consultas frecuentes
- Usar paginación para grandes conjuntos de datos
- Mantener consistencia en formatos de respuesta

## Ejemplos de Uso 🚀

### Obtener Lugares Populares
```bash
GET /api/v1/place/popular
```

### Buscar Lugar por ID
```bash
GET /api/v1/place/1
```

### Buscar Lugares
```bash
GET /api/v1/place/search?keyword=ayacucho
```

## Errores Comunes 🚨
- **400 Bad Request:** Parámetros inválidos
- **401 Unauthorized:** Credenciales incorrectas
- **404 Not Found:** Recurso no encontrado
- **500 Internal Server Error:** Errores del servidor

*Última actualización: Diciembre 2024*