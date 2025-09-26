import { StatusCodes } from "http-status-codes"

export async function POST() {
  return new Response(
    JSON.stringify({ message: "Успешный выход из системы" }),
    {
      status: StatusCodes.OK,
      headers: {
        "Content-Type": "application/json",
        "Set-Cookie": `SESSIONID=; Path=/; HttpOnly; Max-Age=0; SameSite=Lax`,
      },
    }
  )
}
