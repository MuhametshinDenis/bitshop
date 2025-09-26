import { v4 as uuidv4 } from "uuid"

interface UserResponseDto {
  email: string
  firstName: string
  lastName: string | null
  avatarUrl: string | null
  deliveryAddress: string | null
}

interface RegisterDto {
  email: string
  password: string
  firstName: string
  lastName?: string
}

export async function POST(req: Request, res: Response) {
  const body: RegisterDto = await req.json()
  const { email, password, firstName } = body

  if (!email || !password) {
    return new Response(
      JSON.stringify({
        message: "Поле email, password, firstName является обязательными!",
      }),
      {
        status: 401,
        headers: { "content-type": "application/json" },
      }
    )
  }

  if (email === "example@example.com") {
    return new Response(
      JSON.stringify({ message: "Пользователь с таким email уже существует!" }),
      {
        status: 401,
        headers: { "content-type": "application/json" },
      }
    )
  }

  const sessionId = uuidv4()

  const newUser: UserResponseDto = {
    email,
    firstName,
    lastName: body.lastName || null,
    avatarUrl: null,
    deliveryAddress: null,
  }

  return new Response(JSON.stringify(newUser), {
    status: 201,
    headers: {
      "Content-Type": "application/json",
      "Set-Cookie": `SESSIONID=${sessionId}; Path=/; HttpOnly; SameSite=Lax; Max-Age=3600`,
    },
  })
}
