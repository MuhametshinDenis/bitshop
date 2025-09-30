import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form"
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import { useForm } from "react-hook-form"
import { z } from "zod"
import { zodResolver } from "@hookform/resolvers/zod"

const formSchema = z.object({
  email: z.string().email({ message: "Обязательно должно быть почтой." }),
  password: z
    .string()
    .min(2, { message: "Пароль должен состоять минимум из 2 символов." }),
})

const formFields = [
  {
    name: "email" as const,
    label: "Почта",
    placeholder: "m@example.com",
    type: "text",
  },
  {
    name: "password" as const,
    label: "Пароль",
    placeholder: "Пароль",
    type: "password",
  },
]

export function RegisterCredentialsForm() {
  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      email: "",
      password: "",
    },
  })

  function onSubmit(values: z.infer<typeof formSchema>) {
    console.log("Отправка формы:", values)
  }

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
        {formFields.map(({ name, label, placeholder, type }) => (
          <FormField
            key={name}
            control={form.control}
            name={name}
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
          Продолжить
        </Button>
      </form>
    </Form>
  )
}
