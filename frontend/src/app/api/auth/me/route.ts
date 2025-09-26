import { faker } from "@faker-js/faker/locale/ru"
import { cookies } from "next/headers"
import { StatusCodes } from "http-status-codes"

interface UserResponseDto {
  email: string
  firstName: string
  lastName: string | null
  avatarUrl: string | null
  deliveryAddress: string | null
}

export async function GET(request: Request) {
  const cookieStore = await cookies()
  const token = cookieStore.get("SESSIONID")?.value

  if (!token) {
    return new Response(
      JSON.stringify({
        message: "Пользователь не авторизирован!",
      }),
      {
        status: StatusCodes.UNAUTHORIZED,
      }
    )
  }

  const user: UserResponseDto = {
    email: faker.internet.email(),
    firstName: faker.person.firstName(),
    lastName: faker.person.lastName(),
    avatarUrl: faker.image.avatarGitHub(),
    deliveryAddress: faker.location.streetAddress(),
  }

  return new Response(JSON.stringify(user), {
    status: 200,
    headers: {
      "Content-Type": "application/json",
    },
  })
}
