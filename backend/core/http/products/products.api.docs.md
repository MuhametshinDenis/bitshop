# Products API

## Базовый URL
```
/products
```

## Получение списка продуктов
 
```http request
GET /products
```

### Query-параметры (фильтры)

* `categoryId` — ID категории
* `minPrice` — минимальная цена
* `maxPrice` — максимальная цена
* `inStock` — `true/false` (только в наличии)
* `page` — номер страницы (по умолчанию `0`)
* `size` — количество элементов на странице (по умолчанию `20`)
* `sort` — сортировка в формате `field,direction` (например, `price,asc`)

### Пример запроса

```http request
GET /products?categoryId=1&minPrice=100&maxPrice=1000&inStock=true&page=0&size=10&sort=price,asc
```

### Пример ответа

```json
[
  {
    "id": 10,
    "name": "iPhone 15",
    "description": "Latest Apple iPhone",
    "price": 1200.00,
    "quantity": 5,
    "imageUrl": "https://example.com/images/iphone15.jpg",
    "categoryId": 1,
    "categoryName": "Electronics"
  },
  {
    "id": 11,
    "name": "Samsung Galaxy S24",
    "description": "Flagship Samsung phone",
    "price": 999.00,
    "quantity": 12,
    "imageUrl": "https://example.com/images/s24.jpg",
    "categoryId": 1,
    "categoryName": "Electronics"
  }
]
```

---

## Получение продукта по ID

```http request
GET /products/{id}
```

### Пример ответа

```json
{
  "id": 10,
  "name": "iPhone 15",
  "description": "Latest Apple iPhone",
  "price": 1200.00,
  "quantity": 5,
  "imageUrl": "https://example.com/images/iphone15.jpg",
  "categoryId": 1,
  "categoryName": "Electronics"
}
```

---

## Создание продукта

```http request
POST /products
```

### Тело запроса

```json
{
  "name": "MacBook Pro 14",
  "description": "Apple laptop 2025",
  "price": 2500.00,
  "quantity": 3,
  "imageUrl": "https://example.com/images/macbook14.jpg",
  "categoryId": 1
}
```

### Пример ответа

```json
{
  "id": 12,
  "name": "MacBook Pro 14",
  "description": "Apple laptop 2025",
  "price": 2500.00,
  "quantity": 3,
  "imageUrl": "https://example.com/images/macbook14.jpg",
  "categoryId": 1,
  "categoryName": "Electronics"
}
```

---

## Обновление продукта

```http request
PUT /products/{id}
```

### Тело запроса

```json
{
  "name": "MacBook Pro 14 (M5)",
  "description": "Updated Apple laptop 2025",
  "price": 2600.00,
  "quantity": 5,
  "imageUrl": "https://example.com/images/macbook14m5.jpg",
  "categoryId": 1
}
```

### Пример ответа

```json
{
  "id": 12,
  "name": "MacBook Pro 14 (M5)",
  "description": "Updated Apple laptop 2025",
  "price": 2600.00,
  "quantity": 5,
  "imageUrl": "https://example.com/images/macbook14m5.jpg",
  "categoryId": 1,
  "categoryName": "Electronics"
}
```

---

## Удаление продукта

```http request
DELETE /products/{id}
```

### Пример ответа

```json
{
  "message": "Product deleted successfully"
}
```

---

## Статусы ответов

* `200 OK` — успешный запрос
* `201 Created` — успешно создано
* `400 Bad Request` — ошибка валидации
* `404 Not Found` — продукт не найден
* `500 Internal Server Error` — серверная ошибка
