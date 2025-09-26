import { apiInstance } from "@/api/api-instance"
import { User } from "@/types/user"

export const getMe = async () => {
  const response = await apiInstance.get<User>("/auth/me")
  return response.data
}

export const login = async (email: string, password: string) => {
  const response = await apiInstance.post<User>("/auth/login", {
    email,
    password,
  })
  return response.data
}
