# API RESTful Cultura Ayacuchana

API que permite acceder y gestionar información sobre el patrimonio cultural, histórico y artístico de Ayacucho, Perú. Diseñada para facilitar el desarrollo de aplicaciones que promuevan y preserven las tradiciones, festividades y manifestaciones culturales ayacuchanas.

## Propósito
- Proporcionar acceso estructurado a datos culturales de Ayacucho
- Facilitar la integración de contenido cultural en aplicaciones
- Servir como recurso para desarrolladores e investigadores

# Documentación de API

## Tabla de Contenidos
- [AuthController](#authcontroller)
    - [Register](#register)
    - [Login](#login)
- [CategoryController](#categorycontroller)
- [ImageController](#imagecontroller)
- [PlaceController](#placecontroller)

## AuthController

Base URL: `/api/v1/auth`

### Endpoints

#### Register
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

#### Login
**Endpoint:** `POST /login`

**Descripción:** Autentica a un usuario existente.

**Request Body:**
```json
{
    "username": "jimm005",
    "password": "mySecurePassword123"
}
```

**Response:**
```json
{
  "username": "usuarioEjemplo",
  "message": "Inicio de sesión exitoso",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "success": true
}
```

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

