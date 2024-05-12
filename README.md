### Base de datos
Para ejecutar el proyecto correctamente, primero debemos crear una base de datos en MySQL llamada inventorydb.

### Endpoints
### Crear products
### Método: POST
URL: http://localhost:9595/products
### Body: JSON
{
"name": "Producto 1",
"price": 6890
}
### Modificar un producto
### Método: PUT
URL:http://localhost:9595/products/{id}
### Body JSON:
{
"name": "Producto modificado",
"price": 65124
}

### Eliminar producto
### Método: DELETE
UrL: http://localhost:9595/products/{id}

### Consultar producto por id
### Método: GET
URL: http://localhost:9595/products/{id}

### Consultar todos los productos
### Método: GET
URL: http://localhost:9595/products


### Crear Pedido
### Método: POST
URL: http://localhost:9595/iasinventory
### Body: JSON

{
"name": "Pedido 3",
"state": "CONFIRMADO",
"products": [
{"product_id": 1},
{"product_id": 2},
{"product_id": 3}

    ]
}
### Modificar un pedido
### Método: PUT
URL:http://localhost:9595/iasinventory/{id}
### Body JSON:
{
"name": "Pedido 1",
"state": "CONFIRMADO",
"products": [
{"product_id": 1},
{"product_id": 2},
{"product_id": 3}

    ]
}


### Consultar todos los pedidos
### Método: GET
URL: http://localhost:9595/iasinventory

### Consultar pedidos por ID
### Método: GET
URL: http://localhost:9595/iasinventory/{id}


