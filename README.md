List API you can see all data which data cary ActiveStatus.ACTIVE
GET:http://localhost:1010/products/list
All Data Sorting and pagination of this API
GET: http://localhost:1010/products

Create Product API and paload
POST http://localhost:1010/products    
{
    "id": 1,
    "name": "Sandalina soap",
    "description": "premium quality",
    "price": 1.00,
    "stockQuantity": 0,
    "category": "soap",
    "createdAt": "2024-10-25T17:39:21.937323",
    "updatedAt": "2024-10-25T17:39:21.937323",
    "activeStatus": "ACTIVE"
}
Update Product API
PUT http://localhost:1010/products/get/{{id}}
