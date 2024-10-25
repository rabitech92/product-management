
## clone project :
```http
https://github.com/rabitech92/product-management.git
```
## API Reference
#### Get all Products which data are active status

```http
  GET http://localhost:1010/products/list
```
### pageation and sorting API
```http
GET http://localhost:1010/products
```
## create and paylod of product
```http 
POST http://localhost:1010/products
```
payload :{
   
  "name": " Lux soap",
  "description": "premium quility",
  "price": 100,
  "stockQuantity": 10,
  "category": "soap" 

}

## Update product
```http Update product
PUT http://localhost:1010/products/get/id
```
{
    
    "name": " Lux soap",
    "description": "premium quility",
    "price": 100,
    "stockQuantity": 10,
    "category": "soap",
    "createdAt": "2024-10-25T18:47:02.04254",
    "updatedAt": "2024-10-25T20:33:25.04376",
    "activeStatus": "ACTIVE"
}

## Delete product user see this is deleted but this data not delete it's called soft delete it will save in database
```http
DELETE http://localhost:1010/products/id
```
## http only for stockQuantity update
```http
PATCH http://localhost:1010/products/id
```
## Get by Product ID
```http
GET http://localhost:1010/products/id
```

