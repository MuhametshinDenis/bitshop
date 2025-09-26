import { faker } from "@faker-js/faker/locale/ru"
import { StatusCodes } from "http-status-codes"
import { cookies } from "next/headers"
import { v4 as uuidv4 } from "uuid"

interface UserResponseDto {
  email: string
  firstName: string
  lastName: string | null
  avatarUrl: string | null
  deliveryAddress: string | null
}

interface LoginDto {
  email: string
  password: string
}

export async function POST(req: Request, res: Response) {
  const body: LoginDto = await req.json()
  const { email, password } = body

  if (!email || !password) {
    return new Response(
      JSON.stringify({
        message: "Поле email, password является обязательными!",
      }),
      {
        status: StatusCodes.BAD_REQUEST,
        headers: { "content-type": "application/json" },
      }
    )
  }

  if (email !== "example@example.com" || password !== "password") {
    return new Response(
      JSON.stringify({ message: "Ошибка введенных данных!" }),
      {
        status: StatusCodes.UNAUTHORIZED,
        headers: { "content-type": "application/json" },
      }
    )
  }

  const sessionId = uuidv4()

  const newUser: UserResponseDto = {
    email,
    firstName: faker.person.firstName(),
    lastName: faker.person.lastName(),
    avatarUrl: faker.image.avatarGitHub(),
    deliveryAddress: faker.location.streetAddress(),
  }

  return new Response(JSON.stringify(newUser), {
    status: StatusCodes.OK,
    headers: {
      "Content-Type": "application/json",
      "Set-Cookie": `SESSIONID=${sessionId}; Path=/; HttpOnly; SameSite=Lax; Max-Age=3600`,
    },
  })
}
