import { faker } from "@faker-js/faker/locale/ru"

interface Product {
  id: number
  name: string
  description: string
  price: number
  quantity: number
  imageUrl: string
  category: string
}

const generateProducts = (count: number = 10): Product[] => {
  return Array.from({ length: count }, () => ({
    id: faker.number.int({ min: 1, max: 100_000 }),
    name: faker.commerce.productName(),
    description: faker.commerce.productDescription(),
    price: +faker.commerce.price(),
    quantity: faker.number.int({ min: 1, max: 100_000 }),
    imageUrl: faker.image.urlPicsumPhotos(),
    category: faker.word.words(),
  }))
}

export function GET(request: Request) {
  const products = generateProducts(20)

  return new Response(JSON.stringify(products), {
    status: 200,
    headers: { "Content-Type": "application/json" },
  })
}
