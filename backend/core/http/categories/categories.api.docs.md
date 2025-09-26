# Categories API

## Базовый URL

```
/categories
```

---

## Получение списка категорий

```http request
GET /categories
````

### Пример ответа

```json
[
  {
    "id": 1,
    "name": "Electronics"
  },
  {
    "id": 2,
    "name": "Clothing"
  }
]
```

---

## Получение категории по ID

```http request
GET /categories/{id}
```

### Пример ответа

```json
{
  "id": 1,
  "name": "Electronics"
}
```

---

## Создание категории

```http request
POST /categories
```

### Тело запроса

```json
{
  "name": "Accessories"
}
```

### Пример ответа

```json
{
  "id": 3,
  "name": "Accessories"
}
```

---

## Обновление категории

```http request
PUT /categories/{id}
```

### Тело запроса

```json
{
  "name": "Consumer Electronics"
}
```

### Пример ответа

```json
{
  "id": 1,
  "name": "Consumer Electronics"
}
```

---

## Удаление категории

```http request
DELETE /categories/{id}
```

### Пример ответа

```json
{
  "message": "Category deleted successfully"
}
```

---

## Статусы ответов

* `200 OK` — успешный запрос
* `201 Created` — успешно создано
* `400 Bad Request` — ошибка валидации
* `404 Not Found` — категория не найдена
* `500 Internal Server Error` — серверная ошибка

