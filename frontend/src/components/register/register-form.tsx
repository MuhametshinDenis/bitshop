"use client"

import { useState } from "react"
import { useForm } from "react-hook-form"
import { z } from "zod"
import { zodResolver } from "@hookform/resolvers/zod"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import {
  Form,
  FormField,
  FormItem,
  FormLabel,
  FormControl,
  FormMessage,
} from "@/components/ui/form"
import Link from "next/link"
import { ROUTES } from "@/constants/routes"

const credentialsSchema = z.object({
  email: z.string().email({ message: "Обязательно должно быть почтой." }),
  password: z
    .string()
    .min(2, { message: "Пароль должен состоять минимум из 2 символов." }),
})

const profileSchema = z.object({
  firstName: z.string().min(1, { message: "Имя является обязательным полем." }),
  lastName: z.string().optional(),
})

const formSchema = credentialsSchema.merge(profileSchema)

type FormValues = z.infer<typeof formSchema>

//TODO: Подумать над компонентом
export function RegisterForm() {
  const [step, setStep] = useState<"credentials" | "profile">("credentials")

  const form = useForm<FormValues>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      email: "",
      password: "",
      firstName: "",
      lastName: "",
    },
  })

  const onSubmit = (values: FormValues) => {
    if (step === "credentials") {
      setStep("profile")
    } else {
      console.log("Отправка формы:", values)
    }
  }

  const currentFields =
    step === "credentials"
      ? [
          {
            name: "email",
            label: "Почта",
            placeholder: "m@example.com",
            type: "text",
          },
          {
            name: "password",
            label: "Пароль",
            placeholder: "Пароль",
            type: "password",
          },
        ]
      : [
          {
            name: "firstName",
            label: "Имя",
            placeholder: "Иван",
            type: "text",
          },
          {
            name: "lastName",
            label: "Фамилия",
            placeholder: "Иванов",
            type: "text",
          },
        ]

  return (
    <div className="grid gap-3">
      <div className="text-center grid gap-2">
        <h1 className="font-bold text-xl">Создайте аккаунт</h1>
        <p className="text-sm text-muted-foreground">
          Начните с BitShop и открывайте новые возможности шопинга.
        </p>
      </div>

      <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6">
          {currentFields.map(({ name, label, placeholder, type }) => (
            <FormField
              key={name}
              control={form.control}
              name={name as keyof FormValues}
              render={({ field }) => (
                <FormItem>
                  <FormLabel>{label}</FormLabel>
                  <FormControl>
                    <Input placeholder={placeholder} type={type} {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
          ))}

          <Button type="submit" className="w-full font-semibold">
            {step === "credentials" ? "Продолжить" : "Зарегистрироваться"}
          </Button>
        </form>
      </Form>

      <div className="after:border-border relative text-center text-sm after:absolute after:inset-0 after:top-1/2 after:z-0 after:flex after:items-center after:border-t">
        <span className="bg-background text-muted-foreground relative z-10 px-2">
          Или продолжить с
        </span>
      </div>

      <Button variant="outline" className="w-full">
        Войти с Google
      </Button>
      <Button variant="outline" className="w-full">
        Войти с GitHub
      </Button>

      <div className="text-center text-sm">
        Уже существует аккаунт?{" "}
        <Link href={ROUTES.LOGIN} className="underline underline-offset-4">
          Войти
        </Link>
      </div>
    </div>
  )
}
