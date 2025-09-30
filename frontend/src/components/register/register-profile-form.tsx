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
  firstName: z.string().min(1, { message: "Имя является обязательным полем." }),
  lastName: z.string().optional(),
})

const formFields = [
  {
    name: "firstName" as const,
    label: "Имя",
    placeholder: "Иван",
  },
  {
    name: "lastName" as const,
    label: "Фамилия",
    placeholder: "Иванов",
  },
]

export function RegisterProfileForm() {
  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      firstName: "",
      lastName: "",
    },
  })

  function onSubmit(values: z.infer<typeof formSchema>) {
    console.log("Отправка формы:", values)
  }

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
        {formFields.map(({ name, label, placeholder }) => (
          <FormField
            key={name}
            control={form.control}
            name={name}
            render={({ field }) => (
              <FormItem>
                <FormLabel>{label}</FormLabel>
                <FormControl>
                  <Input placeholder={placeholder} {...field} />
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
