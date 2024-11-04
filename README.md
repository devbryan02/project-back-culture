# Documentacion de una Api RESTfull de la Cultura Ayacuchana

Esta API REST proporciona acceso a un conjunto de datos relacionados con la rica y diversa cultura de Ayacucho, Perú. Ayacucho es conocida por su patrimonio histórico, tradiciones ancestrales, festividades y una vibrante escena artística. Esta documentación está diseñada para ayudar a desarrolladores e investigadores a interactuar con la API de manera efectiva, permitiendo la integración de datos culturales en aplicaciones y proyectos.

## Endpoinst
### Solicitudes Response

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













### Fin